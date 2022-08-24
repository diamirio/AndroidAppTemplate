package com.tailoredapps.androidapptemplate.base.provider

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.navigation.NavHostController


@Composable
fun ProvideLocalNavController(
    navHostController: NavHostController,
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(LocalNavController provides navHostController, content = content)
}

val LocalNavController = compositionLocalOf<NavHostController> {
    error("No LocalNavController provided")
}