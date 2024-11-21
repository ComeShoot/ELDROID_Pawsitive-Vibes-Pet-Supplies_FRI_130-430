package com.project.pawsitivevibes.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import com.project.pawsitivevibes.R

class ResetPasswordFragment : Fragment() {
    private lateinit var resetPassword: Button
    private lateinit var backButton: ImageButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = inflater.inflate(R.layout.fragment_reset_password, container, false)
        backButton = binding.findViewById(R.id.backBtn)
        backButton.setOnClickListener {
            // Navigate back to the previous fragment
            parentFragmentManager.popBackStack()
        }
        return binding
    }
}