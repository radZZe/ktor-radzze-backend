package com.radzze.models

import com.radzze.db.UserTable
import com.radzze.db.UserTable.autoIncrement
import com.radzze.db.UserTable.clientDefault

import org.jetbrains.exposed.sql.javatime.datetime
import java.time.LocalDateTime


data class User(
    var id :String,
    var name:String,
    var surname :String,
    var email :String,
    var password :String,
    var createdAt :String,
    var authToken:String? = null
)
