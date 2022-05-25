package com.example.doggyapp.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Favorite(val url: String) {
    @PrimaryKey(autoGenerate = true)
    var id: Long? = null
}
