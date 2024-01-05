package com.example.homework18.presentation.usersList

import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.homework18.data.Resource
import com.example.homework18.data.User
import com.example.homework18.databinding.FragmentUsersListBinding
import com.example.homework18.presentation.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class UsersListFragment :
    BaseFragment<FragmentUsersListBinding>(FragmentUsersListBinding::inflate) {
    private val viewModel: UsersListFragmentViewModel by viewModels()
    private lateinit var usersListAdapter: UsersRecyclerAdapter

    override fun setUp() {
    }

    private fun setRecycler(data: List<User>) {
        with(binding.recyclerUsers) {
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            setHasFixedSize(true)
            usersListAdapter = UsersRecyclerAdapter(
                data
            ) { id ->
                val action =
                    UsersListFragmentDirections.actionUsersListFragmentToUserDetailsFragment(id)
                findNavController().navigate(action)
            }
            adapter = usersListAdapter
        }
    }

    override fun bindObserves() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.responseFlow.collect {
                    when (it) {
                        is Resource.Success -> {
                            setRecycler(it.data)
                        }

                        is Resource.Error -> {
                            Toast.makeText(
                                requireContext(),
                                "error: ${it.errorMessage}",
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                        is Resource.Loading -> {}
                    }
                }
            }
        }
    }
}