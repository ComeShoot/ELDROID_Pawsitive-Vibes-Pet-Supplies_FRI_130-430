package com.project.pawsitivevibes.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.project.pawsitivevibes.R

class RegisterFragment : Fragment() {


    private lateinit var signInButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = inflater.inflate(R.layout.fragment_register, container, false)
        signInButton= binding.findViewById(R.id.signinbutton)

        signInButton.setOnClickListener {
            // Navigate to LoginFragment when "Login" is clicked
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, LoginFragment())
                .addToBackStack(null) // Allows going back to RegisterFragment
                .commit()
        }
        return binding
    }
}