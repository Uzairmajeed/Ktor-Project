package com.facebook.ktor_project

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class MyViewModel : ViewModel() {
    private val network = Network()

    // MutableLiveData to hold the Post data
    private val _postLiveData = MutableLiveData<Post?>()

    // Expose an immutable LiveData to be observed by the UI
    val postLiveData: LiveData<Post?> get() = _postLiveData

    suspend fun fetchFromModel() {
        val post = network.fetchData()
        _postLiveData.postValue(post)

    }

}
