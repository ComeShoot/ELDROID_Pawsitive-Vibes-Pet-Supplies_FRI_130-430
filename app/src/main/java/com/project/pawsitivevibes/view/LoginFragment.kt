package com.project.pawsitivevibes.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.project.pawsitivevibes.R
import com.project.pawsitivevibes.model.UserLogin
import com.project.pawsitivevibes.viewmodel.AuthViewModel
import com.project.pawsitivevibes.view.MainActivity  // Import MainActivity
import com.project.pawsitivevibes.viewmodel.SharedLoginViewModel

class LoginFragment : Fragment() {

    private val authViewModel: AuthViewModel by activityViewModels()
    private val sharedLoginViewModel: SharedLoginViewModel by activityViewModels()  // Get the shared ViewModel

    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var signUpButton: Button
    private lateinit var forgotPasswordButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = inflater.inflate(R.layout.fragment_login, container, false)

        // Initialize UI elements
        emailEditText = binding.findViewById(R.id.emailEditText)
        passwordEditText = binding.findViewById(R.id.passwordEditText)
        signUpButton = binding.findViewById(R.id.signupbutton)
        forgotPasswordButton = binding.findViewById(R.id.forgotBtn)

        // Navigate to Signup Screen
        signUpButton.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, SelectRoleFragment())
                .addToBackStack(null) // Add this fragment to the back stack
                .commit()
        }

        // Navigate to Forgot Password Screen
        forgotPasswordButton.setOnClickListener {
            val intent = Intent(requireContext(), ForgotPasswordActivity::class.java)
            startActivity(intent)
        }

        // Handle Login Button Click
        binding.findViewById<View>(R.id.loginButton).setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()

            if (validateInput(email, password)) {
                val user = UserLogin(cust_email = email, cust_password = password)
                authViewModel.loginUser(user)
            }
        }

        // Observe Login Status
        authViewModel.loginStatus.observe(viewLifecycleOwner, Observer { status ->
            Toast.makeText(requireContext(), status, Toast.LENGTH_SHORT).show()
        })

        // Inside LoginFragment
        authViewModel.loginSuccess.observe(viewLifecycleOwner) { success ->
            if (success) {
                val userRole = authViewModel.userRole.value // Get the role from AuthViewModel
                sharedLoginViewModel.setUserRole(userRole!!) // Set the role in SharedLoginViewModel

                // Pass the role to MainActivity through Intent
                val intent = Intent(requireContext(), MainActivity::class.java).apply {
                    putExtra("userRole", userRole)
                }
                startActivity(intent)
                activity?.finish() // Close LoginFragment or LoginActivity
            }
        }



        return binding
    }

    /**
     * Validates email and password input.
     * @return true if inputs are valid, false otherwise.
     */
    private fun validateInput(email: String, password: String): Boolean {
        return when {
            email.isEmpty() -> {
                emailEditText.error = "Email is required"
                emailEditText.requestFocus()
                false
            }
            !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                emailEditText.error = "Enter a valid email"
                emailEditText.requestFocus()
                false
            }
            password.isEmpty() -> {
                passwordEditText.error = "Password is required"
                passwordEditText.requestFocus()
                false
            }
            password.length < 6 -> {
                passwordEditText.error = "Password must be at least 6 characters"
                passwordEditText.requestFocus()
                false
            }
            else -> true
        }
    }
}
