
package com.example.lemonade
import androidx.compose.material.*
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.lemonade.appBar.BottomBar
import com.example.lemonade.appBar.BottomBarScreens
import com.example.lemonade.ui.theme.LemonadeTheme
import com.example.lemonade.utils.BottomNavGraph
import com.example.lemonade.utils.NavGraph
import com.example.lemonade.utils.SharedState
import com.example.lemonade.utils.AppScreens
import com.example.lemonade.utils.TopBar

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        installSplashScreen()
        setContent {
            LemonadeTheme {
                val navController = rememberNavController()


                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.LightGray)
                )
                {

                }

                NavGraph(navController,AppScreens.Splash.rout)

                }

            }
        }
    }





@Composable
fun Main(navController: NavHostController= rememberNavController()) {

    val bottomBarState = rememberSaveable{mutableStateOf(true)}
    val navBackStackEntry by navController.currentBackStackEntryAsState()


    Scaffold(
     //   topBar = { TopBar() },
        bottomBar = {

                when(navBackStackEntry?.destination?.route){
                    BottomBarScreens.Home.rout->{
                        bottomBarState.value = true
                    }
                    BottomBarScreens.Profile.rout->{
                        bottomBarState.value = true
                    }
                    AppScreens.FilteredShops.rout->{
                        bottomBarState.value = false
                    }
                    AppScreens.Items.rout->{
                        bottomBarState.value = false
                    }
                    AppScreens.Login.rout->{
                        bottomBarState.value = false
                    }
                    AppScreens.Register.rout->{
                        bottomBarState.value = false
                    }
                    AppScreens.Cart.rout->{
                        bottomBarState.value = false
                    }
                }
                BottomBar(navController,bottomBarState)


        })

    { innerPadding ->

        BottomNavGraph(navController,
            modifier = Modifier
                .padding(innerPadding)
        )
    }
    
}




