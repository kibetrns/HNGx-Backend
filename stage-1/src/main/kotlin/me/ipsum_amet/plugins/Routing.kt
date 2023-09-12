package me.ipsum_amet.plugins

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.Serializable
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.*

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

                val dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")
                val dateTime = LocalDateTime.now()
                val formattedUTCTime = dateTime.format(dateTimeFormatter)



                val dayOfWeek = dateTime.dayOfWeek
                val dayOfWeekText = dayOfWeek.getDisplayName(TextStyle.FULL, Locale.ENGLISH)


                val response = Stage1Response(
                    slack_name = slackName,
                    current_day = dayOfWeekText,
                    track = track,
                    utc_time = formattedUTCTime,
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