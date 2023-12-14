package com.example.lemonade.profile.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.lemonade.R
import com.example.lemonade.profile.viewModel.ProfileViewModel

@Composable
fun EditDialog(viewModel: ProfileViewModel){
    Dialog(onDismissRequest = { viewModel.onDismissDialog() },


        ) {
        Card(
            elevation = CardDefaults.cardElevation(4.dp),
            shape = RoundedCornerShape(16.dp),
            border = BorderStroke(2.dp, Color.White),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(text = stringResource(R.string.edit_user_name),
                fontSize = 18.sp,
                modifier = Modifier
                    .padding(12.dp)
            )

            TextField(
                value = viewModel.editUserName,
                onValueChange = {viewModel.editUserName=it},
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.White ,
                    unfocusedContainerColor = Color.White,
                    disabledContainerColor = Color.White ,
                    cursorColor = Color.Black,
                    focusedIndicatorColor = Color.Black
                ),
                modifier = Modifier
                    .padding(6.dp)
            )
            Row {
                Button(onClick = { viewModel.onDismissDialog() },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White,
                        contentColor = Color.Black
                    ),
                    elevation = ButtonDefaults.buttonElevation(1.dp),
                    border = BorderStroke(1.dp, Color.White),
                    modifier = Modifier
                        .weight(1f)
                        .padding(4.dp)
                ) {
                    Text(
                        text = stringResource(R.string.cancel),
                        textAlign = TextAlign.Center,
                        fontSize = 18.sp
                    )
                }
                Button(onClick = { viewModel.onSaveClick() },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White,
                        contentColor = Color.Black

                    ),
                    elevation = ButtonDefaults.buttonElevation(1.dp),
                    border = BorderStroke(1.dp, Color.White),
                    modifier = Modifier
                        .weight(1f)
                        .padding(4.dp)
                ){
                    Text(
                        text = stringResource(R.string.save),
                        textAlign = TextAlign.Center,
                        fontSize = 18.sp
                    )
                }
            }

        }
    }
}