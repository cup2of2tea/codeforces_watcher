package io.xorum.codeforceswatcher.features.news.redux.requests

import io.xorum.codeforceswatcher.network.responses.News
import io.xorum.codeforceswatcher.redux.*
import io.xorum.codeforceswatcher.util.RefreshScreen
import io.xorum.codeforceswatcher.util.defineLang
import io.xorum.codeforceswatcher.util.settings
import tw.geothings.rekotlin.Action

class NewsRequests {

    class FetchNews(
            private val isInitializedByUser: Boolean,
            private val language: String
    ) : Request() {

        override suspend fun execute() {
            analyticsController.logFetchNews()

            val response = backendApiClient.getNews(lang = language.defineLang())
            response?.news?.let { news ->
                analyticsController.logFetchNewsSuccess()
                store.dispatch((Success(news)))
            } ?: dispatchFailure()
        }

        private fun dispatchFailure() {
            val noConnectionError = if (isInitializedByUser) Message.NoConnection else Message.None
            store.dispatch(Failure(noConnectionError))
        }

        data class Success(val news: List<News>) : Action

        data class Failure(override val message: Message) : ToastAction
    }

    class RemovePinnedPost(val link: String) : Request() {

        override suspend fun execute() {
            analyticsController.logPinnedPostClosed()
            settings.writeLastPinnedPostLink(link)
        }
    }
}
