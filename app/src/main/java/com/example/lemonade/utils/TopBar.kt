package com.example.lemonade.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.lemonade.R

@Composable
fun TopBar(navController:NavHostController){
     TopAppBar(title = {
         Text(text = stringResource(R.string.app_name))
                       },
        actions = {

                CartIcon (navController)

        },
        backgroundColor = Color.White,
        elevation = 4.dp,
        modifier = Modifier
            .shadow(4.dp, RoundedCornerShape(bottomEndPercent = 48, bottomStartPercent = 48))
            .clip(
                shape = RoundedCornerShape(bottomEndPercent = 48, bottomStartPercent = 48)

            )
    )

}

@Composable
fun CartIcon(navController: NavHostController) {
    IconButton(onClick = { navController.navigate("Cart/"+0) }) {
        Icon(
            painter = painterResource(R.drawable.baseline_shopping_cart_24),
            contentDescription = "cart",
            tint = Color.Black,
            modifier = Modifier
                .padding(start = 12.dp, bottom = 2.dp, top = 2.dp)
                .size(30.dp)
        )
    }
}
