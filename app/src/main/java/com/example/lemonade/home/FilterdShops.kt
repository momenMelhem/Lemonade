@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.lemonade.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
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
import androidx.navigation.NavHostController
import com.example.lemonade.R
import com.example.lemonade.items.ItemViewModel
import com.example.lemonade.utils.AppScreens
import com.example.lemonade.utils.CartIcon

//TODO("Move Cart here")

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilteredShops(
    navController:NavHostController,
    filterState:Int,
    viewModel: ItemViewModel
){

    val freeList : List <ShopData> = viewModel.shopList.filter { it.deliveryFee==0.0 }.toMutableList()
    val saleList : List <ShopData> = viewModel.shopList.filter { it.sale!!>0 }.sortedBy { it.sale }.toMutableList()
    val hotList : List <ShopData> = viewModel.shopList.filter { it.hasHot }.toMutableList()
    val topRatedList : List <ShopData> = viewModel.shopList.filter { it.rate>=3.5 }.sortedByDescending { it.rate }.toMutableList()


    //choose the right title
    val title= when(filterState){
        1->
            stringResource(R.string.free_delivery)
        2->
            stringResource(R.string.sale)
        3->
            stringResource(R.string.top_rated)
        4->
            stringResource(R.string.hot_drinks)
        else -> ""

    }
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(
                text = title,
                textAlign = TextAlign.Center,
                overflow = TextOverflow.Ellipsis,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )},
                navigationIcon = {
                        IconButton(onClick = {navController.popBackStack()}) {
                        Icon(painter = painterResource(R.drawable.baseline_arrow_back_24), contentDescription = null)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White
                )
            )
        }
    )
    { innerPadding ->

        LazyColumn(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(top = 4.dp)
        )
        {
            when(filterState){
                1->
                    items (freeList.size){ index ->

                        ShopCard(freeList[index]){

                        }
                    }

                2->
                    items(saleList.size){index->
                        ShopCard(saleList[index]){


                            if(saleList[index].shopName==R.string.abu_saleh){
                                navController.navigate("Items/"+R.string.abu_saleh)
                            }
                        }
                    }

                3->
                    items(topRatedList.size){index->
                        ShopCard(topRatedList[index]){}
                    }
                4->
                    items(hotList.size){index->
                        ShopCard(hotList[index]){}
                    }
            }

        }
    }
}