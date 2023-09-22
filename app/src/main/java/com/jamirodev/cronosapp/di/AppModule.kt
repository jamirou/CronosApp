package com.jamirodev.cronosapp.di

import android.content.Context
import androidx.room.Room
import com.jamirodev.cronosapp.room.CronosDataBase
import com.jamirodev.cronosapp.room.CronosDataBaseDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providesCronosDao(cronoDataBase: CronosDataBase) : CronosDataBaseDao {
        return cronoDataBase.cronosDao()
    }

    @Singleton
    @Provides
    fun providesCronosDataBase(@ApplicationContext context: Context): CronosDataBase {
        return Room.databaseBuilder(
            context,
            CronosDataBase::class.java, "cronos_db"
        ).fallbackToDestructiveMigration()
            .build()
    }

}