package com.example.tasksapp.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.shortsapp.data.localDatabase.TaskItem
import com.example.tasksapp.R
import com.example.tasksapp.databinding.ItemTaskBinding

class TasksListAdapter(private val items: List<TaskItem>) :
    RecyclerView.Adapter<TasksListAdapter.TasksListViewHolder>() {

    class TasksListViewHolder(val binding: ItemTaskBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TasksListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemTaskBinding.inflate(inflater, parent, false)
        return TasksListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TasksListViewHolder, position: Int) {
        holder.binding.tvTitle.text = items[position].name
        holder.binding.tvSubtitle.text = items[position].subtitle
    }

    override fun getItemCount(): Int {
        return items.size
    }
}