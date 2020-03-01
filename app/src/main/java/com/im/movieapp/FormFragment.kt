package com.im.movieapp

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.im.movieapp.action.FragmentAction

class FormFragment : Fragment() {

    lateinit var fragmentAction: FragmentAction

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            fragmentAction = context as FragmentAction
        } catch (e: Exception) {
            Log.w(FormFragment::class.java.name, e.printStackTrace().toString())
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_form, container, false)
        val button = view.findViewById<Button>(R.id.frag_button)

        arguments?.let {
            val password = view.findViewById<EditText>(R.id.password)

            if (it.get(SHOW_PASSWORD) == null || it.get(SHOW_PASSWORD) == true) {
                password.visibility = View.VISIBLE
            } else {
                password.visibility = View.GONE
            }

            button.text = it.get(BUTTON_NAME).toString()
        }

        button.setOnClickListener {

            val username = view.findViewById<EditText>(R.id.username).text.toString()
            val password = view.findViewById<EditText>(R.id.password).text.toString()

            fragmentAction.onClick(username, password)
        }

        return view
    }


    companion object {

        const val BUTTON_NAME = "button_name"
        const val SHOW_PASSWORD = "show_password"

        fun newInstance(bundle: Bundle? = null): FormFragment {
            val formFragment = FormFragment()
            formFragment.arguments = bundle
            return formFragment
        }


    }
}
