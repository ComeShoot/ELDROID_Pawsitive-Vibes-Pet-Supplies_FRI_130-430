package com.project.pawsitivevibes.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.project.pawsitivevibes.R
import com.project.pawsitivevibes.model.ForgotPasswordRequest
import com.project.pawsitivevibes.network.ApiService
import com.project.pawsitivevibes.repository.ForgotPasswordRepository
import com.project.pawsitivevibes.viewmodel.ForgotPasswordViewModel
import com.project.pawsitivevibes.viewmodel.ForgotPasswordViewModelFactory
import kotlinx.coroutines.launch

class ResetPasswordFragment : Fragment() {

    private lateinit var newPasswordInput: EditText
    private lateinit var confirmPasswordInput: EditText
    private lateinit var resetPasswordButton: Button
    private lateinit var backButton: ImageButton
    private lateinit var viewModel: ForgotPasswordViewModel
    private var email: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = inflater.inflate(R.layout.fragment_reset_password, container, false)

        // Initialize UI components
        newPasswordInput = binding.findViewById(R.id.newPasswordEditText)
        confirmPasswordInput = binding.findViewById(R.id.confirmPasswordEditText)
        resetPasswordButton = binding.findViewById(R.id.resetButton)
        backButton = binding.findViewById(R.id.backBtn)

        // Retrieve the email passed from ForgotPasswordFragment
        email = arguments?.getString("email")
        if (email.isNullOrBlank()) {
            Toast.makeText(requireContext(), "Error: Email not provided!", Toast.LENGTH_SHORT).show()
            parentFragmentManager.popBackStack()
            return binding
        }

        // Initialize ViewModel
        val apiService = ApiService.create()
        val repository = ForgotPasswordRepository(apiService)
        viewModel = ViewModelProvider(
            this,
            ForgotPasswordViewModelFactory(repository)
        )[ForgotPasswordViewModel::class.java]

        // Set click listeners
        resetPasswordButton.setOnClickListener {
            val newPassword = newPasswordInput.text.toString()
            val confirmPassword = confirmPasswordInput.text.toString()

            if (validateInputs(newPassword, confirmPassword)) {
                lifecycleScope.launch {
                    val request = ForgotPasswordRequest(
                        cust_email = email!!,
                        new_password = newPassword,
                        new_password_confirmation = confirmPassword
                    )
                    viewModel.resetPassword(request)
                }
            }
        }

        viewModel.passwordResetResponse.observe(viewLifecycleOwner) { response ->
            if (response.isSuccessful) {
                Toast.makeText(requireContext(), "Password reset successful!", Toast.LENGTH_SHORT).show()
                // Navigate to LoginActivity
                val intent = Intent(requireContext(), LoginActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            } else {
                Toast.makeText(requireContext(), "Error resetting password", Toast.LENGTH_SHORT).show()
            }
        }

        backButton.setOnClickListener {
            // Navigate back to the ForgotPasswordFragment
            parentFragmentManager.popBackStack()
        }

        return binding
    }

    /**
     * Validates the input fields for resetting the password.
     * @param newPassword The new password entered by the user.
     * @param confirmPassword The confirmation password entered by the user.
     * @return True if the inputs are valid; false otherwise.
     */
    private fun validateInputs(newPassword: String, confirmPassword: String): Boolean {
        return when {
            newPassword.isBlank() -> {
                Toast.makeText(requireContext(), "New password cannot be empty", Toast.LENGTH_SHORT).show()
                false
            }
            confirmPassword.isBlank() -> {
                Toast.makeText(requireContext(), "Confirm password cannot be empty", Toast.LENGTH_SHORT).show()
                false
            }
            newPassword != confirmPassword -> {
                Toast.makeText(requireContext(), "Passwords do not match", Toast.LENGTH_SHORT).show()
                false
            }
            else -> true
        }
    }
}
