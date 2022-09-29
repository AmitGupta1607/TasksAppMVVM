package com.example.shortsapp.data.localDatabase

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface TasksListDao{

    @Query("SELECT * from taskitem")
    suspend fun fetchAllTasks():List<TaskItem>

    @Insert
    suspend fun insertTask(taskItem: TaskItem)

    @Delete
    suspend fun deleteItem(taskItem: TaskItem)


}