package com.example.doggyapp.activities.favoritesDisplay

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.bumptech.glide.Glide
import com.example.doggyapp.R
import com.example.doggyapp.database.AppDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

open class FavoritesDisplayAdapter(private val dogImages: MutableList<String>) : RecyclerView.Adapter<FavoritesDisplayAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val dogImage: ImageView = itemView.findViewById(R.id.iv_fav_dog)
        val removeButton: Button = itemView.findViewById(R.id.btn_remove)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.favorites_display_row,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dogImages.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dog = dogImages[position]
        Glide.with(holder.itemView.context)
            .load(dog)
            .into(holder.dogImage)


        val db = Room.databaseBuilder(
            holder.itemView.context.applicationContext,
            AppDatabase::class.java, "db"
        ).build()

        holder.removeButton.setOnClickListener {

            CoroutineScope(Dispatchers.IO).launch {
                db.favoriteDao().removeFavorite(dog.toString())

                withContext(Dispatchers.Main) {
                    dogImages.removeAt(position)
                    notifyItemRemoved(position)
                    notifyItemRangeChanged(position, itemCount)

                    Toast.makeText(
                        holder.itemView.context,
                        "Removed from Favorites",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}
