package com.example.lemonade.roomDb

import android.content.Context
import kotlinx.coroutines.flow.Flow

class Repository(context: Context) {
    private val itemDao = AppDataBase.getDatabaseInstance(context).itemDao()

//    suspend fun addShop(shopEntity: ShopEntity){
//        shopDao.insertShop(shopEntity)
//    }
   // suspend fun getAllShops() = itemDao.getAll()
//    suspend fun removeShop(shopEntity: ShopEntity){
//        itemDao.deleteShop(shopEntity)
//    }
//    suspend fun updateShop(shopEntity: ShopEntity){
//        itemDao.updateShop(shopEntity)
//    }
       fun getAllItems():MutableList<ItemData>{
        return itemDao.getAllItems()
    }
    suspend fun addItem(itemData: ItemData){
        return itemDao.insertItem(itemData)
    }
    suspend fun updateItem(item:ItemData){
        itemDao.updateItem(item)
    }
    suspend fun removeItem(itemData: ItemData){
        itemDao.deleteItem(itemData)
    }
//
//    suspend fun removeList(itemList: MutableList<ItemData>){
//        shopDao.deleteList(itemList)
//    }

     //suspend fun getItems(shop:ShopData) = shopDao.getList(shop)
}