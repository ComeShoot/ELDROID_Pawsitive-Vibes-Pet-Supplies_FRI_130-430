package com.project.pawsitivevibes.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.project.pawsitivevibes.R

class LoginFragment : Fragment() {


    private lateinit var signUpButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = inflater.inflate(R.layout.fragment_login, container, false)

        signUpButton = binding.findViewById(R.id.signupbutton)

        signUpButton.setOnClickListener {
            // Navigate to RegisterFragment when "Sign Up" is clicked
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, RegisterFragment())
                .addToBackStack(null) // Allows going back to LoginFragment
                .commit()
        }

        return binding
    }
}