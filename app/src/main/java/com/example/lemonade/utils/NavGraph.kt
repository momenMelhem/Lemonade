package com.example.lemonade.utils

import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.lemonade.appBar.BottomBarScreens
import com.example.lemonade.HowToUseView
import com.example.lemonade.LemonState
import com.example.lemonade.Main
import com.example.lemonade.Slider
import com.example.lemonade.login.components.HaveAccountText
import com.example.lemonade.login.view.LoginView
import com.example.lemonade.registration.view.Register
import com.example.lemonade.login.viewModel.LoginViewModel
import com.example.lemonade.registration.viewModel.RegisterViewModel
import com.example.lemonade.utils.auth.GoogleAuthUiClient
import com.example.lemonade.utils.auth.UserData
import com.google.android.gms.auth.api.identity.Identity
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun NavGraph(
    navController: NavHostController= rememberNavController(),
    startDestination:String,

){
    val coroutine = rememberCoroutineScope()
    val viewModel = viewModel<LoginViewModel>()
    val state by viewModel.state.collectAsStateWithLifecycle()
    val context = LocalContext.current
     val googleAuthUiClient by lazy {
        GoogleAuthUiClient(
            context = context,
            oneTapClient = Identity.getSignInClient(context)
        )
    }
    NavHost(
        navController = navController,
        startDestination = startDestination,
    ){
        composable(AppScreens.Login.rout) {

            LaunchedEffect(key1 = Unit) {
                if (googleAuthUiClient.getSignedInUser() != null) {
                    navController.navigate(AppScreens.Main.rout)
                }
            }
            val launcher = rememberLauncherForActivityResult(
                contract = ActivityResultContracts.StartIntentSenderForResult(),
                onResult = { result ->
                    if (result.resultCode == ComponentActivity.RESULT_OK) {
                        coroutine.launch(Dispatchers.IO) {
                            val signInResult = googleAuthUiClient.signInWithIntent(
                                intent = result.data ?: return@launch
                            )
                            viewModel.onSignInResult(signInResult)
                        }
                    }
                }
            )
            LaunchedEffect(key1 = state.isSignedIn) {
                if (state.isSignedIn) {
                    val db: FirebaseFirestore = Firebase.firestore
                    val user = UserData(FirebaseAuth.getInstance().uid,googleAuthUiClient.getSignedInUser()?.userName,googleAuthUiClient.getSignedInUser()?.email)
                    val dbUsers : CollectionReference = db.collection("Users")
                    dbUsers.document(FirebaseAuth.getInstance().currentUser?.uid?:"")
                    dbUsers.add(user)
                    Toast.makeText(
                        context,
                        "Sign in successful",
                        Toast.LENGTH_SHORT
                    ).show()
                    navController.navigate(AppScreens.Main.rout)
                    viewModel.resetState()
                }
            }
            LoginView(navController,viewModel,state) {
                coroutine.launch(Dispatchers.IO) {
                    val signInIntentSender = googleAuthUiClient.signIn()
                    launcher.launch(
                        IntentSenderRequest.Builder(
                            signInIntentSender ?: return@launch
                        ).build()
                    )
                }
            }
        }

        composable(route= AppScreens.Main.rout){
           Main()
        }
        composable(route= BottomBarScreens.Home.rout){
            LemonState()
        }
        composable(route= AppScreens.Register.rout){
            Register(navController,RegisterViewModel())
        }
        composable(route= AppScreens.HavAccount.rout){
            HaveAccountText(LoginViewModel(),navController)
        }
        composable(route= BottomBarScreens.How.rout){
            HowToUseView(navController = navController)
        }
    }
    }
@Composable
fun BottomNavGraph(
    navController: NavHostController= rememberNavController(),
    modifier:Modifier
)
{
    NavHost(
        navController = navController,
        startDestination = BottomBarScreens.Home.rout,
        modifier = modifier
    ){
        composable(route= BottomBarScreens.Home.rout){
            LemonState()
        }
        composable(route= BottomBarScreens.Slide.rout){
            Slider()
        }
    }
}
