package com.example.lemonade.profile.component

import android.app.Activity
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.lemonade.R
import com.example.lemonade.profile.viewModel.ProfileViewModel
import com.example.lemonade.utils.AppScreens
import kotlinx.coroutines.launch

@Composable
fun SignOutCard(viewModel: ProfileViewModel){

    Card (
        colors = CardDefaults.cardColors
            (
            containerColor = Color.White
        ),
        border = BorderStroke(1.dp, Color.White),
        elevation = CardDefaults.cardElevation(4.dp),
        shape= RoundedCornerShape(30.dp),
        modifier = Modifier
            .padding(64.dp),

    ){
        Row (
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .clickable {
                  viewModel.showSignOutDialog=true
                }
        ){
            Text(
                text = stringResource(R.string.sign_out),
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                modifier = Modifier
                    .padding(start = 16.dp, end = 8.dp)
            )

            IconButton(onClick = {

                viewModel.showSignOutDialog=true

                 }

                ) {
                Icon(imageVector = Icons.Default.ExitToApp, contentDescription = "null",
                    modifier = Modifier
                        .padding(4.dp)
                )
            }
        }
    }
}