package com.example.lemonade.login.viewModel

import android.content.Context
import android.widget.Toast
import androidx.activity.result.IntentSenderRequest
import androidx.compose.runtime.currentCompositionLocalContext
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavHostController
import com.example.lemonade.R
import com.example.lemonade.utils.AppScreens
import com.example.lemonade.utils.auth.GoogleAuthUiClient
import com.example.lemonade.utils.auth.SignInResult
import com.google.android.gms.auth.api.identity.Identity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel(){

 val email = mutableStateOf("")
 val password = mutableStateOf("")
 val passwordVisibility = mutableStateOf(false)
 var iconState = mutableIntStateOf(R.drawable.baseline_visibility_24)
private val _state = MutableStateFlow(LogInState())
val state = _state.asStateFlow()
    fun onSignInResult(result:SignInResult){
        _state.update {
            it.copy(
                isSignedIn = result.data!=null,
                signInError = result.errorMessage
            )
        }
    }
    fun resetState(){
        _state.update { LogInState() }
    }
    fun passwordIcon(){
        passwordVisibility.value=!passwordVisibility.value
        if (passwordVisibility.value)
             iconState.intValue=R.drawable.baseline_visibility_off_24
        else
            iconState.intValue=R.drawable.baseline_visibility_24
    }

    fun loginButton(context:Context) {
        if (email.value.isEmpty() || password.value.isEmpty())
            Toast.makeText(context, "Please Fill Required Field", Toast.LENGTH_SHORT).show()
        }

    fun loginText(navController:NavHostController) {

        navController.navigate(AppScreens.Register.rout)
    }
//    fun loginWithGoogleButton(){
//
//    }

}