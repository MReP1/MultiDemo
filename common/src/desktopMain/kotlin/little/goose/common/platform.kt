package little.goose.common

import io.ktor.client.*
import io.ktor.client.engine.cio.*

actual fun getPlatformName(): String {
    return "Desktop"
}

actual fun httpClient(config: HttpClientConfig<*>.() -> Unit) = HttpClient(CIO) {
    config(this)
}