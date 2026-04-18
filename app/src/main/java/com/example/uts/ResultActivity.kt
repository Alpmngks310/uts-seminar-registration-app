package com.example.uts

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton

class ResultActivity : AppCompatActivity() {

    private lateinit var tvNameResult: TextView
    private lateinit var tvEmailResult: TextView
    private lateinit var tvPhoneResult: TextView
    private lateinit var tvGenderResult: TextView
    private lateinit var tvSeminarResult: TextView
    private lateinit var btnBackHome: MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        tvNameResult = findViewById(R.id.tvNameResult)
        tvEmailResult = findViewById(R.id.tvEmailResult)
        tvPhoneResult = findViewById(R.id.tvPhoneResult)
        tvGenderResult = findViewById(R.id.tvGenderResult)
        tvSeminarResult = findViewById(R.id.tvSeminarResult)
        btnBackHome = findViewById(R.id.btnBackHome)

        val name = intent.getStringExtra("name")
        val email = intent.getStringExtra("email")
        val phone = intent.getStringExtra("phone")
        val gender = intent.getStringExtra("gender")
        val seminar = intent.getStringExtra("seminar")

        tvNameResult.text = "Name: $name"
        tvEmailResult.text = "Email: $email"
        tvPhoneResult.text = "Phone: $phone"
        tvGenderResult.text = "Gender: $gender"
        tvSeminarResult.text = "Seminar: $seminar"

        btnBackHome.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
            finish()
        }
    }
}