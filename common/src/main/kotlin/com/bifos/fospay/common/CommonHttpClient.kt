package com.bifos.fospay.common

import org.springframework.stereotype.Component
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse
import java.util.concurrent.CompletableFuture

@Component
class CommonHttpClient {

    private final val httpClient: HttpClient = HttpClient.newBuilder().build()

    fun sendGetRequest(url: String): HttpResponse<String> {
        val request = HttpRequest.newBuilder()
            .uri(URI.create(url))
            .GET()
            .build()

        return httpClient.send(request, HttpResponse.BodyHandlers.ofString())
    }

    fun sendPostRequest(url: String, body: String): CompletableFuture<HttpResponse<String>> {
        val request = HttpRequest.newBuilder()
            .uri(URI.create(url))
            .POST(HttpRequest.BodyPublishers.ofString(body))
            .build()

        return httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString())
    }
}