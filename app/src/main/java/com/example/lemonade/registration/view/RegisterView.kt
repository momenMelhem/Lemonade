package com.example.lemonade.registration.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.lemonade.registration.view.ConfirmPasswordTextField
import com.example.lemonade.registration.view.EmailTextField
import com.example.lemonade.registration.view.NameTextField
import com.example.lemonade.registration.view.PasswordTextField
import com.example.lemonade.registration.view.RegisterButton
import com.example.lemonade.registration.view.RegisterText
import com.example.lemonade.registration.viewModel.RegisterViewModel
import com.example.lemonade.utils.FormDivider
import com.example.lemonade.utils.SignWithGoogleButton

@Composable
fun Register(navController: NavHostController, viewModel: RegisterViewModel){

    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
    ){
        RegisterText()
        NameTextField(viewModel)
        EmailTextField(viewModel)
        PasswordTextField(viewModel)
        ConfirmPasswordTextField(viewModel)
        RegisterButton(viewModel)
        FormDivider()
        SignWithGoogleButton (){viewModel.registerWithGoogleButton()}

    }

}