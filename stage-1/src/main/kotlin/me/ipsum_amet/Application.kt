package me.ipsum_amet

import io.ktor.server.application.*
import me.ipsum_amet.plugins.*

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    configureSerialization()
    configureMonitoring()
    configureRouting()
}
