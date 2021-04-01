package com.noahgeren.trailangel.ui.login.verify

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.noahgeren.trailangel.R
import com.noahgeren.trailangel.api.ApiService
import com.noahgeren.trailangel.api.Callback
import com.noahgeren.trailangel.models.Verification
import com.noahgeren.trailangel.ui.MainActivity
import com.noahgeren.trailangel.ui.common.PreferenceUtils
import com.noahgeren.trailangel.ui.common.Utils
import retrofit2.Call
import retrofit2.Response

class VerifyFragment : Fragment(R.layout.fragment_verify) {

    private val args: VerifyFragmentArgs by navArgs()

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
            ApiService.login(Verification(args.phoneNumber, code.text.toString()), object: Callback<Map<String, Any>>() {
                override fun onResponse(call: Call<Map<String, Any>>, response: Response<Map<String, Any>>) {
                    if(response.body()?.get("login") == true) {
                        PreferenceUtils.setToken(response.body()?.get("token") as String)
                        val intent = Intent(requireContext(), MainActivity::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                        requireActivity().finish()
                        startActivity(intent)
                    } else {

                    }
                }
                override fun onFailure(call: Call<Map<String, Any>>, t: Throwable) {

                }
                override fun onComplete(call: Call<Map<String, Any>>) {
                    loading.visibility = View.GONE
                }
            })
        }
    }
}