package com.example.lemonade.login.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.lemonade.R
import com.example.lemonade.login.viewModel.LoginViewModel

@Composable
fun LoginButton(viewModel: LoginViewModel){
    val context = LocalContext.current

    Button(
        onClick = { viewModel.loginButton(context) },
        modifier = Modifier
            .padding(4.dp)
    ) {
        Text(
            text = stringResource(R.string.login),
            fontSize = 18.sp
            )

    }
}
@Composable
fun HaveAccountText(viewModel: LoginViewModel,navController:NavHostController){

    TextButton(onClick = {viewModel.loginText(navController)}){
        Text (
            text = stringResource(R.string.register_here),
             )
    }
}
@Composable
fun LoginText(){
    Text(
        stringResource(R.string.login),
        fontSize = 32.sp,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center,
        )
}

