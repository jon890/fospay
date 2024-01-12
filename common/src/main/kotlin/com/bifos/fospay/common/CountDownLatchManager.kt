package com.bifos.fospay.common

import org.springframework.stereotype.Component
import java.util.concurrent.CountDownLatch

@Component
class CountDownLatchManager {

    private val countDownLatchMap = mutableMapOf<String, CountDownLatch>()

    private val stringMap = mutableMapOf<String, String>()

    fun addCountDownLatch(key: String) {
        countDownLatchMap[key] = CountDownLatch(1)
    }

    fun setDataForKey(key: String, data: String) {
        stringMap[key] = data
    }

    fun getDataForKey(key: String): String? {
        return stringMap[key]
    }

    fun getCountDownLatch(key: String): CountDownLatch? {
        return countDownLatchMap[key]
    }
}