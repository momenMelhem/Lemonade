package com.example.lemonade.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.lemonade.home.FilteredShops
import com.example.lemonade.home.Home
import com.example.lemonade.appBar.BottomBarScreens
import com.example.lemonade.Main
import com.example.lemonade.profile.Profile
import com.example.lemonade.SplashScreen
import com.example.lemonade.cart.Cart
import com.example.lemonade.items.ItemViewModel
import com.example.lemonade.items.Items
import com.example.lemonade.login.view.LoginView
import com.example.lemonade.profile.viewModel.ProfileViewModel
import com.example.lemonade.registration.view.Register
import com.example.lemonade.registration.viewModel.RegisterViewModel


@Composable
fun NavGraph(
    navController: NavHostController= rememberNavController(),
    startDestination:String,

){
    val context = LocalContext.current
    val profileViewModel = ProfileViewModel(context)


//     val googleAuthUiClient by lazy {
//        GoogleAuthUiClient(
//            context = context,
//            oneTapClient = Identity.getSignInClient(context)
//        )
//    }

    NavHost(
        navController = navController,
        startDestination = startDestination,
    ){
        composable(AppScreens.Login.rout) {

//            LaunchedEffect(key1 = Unit) {
//                if (googleAuthUiClient.getSignedInUser() != null) {
//
//                    navController.navigate(AppScreens.Main.rout)
//                }
//            }
//            val launcher = rememberLauncherForActivityResult(
//                contract = ActivityResultContracts.StartIntentSenderForResult(),
//                onResult = { result ->
//                    if (result.resultCode == ComponentActivity.RESULT_OK) {
//                        coroutine.launch(Dispatchers.IO) {
//                            val signInResult = googleAuthUiClient.signInWithIntent(
//                                intent = result.data ?: return@launch
//                            )
//                            viewModel.onSignInResult(signInResult)
//                        }
//                    }
//                }
//            )

//            LaunchedEffect(key1 = state.isSignedIn) {
//
//                if (state.isSignedIn) {
//
//                    Toast.makeText(
//                        context,
//                        "Sign in successful",
//                        Toast.LENGTH_SHORT
//                    ).show()
//                    navController.navigate(AppScreens.Main.rout)
//                    viewModel.resetState()
//                }
//
//            }

            LoginView(navController)

            }
//            LaunchedEffect(key1 = Unit) {
//                coroutine.launch {
//
//                    googleAuthUiClient.addUserDocument()
//                }

//
//
////                query.get().addOnCompleteListener { task ->
////                    var emailExist = false
////                    Log.d("qwuyqwui 1", task.isSuccessful.toString())
////
////                    if (task.isSuccessful) {
////                        Log.d("qwuyqwui 2", task.isSuccessful.toString())
////
////                        for (doc in task.result) {
////                            Log.d("qwuyqwui 3", doc.getString("email").toString())
//////                                Log.d("doc",doc.getString("email")?:"null")
//////                                Log.d("doc to string",doc.toString())
//////                                val emails = i.getString("email")
////                            if (doc.exists()) {
////                                emailExist = true
////                                Log.d("qwuyqwui 4", "EMAIL EXIST")
////                                break
////                            }
////                        }
////                        if (!emailExist) {
////                            dbUsers.add(user)
////                            Log.d("qwuyqwui 5", "user added")
////                        }
////                    }
////                }
   //         }

        composable(route= AppScreens.Main.rout){
          Main()
        }
        composable(route= AppScreens.Register.rout){
            Register(navController,RegisterViewModel())
        }

        composable(route= BottomBarScreens.Profile.rout){
            Profile(viewModel = profileViewModel, navController =  navController)
        }
        composable(route= AppScreens.Splash.rout){
            SplashScreen(navController)
        }
    }
    }
@Composable
fun BottomNavGraph(
    navController: NavHostController,
    modifier:Modifier
)
{
    val context = LocalContext.current
    val profileViewModel by  remember { mutableStateOf(ProfileViewModel(context))}
    val itemViewModel by remember { mutableStateOf(ItemViewModel(context))}
    NavHost(
        navController = navController,
        startDestination = BottomBarScreens.Home.rout,
        modifier = modifier
    ){
        composable( AppScreens.Login.rout){
            NavGraph(startDestination = AppScreens.Login.rout)
        }
        composable(route= BottomBarScreens.Home.rout){
            Home(navController,itemViewModel)
        }
        composable(route= BottomBarScreens.Profile.rout){
            Profile(navController,profileViewModel)
        }
        composable(
            route= AppScreens.FilteredShops.rout,
            arguments = listOf(navArgument(FILTER_ARGUMENT_KEY){
                type = NavType.IntType
            }
            )
        ){
            FilteredShops(navController,it.arguments?.getInt(FILTER_ARGUMENT_KEY) ?:0,itemViewModel)
        }
        composable(route= AppScreens.Splash.rout){
            SplashScreen(navController)
        }
        composable(
            route= AppScreens.Items.rout,
            arguments = listOf(navArgument(ITEMS_ARGUMENT_KEY){
                type = NavType.IntType
            }
            )
        ){
            Items(
                itemViewModel,it.arguments?.getInt(ITEMS_ARGUMENT_KEY)?:0,
                navController
                )
        }
        composable(route= AppScreens.Cart.rout,
            arguments = listOf(navArgument(Cart_ARGUMENT_KEY){
                type = NavType.IntType
            }
            )
        ){
            Cart(navController,itemViewModel,it.arguments?.getInt(Cart_ARGUMENT_KEY)?:0)
        }
    }
}

