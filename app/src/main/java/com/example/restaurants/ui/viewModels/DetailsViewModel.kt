package com.example.restaurants.ui.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.restaurants.data.remote.model.details.DetailsModel
import com.example.restaurants.data.remote.repository.DetailsRepository
import com.example.restaurants.helpers.DEFAULT_ID_LOADING
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val detailsRepository: DetailsRepository
) : ViewModel() {

    private val mutableDetailsFlow = MutableStateFlow(
        DetailsModel(
            id = DEFAULT_ID_LOADING,
            name = "",
            photos = emptyList(),
            rate = 0.0,
            isFavorite = false,
            averageCheck = emptyList(),
            detailedInfo = ""
        )
    )
    val detailsFlow: StateFlow<DetailsModel> = mutableDetailsFlow.asStateFlow()

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        println(throwable.message)
    }

    fun getDetailsById(id: Int) {
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            mutableDetailsFlow.value = detailsRepository.getDetailsById(id)
        }
    }

    fun addToFavoriteById(id: Int) {
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            detailsRepository.addToFavoriteById(id)
        }
    }

    fun deleteFromFavoriteById(id: Int) {
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            detailsRepository.deleteFromFavoriteById(id)
        }
    }
}