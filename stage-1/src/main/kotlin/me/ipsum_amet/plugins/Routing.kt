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
                    slack_name = slackName,
                    current_day = currentDayOfWeek.toString(),
                    track = track,
                    utc_time = utcTime,
                    github_file_url = "https://github.com/kibetrns/HNGx-Backend/blob/main/stage-1/src/main/kotlin/me/ipsum_amet/Application.kt",
                    github_repo_url = "https://github.com/kibetrns/HNGx-Backend/tree/main/stage-1",
                    status_code = HttpStatusCode.OK.value
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
    val slack_name: String?,
    val current_day: String?,
    val utc_time: String?,
    val track: String?,
    val github_file_url: String?,
    val github_repo_url: String?,
    val status_code: Int
)