package com.geekydroid.chnewsapp.ui.home.composables

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp

@Composable
fun ShimmerAnimationCard(
    modifier: Modifier = Modifier
) {

    val screenConfiguration = LocalConfiguration.current
    val screenWidth = screenConfiguration.screenWidthDp.dp
    val animationColor = Color.LightGray

    val gradientColorsList = listOf(
        animationColor.copy(alpha = 0.5f),
        animationColor.copy(alpha = 1f),
        animationColor.copy(alpha = 0.5f)
    )

    val infiniteTransition = rememberInfiniteTransition(label = "shimmer infinite transition")
    val animateFloatTransition by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = screenWidth.value * 2,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1000,
                easing = LinearEasing
            )
        ), label = "shimmer animation"
    )
    val yTransition = animateFloatTransition + 100f

    Box(
        modifier = modifier
            .background(
                brush = Brush.linearGradient(
                    colors = gradientColorsList,
                    start = Offset(animateFloatTransition, animateFloatTransition),
                    end = Offset(yTransition, yTransition)
                ),
                shape = RoundedCornerShape(8.dp)
            ),
    )
}
