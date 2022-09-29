package com.example.shortsapp.data.localDatabase

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [TaskItem::class], version = 1)
abstract class TasksDatabase :RoomDatabase(){
    abstract fun provideDao(): TasksListDao
}