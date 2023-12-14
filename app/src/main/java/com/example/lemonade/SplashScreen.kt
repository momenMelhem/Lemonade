package com.example.lemonade

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.lemonade.R
import com.example.lemonade.utils.AppScreens
import kotlinx.coroutines.delay


@Composable
fun SplashScreen(navController:NavHostController){
        val composition by rememberLottieComposition(
        spec = LottieCompositionSpec.RawRes(R.raw.anim_9)
    )
    val preloaderProgress by animateLottieCompositionAsState(
        composition,
        iterations = LottieConstants.IterateForever,
        isPlaying = true
    )
    LaunchedEffect(key1 = true){
        delay(1200)
        navController.navigate(AppScreens.Login.rout)
    }
    Box(Modifier.fillMaxSize()) {

        LottieAnimation(composition = composition,

            modifier = Modifier
                .size(400.dp)
                .align(Alignment.Center))
    }
}