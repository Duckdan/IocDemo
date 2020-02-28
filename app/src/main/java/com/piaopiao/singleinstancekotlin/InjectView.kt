package com.piaopiao.singleinstancekotlin

import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy

@Retention(RetentionPolicy.RUNTIME)
@Target(AnnotationTarget.FIELD)
annotation class InjectView(val value: Int = -1)
