package uz.gita.drinkwater.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.gita.drinkwater.domain.AppRepository
import uz.gita.drinkwater.domain.impl.AppRepositoryImpl
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModel {

    @Binds
    @Singleton
    abstract fun getAppRepository(appRepositoryImpl : AppRepositoryImpl) : AppRepository
}