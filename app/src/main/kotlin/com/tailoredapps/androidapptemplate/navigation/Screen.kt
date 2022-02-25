package com.tailoredapps.androidapptemplate.navigation

import androidx.annotation.StringRes
import com.tailoredapps.androidapptemplate.R

sealed class Screen(@StringRes val titleRes: Int, val route: String) {
    object Overview : Screen(R.string.screen_overview, "overview")
}