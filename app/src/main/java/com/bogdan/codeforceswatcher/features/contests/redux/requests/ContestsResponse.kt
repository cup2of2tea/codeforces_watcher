package com.bogdan.codeforceswatcher.features.contests.redux.requests

import com.bogdan.codeforceswatcher.model.Contest
import com.google.gson.annotations.SerializedName

data class ContestsResponse(
    val status: String,
    @SerializedName("result") val contests: List<Contest>
)