package com.tailoredapps.androidapptemplate.ui.toolbar

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.tailoredapps.androidapptemplate.R
import com.tailoredapps.androidapptemplate.ui.theme.AppTheme

@Composable
fun Toolbar(
    title: String = stringResource(id = R.string.app_name),
) {
    TopAppBar(
        title = { Text(text = title) },
        backgroundColor = MaterialTheme.colors.background
    )
}

@Preview(
    name = "Toolbar"
)
@Composable
fun ToolbarPreview() {
    AppTheme {
        Toolbar()
    }
}


@Preview(
    name = "Toolbar Night",
    uiMode = UI_MODE_NIGHT_YES
)
@Composable
fun ToolbarPreviewNight() {
    AppTheme {
        Toolbar()
    }
}