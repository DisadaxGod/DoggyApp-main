package com.example.doggyapp.activities.favoritesDisplay

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.doggyapp.R
import com.example.doggyapp.database.AppDatabase
import com.google.android.material.floatingactionbutton.FloatingActionButton
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

@AndroidEntryPoint
class FavoritesDisplay : AppCompatActivity() {
    @Inject
    @Named("provideAppDatabase")
    lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorites_display)

        val actionbar = supportActionBar
        actionbar!!.title = "Current Favorites"

        actionbar.setDisplayHomeAsUpEnabled(true)




        val recyclerView = findViewById<RecyclerView>(R.id.rv_favs_display)

        val dogImages = ArrayList<String>()

        CoroutineScope(Dispatchers.IO).launch {
            val favArr = db.favoriteDao().getAllFavorites()

            for(i in favArr){
                dogImages.add(i.url)
            }
        }

        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@FavoritesDisplay)
            adapter = FavoritesDisplayAdapter(dogImages)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}