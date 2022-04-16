package com.example.bip.presentation.ui.offers.client

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DatingHomeViewModel : ViewModel() {
    private val _albumLiveData = MutableLiveData<MutableList<People>>()
    val albumLiveData: LiveData<MutableList<People>> = _albumLiveData

    init {
        getAlbums()
    }

    private fun getAlbums() {
        _albumLiveData.value = PeopleDataProvider.albums.take(3).toMutableList()
    }
}
