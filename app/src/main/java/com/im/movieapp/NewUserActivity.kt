package com.im.movieapp

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.im.movieapp.action.FragmentAction

class NewUserActivity : AppCompatActivity(), FragmentAction {

    private lateinit var auth: FirebaseAuth

    override fun onClick(username: String, password: String) {

        if (username.isBlank()) {
            Toast.makeText(
                this, getString(R.string.inform_email),
                Toast.LENGTH_SHORT
            ).show()
            return
        }
        if (password.isBlank()) {
            Toast.makeText(
                this, getString(R.string.inform_password),
                Toast.LENGTH_SHORT
            ).show()
            return
        }


        auth.createUserWithEmailAndPassword(username, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, getString(R.string.user_created), Toast.LENGTH_SHORT).show()
                    finish()
                } else {
                    Log.w(this.localClassName, this.localClassName, task.exception)
                    Toast.makeText(this, getString(R.string.user_not_created), Toast.LENGTH_SHORT).show()
                }
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_user)

        val bundle = Bundle()

        bundle.putString(FormFragment.BUTTON_NAME, getString(R.string.create))
        val fragment = FormFragment.newInstance(bundle)

        supportFragmentManager.beginTransaction()
            .add(R.id.fragment1, fragment)
            .commit()


        auth = FirebaseAuth.getInstance()
    }
}
