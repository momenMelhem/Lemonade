@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.lemonade

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonade.ui.theme.LemonadeTheme
import com.example.lemonade.utils.auth.GoogleAuthUiClient
import com.example.lemonade.utils.auth.UserData
import com.google.android.gms.auth.api.identity.Identity
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore

@Preview(showBackground = true)
@Composable
fun LemonState(){
    var state by remember { mutableIntStateOf(1) }
    val context = LocalContext.current
    LemonadeTheme {
        val googleAuthUiClient by lazy {
            GoogleAuthUiClient(
                context = context,
                oneTapClient = Identity.getSignInClient(context)
            )
        }
    Column (
        horizontalAlignment =  Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ){



        when (state){

            1 ->
            {
                Lemon(
                    lemonData = LemonData(label = R.string.lemon_tree,image=R.drawable.lemon_tree),
                    modifier = Modifier
                ){state++}
            }
            2 ->
            {
                Lemon(
                    lemonData = LemonData(label = R.string.squeeze,image=R.drawable.lemon_squeeze),
                    modifier = Modifier
                ){state++}
            }
            3 ->
            {
                Lemon(
                    lemonData =   LemonData(label =R.string.drink,image=R.drawable.lemon_drink),
                    modifier = Modifier
                ){state++}
            }
            4 ->
            {
                Lemon(
                    lemonData =   LemonData(label = R.string.empty, image = R.drawable.lemon_restart),
                    modifier = Modifier

                ){state=1}
            }
        }
    }



    }
}
@Composable
fun Lemon (
    lemonData: LemonData,
    modifier: Modifier=Modifier,
    click:()->Unit
){
    Column (
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier

            .padding(10.dp)
    ){
        Image(
            painter = painterResource(lemonData.image),
            contentDescription = null,
            modifier= modifier
                .clickable {
                    click()
                }
                .size(150.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(lemonData.label),
            fontSize = 20.sp
        )
    }
}
