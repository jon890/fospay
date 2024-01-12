package com.bifos.fospay.common

data class SubTask(
    val membershipId : Long,
    val subTaskName: String,
    val taskType: String, // banking, membership
    var status : String // success, fail, ready
){
}