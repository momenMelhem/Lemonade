package com.example.lemonade.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonade.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShopCard(shopData: ShopData, onShopClicked:()->Unit){


    Card (
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        border= BorderStroke(4.dp, Color.White
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        onClick = {onShopClicked()}

    ){
        Row (
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Image(
                painter = painterResource(shopData.shopImage),
                contentDescription = "abu saleh",
                modifier = Modifier
                    .size(100.dp)
                    .padding(4.dp)
            )
            Column (
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .padding(start = 44.dp, end = 2.dp, bottom = 2.dp, top = 2.dp)){
                Text(
                    text = stringResource(shopData.shopName),
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    modifier = Modifier
                        .padding(2.dp)
                )
                IconLabel(
                    icon = R.drawable.baseline_star_rate_24,
                    label = shopData.rate.toString(),
                    color = Color(0XFFFFA500)
                )

            }
            Column(modifier = Modifier
                .padding(start = 16.dp)) {
                if (shopData.sale!!>0.0)
                {
                    IconLabel(
                        icon = R.drawable.baseline_local_offer_24,
                        label =shopData.sale.toString()+"%",
                        color = Color.Red
                    )

                }

                IconLabel(
                    icon = R.drawable.baseline_delivery_dining_24,
                    label = if (shopData.deliveryFee==0.0)
                        "free"
                    else
                        shopData.deliveryFee.toString(),

                    )
            }

        }

    }

}
@Composable
fun IconLabel(
    icon : Int,
    label:String?,
    color: Color = Color.Black,
    modifier: Modifier = Modifier
){
    Row {
        Icon(
            painter = painterResource(icon) ,
            contentDescription = null,
            tint = color,
            modifier = Modifier
                .padding(top=8.dp)
        )

        Text(
            text = label!!,
            modifier = Modifier
                .padding(top=8.dp)
        )

    }
}