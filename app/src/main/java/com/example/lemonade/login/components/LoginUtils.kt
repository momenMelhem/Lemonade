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
import com.example.lemonade.login.view.loginButton
import com.example.lemonade.login.viewModel.LoginViewModel
import com.example.lemonade.utils.AppScreens
import kotlinx.coroutines.CoroutineScope

@Composable
fun LoginButton(viewModel: LoginViewModel,navController: NavHostController, coroutineScope: CoroutineScope){
    val context = LocalContext.current

    Button(
        onClick = { loginButton(context,navController,viewModel, coroutineScope) },
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
fun HaveAccountText(navController:NavHostController){

    TextButton(onClick = {navController.navigate(AppScreens.Register.rout)}){
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

