package com.example.lemonade.items

import android.content.Context
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.lemonade.R
import com.example.lemonade.home.CategoryData
import com.example.lemonade.home.ShopData
import com.example.lemonade.roomDb.ItemData
import com.example.lemonade.roomDb.Repository
import com.example.lemonade.roomDb.ShopEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ItemViewModel(context: Context) : ViewModel() {
    private val delivery = CategoryData(R.drawable.baseline_delivery_dining_24,R.string.free_delivery,1)
    private val sale = CategoryData(R.drawable.baseline_local_offer_24,R.string.sale,2)
    private val rate = CategoryData(R.drawable.baseline_star_rate_24,R.string.top_rated,3)
    private val coffee = CategoryData(R.drawable.baseline_coffee_24,R.string.hot,4)
    private val abuSaleh = ShopData(R.drawable.abu_saleh, R.string.abu_saleh,4.0,5.0,1.0,true)
    val cardList = listOf(
        delivery,sale,rate,coffee
    )
    var shopList = mutableListOf<ShopData>(
      abuSaleh,
        ShopData(
            R.drawable.aseer_time, R.string.aseer_time,4.2,10.0,0.5,false
        ),
        ShopData(
            R.drawable.toot_w_looz,R.string.toot_louz,4.5,0.0,0.0,false
        ),
        ShopData(
            R.drawable.avocado,R.string.avocado,4.3,0.0,1.0,false
        ),
        ShopData(
            R.drawable.shake_away,R.string.shake_away,4.2,0.0,2.0,false
        ),
        ShopData(
            R.drawable.batateekh,R.string.batateekh,3.8,20.0,2.0,false
        ),
        ShopData(
            R.drawable.juice_club,R.string.juice_club,3.4,30.0,1.5,false
        ),
        ShopData(
            R.drawable.abu_wadee,R.string.abu_wadee,3.3,25.0,0.25,true
        ),
        ShopData(
            R.drawable.alkabten,R.string.alkabten,3.2,0.0,0.0,true
        ),
        ShopData(
            R.drawable.fruit_salad,R.string.fruie_salad,4.1,0.0,0.75,false
        ),
    )

    internal val repo=Repository(context)
    private val sameera =
        ItemData( R.drawable.sameera,R.string.sameera_description, 1.0,0, R.string.sameera,)
    private val baheera =
        ItemData(
            R.drawable.baheera,R.string.baheera_desription,2.0,0,R.string.baheera)
    private val orio = ItemData(
        R.drawable.oreo, R.string.orioDesription,1.5,0, R.string.orio)
    private val lotus = ItemData(
        R.drawable.lootes, R.string.lootus_description,1.5, 0,R.string.lots)
    private val hotChocolate = ItemData(
       R.drawable.hot_chocolate,
        R.string.hot_chocolate_description,
        1.5,
        0,
        R.string.hot_chocolate
    )
    private val sahlab = ItemData(
        R.drawable.sahlab,R.string.sahlab_description, 1.25, 0, R.string.sahlab)
    val abuSalehItemList: MutableList<ItemData> = mutableListOf(
        sameera,
        baheera,
        orio,
        lotus,
        hotChocolate,
        sahlab
    )

     var itemsList: MutableList<ItemData> by mutableStateOf(mutableListOf())
     fun updateList(){

         itemsList = repo.getAllItems()
    }

    fun onAddItemClick(itemData: ItemData) {
//        itemsList.add(
//
//            abuSalehItemList[index]
//        )
//            var exist = false
        viewModelScope.launch {


            repo.addItem(itemData)
            updateList()
//            for (i in 0 until itemsList.size){
//            if (abuSalehItemList[index].itemName==itemsList[i].itemName) {
//                repo.updateShop((ShopEntity(shop = abuSaleh, item = abuSalehItemList[index])))
//                Log.d("updateItem",repo.updateShop((ShopEntity(shop = abuSaleh, item = abuSalehItemList[index]))).toString())
//                exist=true
//                break
//            }
//            }
//            if (!exist)
        // repo.addShop(ShopEntity(shop=abuSaleh, item = abuSalehItemList[index]))


//         itemsList = repo.getAllItems()

//         Log.d("repo.getAllItems()",repo.getAllItems().toString())
//         Log.d("itemsList.toString()",itemsList.toString())
     }

    }
     fun onRemoveItemClick(itemData: ItemData){
        viewModelScope.launch {
//            if (itemData.itemCount>1){
//                //itemDao.updateItemCount(itemData.itemName,itemData.itemCount-1)
//             //   repo.updateItems(shopList.find { it.shopName==R.string.abu_saleh }!!,itemsList)
//            }
            if(itemData.itemCount<1) {
           //     itemDao.deleteItem(itemData)
                repo.removeItem(itemData)
                itemsList.remove(itemData)
            }
            else {
                repo.updateItem(itemData)

            }
         var itemIndex:Int=abuSalehItemList.find { it.itemName==itemData.itemName }!!.itemCount
            if (itemIndex>0) {
                itemIndex -= 1
            }
           updateList()

            Log.d("repo.getAllItems()",repo.getAllItems().toString())
            Log.d("itemsList",itemsList.toString())
        }


    }
    fun navigateToShop(navController: NavHostController, shopName:Int, index:Int){
        if (shopName==shopList[index].shopName)
            navController.navigate("Items/$shopName")
    }
}


