package com.im.movieapp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.im.movieapp.action.FragmentAction


class RetrievePasswordActivity : AppCompatActivity(), FragmentAction {

    private lateinit var auth: FirebaseAuth

    override fun onClick(username: String, password: String) {

        if (username.isBlank()) {
            Toast.makeText(
                this, getString(R.string.inform_email),
                Toast.LENGTH_SHORT
            ).show()
            return
        }

        auth.sendPasswordResetEmail(username)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, getString(R.string.email_sent), Toast.LENGTH_SHORT).show()
                    finish()
                } else {
                    Toast.makeText(
                        this,
                        getString(R.string.email_sent_error),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_retrieve_password)


        val bundle = Bundle()
        bundle.putBoolean(FormFragment.SHOW_PASSWORD, false)

        bundle.putString(FormFragment.BUTTON_NAME, getString(R.string.retrieve_password))
        val fragment = FormFragment.newInstance(bundle)

        supportFragmentManager.beginTransaction()
            .add(R.id.fragment1, fragment)
            .commit()

        auth = FirebaseAuth.getInstance()
    }
}
