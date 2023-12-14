package com.example.lemonade.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Scaffold
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.lemonade.R
import com.example.lemonade.items.ItemViewModel
import com.example.lemonade.utils.TopBar

@Composable
fun Home(
    navController:NavHostController,
    viewModel: ItemViewModel
){
Scaffold(
    topBar = { TopBar(navController)}
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(it)
    ) {
        LazyRow (
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(start = 4.dp, top = 24.dp, end = 2.dp))
        {

            items(viewModel.cardList.size) {index->
                Row (
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ){
                    CardCategory(categoryData = viewModel.cardList[index]){
                        navController.navigate("Filtered/"+viewModel.cardList[index].state)
                    }
                }
            }
        }
        Divider(
            modifier = Modifier
                .padding(start = 24.dp,end=24.dp,top=12.dp, bottom = 12.dp)

        )
        Text(
            text = stringResource(R.string.all_shops),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(start = 16.dp,bottom=8.dp,top=8.dp)
        )
        LazyColumn()
        {

            items (viewModel.shopList.size){ i->

                ShopCard(viewModel.shopList[i]){

                    viewModel.navigateToShop(navController,viewModel.shopList[i].shopName,i)
                }
            }
        }
    }
}

}

