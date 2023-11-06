package com.example.lemonade.login.view


import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.example.lemonade.login.components.HaveAccountText
import com.example.lemonade.login.components.LoginButton
import com.example.lemonade.login.components.LoginText
import com.example.lemonade.login.components.EmailTextField
import com.example.lemonade.login.components.PasswordTextField
import com.example.lemonade.login.viewModel.LogInState
import com.example.lemonade.login.viewModel.LoginViewModel
import com.example.lemonade.utils.FormDivider
import com.example.lemonade.utils.SignWithGoogleButton

@Composable
fun LoginView(
    navController: NavHostController,
    viewModel: LoginViewModel,
    state: LogInState,
    onClick:()->Unit
)
{
    val context = LocalContext.current
    LaunchedEffect(key1 = state.signInError) {
        state.signInError?.let { error ->
            Toast.makeText(
                context,
                error,
                Toast.LENGTH_SHORT
            ).show()
        }
    }
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
        ){
        LoginText()
        EmailTextField(viewModel)
        PasswordTextField(viewModel)
        LoginButton(viewModel)
        HaveAccountText(viewModel,navController)
        FormDivider()
        SignWithGoogleButton{onClick()}
    }

}