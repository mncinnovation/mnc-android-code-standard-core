package com.mncgroup.auth.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.mncgroup.auth.R
import com.mncgroup.auth.databinding.FragmentLoginBinding
import com.mncgroup.core.ui.BaseFragment
import com.mncgroup.core.util.ext.observeData
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : BaseFragment() {

    private var _binding: FragmentLoginBinding? = null

    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    private val viewModel: LoginViewModel by viewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentLoginBinding.inflate(inflater, container, false)

        with(binding) {
            btnLogin.setOnClickListener {
                viewModel.authLogin(etEmail.text.toString(), etPassword.text.toString())
            }

            tvForgot.setOnClickListener {
                findNavController().navigate(R.id.action_loginFragment_to_forgotPassFragment)
            }
            btnRegistration.setOnClickListener {
                findNavController().navigate(R.id.action_loginFragment_to_registrationFragment)
            }
        }
        with(viewModel) {
            userData.observeData(viewLifecycleOwner) {
                it?.let { userModel ->
                    if(userModel.isNotEmpty()){
                        binding.etEmail.setText("")
                        binding.etPassword.setText("")

                        activity?.onBackPressed()
                    }
                }
            }

            isLoading.observeData(viewLifecycleOwner) {
                if (it == true) {
                    binding.btnLogin.isEnabled = false
                    binding.btnLogin.text = getString(R.string.label_please_wait)
                } else {
                    binding.btnLogin.isEnabled = true
                    binding.btnLogin.text = getString(R.string.title_login)
                }
            }

            showErrorDialog.observeData(viewLifecycleOwner) {
                if (it == null) return@observeData
                val message = let { ctx -> it.withContext(mContext) ?: return@observeData }
                Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show()
            }
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}