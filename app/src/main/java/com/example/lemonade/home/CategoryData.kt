package com.example.lemonade.home

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class CategoryData(
  @DrawableRes val icon:Int,
   @StringRes val label:Int,
   val state:Int
)
