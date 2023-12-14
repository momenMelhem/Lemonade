package com.example.lemonade.profile

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.example.lemonade.R
import com.example.lemonade.login.viewModel.LoginViewModel
import com.example.lemonade.profile.component.EditDialog
import com.example.lemonade.profile.component.ProfileCard
import com.example.lemonade.profile.component.SignOutCard
import com.example.lemonade.profile.component.SignOutDialog
import com.example.lemonade.profile.viewModel.ProfileViewModel
import com.example.lemonade.utils.auth.GoogleAuthUiClient
import com.google.android.gms.auth.api.identity.Identity

@Composable
fun Profile(
    navController: NavHostController,
    viewModel: ProfileViewModel
){

    val context = LocalContext.current
    val googleAuthUiClient by lazy {
        GoogleAuthUiClient(
            context = context,
            oneTapClient = Identity.getSignInClient(context)
        )
    }


    Column (
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()

) {
        ProfileCard(text = stringResource(R.string.user_name)+" :  ${viewModel.currentUserName ?: ""}",Icons.Default.Edit){
                  viewModel.onEditClick()
        }
        ProfileCard(text = stringResource(R.string.email)+" :  ${viewModel.currentUserEmail ?: ""}",Icons.Default.Edit){}

        SignOutCard(viewModel)
        AnimatedVisibility(visible = viewModel.showEditDialog.value) {
            EditDialog(viewModel)
        }
        AnimatedVisibility(visible = viewModel.showSignOutDialog) {
            SignOutDialog(viewModel,navController)
        }



}
}

