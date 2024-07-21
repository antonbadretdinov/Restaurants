package com.example.restaurants.data.remote.model.organizations

import com.google.gson.annotations.SerializedName

data class OrganizationsDataModel(
    @SerializedName("data")
    val organizationsData: List<OrganizationModel>,
)