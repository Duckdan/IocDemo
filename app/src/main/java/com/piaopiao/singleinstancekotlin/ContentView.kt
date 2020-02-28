package com.piaopiao.singleinstancekotlin

import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy

@Retention(RetentionPolicy.RUNTIME)
@Target(AnnotationTarget.CLASS)
annotation class ContentView(val value: Int = -1)
