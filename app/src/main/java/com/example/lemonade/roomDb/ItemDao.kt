package com.example.lemonade.roomDb

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemDao {

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(itemData: ItemData)

    @Update
    suspend fun updateItem(itemData: ItemData)

    @Delete
    suspend fun deleteItem(itemData: ItemData)

    @Query("Select * From ItemData")
      fun getAllItems():MutableList<ItemData>

    //    @Query ("Update ItemData Set itemCount= :itemCount Where itemName=:itemName")

    // suspend fun updateItemCount(itemName: Int,itemCount: Int)
//    @Transaction
//    @Query ("Select listOfItems From")
//    fun insertItem() =
//    @Query("Update ShopEntity Insert Into listOfItems  = :item Where shopName=:shopName")
    //    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    @Query ("Update ShopEntity Set listOfItems=:itemList Where shopName=:shopName")
//    suspend fun insertItem(shopName:String ,itemList: List<ItemData>
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertShop( shop: ShopEntity)
//    @Update
//    suspend fun updateShop( shop : ShopEntity)
//    @Delete
//    suspend fun deleteShop( shop: ShopEntity )
//    @Query("Select * From ShopEntity")
//   suspend  fun getAll(): MutableList<ShopEntity>
//   @Query ("Select item From ShopEntity ")
//   suspend fun getItems():MutableList<ItemData>
//    @Query (" Insert Into ShopEntity (shop,item) Values (:shop,:itemData)")
//    suspend fun insertItem(shop:ShopData,itemData: ItemData)
//    @Query ("Update ShopEntity Set item=:itemData Where shop=:shop")
//    suspend fun updateItem(shop:ShopData,itemData: ItemData)
//    @Query ("Delete From ShopEntity Where shop=:shop AND item=:itemData")
//    suspend fun deleteItem(shop:ShopData,itemData: ItemData)


//    @Query ("Update  ShopEntity set listOfItems = (:itemList) Where shop=:shop")
//    suspend fun updateListItem(shop:ShopData, itemList: MutableList<ItemData>)
//    @Query ("Select listOfItems From ShopEntity Where shop=:shop ")
//     suspend fun getList(shop:ShopData):MutableList<ItemData>
//    @Query("Delete From ShopEntity Where listOfItems =:items")
//   suspend fun deleteList(items:MutableList<ItemData>)
}
