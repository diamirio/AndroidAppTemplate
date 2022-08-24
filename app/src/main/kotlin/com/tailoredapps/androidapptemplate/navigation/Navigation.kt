package com.tailoredapps.androidapptemplate.navigation

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import com.tailoredapps.androidapptemplate.R
import com.tailoredapps.androidapptemplate.ui.overview.OverviewScreen

sealed class Screen(
    @StringRes val titleRes: Int,
    val route: String,
) {
    @Composable
    abstract fun View(navBackStackEntry: NavBackStackEntry)

    object Overview : Screen(R.string.screen_overview, "overview") {
        @Composable
        override fun View(navBackStackEntry: NavBackStackEntry) = OverviewScreen()
    }
}

enum class NestedNav(val route: String) {
    Main("nav_main"),
}