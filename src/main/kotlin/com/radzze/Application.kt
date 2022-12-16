package com.radzze

import com.radzze.db.DatabaseFactory
import io.ktor.server.application.*
import com.radzze.plugins.*

fun main(args: Array<String>): Unit =
    io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // application.conf references the main function. This annotation prevents the IDE from marking it as unused.
fun Application.module() {
    DatabaseFactory.init()
    configureMonitoring()
    configureSockets()
    configureSerialization()
    configureSecurity()
    configureRouting()
}
