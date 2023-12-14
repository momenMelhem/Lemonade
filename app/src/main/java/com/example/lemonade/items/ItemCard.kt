package com.example.lemonade.items

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import com.example.lemonade.roomDb.ItemData
//TODO("Make every item clickable")
@Composable
fun ItemCard(itemData : ItemData, onAddItemClick:()-> Unit ){

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
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
            .fillMaxWidth()
        ) {
            Text(
                text = stringResource(itemData.itemName),
                textAlign = TextAlign.Start,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                overflow = TextOverflow.Visible,
                modifier = Modifier
                    .padding(2.dp)
            )
        }
        Row (
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Image(
                painter = painterResource(itemData.itemImage),
                contentDescription = "item image",
                modifier = Modifier
                    .size(100.dp)
                    .padding(end = 2.dp,)
            )
            Column (
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Row (
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .padding(4.dp)
                ){
                    Text(
                        text = stringResource(itemData.itemDescription),
                        textAlign = TextAlign.Center,
                        overflow = TextOverflow.Visible,
                        modifier = Modifier
                            .padding(4.dp)

                    )
                }

                Row (

                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)

                ) {

                    Text(
                        text = "JOD "+itemData.itemPrice.toString(),
                        overflow = TextOverflow.Visible,
                        modifier = Modifier
                            .padding(4.dp)


                    )
                    Spacer(modifier = Modifier.size(16.dp))

                    IconButton(onClick = { onAddItemClick() },


                    ) {
                        Icon(Icons.Default.Add,"add", tint = Color.Blue,
                            modifier = Modifier
                                .size(25.dp)

                        )
                    }

                }
            }

        }


//        Row (
//            verticalAlignment = Alignment.CenterVertically,
//            horizontalArrangement = Arrangement.Start,
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(8.dp)
//        ) {
//
//
//
//
//            Column (
//                horizontalAlignment = Alignment.Start,
//                verticalArrangement = Arrangement.Top,
//                modifier = Modifier
//                    .padding(end = 2.dp, bottom = 2.dp, top = 3.dp)
//                    .fillMaxHeight()
//
//            ){
//
//            }
//
//
//            }


        }


    }


