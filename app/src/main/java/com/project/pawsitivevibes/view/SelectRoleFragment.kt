package com.project.pawsitivevibes.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.google.android.material.card.MaterialCardView
import com.project.pawsitivevibes.R
import com.project.pawsitivevibes.viewmodel.SharedViewModel

class SelectRoleFragment : Fragment() {

    private val sharedViewModel: SharedViewModel by activityViewModels()  // Access SharedViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = inflater.inflate(R.layout.fragment_select_role, container, false)

        // Set click listeners for both roles
        binding.findViewById<MaterialCardView>(R.id.sellerCard).setOnClickListener {
            sharedViewModel.setSelectedRole("Seller")  // Set the role to Seller
            navigateToRegisterFragment()
        }

        binding.findViewById<MaterialCardView>(R.id.customerCard).setOnClickListener {
            sharedViewModel.setSelectedRole("Customer")  // Set the role to Customer
            navigateToRegisterFragment()
        }

        return binding
    }

    private fun navigateToRegisterFragment() {
        // Navigate to RegisterFragment
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, RegisterFragment())
            .addToBackStack(null)  // Allows going back to SelectRoleFragment
            .commit()
    }
}
