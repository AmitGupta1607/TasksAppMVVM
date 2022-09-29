package com.example.tasksapp

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shortsapp.data.localDatabase.TaskItem
import com.example.tasksapp.common.Resource
import com.example.tasksapp.databinding.ActivityMainBinding
import com.example.tasksapp.presentation.BottomSheetCallback
import com.example.tasksapp.presentation.TaskAddItemBottomSheet
import com.example.tasksapp.presentation.TasksListAdapter
import com.example.tasksapp.presentation.TasksListViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.ArrayList

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: TasksListViewModel

    private lateinit var binding: ActivityMainBinding

    private var itemsToShow = ArrayList<TaskItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(TasksListViewModel::class.java)

        viewModel.liveDataTasks.observe(this, {
            when (it) {
                is Resource.Success -> {
                    showItems(it.data)
                }
                is Resource.Error -> {
                    showError()
                }

                is Resource.Loading -> {
                    showLoading()
                }
            }
        })

        binding.fab.setOnClickListener {
            openBottomSheet()
        }

    }

    private fun openBottomSheet() {
        val bottomSheet = TaskAddItemBottomSheet()
        bottomSheet.show(supportFragmentManager, "BOTTOM_SHEET")
        bottomSheet.setCallback(object : BottomSheetCallback {
            override fun onItemAdded() {
                viewModel.fetchAllTasks()
            }

        })

    }

    private fun showItems(items: List<TaskItem>?) {
        items?.let {
            if (it.isNotEmpty()) {
                val adapter = TasksListAdapter(it)
                itemsToShow = items as ArrayList<TaskItem>
                binding.recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
                binding.recyclerView.adapter = adapter
                val itemTouchHelper = ItemTouchHelper(ItemTouchHelperRecycleview())
                itemTouchHelper.attachToRecyclerView(binding.recyclerView)
                binding.tvEmptyText.visibility = View.GONE
            } else {
                binding.tvEmptyText.visibility = View.VISIBLE
            }
        }

    }


    inner class ItemTouchHelperRecycleview() :
        ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT) {

        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            return false
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            val position = viewHolder.adapterPosition
            onSwipeAction(position)
            Toast.makeText(this@MainActivity, "Item Removed", Toast.LENGTH_SHORT).show()
        }

    }

    fun onSwipeAction(position: Int) {
        viewModel.deleteTask(itemsToShow[position])
        binding.recyclerView.adapter?.notifyItemRemoved(position)
        itemsToShow.removeAt(position)
        updateEmptyView(itemsToShow)
    }

    fun updateEmptyView(items: ArrayList<TaskItem>) {
        if (items.size == 0) {
            binding.tvEmptyText.visibility = View.VISIBLE
        } else {
            binding.tvEmptyText.visibility = View.GONE
        }
    }


    private fun showError() {

    }

    private fun showLoading() {

    }
}