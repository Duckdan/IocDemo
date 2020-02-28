package com.piaopiao.singleinstancekotlin

class Utils private constructor() {
    companion object {
        fun getInstance(): Utils {
            return Instance.instance
        }
    }

    private object Instance {
        val instance = Utils()
    }
}