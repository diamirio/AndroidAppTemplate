/*
 * Copyright 2020 Tailored Media GmbH.
 * Created by Florian Schuster.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.tailoredapps.androidapptemplate.ui

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.tailoredapps.androidapptemplate.base.provider.ProvideLocalNavController
import com.tailoredapps.androidapptemplate.base.ui.theme.AppTheme
import com.tailoredapps.androidapptemplate.navigation.AppNavHost
import com.tailoredapps.androidapptemplate.ui.toolbar.Toolbar

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { MainView() }
    }
}

@Composable
fun MainView() {
    AppTheme {
        val scaffoldState = rememberScaffoldState()
        val navController = rememberNavController()

        Scaffold(
            scaffoldState = scaffoldState,
            topBar = { Toolbar() }
        ) { contentPadding ->
            Box(
                modifier = Modifier
                    .padding(contentPadding)
                    .imePadding()
            ) {
                ProvideLocalNavController(navController) {
                    navController.AppNavHost()
                }
            }
        }
    }
}

@Preview(name = "MainView")
@Composable
fun MainViewPreview() {
    MainView()
}

@Preview(name = "MainView Night", uiMode = UI_MODE_NIGHT_YES)
@Composable
fun MainViewPreviewNight() {
    MainView()
}
