package com.example.lemonade.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

@Composable
fun CardCategory(
    modifier: Modifier = Modifier,
    categoryData: CategoryData,
    onClick:()->Unit
    ){
    Card(
        border = BorderStroke(1.dp, Color.White),
        elevation = CardDefaults.cardElevation(4.dp),
        shape= RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        modifier =
        modifier
            .padding(8.dp)
            .size(108.dp)
            .clickable {
                onClick()
            },


        ) {
        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
        ) {

            Image(
                painter = painterResource(categoryData.icon),contentDescription = null,
                modifier = modifier
                    .size(35.dp)
            )

            Text(
                text = stringResource(categoryData.label),
                modifier.padding(start = 4.dp,top=12.dp),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                overflow = TextOverflow.Visible,

                )
        }
    }
}