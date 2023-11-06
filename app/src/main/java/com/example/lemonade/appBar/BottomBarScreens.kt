package com.example.lemonade.appBar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.ui.graphics.vector.ImageVector
sealed class BottomBarScreens(
   val rout:String,
   val title:String,
   val icon:ImageVector
){
        object Home : BottomBarScreens(
            rout = "home",
            title="Home",
            icon = Icons.Default.Home
        )
        object Slide : BottomBarScreens(
        rout = "slide",
        title="Slide",
        icon = Icons.Default.PlayArrow
    )
    object How : BottomBarScreens(
        rout = "how",
        title="How",
        icon = Icons.Default.PlayArrow
    )

}
