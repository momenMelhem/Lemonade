package com.example.lemonade.roomDb

import android.content.res.Resources
import android.os.Parcel
import android.os.Parcelable
import android.util.Log
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parceler
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

//@Parcelize
@Serializable
@Entity
data class ItemData(

     val itemImage:Int,
     val itemDescription:Int,
     val itemPrice:Double,
     var itemCount:Int,
     @PrimaryKey (autoGenerate = false)
     val itemName: Int
)
//{
////    constructor(parcel: Parcel) : this(
////        parcel.readInt(),
////        parcel.readInt(),
////        parcel.readDouble(),
////        parcel.readInt(),
////        parcel.readInt()
////    )
//
//
////    override fun writeToParcel(parcel: Parcel, flags: Int) {
////        parcel.writeInt(itemImage)
////        parcel.writeInt(itemDescription)
////        parcel.writeDouble(itemPrice)
////        parcel.writeInt(itemCount)
////        parcel.writeInt(itemName)
////    }
//
////    override fun describeContents(): Int {
////        return 0
////    }
////    fun toParcel(): Parcel {
////        val parcel = Parcel.obtain()
////        writeToParcel(parcel, 0)
////        parcel.setDataPosition(0) // Reset the position for reading
////        return parcel
////    }
////        companion object : Parceler<ItemData> {
//////        override fun createFromParcel(parcel: Parcel): ItemData {
//////            return ItemData(parcel)
//////        }
////
////            override fun create(parcel: Parcel): ItemData {
////
////                return ItemData(
////                    parcel.readInt(),
////                    parcel.readInt(),
////                    parcel.readDouble(),
////                    parcel.readInt(),
////                    parcel.readInt()
////                )
////            }
////
//////            override fun newArray(size: Int): Array<ItemData?> {
//////            return arrayOfNulls(size)
//////        }
////
////            override fun ItemData.write(parcel: Parcel, flags: Int) {
////            parcel.writeInt(itemImage)
////            parcel.writeInt(itemDescription)
////            parcel.writeDouble(itemPrice)
////            parcel.writeInt(itemCount)
////            parcel.writeInt(itemName)
////            }
////
//////            fun fromParcel(parcel: Parcel): ItemData {
//////            return ItemData(parcel)
//////        }
//////
////        fun fromString(serializedItemData: String?): ItemData {
////            val parcel = Parcel.obtain()
////            parcel.writeString(serializedItemData)
////            parcel.setDataPosition(0)
////            return ItemData.create(parcel)
////        }
////    }
////}
//
////{
////    override fun toString(): String {
////
////    return "$itemImage,$itemDescription,$itemPrice,$itemCount,$itemName"
////}
////
//  companion object {
//
////
////        fun fromString(value: String): ItemData? {
////
////            val parts = value.split(',')
////
////
////            Log.d("parts.toString()",parts.toString())
////            if (parts.size>4) {
////                val itemData = ItemData(
////                    itemImage = parts[0].toIntOrNull() ?: 0,
////                    itemDescription = parts[1].toIntOrNull() ?: 0,
////                    itemPrice = parts[2].toDoubleOrNull() ?: 0.0,
////                    itemCount = parts[3].toIntOrNull() ?: 0,
////                    itemName = parts[4].toIntOrNull() ?: 0
////                )
////                return itemData
////            }
////             return ItemData(0,0,0.0,0,0)
////        }
//   }
//}


