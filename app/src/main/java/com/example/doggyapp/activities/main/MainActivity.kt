package com.example.doggyapp.activities.main

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.doggyapp.DogApi
import com.example.doggyapp.databinding.ActivityMainBinding
import com.example.doggyapp.activities.favoritesDisplay.FavoritesDisplay
import com.example.doggyapp.models.BreedsAndSubBreeds
import com.example.doggyapp.models.Dog
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Named

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    @Inject
    @Named("provideDogApi")
    lateinit var api: DogApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnShowFavs.setOnClickListener {
            val intent = Intent(this, FavoritesDisplay::class.java)
            startActivity(intent)
        }

        GlobalScope.launch(Dispatchers.IO){
            try {
                val response = api.fetchAllBreedsAndSubBreeds()
                if(response.isSuccessful) {
                    withContext(Dispatchers.Main) {
                        handleData(response.body()!!)
                    }
                }
            } catch(e: Throwable) {

                withContext(Dispatchers.Main) {
                    Toast.makeText(applicationContext, e.message, Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun handleData(breeds: BreedsAndSubBreeds){
        val obj = breeds.message
        val set = obj.keySet()
        val doggies = ArrayList<Dog>()

        for(mainBreedName in set) {
            if(obj.getAsJsonArray(mainBreedName).size() > 0) {
                val arr = obj.getAsJsonArray(mainBreedName)
//
                for(subBreedName in arr){
                    if("${subBreedName.asString} $mainBreedName".contains("shepherd australian")){

                        doggies.add(Dog("$mainBreedName ${subBreedName.asString}"))
                    } else {
                        doggies.add(Dog("${subBreedName.asString} $mainBreedName"))
                    }
                }
            } else {
                doggies.add(Dog(mainBreedName))
            }
        }

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = DogListAdapter(doggies)
        }

    }
}