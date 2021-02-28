package com.noahgeren.trailangel.ui.login

import android.os.Bundle
import android.os.Handler
import android.telephony.PhoneNumberFormattingTextWatcher
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.noahgeren.trailangel.R
import com.noahgeren.trailangel.ui.common.Utils

class LoginFragment : Fragment(R.layout.fragment_login) {

    private lateinit var phoneNumber: EditText
    private lateinit var submit: Button
    private lateinit var loading: FrameLayout

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        phoneNumber = view.findViewById(R.id.login_phone_number)
        submit = view.findViewById(R.id.login_submit)
        loading = requireActivity().findViewById(R.id.login_loading)

        // TODO: Add error checking
        phoneNumber.addTextChangedListener(PhoneNumberFormattingTextWatcher())
        Utils.setupSubmitActionListener(phoneNumber, EditorInfo.IME_ACTION_GO, submit)

        submit.setOnClickListener {
            Utils.hideKeyboard(requireActivity())
            loading.visibility = View.VISIBLE
            Handler().postDelayed({
                loading.visibility = View.GONE
                Navigation.findNavController(view).navigate(R.id.action_login_to_verify)
//                    val intent = Intent(requireContext(), MainActivity::class.java)
//                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
//                    requireActivity().finish()
//                    startActivity(intent)
            }, 2000)
        }
    }

}