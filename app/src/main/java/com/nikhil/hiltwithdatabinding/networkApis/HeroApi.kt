package com.nikhil.hiltwithdatabinding.networkApis

import com.nikhil.hiltwithdatabinding.constants.Constants
import com.nikhil.hiltwithdatabinding.responses.HeroResponse
import retrofit2.Response
import retrofit2.http.GET


interface HeroApi {

    @GET(Constants.HERO)
    suspend fun getHero(): Response<List<HeroResponse>>

}