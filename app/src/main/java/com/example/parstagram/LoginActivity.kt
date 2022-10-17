package com.example.parstagram

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.parse.ParseUser

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //check if user is logged in??
        //if there is take them to main activity
        if(ParseUser.getCurrentUser() != null) {
            goToMainActivity()
        }



        findViewById<Button>(R.id.login_button).setOnClickListener {

            val username = findViewById<EditText>(R.id.et_username).text.toString()
            val password = findViewById<EditText>(R.id.et_password).text.toString()
            loginUser(username,password)
        }

        findViewById<Button>(R.id.signupBtn).setOnClickListener {

            val username = findViewById<EditText>(R.id.et_username).text.toString()
            val password = findViewById<EditText>(R.id.et_password).text.toString()
            signUpUser(username,password)
        }


    }

    private fun signUpUser(username: String, password: String){
        // Create the ParseUser
        val user = ParseUser()

// Set fields for the user to be created
        user.setUsername(username)
        user.setPassword(password)

        user.signUpInBackground { e ->
            if (e == null) {
                //user successfully signed up for new account
                Toast.makeText(this,"Successfully Signed up", Toast.LENGTH_SHORT).show()
                goToMainActivity()

            } else {
                //show toast for not being successful
                e.printStackTrace()
                Toast.makeText(this,"Error Signing up", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun loginUser(username: String, password: String){
        ParseUser.logInInBackground(username, password, ({ user, e ->
            if (user != null) {
                Log.i(TAG,"Successfully logged in user ")
                goToMainActivity()
            } else {
                e.printStackTrace()
                Toast.makeText(this,"Error logging in", Toast.LENGTH_SHORT).show()
            }})
        )
    }

    private fun goToMainActivity() {

        val intent = Intent(this@LoginActivity, MainActivity::class.java)
        startActivity(intent)
        finish()

    }

    companion object{
       const val TAG = "LoginActivity"
    }

}
