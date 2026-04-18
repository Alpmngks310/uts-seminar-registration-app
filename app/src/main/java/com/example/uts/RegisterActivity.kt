package com.example.uts

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class RegisterActivity : AppCompatActivity() {

    private lateinit var tilName: TextInputLayout
    private lateinit var tilEmail: TextInputLayout
    private lateinit var tilPassword: TextInputLayout
    private lateinit var tilConfirmPassword: TextInputLayout

    private lateinit var etName: TextInputEditText
    private lateinit var etEmail: TextInputEditText
    private lateinit var etPassword: TextInputEditText
    private lateinit var etConfirmPassword: TextInputEditText

    private lateinit var btnRegister: MaterialButton
    private lateinit var tvLogin: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        tilName = findViewById(R.id.tilName)
        tilEmail = findViewById(R.id.tilEmail)
        tilPassword = findViewById(R.id.tilPassword)
        tilConfirmPassword = findViewById(R.id.tilConfirmPassword)

        etName = findViewById(R.id.etName)
        etEmail = findViewById(R.id.etEmail)
        etPassword = findViewById(R.id.etPassword)
        etConfirmPassword = findViewById(R.id.etConfirmPassword)

        btnRegister = findViewById(R.id.btnRegister)
        tvLogin = findViewById(R.id.tvLogin)

        btnRegister.setOnClickListener {
            validateRegister()
        }

        tvLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }

    private fun validateRegister() {
        val name = etName.text.toString().trim()
        val email = etEmail.text.toString().trim()
        val password = etPassword.text.toString().trim()
        val confirmPassword = etConfirmPassword.text.toString().trim()

        tilName.error = null
        tilEmail.error = null
        tilPassword.error = null
        tilConfirmPassword.error = null

        var isValid = true

        if (name.isEmpty()) {
            tilName.error = "Full name is required"
            isValid = false
        }

        if (email.isEmpty()) {
            tilEmail.error = "Email is required"
            isValid = false
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            tilEmail.error = "Enter a valid email"
            isValid = false
        }

        if (password.isEmpty()) {
            tilPassword.error = "Password is required"
            isValid = false
        } else if (password.length < 6) {
            tilPassword.error = "Password must be at least 6 characters"
            isValid = false
        }

        if (confirmPassword.isEmpty()) {
            tilConfirmPassword.error = "Please confirm your password"
            isValid = false
        } else if (password != confirmPassword) {
            tilConfirmPassword.error = "Password does not match"
            isValid = false
        }

        if (!isValid) return

        Toast.makeText(this, "Registration successful", Toast.LENGTH_SHORT).show()

        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }
}