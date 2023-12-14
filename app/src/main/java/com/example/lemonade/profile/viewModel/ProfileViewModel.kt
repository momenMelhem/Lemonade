package com.example.lemonade.profile.viewModel

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.lemonade.login.viewModel.LoginViewModel
import com.example.lemonade.utils.auth.GoogleAuthUiClient
import com.google.android.gms.auth.api.identity.Identity
import kotlinx.coroutines.CoroutineScope
import kotlin.coroutines.EmptyCoroutineContext

class ProfileViewModel(
     context: Context,

):ViewModel(){

    val coroutine =  CoroutineScope(EmptyCoroutineContext)
    val loginViewModel = LoginViewModel(context)
    val googleAuthUiClient by lazy {
        GoogleAuthUiClient(
            context = context,
            oneTapClient = Identity.getSignInClient(context)
        )
    }
    var currentUserName by mutableStateOf(loginViewModel.sharedPref.userName(context))
    var currentUserEmail by mutableStateOf(loginViewModel.sharedPref.email(context))
    var editUserName by mutableStateOf("")
    var showEditDialog = mutableStateOf(false)
        private set
    var showSignOutDialog by mutableStateOf(false)

    fun onEditClick(){
        showEditDialog.value=true
    }
    fun onDismissDialog(){
        showEditDialog.value = false

    }
    fun onDismissSignOutDialog(){
        showSignOutDialog = false

    }
    fun onSaveClick(){

         googleAuthUiClient.updateUserName(editUserName)
         currentUserName = editUserName
        onDismissDialog()
    }

    suspend fun onSignOutClick(context: Context) {

             googleAuthUiClient.signOut()
             loginViewModel.sharedPref.removeFromSharedPref(loginViewModel.emailKey,  context)
             loginViewModel.sharedPref.removeFromSharedPref(loginViewModel.userNameKey, context)

    }
}