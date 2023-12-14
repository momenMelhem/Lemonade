package com.example.lemonade.login.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.lemonade.login.viewModel.LoginViewModel
@Composable
 fun TextField(text:String, viewModel: LoginViewModel){
    OutlinedTextField(
    value = when (text){
        "Password" -> viewModel.password.value
        else -> {viewModel.email.value}
    },
    onValueChange = {
        when (text){
            "Password" -> viewModel.password.value=it
            else -> {viewModel.email.value=it}
        }
                    },
    label = { Text(text = text) },
    singleLine = true,
    textStyle = TextStyle(fontWeight = FontWeight.Bold),
    visualTransformation =
    if (text == "Password" && viewModel.passwordVisibility.value) VisualTransformation.None else PasswordVisualTransformation(),
    trailingIcon = {
        if(text == "Password") {
            IconButton(onClick = {
                viewModel.passwordIcon()
                //write it here direct!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
            }) {
                Icon(
                    painter = painterResource(id = viewModel.iconState.intValue),
                    contentDescription = ""
                )
            }
        }
    },
    modifier = Modifier
        .padding(16.dp)
)
 }
