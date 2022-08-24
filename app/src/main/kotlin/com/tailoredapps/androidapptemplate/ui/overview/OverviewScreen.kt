package com.tailoredapps.androidapptemplate.ui.overview

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import coil.compose.rememberAsyncImagePainter
import org.koin.androidx.compose.getViewModel

@Composable
fun OverviewScreen(
    viewModel: OverviewViewModel = getViewModel(),
) {
    val viewModelState by viewModel.state.collectAsState()

    Image(
        painter = rememberAsyncImagePainter(viewModelState.logoUrl),
        contentDescription = null,
    )
}

