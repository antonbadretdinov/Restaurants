package com.example.restaurants.data.remote.retrofit

import com.example.restaurants.data.remote.model.organizations.OrganizationsDataModel
import com.example.restaurants.helpers.AUTH_TOKEN
import com.example.restaurants.helpers.ID_KEY
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path

interface OrganizationsApi {

    @Headers("Authorization: Token $AUTH_TOKEN")
    @GET("organizations/category/1/organizations/")
    suspend fun getOrganizationsList(): OrganizationsDataModel

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