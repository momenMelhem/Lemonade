package com.example.lemonade.login.view


import android.content.Context
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.lemonade.login.components.HaveAccountText
import com.example.lemonade.login.components.LoginButton
import com.example.lemonade.login.components.LoginText
import com.example.lemonade.login.components.TextField
import com.example.lemonade.login.viewModel.LoginViewModel
import com.example.lemonade.utils.AppScreens
import com.example.lemonade.utils.FormDivider
import com.example.lemonade.utils.SignWithGoogleButton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun LoginView(
    navController: NavHostController
)

{
    val context = LocalContext.current

    val viewModel = LoginViewModel(context)
    val state by viewModel.state.collectAsStateWithLifecycle()
    val coroutine = rememberCoroutineScope()

    LaunchedEffect(key1 = Unit) {



            if (viewModel. googleAuthUiClient.getSignedInUser() != null) {

                navController.navigate(AppScreens.Main.rout)
            }

        state.signInError?.let { error ->
            Toast.makeText(
                context,
                error,
                Toast.LENGTH_SHORT
            ).show()
        }
    }
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartIntentSenderForResult(),
        onResult = { result ->
            if (result.resultCode == ComponentActivity.RESULT_OK) {
                coroutine.launch(Dispatchers.IO) {
                    val signInResult = viewModel.googleAuthUiClient.signInWithIntent(
                        intent = result.data ?: return@launch
                    )
                    viewModel.onSignInResult(signInResult)
                }
            }
        }
    )

    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
        ){
        LoginText()
        TextField("Email", viewModel)
        TextField("Password", viewModel)
        LoginButton(viewModel,navController ,coroutine)
        HaveAccountText(navController)
        FormDivider()
        SignWithGoogleButton(viewModel){
            coroutine.launch(Dispatchers.Main) {
                val signInIntentSender = viewModel.googleAuthUiClient.signIn()
                launcher.launch(
                    IntentSenderRequest.Builder(
                        signInIntentSender ?: return@launch
                    ).build()
                )
            }

        }

        LaunchedEffect(key1 = state.isSignedIn){
            if(state.isSignedIn) {
                navController.navigate(AppScreens.Main.rout)
                coroutine.launch(Dispatchers.IO) {
                    viewModel.googleAuthUiClient.addUserDocument()
                    viewModel.sharedPref.setUserName(context, viewModel.googleAuthUiClient.getSignedInUser()?.userName ?: "")
                    viewModel.sharedPref.setEmail(context, viewModel.googleAuthUiClient.getSignedInUser()?.email ?: "")
                }
                viewModel.resetState()
            }

        }
    }
}

fun loginButton(context: Context, navController: NavHostController, viewModel: LoginViewModel, coroutineScope: CoroutineScope) {
    if (viewModel.email.value.isEmpty() || viewModel.password.value.isEmpty())
        Toast.makeText(context, "Please Fill Required Field", Toast.LENGTH_SHORT).show()
    else {
        coroutineScope.launch(Dispatchers.IO) {

            viewModel.dbUsers.whereEqualTo("email", viewModel.email.value).get().addOnSuccessListener {
                if (it.size() == 0)
                    Toast.makeText(context, "There is no account ", Toast.LENGTH_SHORT).show()
                else
                    viewModel.dbUsers.whereEqualTo("password", viewModel.password.value).get().addOnSuccessListener {
                        if (it.size() == 0)
                            Toast.makeText(context, "Wrong password", Toast.LENGTH_SHORT).show()
                        else
                            navController.navigate(AppScreens.Main.rout)
                    }
            }
        }
    }
}