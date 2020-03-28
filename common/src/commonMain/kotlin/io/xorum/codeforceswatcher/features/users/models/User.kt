package io.xorum.codeforceswatcher.features.users.models

import io.xorum.codeforceswatcher.DbUser
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import kotlinx.serialization.list

@Serializable
data class User(
        var id: Long = 0,
        val avatar: String,
        val rank: String? = null,
        val handle: String,
        val rating: Int? = null,
        val maxRating: Int? = null,
        val firstName: String? = null,
        val lastName: String? = null,
        var ratingChanges: List<RatingChange> = listOf()
) {
    companion object {

        fun fromDB(dbUser: DbUser): User {
            val serializer = Json(JsonConfiguration.Stable.copy(strictMode = false))
            return User(
                    id = dbUser.id,
                    avatar = dbUser.avatar,
                    rank = dbUser.rank,
                    handle = dbUser.handle,
                    rating = dbUser.rating?.toInt(),
                    maxRating = dbUser.maxRating?.toInt(),
                    firstName = dbUser.firstName,
                    lastName = dbUser.lastName,
                    ratingChanges = serializer.parse(RatingChange.serializer().list, dbUser.ratingChanges)
            )
        }
    }
}
