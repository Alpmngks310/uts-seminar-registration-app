package com.example.uts

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.card.MaterialCardView

class MainActivity : AppCompatActivity() {

    private lateinit var tvWelcome: TextView
    private lateinit var btnRegisterSeminar: MaterialButton

    // My Activities
    private lateinit var cardMyActivity: MaterialCardView
    private lateinit var tvMyStatus: TextView
    private lateinit var tvMyTitle: TextView
    private lateinit var tvMyDate: TextView
    private lateinit var tvMyDesc: TextView

    // Cards
    private lateinit var cardRecent1: MaterialCardView
    private lateinit var cardRecent2: MaterialCardView
    private lateinit var cardUpcoming1: MaterialCardView
    private lateinit var cardUpcoming2: MaterialCardView
    private lateinit var cardUpcoming3: MaterialCardView
    private lateinit var cardUpcoming4: MaterialCardView
    private lateinit var cardUpcoming5: MaterialCardView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Header
        tvWelcome = findViewById(R.id.tvWelcome)
        btnRegisterSeminar = findViewById(R.id.btnRegisterSeminar)

        // My Activities
        cardMyActivity = findViewById(R.id.cardMyActivity)
        tvMyStatus = findViewById(R.id.tvMyStatus)
        tvMyTitle = findViewById(R.id.tvMyTitle)
        tvMyDate = findViewById(R.id.tvMyDate)
        tvMyDesc = findViewById(R.id.tvMyDesc)


        // Cards
        cardRecent1 = findViewById(R.id.cardRecent1)
        cardRecent2 = findViewById(R.id.cardRecent2)
        cardUpcoming1 = findViewById(R.id.cardUpcoming1)
        cardUpcoming2 = findViewById(R.id.cardUpcoming2)
        cardUpcoming3 = findViewById(R.id.cardUpcoming3)
        cardUpcoming4 = findViewById(R.id.cardUpcoming4)
        cardUpcoming5 = findViewById(R.id.cardUpcoming5)

        tvWelcome.text = "Welcome Back!"

        // 🔥 PANGGIL DI SINI
        showUserActivities()

        btnRegisterSeminar.setOnClickListener {
            startActivity(Intent(this, SeminarFormActivity::class.java))
        }

        // Click My Activity
        cardMyActivity.setOnClickListener {
            if (UserSession.registeredActivities.isNotEmpty()) {
                val latest = UserSession.registeredActivities.last()

                openDetail(
                    status = "Upcoming",
                    title = latest,
                    date = tvMyDate.text.toString(),
                    speaker = "Seminar Info",
                    content = tvMyDesc.text.toString()
                )
            }
        }

        // Recent
        cardRecent1.setOnClickListener {
            openDetail(
                "Completed",
                "Mural Collaboration Activity",
                "April 10, 2026",
                "UTB & TNI",
                "Collaborative mural event promoting public communication through visual art."
            )
        }

        cardRecent2.setOnClickListener {
            openDetail(
                "Completed",
                "Mental Health Seminar",
                "April 3, 2026",
                "Counseling Team",
                "Discussion on emotional well-being and stress management."
            )
        }

        // Upcoming
        cardUpcoming1.setOnClickListener {
            openDetail(
                "Upcoming",
                "Financial Planning Seminar",
                "April 25, 2026",
                "Financial Expert",
                "Learn budgeting and financial habits."
            )
        }

        cardUpcoming2.setOnClickListener {
            openDetail(
                "Upcoming",
                "Career Preparation Talk",
                "May 2, 2026",
                "HR Specialist",
                "Prepare for internships and interviews."
            )
        }

        cardUpcoming3.setOnClickListener {
            openDetail(
                "Upcoming",
                "Personal Branding Class",
                "May 10, 2026",
                "Branding Consultant",
                "Build confidence and communication skills."
            )
        }
        cardUpcoming4.setOnClickListener {
            openDetail(
                "Upcoming",
                "Public Speaking Workshop",
                "May 17, 2026",
                "Communication Coach",
                "Improve confidence, speaking structure, and audience engagement skills."
            )
        }

        cardUpcoming5.setOnClickListener {
            openDetail(
                "Upcoming",
                "Mental Health Awareness Seminar",
                "May 24, 2026",
                "Psychologist",
                "Learn stress management, emotional balance, and mental well-being strategies."
            )
        }
    }

    override fun onResume() {
        super.onResume()
        showUserActivities()
    }

    // 🔥 FIXED FUNCTION (DI LUAR onCreate)
    private fun showUserActivities() {

        if (UserSession.registeredActivities.isNotEmpty()) {

            val latest = UserSession.registeredActivities.last()

            tvMyStatus.text = "Upcoming"
            tvMyTitle.text = latest

            when (latest) {
                "Financial Planning Seminar" -> {
                    tvMyDate.text = "Date: April 25, 2026"
                    tvMyDesc.text = "Learn budgeting and financial habits."
                }
                "Career Preparation Talk" -> {
                    tvMyDate.text = "Date: May 2, 2026"
                    tvMyDesc.text = "Prepare for internships and interviews."
                }
                "Personal Branding Class" -> {
                    tvMyDate.text = "Date: May 10, 2026"
                    tvMyDesc.text = "Build confidence and personal image."
                }
                "Public Speaking Workshop" -> {
                    tvMyDate.text = "Date: May 17, 2026"
                    tvMyDesc.text = "Improve confidence, speaking structure, and audience engagement skills."
                }
                "Mental Health Awareness Seminar" -> {
                    tvMyDate.text = "Date: May 24, 2026"
                    tvMyDesc.text = "Learn stress management, emotional balance, and mental well-being strategies."
                }
            }

        } else {
            tvMyStatus.text = "-"
            tvMyTitle.text = "No registered activities yet"
            tvMyDate.text = "Date: -"
            tvMyDesc.text = "Register a seminar to see your activity here."
        }
    }

    private fun openDetail(
        status: String,
        title: String,
        date: String,
        speaker: String,
        content: String
    ) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("status", status)
        intent.putExtra("title", title)
        intent.putExtra("date", date)
        intent.putExtra("speaker", speaker)
        intent.putExtra("content", content)
        startActivity(intent)
    }
}