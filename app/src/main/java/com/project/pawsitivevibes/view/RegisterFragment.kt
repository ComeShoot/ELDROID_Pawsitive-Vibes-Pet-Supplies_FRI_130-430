package com.project.pawsitivevibes.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.project.pawsitivevibes.R
import com.project.pawsitivevibes.model.User
import com.project.pawsitivevibes.viewmodel.AuthViewModel
import com.project.pawsitivevibes.viewmodel.SharedViewModel
import android.util.Log


class RegisterFragment : Fragment() {

    private val authViewModel: AuthViewModel by activityViewModels()  // Access AuthViewModel
    private val sharedViewModel: SharedViewModel by activityViewModels()  // Access SharedViewModel
    private lateinit var nameEditText: EditText
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var phoneEditText: EditText
    private lateinit var locationEditText: EditText
    private lateinit var roleTextView: TextView
    private lateinit var signInButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = inflater.inflate(R.layout.fragment_register, container, false)

        nameEditText = binding.findViewById(R.id.FullNameEditText)
        emailEditText = binding.findViewById(R.id.emailEditText)
        passwordEditText = binding.findViewById(R.id.passwordEditText)
        phoneEditText = binding.findViewById(R.id.phoneEditText)
        locationEditText = binding.findViewById(R.id.locationEditText)
        roleTextView = binding.findViewById(R.id.roleTextView)
        signInButton = binding.findViewById(R.id.signinbutton)

        // Observe the selected role from SharedViewModel
        sharedViewModel.selectedRole.observe(viewLifecycleOwner) { role ->
            // Display the selected role in the UI
            roleTextView.text = "Create your new $role account"
        }

        signInButton.setOnClickListener {
            // Navigate to LoginFragment when "Sign In" is clicked
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, LoginFragment())
                .addToBackStack(null)  // Allows going back to RegisterFragment
                .commit()
        }

        // Observe the register status
        authViewModel.registerStatus.observe(viewLifecycleOwner, Observer { status ->
            Toast.makeText(context, status, Toast.LENGTH_SHORT).show()
        })

        // Handle register button click
        binding.findViewById<View>(R.id.registerButton).setOnClickListener {
            val name = nameEditText.text.toString()
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()
            val phoneNumber = phoneEditText.text.toString()
            val location = locationEditText.text.toString()


            if (email.isNotEmpty() && password.isNotEmpty()) {
                // Get the selected role directly from the SharedViewModel
                val role = sharedViewModel.selectedRole.value ?: "Unknown Role"

                // Create a user object with the email, password, and role
                val user = User(name, email, password, phoneNumber, location, role)
                Log.d("RegisterFragment", "Registering user: $user")
                // Register the user with the selected role
                authViewModel.registerUser(user)
            } else {
                Toast.makeText(context, "Please enter both email and password", Toast.LENGTH_SHORT).show()
            }
        }

        return binding
    }
}
