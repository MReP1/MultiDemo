package little.goose.common.network

import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.cookies.*
import io.ktor.client.plugins.logging.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import little.goose.common.httpClient

val httpClient = httpClient {
    install(DefaultRequest) {
         url {
             protocol = URLProtocol.HTTP
             host = "192.168.31.229"
             port = 8088
         }
    }
    install(Logging) {
        level = LogLevel.ALL
    }
    install(HttpCookies) {
        storage = AcceptAllCookiesStorage()
    }
    install(ContentNegotiation) {
        json()
    }
}