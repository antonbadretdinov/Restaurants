package com.example.restaurants.data.remote.model.organizations

data class OrganizationModel(
    val id: Int,
    val name: String,
    val photo: String,
    val rate: Double,
    val cuisines: List<String>,
    val isFavorite: Boolean,
    val averageCheck: List<Double>
)