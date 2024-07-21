package com.example.restaurants.data.remote.retrofit

import com.example.restaurants.data.remote.model.details.DetailsModel
import com.example.restaurants.helpers.AUTH_TOKEN
import com.example.restaurants.helpers.ID_KEY
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path

interface DetailsApi {

    @Headers("Authorization: Token $AUTH_TOKEN")
    @GET("organization/{$ID_KEY}")
    suspend fun getDetailsById(
        @Path(ID_KEY) id: Int
    ): DetailsModel

    @Headers("Authorization: Token $AUTH_TOKEN")
    @POST("organization/{$ID_KEY}/favorite/")
    suspend fun addToFavoriteById(
        @Path(ID_KEY) id: Int
    )

    @Headers("Authorization: Token $AUTH_TOKEN")
    @DELETE("organization/{$ID_KEY}/favorite/")
    suspend fun deleteFromFavoriteById(
        @Path(ID_KEY) id: Int
    )
}