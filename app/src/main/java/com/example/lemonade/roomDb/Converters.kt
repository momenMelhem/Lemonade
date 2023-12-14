package com.example.lemonade.roomDb


import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.example.lemonade.home.ShopData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class Converters {

//    val bundle = Bundle()
//    @TypeConverter
//    fun listToJson(itemList:MutableList<ItemData>?) : String = Gson().toJson(itemList)
//    @TypeConverter
//    fun jsonToList(value:String):MutableList<ItemData>? =
//        Gson().fromJson<MutableList<ItemData>?>(value,object :TypeToken<MutableList<ItemData>>(){}.type).toMutableList()

    @TypeConverter
    fun shopToJson(shop: ShopData?) : String = Gson().toJson(shop)

    @TypeConverter
    fun jsonToShop (value: String?): ShopData? = Gson().fromJson(value,object : TypeToken<ShopData>(){}.type)
    @TypeConverter
    fun itemToJson(item: ItemData?) : String = Gson().toJson(item)

    @TypeConverter
    fun jsonToItem (value: String?): ItemData? = Gson().fromJson(value,object : TypeToken<ItemData>(){}.type)

//    @TypeConverter
//    fun itemToString(item: ItemData) : String {
//
//    return Json.encodeToString(item)
//    }
//    @TypeConverter
//    fun stringToItem (value: String): ItemData  {
//
//       return  Json.decodeFromString<ItemData>(value)
//
//    }

}