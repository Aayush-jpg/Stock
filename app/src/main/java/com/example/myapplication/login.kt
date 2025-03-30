package com.example.myapplication

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.Gravity
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class Login : AppCompatActivity() {

    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        emailEditText = findViewById(R.id.email_edittext_login)
        passwordEditText = findViewById(R.id.password_edittext_login)
        val forgotPasswordText = findViewById<TextView>(R.id.forgot_password_login)

        // Setup login button (removed duplicate listener)
        findViewById<Button>(R.id.signin_button_login).setOnClickListener {
            handleLogin()
        }

        forgotPasswordText.setOnClickListener {
            showForgotPasswordDialog()
        }
    }

    private fun handleLogin() {
        val email = emailEditText.text.toString().trim()
        val password = passwordEditText.text.toString().trim()

        if (!validateInput(email, password)) return

        authenticateUser(email, password)
    }

    private fun validateInput(email: String, password: String): Boolean {
        return when {
            email.isEmpty() -> {
                showToast("Please enter your email")
                false
            }
            !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                showToast("Please enter a valid email")
                false
            }
            password.isEmpty() -> {
                showToast("Please enter your password")
                false
            }
            password.length < 6 -> {
                showToast("Password must be at least 6 characters")
                false
            }
            else -> true
        }
    }

    private fun authenticateUser(email: String, password: String) {
        // For now, simulate successful login and navigate to Profile
        // Replace with your own authentication logic
        navigateToProfile(email, "User")
    }

    private fun navigateToProfile(email: String, name: String) {
        startActivity(
            Intent(this, ProfileActivity::class.java).apply {
                putExtra("EMAIL", email)
                putExtra("NAME", name)
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }
        )
        finish()
    }

    private fun showForgotPasswordDialog() {
        val view = layoutInflater.inflate(R.layout.dialog_forgot_password, null)
        val dialogEmailEditText = view.findViewById<EditText>(R.id.forgot_password_email)

        AlertDialog.Builder(this)
            .setView(view)
            .setTitle("Password Reset")
            .setPositiveButton("Submit") { _, _ ->
                val email = dialogEmailEditText.text.toString().trim()
                if (validateForgotPasswordEmail(email)) {
                    sendPasswordResetEmail(email)
                }
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    private fun validateForgotPasswordEmail(email: String): Boolean {
        if (email.isEmpty()) {
            showToast("Please enter your email")
            return false
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            showToast("Please enter a valid email")
            return false
        }
        return true
    }

    private fun sendPasswordResetEmail(email: String) {
        // Simulate sending a password reset email
        // Replace this with your own logic to send an email if needed
        showToast("Password reset email sent to $email")
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).apply {
            setGravity(Gravity.CENTER, 0, 0)
            show()
        }
    }
}
