package com.example.uts

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton

class DetailActivity : AppCompatActivity() {

    private lateinit var tvDetailStatus: TextView
    private lateinit var tvDetailTitle: TextView
    private lateinit var tvDetailDate: TextView
    private lateinit var tvDetailSpeaker: TextView
    private lateinit var tvDetailContent: TextView
    private lateinit var btnBack: MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        tvDetailStatus = findViewById(R.id.tvDetailStatus)
        tvDetailTitle = findViewById(R.id.tvDetailTitle)
        tvDetailDate = findViewById(R.id.tvDetailDate)
        tvDetailSpeaker = findViewById(R.id.tvDetailSpeaker)
        tvDetailContent = findViewById(R.id.tvDetailContent)
        btnBack = findViewById(R.id.btnBack)

        val status = intent.getStringExtra("status") ?: "-"
        val title = intent.getStringExtra("title") ?: "-"
        val date = intent.getStringExtra("date") ?: "-"
        val speaker = intent.getStringExtra("speaker") ?: "-"
        val content = intent.getStringExtra("content") ?: "-"

        tvDetailStatus.text = status
        tvDetailTitle.text = title
        tvDetailDate.text = date
        tvDetailSpeaker.text = "Speaker / Info: $speaker"
        tvDetailContent.text = content

        btnBack.setOnClickListener {
            finish()
        }
    }
}