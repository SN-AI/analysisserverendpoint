//write the sentiment analysis function that accepts a string and returns the sentiment score
package com.analysisserver.plugins

import com.azure.ai.textanalytics.TextAnalyticsClientBuilder
import com.azure.ai.textanalytics.TextAnalyticsClient
import com.azure.ai.textanalytics.models.TextSentiment
import com.azure.ai.textanalytics.models.DocumentSentiment
import com.azure.core.credential.AzureKeyCredential
import java.net.URI
import java.util.Properties

suspend fun sentimentAnalysis(text: String): String {

    // use Azure Language Understanding API to get sentiment score
    val endpoint = "https://sentiment-algo-string.cognitiveservices.azure.com/"
    val key = "b851ce0e48c44315b1455ed920d60a7e" //valid until October 20 2024

    val textAnalyticsClient: TextAnalyticsClient = TextAnalyticsClientBuilder()
        .credential(AzureKeyCredential(key))
        .endpoint(endpoint)
        .buildClient()
    
    val documentSentiment: DocumentSentiment = textAnalyticsClient.analyzeSentiment(text);
    val sentiment = documentSentiment.getSentiment().toString()
       
    return sentiment
}