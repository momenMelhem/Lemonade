package com.example.lemonade.utils

import android.content.Context
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Entity
data class Juice(
    var juiceName:Int?,
    @PrimaryKey (autoGenerate = true) val id: Int

    )
@Dao
interface JuiceDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
   fun insertJuice(vararg juice: Juice)
    @Update
    fun updateJuice(vararg juice : Juice)
    @Delete
     fun deleteJuice(vararg juice : Juice)
    @Query ("Select * From Juice")
    fun getAll():MutableList<Juice>
}

@Database(entities =[Juice::class], version = 1, exportSchema = false)
abstract class AppDataBase():RoomDatabase(){
    abstract fun juiceDao(): JuiceDao

    companion object {
        private fun buildAppDataBase(context: Context): AppDataBase {

            return Room.databaseBuilder(
                context,
                AppDataBase::class.java, "database"
            ).allowMainThreadQueries().build()
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

