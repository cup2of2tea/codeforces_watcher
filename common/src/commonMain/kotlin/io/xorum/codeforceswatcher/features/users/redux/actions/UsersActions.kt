package io.xorum.codeforceswatcher.features.users.redux.actions

import io.xorum.codeforceswatcher.features.users.redux.states.UsersState
import tw.geothings.rekotlin.Action

class UsersActions {

    data class Sort(val sortType: UsersState.SortType) : Action

    class ClearAddUserState : Action
}
