package com.project.pawsitivevibes.view

import android.os.Bundle
import android.util.Log
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

        Log.d("MainActivity", "MainActivity onCreate called") // Log when MainActivity starts


        // Check if role is passed through intent and set it in SharedLoginViewModel
        intent.getStringExtra("userRole")?.let { role ->
            sharedLoginViewModel.setUserRole(role)
            loadFragmentBasedOnRole(role)  // Immediately load the fragment
        }

        sharedLoginViewModel.userRole.observe(this) { role ->
            role?.let {
                Log.d("MainActivity", "Observed user role: $role")
                loadFragmentBasedOnRole(role)
            } ?: Log.d("MainActivity", "Role is null, cannot load fragment")
        }


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
                    sharedLoginViewModel.userRole.observe(this) { role ->
                        role?.let {
                            loadFragmentBasedOnRole(role)
                        } ?: Log.d("MainActivity", "Role is null, cannot load fragment")
                    }
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
    // In MainActivity
    private fun loadFragmentBasedOnRole(role: String) {
        Log.d("MainActivity", "Loading fragment for role: $role")

        val fragmentTransaction = supportFragmentManager.beginTransaction()
        val fragment: Fragment = if (role == "Seller") {
            HomeSellerFragment()
        } else {
            HomeFragment()
        }

        val fragmentTag = fragment.javaClass.simpleName
        val existingFragment = supportFragmentManager.findFragmentByTag(fragmentTag)

        if (existingFragment == null) {
            Log.d("MainActivity", "Replacing fragment with tag: $fragmentTag")
            fragmentTransaction.replace(R.id.fragment_container_two, fragment, fragmentTag)
            fragmentTransaction.commit()
        } else {
            Log.d("MainActivity", "Fragment already loaded, skipping transaction for $fragmentTag")
        }
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
