package me.ipsum_amet.plugins

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlinx.serialization.Serializable

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondText("Add this `/api?slack_name=<Name>&track=<TrackName>` to the base URL to get what you came for :).")
        }
        get("/api") {

            try {
                val queryParameters = call.request.queryParameters

                val slackName = queryParameters["slack_name"]

                val track = queryParameters["track"]

                val currentDateTime = Clock.System.now()

                val currentDayOfWeek = currentDateTime.toLocalDateTime(TimeZone.UTC).dayOfWeek

                val utcTime = currentDateTime.toString()


                val response = Stage1Response(
                    slackName = slackName,
                    currentDay = currentDayOfWeek.toString(),
                    track = track,
                    utcTime = utcTime,
                    githubFileUrl = "TODO",
                    githubRepoUrl = "TODO",
                    statusCode = HttpStatusCode.OK.value
                )

                call.respond(HttpStatusCode.OK, response)

            } catch (ex: Exception) {
                call.respond(ex.localizedMessage)
            }
        }
    }
}

@Serializable
data class Stage1Response(
    val slackName: String?,
    val currentDay: String?,
    val utcTime: String?,
    val track: String?,
    val githubFileUrl: String?,
    val githubRepoUrl: String?,
    val statusCode: Int
)
