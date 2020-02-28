package com.piaopiao.singleinstancekotlin

import android.content.Context
import java.lang.reflect.Proxy

object InjectUtils {

    fun inject(context: Context) {
        injectContentView(context)
        injectView(context)
        injectViewEvent(context)
    }

    /**
     * ioc编译文件
     *
     * @param context
     */
    fun injectContentView(context: Context) {
        val clazz = context.javaClass
        //获取当前Activity的字节码文件
        val contentView = clazz.getAnnotation(ContentView::class.java)
        //说明上面存在注解ContentView
        contentView?.apply {
            val setContentViewMethod = clazz.getMethod("setContentView", Int::class.java)
            //执行当前Activity的setContentView方法
            setContentViewMethod.invoke(context, value)
        }

    }

    /**
     * 注册View组件
     *
     * @param context
     */
    private fun injectView(context: Context) {
        val clazz = context.javaClass
        //获取当前Activity的所有字段,用getDeclaredFields不管什么修饰符都可以拿到
        clazz.declaredFields.takeIf {
            it.isNotEmpty()
        }?.forEach {
            //设置访问权限
            it.isAccessible = true
            it.getAnnotation(InjectView::class.java)?.takeIf { it ->
                it.value > -1
            }?.apply {
                val findViewByIdMethod = clazz.getMethod("findViewById", Int::class.java)
                //执行findViewById方法获取到View
                val view = findViewByIdMethod.invoke(context, value)
                //把找到的View交给当前字段field
                it.set(context, view)
            }
        }
    }

    /**
     * 处理点击事件
     *
     *
     * btn1.setOnLongClickListener(new View.OnLongClickListener() {
     *
     * @param context
     * @Override public boolean onLongClick(View v) {
     * return false;
     * }
     * });
     *
     *
     * btn1.setOnClickListener(new View.OnClickListener() {
     * @Override public void onClick(View v) {
     *
     *
     * }
     * });
     */
    private fun injectViewEvent(context: Context) {
        val clazz = context.javaClass
        //获取当前Activity字节码的所有方法
        clazz.declaredMethods.takeIf {
            it.isNotEmpty()
        }?.forEach { currentMethod ->
            currentMethod.isAccessible = true
            //获取该方法上面的所有注解
            currentMethod.declaredAnnotations.takeIf {
                it.isNotEmpty()
            }?.forEach {
                //获取该注解的类型
                val annotationClass = it.annotationClass.java
                annotationClass.getAnnotation(BaseEvent::class.java)?.apply {
                    val declaredMethod = annotationClass.getDeclaredMethod("value")
                    declaredMethod.isAccessible = true
                    //拿到ViewId
                    (declaredMethod.invoke(it) as IntArray).takeIf {
                        it.isNotEmpty()
                    }?.forEach {
                        val findViewByIdMethod = clazz.getMethod("findViewById", Int::class.java)
                        //找到id对应控件
                        findViewByIdMethod.invoke(context, it)?.apply {
                            //获取控件想要设置的办法
                            val viewEventMethod =
                                this::class.java.getMethod(setEventName, setEventType.java)
                            //获取代理对象
                            val proxyClickListener = Proxy.newProxyInstance(
                                setEventType.java.classLoader,
                                arrayOf(setEventType.java)
                            ) { proxy, method, args ->
                                //在数组对象前加*号可以将数组展开方便传值
                                currentMethod.invoke(context, *args)
                            }
                            //将代理对象作为参数传递给view的相应事件
                            viewEventMethod.invoke(this, proxyClickListener)
                        }
                    }
                }
            }
        }
    }


}
