package com.example.lemonade
import android.annotation.SuppressLint
import androidx.compose.material.*
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

import androidx.navigation.compose.rememberNavController
import com.example.lemonade.appBar.BottomBar
import com.example.lemonade.appBar.BottomBarScreens
import com.example.lemonade.ui.theme.LemonadeTheme
import com.example.lemonade.utils.BottomNavGraph
import com.example.lemonade.utils.NavGraph
import com.example.lemonade.utils.SharedState
import com.example.lemonade.utils.AppScreens
import com.example.lemonade.utils.navDrawer.NavDrawer


class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContent {

            LemonadeTheme {

                val navController = rememberNavController()
                val context = LocalContext.current
                val sharedState = SharedState()
                val isFirstTime by remember { mutableStateOf(sharedState.isFirstTime(context)) }
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.LightGray)
                )
                {

                }

                if (isFirstTime) {
                    sharedState.setFirstTime(context, false)
                    NavGraph(navController,BottomBarScreens.How.rout)
                } else {

                NavGraph(navController,AppScreens.Login.rout)
                }
            }
        }
    }
}
@Composable
fun Main(navController: NavHostController= rememberNavController()) {

    Scaffold(topBar = {
        TopAppBar(
            title = {
                Text(
                    "Lemonade App",
                    textAlign = TextAlign.Center,
                    color = Color.White,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        )
    },
        bottomBar = {
            BottomBar(navController)
        })

    { innerPadding ->

        BottomNavGraph(navController,
            modifier = Modifier
                .padding(innerPadding))
    }
}




