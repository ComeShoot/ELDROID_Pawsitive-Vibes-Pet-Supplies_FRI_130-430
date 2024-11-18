package com.project.pawsitivevibes.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import com.project.pawsitivevibes.R

class ForgotPasswordFragment : Fragment() {
    private lateinit var resetPassword: Button
    private lateinit var backButton: ImageButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = inflater.inflate(R.layout.fragment_forgot_password, container, false)

        resetPassword = binding.findViewById(R.id.forgotEmailButton)
        backButton = binding.findViewById(R.id.backBtn)

        resetPassword.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, ResetPasswordFragment())
                .addToBackStack(null) // Allows going back to LoginFragment
                .commit()
        }
        backButton.setOnClickListener {
            // Navigate back to the previous fragment
            parentFragmentManager.popBackStack()
        }
        return binding
    }
}