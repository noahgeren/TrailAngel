package com.noahgeren.trailangel.ui.login.setup

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.noahgeren.trailangel.R
import com.noahgeren.trailangel.api.ApiService
import com.noahgeren.trailangel.models.User
import com.noahgeren.trailangel.ui.MainActivity
import com.noahgeren.trailangel.ui.common.PreferenceUtils
import com.noahgeren.trailangel.ui.common.Utils

class FragmentSetup: Fragment(R.layout.fragment_setup) {

    private lateinit var name: EditText
    private lateinit var trailName: EditText
    private lateinit var submit: Button

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        name = view.findViewById(R.id.setup_name)
        trailName = view.findViewById(R.id.setup_trail_name)
        submit = view.findViewById(R.id.setup_submit)

        submit.setOnClickListener {
            Utils.hideKeyboard(requireActivity())
            ApiService.updateAccount(User("", name.text.toString(), trailName.text.toString()))
            val intent = Intent(requireContext(), MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            requireActivity().finish()
            startActivity(intent)
        }
    }

}