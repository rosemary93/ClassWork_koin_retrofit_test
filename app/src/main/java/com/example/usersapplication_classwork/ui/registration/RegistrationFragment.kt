package com.example.usersapplication_classwork.ui.registration

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.usersapplication_classwork.R
import com.example.usersapplication_classwork.databinding.FragmentRegistrationBinding
import com.example.usersapplication_classwork.model.User
import org.koin.androidx.viewmodel.ext.android.viewModel


class RegistrationFragment : Fragment() {
    val registrationViewModel: RegistrationViewModel by viewModel()
    lateinit var binding: FragmentRegistrationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegistrationBinding.inflate(
            inflater,
            container,
            false
        )        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var id = ""
       /* registrationViewModel.userLiveData.observe(viewLifecycleOwner) {
            id = it.id
        }*/

        binding.btnRegister.setOnClickListener {
            if (areValidInputs()) {
                val user = User("0", binding.etName.text.toString(), binding.etPassword.text.toString())
                registrationViewModel.register(user)
                registrationViewModel.userLiveData.observe(viewLifecycleOwner){
                    Toast.makeText(
                        requireContext(),
                        "contact saved, id= ${it.id}",
                        Toast.LENGTH_SHORT
                    ).show()
                    findNavController().navigate(R.id.action_registrationFragment_to_homeFragment)
                }


            }
        }

        binding.btnLogin.setOnClickListener {
            findNavController().navigate(R.id.action_registrationFragment_to_loginFragment)

        }

    }

    fun areValidInputs(): Boolean {
        if (binding.etName.text.isNullOrBlank()) {
            binding.etName.error = "fill here"
            return false
        }

        if (binding.etPassword.text.isNullOrBlank()) {
            binding.etPassword.error = "fill here"
            return false
        }

        if (binding.etPassword.text.length < 6) {
            binding.etPassword.error = "password has at least 6 character"
            return false
        }
        return true
    }


}