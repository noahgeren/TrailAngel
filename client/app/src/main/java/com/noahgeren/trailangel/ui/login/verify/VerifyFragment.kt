package com.noahgeren.trailangel.ui.login.verify

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import com.noahgeren.trailangel.R
import com.noahgeren.trailangel.repos.UserRepo
import com.noahgeren.trailangel.ui.MainActivity
import com.noahgeren.trailangel.ui.common.Utils

class VerifyFragment : Fragment(R.layout.fragment_verify) {

    private lateinit var code: EditText
    private lateinit var submit: Button
    private lateinit var loading: FrameLayout

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        code = view.findViewById(R.id.verify_code)
        submit = view.findViewById(R.id.verify_submit)
        loading = requireActivity().findViewById(R.id.login_loading)

        Utils.setupSubmitActionListener(code, EditorInfo.IME_ACTION_GO, submit)

        submit.setOnClickListener {
            Utils.hideKeyboard(requireActivity())
            loading.visibility = View.VISIBLE
            Handler().postDelayed({
                loading.visibility = View.GONE
                if(UserRepo.login()) {
                    val intent = Intent(requireContext(), MainActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                    requireActivity().finish()
                    startActivity(intent)
                }
            }, 2000)
        }
    }
}