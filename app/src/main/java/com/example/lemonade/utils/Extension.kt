package com.example.lemonade.utils

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import com.example.lemonade.R
import com.example.lemonade.login.viewModel.LogInState
import com.example.lemonade.login.viewModel.LoginViewModel
import java.util.regex.Pattern

fun String.validEmail() : Boolean {
    val emailPattern = Pattern.compile(
        "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                "\\@" +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                "(" +
                "\\." +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                ")+"
    )
    return emailPattern.matcher(this).matches()
}

fun String.validPassword() : Boolean {
    // Password Should be more than 8 chars and contain a lowercase, uppercase and symbol
    if (this.length < 8) return false
    if (this.filter { it.isDigit() }.firstOrNull() == null) return false
    if (this.filter { it.isLetter() }.filter { it.isUpperCase() }
            .firstOrNull() == null) return false
    if (this.filter { it.isLetter() }.filter { it.isLowerCase() }
            .firstOrNull() == null) return false
    if (this.filter { !it.isLetterOrDigit() }.firstOrNull() == null) return false

    return true
}
fun String.validConfirmPassword(password:String) : Boolean {

    return  this == password

}
@Composable
fun FormDivider(){
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ){
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        )
        Text(
            text = "OR",
            modifier = Modifier
                .padding(8.dp),
            fontSize = 18.sp

        )
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
        )
    }
}
@Composable
fun SignWithGoogleButton(viewModel: LoginViewModel,onClick:()->Unit){

    Button(
        onClick = {

            onClick()

        },
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.White,
        ),
        shape = RoundedCornerShape(24.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 116.dp, end = 116.dp)
    ) {
        Image(
            painter = painterResource(R.drawable.icons8_google_48),
            contentDescription = null,
            modifier = Modifier
                .size(30.dp)
        )
    }
}
