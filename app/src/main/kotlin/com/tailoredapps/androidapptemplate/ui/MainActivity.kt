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
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.tailoredapps.androidapptemplate.ui.overview.OverviewScreen
import com.tailoredapps.androidapptemplate.navigation.Navigator
import com.tailoredapps.androidapptemplate.navigation.Screen
import com.tailoredapps.androidapptemplate.ui.theme.AppTheme
import com.tailoredapps.androidapptemplate.ui.toolbar.Toolbar
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.compose.get


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { MainView() }
    }
}

@Composable
fun MainView(navigator: Navigator = get()) {
    val context = LocalContext.current

    AppTheme {
        val scaffoldState = rememberScaffoldState()
        val navController = rememberNavController()

        LaunchedEffect(Unit) {
            navigator.observe()
                .onEach {
                    navController.it(context)
                }
                .launchIn(this)
        }

        Scaffold(
            scaffoldState = scaffoldState,
            topBar = { Toolbar() }
        ) {
            NavHost(navController = navController,
                startDestination = Screen.Overview.route) {
                composable(Screen.Overview.route) { OverviewScreen() }
            }
        }
    }
}

@Preview(name = "MainView")
@Composable
fun MainViewPreview() {
    MainView(Navigator())
}

@Preview(name = "MainView Night", uiMode = UI_MODE_NIGHT_YES)
@Composable
fun MainViewPreviewNight() {
    MainView(Navigator())
}
