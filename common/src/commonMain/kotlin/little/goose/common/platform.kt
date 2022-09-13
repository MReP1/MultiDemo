package little.goose.common

import io.ktor.client.*

expect fun getPlatformName(): String

expect fun httpClient(config: HttpClientConfig<*>.() -> Unit = {}): HttpClient
