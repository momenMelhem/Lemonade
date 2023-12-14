package com.example.lemonade.cart


import android.util.Log
import android.widget.Toast
import androidx.annotation.StringRes
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.example.lemonade.R
import com.example.lemonade.items.ItemViewModel
import com.example.lemonade.roomDb.ItemData
import com.example.lemonade.roomDb.ShopEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.IndexOutOfBoundsException

//TODO("Make it save data to room when back button clicked")
//TODO("Let cart take an entity object")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Cart(
    navController: NavHostController,
    viewModel: ItemViewModel,
    shopName: Int
) {

    val context = LocalContext.current
    val toast = Toast.makeText(context, "", Toast.LENGTH_SHORT)
    val coroutine = rememberCoroutineScope()
    //  TODO("Check if list is empty before it reads from room")

    if (viewModel.itemsList.isEmpty())
        viewModel.updateList()


//    LaunchedEffect(Unit){
//        coroutine.launch(Dispatchers.IO){
//            items =  viewModel.itemDao.getAll()
//
//        }
//    }
    Scaffold(
        topBar =
        {
            CenterAlignedTopAppBar(
                title = { Text(text = "Cart") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                    }
                },
            )
        }
    )
    { innerPadding ->

        LazyColumn(
            modifier = Modifier.padding(innerPadding)
        ) {
            when (shopName) {
                R.string.abu_saleh -> {

                }
            }
            items(viewModel.itemsList.size) { index ->

                ItemCart(
                    viewModel.itemsList[index],
                    onAddItemClick = {
                        viewModel.onAddItemClick(viewModel.itemsList[index])
                        viewModel.itemsList[index].itemCount+=1
                        viewModel.updateList()
                    },
                    onRemoveItemClick = {

                        try {
                            viewModel.itemsList[index].itemCount -= 1

                        } catch (e: IndexOutOfBoundsException) {
                            Log.d("IndexOutOfBoundsException", e.stackTrace.toString())
                        }

                        viewModel.onRemoveItemClick(viewModel.itemsList[index])
                        viewModel.updateList()
                        toast.setText("Item Removed !")
                        toast.show()

                    }
                )
            }
        }

        // summary contains delivery fee, sales percent, and total price
    }


}
