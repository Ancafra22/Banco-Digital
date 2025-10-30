package com.example.bancodigital.presenter.auth.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.bancodigital.R
import com.example.bancodigital.data.model.User
import com.example.bancodigital.databinding.FragmentRegisterBinding
import com.example.bancodigital.presenter.profile.ProfileViewModel
import com.example.bancodigital.util.FirebaseHelper
import com.example.bancodigital.util.StateView
import com.example.bancodigital.util.initToolbar
import com.example.bancodigital.util.showBottomSheet
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class RegisterFragment : Fragment() {
    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    private val registerViewModel: RegisterViewModel by viewModels()
    private val profileViewModel: ProfileViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar(binding.toolbar)
        initListeners()
    }

    //função de evento de clique do botão enviar
    private fun initListeners() {
        binding.btnSend.setOnClickListener {
            validateData()
        }
    }

    //função para recuperar e validar os dados digitados pelo usuário
    private fun validateData() {
        //recuperando os dados digitados, tranformando em string e retirando os espaços em branco
        val name = binding.edtName.text.toString().trim()
        val email = binding.edtEmail.text.toString().trim()
        val mobileNumber = binding.edtMobileNumber.unMaskedText
        val password = binding.edtPassword.text.toString().trim()
        val confirmPassword = binding.edtConfirmPassword.text.toString().trim()

        if (name.isNotEmpty()) {
            if (email.isNotEmpty()) {
                if (mobileNumber?.isNotEmpty() == true) {

                    if (mobileNumber.length == 9) {

                        if (password.isNotEmpty()) {
                            if (confirmPassword.isNotEmpty()) {

                                val user =
                                    User(name, email, mobileNumber, password, confirmPassword)
                                registerUser(user)

                            } else {
                                showBottomSheet(message = getString(R.string.confirm_password))
                            }
                        } else {
                            showBottomSheet(message = getString(R.string.enter_password))
                        }

                    } else {
                        showBottomSheet(message = getString(R.string.local_mobile_phone_invalid))
                    }
                } else {
                    showBottomSheet(message = getString(R.string.enter_mobile_phone))
                }
            } else {
                showBottomSheet(message = getString(R.string.enter_email))
            }
        } else {
            showBottomSheet(message = getString(R.string.enter_name))
        }
    }

    private fun registerUser(user: User) {

        registerViewModel.register(user).observe(viewLifecycleOwner) { stateView ->

            when (stateView) {
                is StateView.Loading -> {
                    binding.progressBar.isVisible = true
                }

                is StateView.Sucess -> {
                    saveProfile(user)
                    findNavController().navigate(R.id.action_global_homeFragment)

                }

                is StateView.Error -> {
                    binding.progressBar.isVisible = false
                    showBottomSheet(
                        message = getString(
                            FirebaseHelper.validError(
                                stateView.message ?: ""
                            )
                        )
                    )
                }
            }

        }

    }

    private fun saveProfile(user: User) {
        profileViewModel.saveProfile(user).observe(viewLifecycleOwner) { stateView ->

            when (stateView) {
                is StateView.Loading -> {

                }

                is StateView.Sucess -> {
                    binding.progressBar.isVisible = false
                    findNavController().navigate(R.id.action_global_homeFragment)

                }

                is StateView.Error -> {
                    binding.progressBar.isVisible = false
                    showBottomSheet(
                        message = getString(
                            FirebaseHelper.validError(
                                stateView.message ?: ""
                            )
                        )
                    )
                }
            }

        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}