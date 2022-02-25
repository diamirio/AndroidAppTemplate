package com.tailoredapps.androidapptemplate.ui.theme

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

class Dimensions(
    /**
     * default: 4.dp
     */
    val elevationSmall: Dp = 4.dp,
    /**
     * default: 6.dp
     */
    val elevationNormal: Dp = 6.dp,
    /**
     * default: 8.dp
     */
    val elevationLarge: Dp = 8.dp,
    /**
     * default: 2.dp
     */
    val paddingExtraSmall: Dp = 2.dp,
    /**
     * default: 4.dp
     */
    val paddingSmall: Dp = 4.dp,
    /**
     * default: 6.dp
     */
    val paddingMedium: Dp = 6.dp,
    /**
     * default: 8.dp
     */
    val paddingLarge: Dp = 8.dp,
    /**
     * default: 10.dp
     */
    val paddingExtraLarge: Dp = 16.dp,
    /**
     * default: 70.dp
     */
    val paddingFab: Dp = 80.dp
)

val smallDimensions = Dimensions()

val sw360Dimensions = Dimensions()