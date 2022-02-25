package com.tailoredapps.androidapptemplate.navigation

import android.content.Context
import androidx.navigation.NavController
import androidx.navigation.NavOptionsBuilder
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.filterNotNull

typealias NavigationCommand = NavController.(Context) -> Unit

class Navigator {
    private val sharedFlow = MutableSharedFlow<NavigationCommand?>(
        extraBufferCapacity = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )

    private fun emit(command: NavigationCommand) {
        sharedFlow.tryEmit(command)
    }

    fun navigate(screen: Screen) = emit { navigate(screen.route) }

    fun navigate(route: String) = emit { navigate(route) }

    fun navigate(route: String, builder: NavOptionsBuilder.() -> Unit) =
        emit { navigate(route, builder) }

    fun popBackStack() = emit { popBackStack() }

    fun observe() = sharedFlow.asSharedFlow().filterNotNull()
}

