package com.piaopiao.singleinstancekotlin

import android.view.View
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy

/**
 * 单击事件注解
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(AnnotationTarget.FUNCTION)
@BaseEvent(
    setEventName = "setOnClickListener",
    setEventType = View.OnClickListener::class,
    setEventCallbackName = "onClick"
)
annotation class OnClick(vararg val value: Int = [-1])
