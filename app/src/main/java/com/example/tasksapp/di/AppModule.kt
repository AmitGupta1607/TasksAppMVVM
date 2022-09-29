package com.example.tasksapp.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.shortsapp.data.localDatabase.TasksDatabase
import com.example.shortsapp.data.localDatabase.TasksListDao
import com.example.tasksapp.data.repository.TasksListRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class AppModule {

    @Provides
    fun providesRoomDatabaseModule(@ApplicationContext context: Context): TasksListDao {
        return Room.databaseBuilder(context, TasksDatabase::class.java, "Tasks_db").build()
            .provideDao()
    }

    @Provides
    fun providesRepository(dao: TasksListDao): TasksListRepository {
        return TasksListRepository(dao)
    }

}