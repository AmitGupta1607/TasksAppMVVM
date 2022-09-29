package com.example.tasksapp.presentation

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.content.DialogInterface.OnShowListener
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.example.tasksapp.R
import com.example.tasksapp.databinding.FragmentAddItemBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import androidx.core.content.ContextCompat.getSystemService








class TaskAddItemBottomSheet : BottomSheetDialogFragment() {
    lateinit var binding: FragmentAddItemBottomSheetBinding

    private var bottomSheetCallback: BottomSheetCallback? = null

    fun setCallback(callback: BottomSheetCallback) {
        bottomSheetCallback = callback
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddItemBottomSheetBinding.inflate(inflater, container, false)

        val viewModel = ViewModelProvider(requireActivity()).get(TasksListViewModel::class.java)

        val editTextTitle = binding.etTitle
        val editTextSubtitle = binding.etSubtitle

        binding.cardViewAdd.setOnClickListener {
            val result =
                viewModel.addTask(editTextTitle.text.toString(), editTextSubtitle.text.toString())
            if (!result) {
                showError()
            } else {
                showSuccess()
                bottomSheetCallback?.onItemAdded()
            }
        }

       showKeyBoard()

        return binding.root


    }

    fun showKeyBoard(){
        val inputMethodManager = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
        inputMethodManager?.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
    }

    private fun showError() {
        binding.textViewError.text = getString(R.string.error_input_message)
    }

    private fun showSuccess() {
        Toast.makeText(requireContext(), getString(R.string.message_success), Toast.LENGTH_LONG)
            .show()
        dismiss()
    }
}