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
import com.noahgeren.trailangel.models.User
import com.noahgeren.trailangel.services.ApiService
import com.noahgeren.trailangel.ui.common.Utils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory

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
//            val retrofit = Retrofit.Builder()
//                .baseUrl("http://192.168.0.155:8080/")
//                .addConverterFactory(JacksonConverterFactory.create())
//                .build()
//            val apiService = retrofit.create(ApiService::class.java)
//            val requestLoginCall = apiService.requestLogin(User("417-773-9631"))
//            requestLoginCall.enqueue(object: Callback<Map<String, Boolean>> {
//                override fun onResponse(call: Call<Map<String, Boolean>>, response: Response<Map<String, Boolean>>) {
//                    Log.d(TAG, "Code: ${response.code()} Sent: ${response.body()?.get("sent")}")
//                    loading.visibility = View.GONE
//                }
//                override fun onFailure(call: Call<Map<String, Boolean>>, t: Throwable) {
//                    Log.d(TAG, "ERROR")
//                }
//            })
            Handler().postDelayed({
                loading.visibility = View.GONE
                Navigation.findNavController(view).navigate(R.id.action_login_to_verify)
            }, 2000)
        }
    }

}