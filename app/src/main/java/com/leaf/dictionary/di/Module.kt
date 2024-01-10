package com.leaf.dictionary.di

import android.content.Context
import com.leaf.dictionary.data.AppDataBase
import com.leaf.dictionary.data.dao.DictDao
import com.leaf.dictionary.data.repository.mainRepository.MainRepository
import com.leaf.dictionary.data.repository.mainRepository.MainRepositoryImp
import com.leaf.dictionary.data.repository.translatedRepository.TranslatedRepository
import com.leaf.dictionary.data.repository.translatedRepository.TranslatedRepositoryImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {

    @Provides
    @Singleton
    fun provideDataBase(@ApplicationContext context: Context) = AppDataBase.getData(context).dicDao()

    @Provides
    @Singleton
    fun provideMainRepository(dataBase: DictDao): MainRepository = MainRepositoryImp(dataBase)

    @Provides
    @Singleton
    fun provideTranslatedRepository(dataBase: DictDao): TranslatedRepository = TranslatedRepositoryImp(dataBase)

}