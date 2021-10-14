package com.assessment.data.di

import com.assessment.data.repository.AlbumsRepositoryImpl
import com.assessment.data.repository.PhotosRepositoryImpl
import com.assessment.data.repository.UserRepositoryImpl
import com.assessment.domain.repository.AlbumsRepository
import com.assessment.domain.repository.PhotosRepository
import com.assessment.domain.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoriesModules {

    @Binds
    @Singleton
    abstract fun provideUserRepository(userRepoImpl: UserRepositoryImpl): UserRepository

    @Binds
    @Singleton
    abstract fun provideAlbumsRepository(albumsRepoImpl: AlbumsRepositoryImpl): AlbumsRepository

    @Binds
    @Singleton
    abstract fun providePhotosRepository(photoRepoImpl: PhotosRepositoryImpl): PhotosRepository

}