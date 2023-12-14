package com.example.lemonade.roomDb

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.lemonade.home.ShopData

@Entity
data class ShopEntity (
    val shop : ShopData,
    @PrimaryKey(autoGenerate = false)
    var item:ItemData
)