package com.noahgeren.trailangel.ui.login

import android.os.Bundle
import android.os.Handler
import android.telephony.PhoneNumberFormattingTextWatcher
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.noahgeren.trailangel.R
import com.noahgeren.trailangel.api.ApiService
import com.noahgeren.trailangel.api.Callback
import com.noahgeren.trailangel.models.User
import com.noahgeren.trailangel.ui.common.Utils
import com.noahgeren.trailangel.ui.login.verify.VerifyFragment
import retrofit2.Call
import retrofit2.Response

const val TAG = "LoginFragment"

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
            val phoneNumber = phoneNumber.text.toString()
            ApiService.requestLogin(User(phoneNumber), object: Callback<Map<String, Boolean>>() {
                override fun onResponse(call: Call<Map<String, Boolean>>, response: Response<Map<String, Boolean>>) {
                    if(response.body()?.get("sent") == true) {
                        val action = LoginFragmentDirections.actionLoginToVerify(phoneNumber)
                        Navigation.findNavController(view).navigate(action)
                    } else {
                        // TODO: Error here
                    }
                }

                override fun onComplete(call: Call<Map<String, Boolean>>) {
                    loading.visibility = View.GONE
                }

                override fun onFailure(call: Call<Map<String, Boolean>>, t: Throwable) {
                    t.message?.let {
                        Log.e(TAG, it)
                    }
                }

            })
        }
    }

}