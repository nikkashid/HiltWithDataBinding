package com.nikhil.hiltwithdatabinding.dependencyInjection

import com.nikhil.hiltwithdatabinding.networkApis.HeroApi
import com.nikhil.hiltwithdatabinding.repositories.HerosRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object HeroRepositoryModule {

    @Provides
    @Singleton
    fun provideHeroRepository(heroApi: HeroApi): HerosRepository {
        return HerosRepository(heroApi)
    }
}