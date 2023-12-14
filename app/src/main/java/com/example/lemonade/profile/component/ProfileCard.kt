package com.example.lemonade.profile.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun ProfileCard(
    text:String,
    icon: ImageVector,
    modifier: Modifier = Modifier,
    onIconClicked:()->Unit
){
    Card (
        colors = CardDefaults.cardColors
            (
            containerColor = Color.White
        ),
        border = BorderStroke(1.dp, Color.White),
        elevation = CardDefaults.cardElevation(4.dp),
        shape= RoundedCornerShape(16.dp),
        modifier = modifier
            .padding(top = 24.dp, bottom = 8.dp)


    ){
        Row (
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(2.dp)
        ){
            Text(
                text =text,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(start = 16.dp, end = 8.dp)
            )

            IconButton(onClick = { onIconClicked() },

                ) {
                Icon(imageVector = icon, contentDescription = "null",
                    modifier = Modifier
                        .padding(4.dp)
                )
            }
        }
    }
}