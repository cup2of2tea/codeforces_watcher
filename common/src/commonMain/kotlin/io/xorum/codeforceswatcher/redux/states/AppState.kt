package io.xorum.codeforceswatcher.redux.states

import io.xorum.codeforceswatcher.features.news.redux.states.NewsState
import io.xorum.codeforceswatcher.features.problems.redux.states.ProblemsState
import io.xorum.codeforceswatcher.features.contests.redux.states.ContestsState
import io.xorum.codeforceswatcher.features.users.redux.states.UsersState
import tw.geothings.rekotlin.StateType

data class AppState(
        val contests: ContestsState = ContestsState(),
        val users: UsersState = UsersState(),
        val news: NewsState = NewsState(),
        val problems: ProblemsState = ProblemsState()
) : StateType
