package com.example.lemonade.registration.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.lemonade.R
import com.example.lemonade.login.viewModel.LoginViewModel
import com.example.lemonade.registration.viewModel.RegisterViewModel
import com.example.lemonade.utils.AppScreens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NameTextField(viewModel: RegisterViewModel){

        OutlinedTextField(
            value = viewModel.name.value,
            onValueChange = { viewModel.name.value = it },
            label = { Text(text = "User Name") },
            singleLine = true,
            textStyle = TextStyle(fontWeight = FontWeight.Bold),
            modifier = Modifier
                .padding(16.dp)
        )


}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmailTextField(viewModel: RegisterViewModel){

    OutlinedTextField(
        value = viewModel.email.value,
        onValueChange = { viewModel.email.value = it },
        label = { Text(text = "Email") },
        singleLine = true,
        textStyle = TextStyle(fontWeight = FontWeight.Bold),
        modifier = Modifier
            .padding(16.dp)
    )


}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordTextField(viewModel : RegisterViewModel){
        OutlinedTextField(
            value = viewModel.password.value,
            onValueChange = { viewModel.password.value = it },
            label = { Text(text = stringResource(R.string.password)) },
            placeholder = { Text(text = "at least 8 chars with uppercase and symbol",
               fontSize = 10.sp)},
            singleLine = true,
            visualTransformation = if (viewModel.passwordVisibility.value) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                IconButton(onClick = {
                    viewModel.passwordIcon()
                }) {
                    Icon(painter = painterResource(id = viewModel.iconState.value), contentDescription = "")
                }
            },
            modifier = Modifier
                .padding(16.dp)
        )
    }


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConfirmPasswordTextField(viewModel : RegisterViewModel){
    OutlinedTextField(
        value = viewModel.confirmPassword.value,
        onValueChange = { viewModel.confirmPassword.value = it },
        label = { Text(text = stringResource(R.string.confirm_password)) },
        singleLine = true,
        visualTransformation = if (viewModel.passwordVisibility.value) VisualTransformation.None else PasswordVisualTransformation(),
        trailingIcon = {
            IconButton(onClick = {
                viewModel.passwordIcon()
            }) {
                Icon(painter = painterResource(id = viewModel.iconState.value), contentDescription = "")
            }
        },
        modifier = Modifier
            .padding(16.dp)
    )
}

@Composable
fun RegisterButton(viewModel: RegisterViewModel){
    val context = LocalContext.current
    Button(
        onClick = { viewModel.registerButton(context) },
        modifier = Modifier
            .padding(4.dp)
    ) {
        Text(
            text = stringResource(R.string.register),
            fontSize = 18.sp
        )

    }
}

@Composable
fun RegisterText(){
    Text(
        text = stringResource(R.string.register),
        fontSize = 32.sp,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center,
    )
}