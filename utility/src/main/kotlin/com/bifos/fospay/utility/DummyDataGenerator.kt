package com.bifos.fospay.utility

import java.sql.Connection
import java.sql.DriverManager
import java.util.*

class DummyDataGenerator {

    companion object {
        const val DB_URL = "jdbc:mysql://localhost:13306/fospay"
        const val DB_USER = "mysqluser"
        const val DB_PASSWORD = "mysqlpw"

        val ADDRESSES = arrayOf("강남구", "관악구", "서초구")

        fun generateDummyData(connection: Connection) {
            val insertQuery =
                "INSERT INTO td_membership (id, address, email, is_corp, is_valid, name) VALUES(?, ?, ?, ?, ?, ?)"
            val random = Random()

            val preparedStatement = connection.prepareStatement(insertQuery)

            val numberOfDummyData = 1000

            for (i in 1..numberOfDummyData) {
                preparedStatement.setLong(1, i.toLong())
                preparedStatement.setString(2, ADDRESSES[random.nextInt(ADDRESSES.size)])
                preparedStatement.setString(3, "email_${i}@example.com")
                preparedStatement.setBoolean(4, random.nextBoolean())
                preparedStatement.setBoolean(5, random.nextBoolean())
                preparedStatement.setString(6, "User${i}")

                preparedStatement.executeUpdate()
            }
        }
    }
}

fun main(args: Array<String>) {
    try {
        Class.forName("com.mysql.cj.jdbc.Driver")
        val connection = DriverManager.getConnection(
            DummyDataGenerator.DB_URL,
            DummyDataGenerator.DB_USER,
            DummyDataGenerator.DB_PASSWORD
        )

        DummyDataGenerator.generateDummyData(connection)

        connection.close()
    } catch (e: Exception) {
        e.printStackTrace()
    }
}