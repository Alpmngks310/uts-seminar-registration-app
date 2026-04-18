package com.example.uts

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class LoginActivity : AppCompatActivity() {

    private lateinit var tilEmail: TextInputLayout
    private lateinit var tilPassword: TextInputLayout
    private lateinit var etEmail: TextInputEditText
    private lateinit var etPassword: TextInputEditText
    private lateinit var btnLogin: MaterialButton
    private lateinit var tvRegister: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        tilEmail = findViewById(R.id.tilEmail)
        tilPassword = findViewById(R.id.tilPassword)
        etEmail = findViewById(R.id.etEmail)
        etPassword = findViewById(R.id.etPassword)
        btnLogin = findViewById(R.id.btnLogin)
        tvRegister = findViewById(R.id.tvRegister)

        btnLogin.setOnClickListener {
            validateAndLogin()
        }

        tvRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }

    private fun validateAndLogin() {
        val email = etEmail.text.toString().trim()
        val password = etPassword.text.toString().trim()

        tilEmail.error = null
        tilPassword.error = null

        var isValid = true

        if (email.isEmpty()) {
            tilEmail.error = "Email or username is required"
            isValid = false
        }

        if (password.isEmpty()) {
            tilPassword.error = "Password is required"
            isValid = false
        } else if (password.length < 6) {
            tilPassword.error = "Password must be at least 6 characters"
            isValid = false
        }

        if (!isValid) return

        val adminUsername = "admin"
        val adminPassword = "admin123"

        val validEmail = "admin@gmail.com"
        val validPassword = "123456"

        if ((email == adminUsername && password == adminPassword) ||
            (email == validEmail && password == validPassword)
        ) {
            Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        } else {
            Toast.makeText(this, "Username or password is incorrect", Toast.LENGTH_SHORT).show()
        }
    }
}