package com.bifos.fospay.utility

import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.util.*

class MoneyDataSimulator {
    companion object {
        const val DECREASE_API_ENDPOINT = "http://localhost:18083/money/decrease-eda"
        const val INCREASE_API_ENDPOINT = "http://localhost:18083/money/increase-eda"
        const val CREATE_MONEY_API_ENDPOINT = "http://localhost:18083/money/create-member-money"
        const val REGISTER_ACCOUNT_API_ENDPOINT = "http://localhost:18082/banking/account"
        val BANK_NAMES = arrayOf("KBB", "신한한", "우리리")

        fun registerAccountSimulator(apiEndPoint: String, targetMembershipId: Int) {
            try {
                val url = URL(apiEndPoint)
                val conn = url.openConnection() as HttpURLConnection
                conn.requestMethod = "POST"
                conn.setRequestProperty("Content-Type", "appliction/json")
                conn.doOutput = true

                val random = Random()

                val jsonRequestBody = JSONObject()
                jsonRequestBody.put("bankAccountNumber", generateRandomAccountNumber())
                jsonRequestBody.put("bankName", BANK_NAMES[random.nextInt(BANK_NAMES.size)])
                jsonRequestBody.put("membershipId", targetMembershipId)
                jsonRequestBody.put("valid", true)

                call(apiEndPoint, conn, jsonRequestBody)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        fun createMemberMoneySimulator(apiEndPoint: String, targetMembershipId: Int) {
            try {
                val url = URL(apiEndPoint)
                val conn = url.openConnection() as HttpURLConnection
                conn.requestMethod = "POST"
                conn.setRequestProperty("Content-Type", "appliction/json")
                conn.doOutput = true

                val jsonRequestBody = JSONObject()
                jsonRequestBody.put("membershipId", targetMembershipId)

                call(apiEndPoint, conn, jsonRequestBody)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        fun increaseMemberMoneySimulator(apiEndPoint: String, amount: Int, targetMembershipId: Int) {
            try {
                val url = URL(apiEndPoint)
                val conn = url.openConnection() as HttpURLConnection
                conn.requestMethod = "POST"
                conn.setRequestProperty("Content-Type", "appliction/json")
                conn.doOutput = true

                val jsonRequestBody = JSONObject()
                jsonRequestBody.put("amount", amount)
                jsonRequestBody.put("targetMembershipId", targetMembershipId)

                call(apiEndPoint, conn, jsonRequestBody)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        private fun generateRandomAccountNumber(): String {
            val sb = StringBuilder()
            val random = Random()

            for (i in 0..9) {
                val digit = random.nextInt(10)
                sb.append(digit)
            }

            return sb.toString()
        }

        private fun call(apiEndPoint: String, conn: HttpURLConnection, jsonRequestBody: JSONObject) {
            val outputStream = conn.outputStream
            outputStream.write(jsonRequestBody.toString().toByteArray())
            outputStream.flush()

            val reader = BufferedReader(InputStreamReader(conn.inputStream))
            val response = StringBuilder()

            while (true) {
                val line = reader.readLine() ?: break
                response.append(line)
            }
            reader.close()

            println("API Response from $apiEndPoint : $response")
        }
    }
}

fun main(args: Array<String>) {
    val random = Random()
    val readyMemberList = mutableListOf<Int>()

    while (true) {
        var amount = random.nextInt(20001) + 1// 1 ~ 20000
        val targetMembershipId = random.nextInt(1001) + 1 // 1 ~ 1000

        MoneyDataSimulator.registerAccountSimulator(
            MoneyDataSimulator.REGISTER_ACCOUNT_API_ENDPOINT,
            targetMembershipId
        )
        MoneyDataSimulator.createMemberMoneySimulator(MoneyDataSimulator.CREATE_MONEY_API_ENDPOINT, targetMembershipId)

        Thread.sleep(1000)
        readyMemberList.add(targetMembershipId)

        // 증액
        MoneyDataSimulator.increaseMemberMoneySimulator(
            MoneyDataSimulator.INCREASE_API_ENDPOINT,
            amount,
            targetMembershipId
        )

        amount = random.nextInt(20001) + 1
        val decreaseTargetMembershipId = readyMemberList[random.nextInt(readyMemberList.size)]
        MoneyDataSimulator.increaseMemberMoneySimulator(
            MoneyDataSimulator.DECREASE_API_ENDPOINT,
            amount,
            decreaseTargetMembershipId
        )

        Thread.sleep(1000)
    }
}