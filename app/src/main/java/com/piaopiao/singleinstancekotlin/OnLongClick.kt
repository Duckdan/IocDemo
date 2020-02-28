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
    setEventName = "setOnLongClickListener",
    setEventType = View.OnLongClickListener::class,
    setEventCallbackName = "onLongClick"
)
annotation class OnLongClick(vararg val value: Int = [-1])
