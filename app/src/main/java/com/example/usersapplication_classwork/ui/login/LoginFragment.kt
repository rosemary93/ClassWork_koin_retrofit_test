package com.example.usersapplication_classwork.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.usersapplication_classwork.R
import com.example.usersapplication_classwork.databinding.FragmentLoginBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class LoginFragment : Fragment() {
    val loginViewModel: LoginViewModel by viewModel()
    lateinit var binding: FragmentLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnLoginLg.setOnClickListener {
            if (areValidInputs()) {
                loginViewModel.login(
                    binding.etId.text.toString(),
                    binding.etPasswordLg.text.toString()
                )
            }
        }

        loginViewModel.loginSuccess.observe(viewLifecycleOwner) {
            if (it) {
                findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
            } else {
                Toast.makeText(requireContext(), "login failed", Toast.LENGTH_SHORT).show()
            }
        }


    }

    fun areValidInputs(): Boolean {
        if (binding.etId.text.isNullOrBlank()) {
            binding.etId.error = "fill here"
            return false
        }
        if (binding.etPasswordLg.text.isNullOrBlank()) {
            binding.etPasswordLg.error = "fill here"
            return false
        }
        return true
    }


}