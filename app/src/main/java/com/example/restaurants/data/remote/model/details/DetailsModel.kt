package com.example.restaurants.data.remote.model.details

data class DetailsModel(
    val id: Int,
    val name: String,
    val photos: List<String>,
    val rate: Double,
    val isFavorite: Boolean,
    val averageCheck: List<Double>,
    val detailedInfo: String
)