package com.example.doggyapp

import com.example.doggyapp.models.BreedsAndSubBreeds
import com.example.doggyapp.models.DogBreeds
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface DogApi {
    @GET("breeds/list/all")
    suspend fun fetchAllBreedsAndSubBreeds(): Response<BreedsAndSubBreeds>

    @GET("breed/{selectedBreed}/images")
    suspend fun fetchAllImages(
        @Path(value = "selectedBreed", encoded = true) selectedBreed: String
    ): Response<DogBreeds>
}
