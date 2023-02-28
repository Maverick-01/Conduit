package com.maverick.conduit.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.maverick.conduit.AuthViewModel
import com.maverick.conduit.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {
    private var binding: FragmentLoginBinding? = null
    val viewModel: AuthViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            loginButton.setOnClickListener {
                viewModel.login(email = emailEditText.text.toString(), password = passwordEditText.text.toString())
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}