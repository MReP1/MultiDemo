package little.goose.common

import io.ktor.client.*
import io.ktor.client.engine.okhttp.*

actual fun getPlatformName(): String {
    return "Android"
}

actual fun httpClient(config: HttpClientConfig<*>.() -> Unit) = HttpClient(OkHttp) {
    config(this)
}