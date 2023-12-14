package com.example.lemonade.roomDb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters


@Database(entities =[ItemData::class], version = 5 , exportSchema = false)
@TypeConverters (Converters::class)
abstract class AppDataBase :RoomDatabase(){
    abstract fun itemDao(): ItemDao

    companion object {
        private fun buildAppDataBase(context: Context): AppDataBase {

            return Room.databaseBuilder(
                context,
                AppDataBase::class.java, "database"
            ).fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build()
        }
        @Volatile
        private var INSTANCE : AppDataBase?= null

       fun getDatabaseInstance (context: Context): AppDataBase {
           if(INSTANCE ==null){
               synchronized(this){
                 INSTANCE = buildAppDataBase(context)
               }

           }
           return INSTANCE!!
       }
    }

}

