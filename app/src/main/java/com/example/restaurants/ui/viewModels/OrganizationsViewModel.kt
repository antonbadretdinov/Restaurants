package com.example.restaurants.ui.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.restaurants.data.remote.model.organizations.OrganizationsDataModel
import com.example.restaurants.data.remote.repository.OrganizationsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrganizationsViewModel @Inject constructor(
    private val organizationsRepository: OrganizationsRepository
) : ViewModel() {

    private val mutableOrganizationsFlow = MutableStateFlow(OrganizationsDataModel(emptyList()))
    val organizationsFlow: StateFlow<OrganizationsDataModel> =
        mutableOrganizationsFlow.asStateFlow()

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        println(throwable.message)
    }

    fun getOrganizationsList() {
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            mutableOrganizationsFlow.value = organizationsRepository.getOrganizationsList()
        }
    }

    fun addToFavoriteById(id: Int){
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            organizationsRepository.addToFavoriteById(id)
        }
    }

    fun deleteFromFavoriteById(id: Int){
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            organizationsRepository.deleteFromFavoriteById(id)
        }
    }
}