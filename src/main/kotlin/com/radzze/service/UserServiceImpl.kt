package com.radzze.service

import com.radzze.db.DatabaseFactory.databaseQuery
import com.radzze.db.UserTable
import com.radzze.models.User
import com.radzze.security.hash
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.statements.InsertStatement
import java.time.LocalDateTime

class UserServiceImpl : UserService {
    override suspend fun registerUser(params: CreateUserParams): User? {
        var statement:InsertStatement<Number>? = null
        databaseQuery{
            statement = UserTable.insert{
                it[email] = params.email
                it[name] = params.name
                it[surname] = params.surname
                it[password] = hash(params.password)
                it[createdAt] = LocalDateTime.now()
            }
        }
        return rowToUser(statement?.resultedValues?.get(0))
    }

    override suspend fun findUserByEmail(email: String): User? {
        val user = databaseQuery {
            UserTable.select{UserTable.email.eq(email)}
                .map{rowToUser(it)}.singleOrNull()
        }
        return user
    }

    private fun rowToUser(row: ResultRow?):User?{
        return if(row == null) null
        else User(
            id =row[UserTable.id].toString(),
            name = row[UserTable.name],
            surname = row[UserTable.surname],
            email = row[UserTable.email],
            password = row[UserTable.password],
            createdAt = row[UserTable.createdAt].toString(),



        )
    }
}