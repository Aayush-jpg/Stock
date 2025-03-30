package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SignUp : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        // Initialize the signup button and set an onClick listener
        val signupButton = findViewById<Button>(R.id.btnSignup)
        signupButton.setOnClickListener {
            performRegister()
        }
    }

    // Function to perform the registration
    private fun performRegister() {
        // Get values from the input fields
        val email = findViewById<EditText>(R.id.email_edittext_signup).text.toString()
        val password = findViewById<EditText>(R.id.password_edittext_signup).text.toString()
        val username = findViewById<EditText>(R.id.username_edittext_signup).text.toString()
        val phoneNumber = findViewById<EditText>(R.id.phonenumber_edittext_signup).text.toString()

        // Validate inputs
        if (email.isEmpty() || password.isEmpty() || username.isEmpty() || phoneNumber.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            return
        }

        // Save the user data in SharedPreferences
        val sharedPref = getSharedPreferences("UserDetails", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()

        // Storing user details in SharedPreferences
        editor.putString("email", email)
        editor.putString("password", password)
        editor.putString("username", username)
        editor.putString("phoneNumber", phoneNumber)
        editor.apply()

        // Show success message
        Toast.makeText(this, "User Registered Successfully!", Toast.LENGTH_SHORT).show()

        // Navigate to the main activity (or any other activity after registration)
        val intent = Intent(this, MainActivity2::class.java)
        startActivity(intent)
        finish()  // Close this activity after successful registration
    }

    // This function opens the login screen
    fun openLogin(view: View) {
        val intent = Intent(this, Login::class.java)
        startActivity(intent)
    }
}
