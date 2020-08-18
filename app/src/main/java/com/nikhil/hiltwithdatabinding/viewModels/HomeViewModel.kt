package com.nikhil.hiltwithdatabinding.viewModels

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nikhil.hiltwithdatabinding.constants.Constants
import com.nikhil.hiltwithdatabinding.repositories.HerosRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class HomeViewModel @ViewModelInject constructor(private val herosRepository: HerosRepository) :
    ViewModel() {

    private val serverResponse = MutableLiveData<Any?>()

    companion object {
        private const val TAG = "HomeViewModel"
    }

    suspend fun getHeros(): Any? {
        return herosRepository.getHeros()
    }

    fun onButtonClick() {
        Log.d(TAG, "onButtonClick: Button Click Called.")

        GlobalScope.launch(Dispatchers.IO) {
            setResponse(Constants.NETWORK_HIT_INITIATED)
            val response: Any? = getHeros()
            setResponse(response)
        }

    }

    private fun setResponse(response: Any?) {
        serverResponse.postValue(response)
    }

    fun observeResponse(): MutableLiveData<Any?> {
        return serverResponse
    }

}