package com.example.doggyapp.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Favorite::class], version = 3)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favoriteDao(): FavoriteDao
}