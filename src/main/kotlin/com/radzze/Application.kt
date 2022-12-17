package com.radzze

import com.radzze.db.DatabaseFactory
import io.ktor.server.application.*
import com.radzze.plugins.*
import com.radzze.repository.UserRepositoryImpl
import com.radzze.routes.authRoutes
import com.radzze.service.UserServiceImpl
import io.ktor.serialization.jackson.*
import io.ktor.server.plugins.contentnegotiation.*
import kotlinx.serialization.serializer
import javax.swing.text.AbstractDocument.Content

fun main(args: Array<String>): Unit =
    io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // application.conf references the main function. This annotation prevents the IDE from marking it as unused.
fun Application.module() {
    DatabaseFactory.init()
    install(ContentNegotiation) {
        jackson()
    }
    val service = UserServiceImpl()
    val repository = UserRepositoryImpl(service)
    configureMonitoring()
    configureSockets()
    authRoutes(repository)
    configureSecurity()
    configureRouting()
}
