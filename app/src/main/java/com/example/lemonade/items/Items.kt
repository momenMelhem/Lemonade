package com.example.lemonade.items

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.example.lemonade.R
import com.example.lemonade.utils.AppScreens
import com.example.lemonade.utils.CartIcon
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Items(viewModel: ItemViewModel,shopName:Int,
          navController:NavHostController
) {
    val context = LocalContext.current
    val toast = Toast.makeText(context,"",Toast.LENGTH_SHORT)

    Scaffold(topBar = {
        CenterAlignedTopAppBar(
            title = { Text(text = stringResource(shopName)) },
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "back")
                }
            },
            actions = { CartIcon(navController) }
        )
    }
    )
    {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {

            LazyColumn {
                when (shopName) {

                    R.string.abu_saleh -> {

                        items(viewModel.abuSalehItemList.size) { index ->

                            ItemCard(itemData = viewModel.abuSalehItemList[index]) {
                                viewModel.onAddItemClick(viewModel.abuSalehItemList[index])
                                viewModel.abuSalehItemList[index].itemCount+=1
                                toast.setText("Item added to cart")
                                toast.show()
                            }
                        }
                    }
                }
            }

        }
    }

}