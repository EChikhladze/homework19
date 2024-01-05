package com.example.homework18.presentation.userDetails

import android.util.Log.d
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.homework18.data.Resource
import com.example.homework18.data.User
import com.example.homework18.databinding.FragmentUserDetailsBinding
import com.example.homework18.presentation.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class UserDetailsFragment :
    BaseFragment<FragmentUserDetailsBinding>(FragmentUserDetailsBinding::inflate) {
    private val viewModel: UserDetailsFragmentViewModel by viewModels()
    private val args: UserDetailsFragmentArgs by navArgs()

    override fun setUp() {
        viewModel.getUserDetails(args.id)
    }

    override fun bindObserves() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.responseFlow.collect {
                    when (it) {
                        is Resource.Success -> {
                            displayData(it.data)
                        }

                        is Resource.Error -> {
                            Toast.makeText(
                                requireContext(),
                                "error: ${it.errorMessage}",
                                Toast.LENGTH_SHORT
                            ).show()
                            d("details", it.errorMessage)
                        }

                        is Resource.Loading -> {}
                    }
                }
            }
        }
    }

    private fun displayData(user: User) {
        with(binding) {
            tvFirstName.text = user.firstName
            tvLastName.text = user.lastName
            tvEmail.text = user.email
            Glide.with(requireContext()).load(user.avatar).into(imgAvatar)
        }
    }
}