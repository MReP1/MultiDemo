package little.goose.common.network

import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import little.goose.common.model.Cat
import little.goose.common.model.SpecialCatRequest

object CatSource {

    suspend fun getRandomCat(): Result<Cat> {
        return runCatching {
            httpClient.get("random-cat").body()
        }
    }

    suspend fun postSpecialCat(number: Int): Result<Cat> {
        return runCatching {
            httpClient.post {
                url("special-cat")
                setBody(SpecialCatRequest(number = number))
                contentType(ContentType.Application.Json)
            }.body()
        }
    }

}