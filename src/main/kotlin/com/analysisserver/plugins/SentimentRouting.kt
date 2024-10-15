package com.analysisserver.plugins

//client for executing Sentiment analysis

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.sql.*
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.ticker
import org.jetbrains.exposed.sql.*
import com.analysisserver.plugins.sentimentAnalysis

fun Application.sentimentCalculator() {


    routing {
        // Get sentiment score for a given string

        get("/sentiment/{stringQuery}") {
            val stringQuery = call.parameters["stringQuery"]
            if (stringQuery == null) {
                call.respondText("String Query parameter required", status = HttpStatusCode.BadRequest)
                return@get
            }

            // call the suspend function within a coroutine context for calculating sentiment
            
            val sentimentScore = withContext(Dispatchers.IO) {
                sentimentAnalysis(stringQuery)
            }

            call.respond(HttpStatusCode.OK, sentimentScore)
        }

    }
}