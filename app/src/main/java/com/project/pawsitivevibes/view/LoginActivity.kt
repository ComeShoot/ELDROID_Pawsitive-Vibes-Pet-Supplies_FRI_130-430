package com.project.pawsitivevibes.view


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.project.pawsitivevibes.R

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Check if the fragment is already loaded, otherwise load LoginFragment as default
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, LoginFragment())
                .commit()
        }
    }
}
