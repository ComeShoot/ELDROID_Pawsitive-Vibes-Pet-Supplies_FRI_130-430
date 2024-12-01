package com.project.pawsitivevibes.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.project.pawsitivevibes.R
import com.project.pawsitivevibes.viewmodel.SharedLoginViewModel

class MainActivity : AppCompatActivity() {

    private val sharedLoginViewModel: SharedLoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Observe the user role and load the appropriate dashboard fragment
        sharedLoginViewModel.userRole.observe(this, { role ->
            loadFragmentBasedOnRole(role)
        })

        // Default fragments in both containers (can be set as a fallback or initial fragments)
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_one, HomeHeaderFragment())
            .replace(R.id.fragment_container_two, HomeFragment())  // This can be the default Customer dashboard
            .commit()

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    loadFragmentInContainerOne(HomeHeaderFragment())
                    loadFragmentInContainerTwo(HomeFragment())
                    true
                }
                R.id.nav_transaction -> {
                    loadFragmentInContainerOne(TransactionHeaderFragment())
                    loadFragmentInContainerTwo(TransactionFragment())
                    true
                }
                R.id.nav_notification -> {
                    loadFragmentInContainerOne(NotificationHeaderFragment())
                    loadFragmentInContainerTwo(NotificationFragment())
                    true
                }
                R.id.nav_settings -> {
                    loadFragmentInContainerOne(SettingsHeaderFragment())
                    loadFragmentInContainerTwo(SettingsFragment())
                    true
                }
                else -> false
            }
        }
    }

    // Load the appropriate dashboard fragment based on user role
    private fun loadFragmentBasedOnRole(role: String) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()

        // Check the user role and load the corresponding fragment
        val fragment: Fragment = if (role == "Seller") {
            HomeSellerFragment() // Seller dashboard fragment
        } else {
            HomeFragment() // Default Customer dashboard fragment
        }

        // Replace the fragment container with the selected fragment
        fragmentTransaction.replace(R.id.fragment_container, fragment)
        fragmentTransaction.commit()
    }

    private fun loadFragmentInContainerOne(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_one, fragment)
            .commit()
    }

    private fun loadFragmentInContainerTwo(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_two, fragment)
            .commit()
    }
}
