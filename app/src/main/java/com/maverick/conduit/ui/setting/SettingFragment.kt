package com.maverick.conduit.ui.setting

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.maverick.conduit.AuthViewModel
import com.maverick.conduit.databinding.FragmentSettingBinding

class SettingFragment : Fragment() {
    private val authViewModel by activityViewModels<AuthViewModel>()
    private var binding: FragmentSettingBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSettingBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        authViewModel.user.observe({ lifecycle }) {
            binding?.apply {
                bioEditText.setText(it?.bio ?: "")
                emailEditText.setText(it?.email ?: "")
                imageEditText.setText(it?.image ?: "")
                usernameEditText.setText(it?.username ?: "")
            }
        }

        binding?.apply {
            submitButton.setOnClickListener {
                authViewModel.update(
                    bioEditText.text.toString(),
                    usernameEditText.text.toString().takeIf { it.isNotBlank() },
                    emailEditText.text.toString().takeIf { it.isNotBlank() },
                    passwordEditText.text.toString()
                        .takeIf { it.isNotBlank() },//if field is blank it will set to null;
                    imageEditText.text.toString()
                )
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}