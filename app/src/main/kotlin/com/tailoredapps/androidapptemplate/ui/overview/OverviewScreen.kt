package com.tailoredapps.androidapptemplate.ui.overview

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import coil.compose.rememberImagePainter
import com.tailoredapps.androidapptemplate.navigation.Navigator
import org.koin.androidx.compose.get
import org.koin.androidx.compose.getViewModel

@Composable
fun OverviewScreen(
    navigator: Navigator = get(),
    viewModel: OverviewViewModel = getViewModel(),
) {
    val viewModelState by viewModel.state.collectAsState()

    Image(
        painter = rememberImagePainter(viewModelState.logoUrl),
        contentDescription = null,
    )
}

