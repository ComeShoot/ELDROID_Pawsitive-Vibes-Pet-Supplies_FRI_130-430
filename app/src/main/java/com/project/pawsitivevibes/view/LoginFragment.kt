package com.project.pawsitivevibes.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.project.pawsitivevibes.R
import com.project.pawsitivevibes.model.User
import com.project.pawsitivevibes.viewmodel.AuthViewModel

class LoginFragment : Fragment() {

    private val authViewModel: AuthViewModel by activityViewModels()

    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = inflater.inflate(R.layout.fragment_login, container, false)

        emailEditText = binding.findViewById(R.id.emailEditText)
        passwordEditText = binding.findViewById(R.id.passwordEditText)

        binding.findViewById<View>(R.id.loginButton).setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()
            if (email.isNotEmpty() && password.isNotEmpty()) {
                val user = User(email, password)
                authViewModel.loginUser(user)
            }
        }

        authViewModel.loginStatus.observe(viewLifecycleOwner, Observer { status ->
            Toast.makeText(context, status, Toast.LENGTH_SHORT).show()
        })

        return binding
    }
}