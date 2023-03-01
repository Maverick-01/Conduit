package com.maverick.conduit.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.maverick.conduit.AuthViewModel
import com.maverick.conduit.databinding.FragmentLoginSignupBinding

class SignUpFragment : Fragment() {
    private var binding: FragmentLoginSignupBinding? = null
    val viewModel: AuthViewModel by activityViewModels()//by initializing your view model to main activity we have maintained its lifecycle throughout the app now.

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginSignupBinding.inflate(inflater, container, false)
        binding?.usernameEditText?.isVisible = true
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            submitButton.setOnClickListener {
                viewModel.signup(
                    usernameEditText.text.toString(),
                    email = emailEditText.text.toString(),
                    password = passwordEditText.text.toString()
                )
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}