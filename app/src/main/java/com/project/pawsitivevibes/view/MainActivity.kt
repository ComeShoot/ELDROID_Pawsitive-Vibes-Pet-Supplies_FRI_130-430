package com.project.pawsitivevibes.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.project.pawsitivevibes.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Default fragments in both containers
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_one, HomeHeaderFragment())
            .replace(R.id.fragment_container_two, HomeFragment())
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

