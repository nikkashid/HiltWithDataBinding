package com.nikhil.hiltwithdatabinding.repositories

import com.nikhil.hiltwithdatabinding.constants.Constants
import com.nikhil.hiltwithdatabinding.networkApis.HeroApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class HerosRepository @Inject constructor(private val heroApi: HeroApi) {

    private val TAG = "HerosRepository"

    suspend fun getHeros(): Any? {

        return withContext(Dispatchers.IO) {

            val response = heroApi.getHero()

            if (response.isSuccessful) {
                return@withContext response.body()
            } else {
                return@withContext Constants.ERROR_MSG
            }
        }
    }

}