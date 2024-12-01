package com.project.pawsitivevibes.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.project.pawsitivevibes.R
import com.project.pawsitivevibes.model.ForgotPasswordRequest
import com.project.pawsitivevibes.network.ApiService
import com.project.pawsitivevibes.repository.ForgotPasswordRepository
import com.project.pawsitivevibes.viewmodel.ForgotPasswordViewModel
import com.project.pawsitivevibes.viewmodel.ForgotPasswordViewModelFactory

class ForgotPasswordFragment : Fragment() {
    private lateinit var emailInput: EditText
    private lateinit var verifyEmailButton: Button
    private lateinit var viewModel: ForgotPasswordViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = inflater.inflate(R.layout.fragment_forgot_password, container, false)
        emailInput = binding.findViewById(R.id.forgotEmailEditText)
        verifyEmailButton = binding.findViewById(R.id.forgotEmailButton)

        val apiService = ApiService.create()
        val repository = ForgotPasswordRepository(apiService)
        viewModel = ViewModelProvider(
            this,
            ForgotPasswordViewModelFactory(repository)
        )[ForgotPasswordViewModel::class.java]

        verifyEmailButton.setOnClickListener {
            val email = emailInput.text.toString()
            if (email.isNotBlank()) {
                viewModel.verifyEmail(email)
            }
        }

        viewModel.emailVerificationResponse.observe(viewLifecycleOwner) { response ->
            if (response.isSuccessful) {
                val resetPasswordFragment = ResetPasswordFragment().apply {
                    arguments = Bundle().apply {
                        putString("email", emailInput.text.toString())
                    }
                }
                parentFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, resetPasswordFragment)
                    .addToBackStack(null)
                    .commit()
            } else {
                Toast.makeText(requireContext(), "Email not found", Toast.LENGTH_SHORT).show()
            }
        }


        return binding
    }
}

