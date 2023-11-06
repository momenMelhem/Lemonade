package com.example.lemonade.login.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.lemonade.R
import com.example.lemonade.login.viewModel.LoginViewModel
@Composable
 fun EmailTextField(viewModel: LoginViewModel){

    OutlinedTextField(
    value = viewModel.email.value,
    onValueChange = {viewModel.email.value=it},
    label = { Text(text = stringResource(R.string.email))},
    singleLine = true,
    textStyle = TextStyle(fontWeight = FontWeight.Bold),
    modifier = Modifier
        .padding(16.dp)
)
 }
@Composable
fun PasswordTextField(viewModel : LoginViewModel){
    OutlinedTextField(
        value = viewModel.password.value,
        onValueChange = {viewModel.password.value=it},
        label = {Text(text = "Password")},
        singleLine = true,
        visualTransformation =  if (viewModel.passwordVisibility.value) VisualTransformation.None else PasswordVisualTransformation(),
        trailingIcon = {
                       IconButton(onClick = {
                           viewModel.passwordIcon()
                       }){
                       Icon(painter = painterResource(id = viewModel.iconState.intValue), contentDescription ="" )
                       }
        },
        modifier = Modifier
            .padding(16.dp)
    )
}
