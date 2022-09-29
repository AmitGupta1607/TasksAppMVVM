package com.example.shortsapp.data.localDatabase

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TaskItem(
    @PrimaryKey(autoGenerate =true) val id: Int=0,
    val name: String,
    val subtitle: String,
    val isTaskCompleted: Boolean = false
)