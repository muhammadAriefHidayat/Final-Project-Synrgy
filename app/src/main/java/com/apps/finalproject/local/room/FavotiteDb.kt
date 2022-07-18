package com.apps.finalproject.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.apps.finalproject.local.entity.Favorite

@Database(entities = [Favorite::class], version = 1, exportSchema = false)
abstract class FavoriteDb : RoomDatabase(){
    abstract fun favoriteDao() : FavoriteDao

    companion object {
        @Volatile
        private var instance: FavoriteDb? = null
        fun getInstance(context: Context): FavoriteDb =
            instance ?: synchronized(this){
                instance ?: Room.databaseBuilder(
                    context.applicationContext,
                    FavoriteDb::class.java, "Favorite_Product.db"
                ).fallbackToDestructiveMigration().build().apply { instance = this }
            }
    }
}