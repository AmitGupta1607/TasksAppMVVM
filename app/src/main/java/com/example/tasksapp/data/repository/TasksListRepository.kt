package com.example.tasksapp.data.repository

import com.example.shortsapp.data.localDatabase.TaskItem
import com.example.shortsapp.data.localDatabase.TasksListDao
import kotlinx.coroutines.flow.Flow

import javax.inject.Inject

class TasksListRepository @Inject constructor(private val roomDao: TasksListDao) {

    suspend fun addTask(task:TaskItem){
        roomDao.insertTask(task)
    }

    suspend fun getAllTasks():List<TaskItem>{
        return roomDao.fetchAllTasks()
    }

    suspend fun deleteTask(task: TaskItem){
        roomDao.deleteItem(task)
    }
}