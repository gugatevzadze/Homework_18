package com.example.homework_18.user

import android.util.Log
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.homework_18.BaseFragment
import com.example.homework_18.databinding.FragmentMainBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainFragment : BaseFragment<FragmentMainBinding>(FragmentMainBinding::inflate) {
    private val viewModel: UserViewModel by viewModels()
    private val adapter = UserAdapter()

    override fun setUp() {
        recyclerViewSetup()
    }

    override fun onClickListeners() {
    }

    override fun bindObservers() {
        observingData()
    }
    //function to setup recyclerView
    private fun recyclerViewSetup() {
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }
    //function to observe data
    private fun observingData() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                try {
                    viewModel.getUsers(viewLifecycleOwner.lifecycleScope).collectLatest { pagingData ->
                        adapter.submitData(pagingData)
                    }
                } catch (e: Exception) {
                    Log.e("MainFragment", "bindObservers: ${e.message}")
                }
            }
        }
    }
}