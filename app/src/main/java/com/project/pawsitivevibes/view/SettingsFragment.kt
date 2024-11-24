package com.project.pawsitivevibes.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.content.Context
import android.content.SharedPreferences
import androidx.fragment.app.Fragment
import com.project.pawsitivevibes.R

class SettingsFragment : Fragment() {
    private lateinit var editProfileButton: Button
    private lateinit var aboutUsButton: Button
    private lateinit var signOutButton: Button

    // Assuming you're using SharedPreferences to store user session
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = inflater.inflate(R.layout.fragment_settings, container, false)

        // Initialize UI elements
        editProfileButton = binding.findViewById(R.id.editpfpbtn)
        aboutUsButton = binding.findViewById(R.id.aboutbtn)
        signOutButton = binding.findViewById(R.id.signoutbtn)

        // Initialize SharedPreferences (replace with your app's SharedPreferences name)
        sharedPreferences = requireActivity().getSharedPreferences("UserSession", Context.MODE_PRIVATE)

        // Navigate to EditProfileActivity
        editProfileButton.setOnClickListener {
            val intent = Intent(requireContext(), EditProfileActivity::class.java)
            startActivity(intent)
        }

        // Navigate to AboutUsActivity
        aboutUsButton.setOnClickListener {
            val intent = Intent(requireContext(), AboutUsActivity::class.java)
            startActivity(intent)
        }

        // Handle Sign Out
        signOutButton.setOnClickListener {
            logoutUser()
        }

        return binding
    }

    // Clear session data and navigate to LoginActivity
    private fun logoutUser() {
        // Clear SharedPreferences (or any other method you use to store user session)
        val editor = sharedPreferences.edit()
        editor.clear()  // Clears all data stored in SharedPreferences
        editor.apply()

        // Redirect to LoginActivity
        val intent = Intent(requireContext(), LoginActivity::class.java)
        startActivity(intent)

        // Optionally, you can finish the current activity to remove it from the stack
        activity?.finish()
    }
}
