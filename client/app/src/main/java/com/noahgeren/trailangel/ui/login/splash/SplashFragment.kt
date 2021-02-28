package com.noahgeren.trailangel.ui.login.splash

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.noahgeren.trailangel.R
import com.noahgeren.trailangel.ui.MainActivity

class SplashFragment : Fragment(R.layout.fragment_splash) {

    private val viewModel: SplashViewModel by viewModels({requireActivity()})

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.loggedIn.observe(viewLifecycleOwner) {
            if(it) {
                val intent = Intent(requireContext(), MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NO_ANIMATION or Intent.FLAG_ACTIVITY_CLEAR_TOP
                requireActivity().finish()
                startActivity(intent)
            } else {
                Navigation.findNavController(view).navigate(R.id.action_splash_to_login)
            }
        }
    }

}