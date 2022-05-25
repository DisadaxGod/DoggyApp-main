package com.example.doggyapp.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface FavoriteDao {
    @Query("SELECT * from Favorite")
    fun getAllFavorites(): List<Favorite>

    @Insert
    fun addFavorite(favorite: Favorite)

    @Query("DELETE FROM favorite WHERE url = :favorite")
    fun removeFavorite(favorite: String)
}