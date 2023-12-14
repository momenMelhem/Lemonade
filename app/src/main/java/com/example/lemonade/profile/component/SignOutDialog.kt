package com.example.lemonade.profile.component

import android.app.Activity
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.lemonade.R
import com.example.lemonade.profile.viewModel.ProfileViewModel
import com.example.lemonade.utils.AppScreens
import kotlinx.coroutines.launch

@Composable
fun SignOutDialog(viewModel: ProfileViewModel,navController: NavHostController){
    val context = LocalContext.current
    val coroutine = rememberCoroutineScope()
    val activity = (context as? Activity)

    AlertDialog(
        onDismissRequest = { viewModel.onDismissSignOutDialog() },
        confirmButton = {
            Button(
                onClick = {
                coroutine.launch {
                    viewModel.onSignOutClick(context)
                    Toast.makeText(context,"Sign Out Successful !", Toast.LENGTH_LONG).show()
                    navController.navigate(AppScreens.Login.rout)
                }
                    },
                colors = ButtonDefaults.buttonColors(
                containerColor = Color.White,
                contentColor = Color.Black

            ),
            elevation = ButtonDefaults.buttonElevation(1.dp),
            border = BorderStroke(1.dp, Color.White),
            modifier = Modifier
                .padding(4.dp)
        ) {
                Text(
                    text = stringResource(R.string.sign_out),
                    fontSize = 18.sp,
                    color = Color.Red
                )
            }
                        },
        dismissButton = {
            Button(
                onClick = { viewModel.onDismissSignOutDialog() },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                    contentColor = Color.Black
                ),
                elevation = ButtonDefaults.buttonElevation(1.dp),
                border = BorderStroke(1.dp, Color.White),
                modifier = Modifier
                    .padding(4.dp)
            ) {
                Text(
                    text = stringResource(R.string.cancel),
                    textAlign = TextAlign.Center,
                    fontSize = 18.sp
                )
            }
        },
        containerColor = Color.White,
        text = {
            Text(text = stringResource(R.string.sign_out_dialog),
            fontSize = 18.sp)}
            )
}
