package com.example.lemonade

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.lemonade.ui.theme.LemonadeTheme
import com.example.lemonade.utils.AppScreens


@Composable
fun HowToUseView(navController:NavHostController) {
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray)
    ){
        Text(
            text = stringResource(R.string.welcome_first_time),
            fontSize = 25.sp,
            modifier = Modifier.padding(14.dp),
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        Text(
            text = stringResource(R.string.how_to_use),
            fontSize = 25.sp,
            modifier = Modifier.padding(10.dp),
            fontWeight = FontWeight.Bold

        )
        Text(
            text = stringResource(R.string.first_section),
            fontSize = 22.sp,
            modifier = Modifier.padding(10.dp)
        )
        Text(
            text= stringResource(R.string.second_section),
            fontSize = 22.sp,
            modifier = Modifier.padding(10.dp)
            )

        Button(
            modifier = Modifier.padding(4.dp),
            onClick = {

               navController.navigate(AppScreens.Login.rout)

            })
        {

            Text(
                text = stringResource(R.string.btn_next),
                fontSize = 20.sp)
        }

}
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LemonadeTheme {

    }
}