package com.example.lemonade.login.viewModel

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.activity.result.IntentSenderRequest
import androidx.compose.runtime.currentCompositionLocalContext
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.lemonade.R
import com.example.lemonade.utils.AppScreens
import com.example.lemonade.utils.SharedState
import com.example.lemonade.utils.auth.GoogleAuthUiClient
import com.example.lemonade.utils.auth.SignInResult
import com.google.android.gms.auth.api.identity.Identity
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.coroutines.EmptyCoroutineContext

class LoginViewModel(context: Context) : ViewModel(){
    val coroutine = CoroutineScope(EmptyCoroutineContext)
 val sharedPref = SharedState()
 val userNameKey = SharedState.USER_NAME_KEY
 val emailKey = SharedState.EMAIL_KEY
    private val db: FirebaseFirestore = Firebase.firestore

    val dbUsers = db.collection("Users")

 val email = mutableStateOf("")
 val password = mutableStateOf("")
    val googleAuthUiClient by lazy {
        GoogleAuthUiClient(
            context = context,
            oneTapClient = Identity.getSignInClient(context)
        )
    }
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

    fun loginButton(context:Context,navController: NavHostController) {
        if (email.value.isEmpty() || password.value.isEmpty())
            Toast.makeText(context, "Please Fill Required Field", Toast.LENGTH_SHORT).show()
        else {
            viewModelScope.launch(Dispatchers.Main) {

                dbUsers.whereEqualTo("email", email.value).get().addOnSuccessListener {
                    if (it.size() == 0)
                        Toast.makeText(context, "There is no account ", Toast.LENGTH_SHORT).show()
                    else
                        dbUsers.whereEqualTo("password", password.value).get().addOnSuccessListener {
                            if (it.size() == 0)
                                Toast.makeText(context, "Wrong password", Toast.LENGTH_SHORT).show()
                            else
                                navController.navigate(AppScreens.Main.rout)


                        }
                }

            }




        }
        }
    fun loginText(navController:NavHostController) {

        navController.navigate(AppScreens.Register.rout)
    }
        }



//    fun loginWithGoogleButton(){
//
//    }

