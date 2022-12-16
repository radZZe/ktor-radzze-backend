package com.radzze.db

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.datetime
import java.time.LocalDate
import java.time.LocalDateTime

object UserTable:Table("users") {
    val id = integer("id").autoIncrement()
    val name = varchar("name",256)
    val surname = varchar("surname",256)
    val email = varchar("email",256)
    val password = text("password")
    val createdAt = datetime("created_at").clientDefault { LocalDateTime.now() }
    override val primaryKey = PrimaryKey(id)
}