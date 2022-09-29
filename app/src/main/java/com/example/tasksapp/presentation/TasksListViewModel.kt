package com.example.tasksapp.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shortsapp.data.localDatabase.TaskItem
import com.example.tasksapp.common.Resource
import com.example.tasksapp.data.repository.TasksListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.forEach
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class TasksListViewModel @Inject constructor(private val repository: TasksListRepository) :
    ViewModel() {

    private var _mutableLiveData = MutableLiveData<Resource<List<TaskItem>>>()
    val liveDataTasks: LiveData<Resource<List<TaskItem>>> = _mutableLiveData

    init {
        fetchAllTasks()
    }

    fun fetchAllTasks() {
        viewModelScope.launch {
            val tasks = repository.getAllTasks()
            _mutableLiveData.value = Resource.Success(tasks)
        }
    }

    fun addTask(title: String, subtitle: String): Boolean {
        var taskCompleted = false
        if (InputValidator.validateInput(title, subtitle)) {
            taskCompleted = true
            viewModelScope.launch {
                val taskItem =
                    TaskItem(name = title, subtitle = subtitle, isTaskCompleted = false)
                repository.addTask(taskItem)
            }
            taskCompleted = true
        }

        return taskCompleted
    }

    fun deleteTask(taskItem: TaskItem) {
        viewModelScope.launch {
            repository.deleteTask(taskItem)
        }
    }

}