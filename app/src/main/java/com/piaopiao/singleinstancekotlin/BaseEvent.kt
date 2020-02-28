package com.piaopiao.singleinstancekotlin

import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import kotlin.reflect.KClass

/**
 * 基础事件：便于以后扩展
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(AnnotationTarget.ANNOTATION_CLASS)
annotation class BaseEvent(//事件名称
    val setEventName: String = "", //事件类型名称
    val setEventType: KClass<*>, //事件回调方法名称
    val setEventCallbackName: String = ""
)
