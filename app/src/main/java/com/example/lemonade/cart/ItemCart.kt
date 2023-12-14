package com.example.lemonade.cart

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.unit.sp
import com.example.lemonade.R
import com.example.lemonade.roomDb.ItemData

@Composable
fun ItemCart(
    itemData : ItemData,
    onAddItemClick:()-> Unit,
    onRemoveItemClick:()-> Unit

){
    Card (
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        border= BorderStroke(4.dp, Color.White),
        modifier = Modifier
            .fillMaxWidth()
            .height(175.dp)
            .padding(16.dp),

        ){
        Row (
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        ) {


            Image(
                painter = painterResource(itemData.itemImage),
                contentDescription = "item image",
                modifier = Modifier
                    .size(100.dp)
                    .padding(top = 10.dp)
            )
            //price
            Text(
                text = "JOD "+itemData.itemPrice.toString(),
                overflow = TextOverflow.Visible,
                modifier = Modifier
                    .align(Alignment.Bottom)
                    .padding(end = 2.dp, bottom = 32.dp)

            )
            // item name and description
            Column (
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Top,
                modifier = Modifier
                    .padding(end = 2.dp, bottom = 2.dp, top = 3.dp)
                    .fillMaxSize()

            ){

                Text(
                    text = stringResource(itemData.itemName),
                    textAlign = TextAlign.Start,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    overflow = TextOverflow.Visible,

                )
                Text(
                    text = stringResource(itemData.itemDescription),
                    textAlign = TextAlign.Start,
                    overflow = TextOverflow.Visible,
                )
                Row {
                    IconButton(onClick = { onRemoveItemClick() },

                        ) {
                        Icon(
                          painterResource(R.drawable.baseline_remove_24)  ,"remove", tint = Color.Blue,
                            modifier = Modifier
                                .size(25.dp)
                        )
                    }

                    Text(text = itemData.itemCount.toString(),
                        modifier = Modifier
                            .padding(top=10.dp))
                    IconButton(onClick = { onAddItemClick() },


                        ) {
                        Icon(
                            Icons.Default.Add,"add", tint = Color.Blue,
                            modifier = Modifier
                                .size(25.dp)

                        )
                    }
                }

            }



        }

    }

}