package com.example.uts

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.CheckBox
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout


class SeminarFormActivity : AppCompatActivity() {

    private lateinit var tilName: TextInputLayout
    private lateinit var tilEmail: TextInputLayout
    private lateinit var tilPhone: TextInputLayout

    private lateinit var etName: TextInputEditText
    private lateinit var etEmail: TextInputEditText
    private lateinit var etPhone: TextInputEditText

    private lateinit var rgGender: RadioGroup
    private lateinit var spinnerSeminar: Spinner
    private lateinit var cbAgreement: CheckBox
    private lateinit var tvWarning: TextView
    private lateinit var btnSubmit: MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seminar_form)

        tilName = findViewById(R.id.tilName)
        tilEmail = findViewById(R.id.tilEmail)
        tilPhone = findViewById(R.id.tilPhone)

        etName = findViewById(R.id.etName)
        etEmail = findViewById(R.id.etEmail)
        etPhone = findViewById(R.id.etPhone)

        rgGender = findViewById(R.id.rgGender)
        spinnerSeminar = findViewById(R.id.spinnerSeminar)
        cbAgreement = findViewById(R.id.cbAgreement)
        tvWarning = findViewById(R.id.tvAgreementWarning)
        btnSubmit = findViewById(R.id.btnSubmit)

        setupSpinner()
        setupRealTimeValidation()

        btnSubmit.setOnClickListener {
            validateForm()
        }
    }

    private fun setupSpinner() {
        val seminarList = listOf(
            "Select Seminar",
            "Public Speaking Workshop",
            "Mental Health Awareness Seminar",
            "Financial Planning Seminar",
            "Career Preparation Talk",
            "Personal Branding Class"
        )

        val adapter = object : ArrayAdapter<String>(
            this,
            android.R.layout.simple_spinner_item,
            seminarList
        ) {
            override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
                val view = super.getView(position, convertView, parent)
                (view as TextView).setTextColor(getColor(android.R.color.black))
                return view
            }

            override fun getDropDownView(
                position: Int,
                convertView: View?,
                parent: ViewGroup
            ): View {
                val view = super.getDropDownView(position, convertView, parent)
                (view as TextView).setTextColor(getColor(android.R.color.black))
                return view
            }
        }

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerSeminar.adapter = adapter
    }

    private fun setupRealTimeValidation() {
        etName.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val name = s.toString().trim()
                tilName.error = if (name.isEmpty()) "Name is required" else null
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        etEmail.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val email = s.toString().trim()
                tilEmail.error = when {
                    email.isEmpty() -> "Email is required"
                    !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> "Invalid email"
                    else -> null
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        etPhone.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val phone = s.toString().trim()
                tilPhone.error = when {
                    phone.isEmpty() -> "Phone number is required"
                    !phone.matches(Regex("^08[0-9]*$")) ->
                        "Phone number must start with 08 and contain only digits"

                    phone.length < 10 || phone.length > 13 ->
                        "Phone number must be 10-13 digits"

                    else -> null
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }

    private fun validateForm() {
        val name = etName.text.toString().trim()
        val email = etEmail.text.toString().trim()
        val phone = etPhone.text.toString().trim()
        val selectedSeminar = spinnerSeminar.selectedItem.toString()

        tilName.error = null
        tilEmail.error = null
        tilPhone.error = null
        tvWarning.visibility = View.GONE

        var isValid = true

        if (name.isEmpty()) {
            tilName.error = "Name is required"
            isValid = false
        }

        if (email.isEmpty()) {
            tilEmail.error = "Email is required"
            isValid = false
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            tilEmail.error = "Invalid email"
            isValid = false
        }

        if (phone.isEmpty()) {
            tilPhone.error = "Phone number is required"
            isValid = false
        } else if (!phone.matches(Regex("^08[0-9]{8,11}$"))) {
            tilPhone.error = "Must start with 08 (10-13 digits)"
            isValid = false
        }

        if (rgGender.checkedRadioButtonId == -1) {
            Toast.makeText(this, "Please select gender", Toast.LENGTH_SHORT).show()
            isValid = false
        }

        if (selectedSeminar == "Select Seminar") {
            Toast.makeText(this, "Please select a seminar", Toast.LENGTH_SHORT).show()
            isValid = false
        }

        if (!cbAgreement.isChecked) {
            tvWarning.visibility = View.VISIBLE
            isValid = false
        }

        if (!isValid) return

        val selectedGender =
            findViewById<RadioButton>(rgGender.checkedRadioButtonId).text.toString()

        showConfirmationDialog(name, email, phone, selectedGender, selectedSeminar)
    }

    private fun showConfirmationDialog(
        name: String,
        email: String,
        phone: String,
        gender: String,
        seminar: String
    ) {
        val dialogView = layoutInflater.inflate(R.layout.dialog_confirm_registration, null)

        val btnCancelDialog = dialogView.findViewById<MaterialButton>(R.id.btnCancelDialog)
        val btnConfirmDialog = dialogView.findViewById<MaterialButton>(R.id.btnConfirmDialog)

        val dialog = AlertDialog.Builder(this)
            .setView(dialogView)
            .create()

        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

        btnCancelDialog.setOnClickListener {
            dialog.dismiss()
        }

        btnConfirmDialog.setOnClickListener {
            if (!UserSession.registeredActivities.contains(seminar)) {
                UserSession.registeredActivities.add(seminar)
            }

            val intent = Intent(this@SeminarFormActivity, ResultActivity::class.java)
            intent.putExtra("name", name)
            intent.putExtra("email", email)
            intent.putExtra("phone", phone)
            intent.putExtra("gender", gender)
            intent.putExtra("seminar", seminar)
            startActivity(intent)

            dialog.dismiss()
        }

        dialog.show()
    }
}