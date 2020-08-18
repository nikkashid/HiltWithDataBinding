package com.nikhil.hiltwithdatabinding.networkApis

import com.nikhil.hiltwithdatabinding.BuildConfig
import com.nikhil.hiltwithdatabinding.responses.HeroResponse
import retrofit2.Response
import retrofit2.http.GET


interface HeroApi {

    @GET(BuildConfig.HERO)
    suspend fun getHero(): Response<List<HeroResponse>>

}