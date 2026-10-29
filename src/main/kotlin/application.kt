package com.tactical.ptt

import com.tactical.ptt.plugins.configureMonitoring
import com.tactical.ptt.plugins.configureRouting
import com.tactical.ptt.plugins.configureSerialization
import com.tactical.ptt.plugins.configureSockets
import com.tactical.ptt.session.PttRoomManager
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main() {
    // Port dinamis dari environment variable untuk cloud deployment (Render.com / Cloud Run / Railway)
    val port = System.getenv("PORT")?.toInt() ?: 8080

    embeddedServer(
        factory = Netty,
        port = port,
        host = "0.0.0.0",
        module = Application::module
    ).start(wait = true)
}

fun Application.module() {
    val roomManager = PttRoomManager()

    configureSerialization()
    configureMonitoring()
    configureSockets(roomManager)
    configureRouting(roomManager)
}
