package com.project.pawsitivevibes.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.project.pawsitivevibes.R

class SettingsFragment : Fragment() {
    private lateinit var editProfileButton: Button
    private lateinit var aboutUsButton: Button
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = inflater.inflate(R.layout.fragment_settings, container, false)

        editProfileButton = binding.findViewById(R.id.editpfpbtn)
        aboutUsButton = binding.findViewById(R.id.aboutbtn)

        editProfileButton.setOnClickListener {
            val intent = Intent(requireContext(), EditProfileActivity::class.java)
            startActivity(intent)
        }
        aboutUsButton.setOnClickListener {
            val intent = Intent(requireContext(), AboutUsActivity::class.java)
            startActivity(intent)
        }

        return binding
    }
}