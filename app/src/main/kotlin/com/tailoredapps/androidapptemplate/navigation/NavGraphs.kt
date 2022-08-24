package com.tailoredapps.androidapptemplate.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation

/**
 * App's Nav Host
 */
@Composable
fun NavHostController.AppNavHost() {
    NavHost(navController = this, startDestination = NestedNav.Main.route) {
        mainGraph()
    }
}

/**
 * Main Graph
 */
fun NavGraphBuilder.mainGraph() {
    navigation(startDestination = Screen.Overview.route, route = NestedNav.Main.route) {
        composableOf(Screen.Overview)
    }
}

private fun NavGraphBuilder.composableOf(screen: Screen) {
    composable(screen.route) { screen.View(it) }
}