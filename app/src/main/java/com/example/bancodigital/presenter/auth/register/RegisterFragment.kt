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
import com.example.bancodigital.util.StateView
import com.example.bancodigital.util.initToolbar
import com.example.bancodigital.util.showBottomSheet
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class RegisterFragment : Fragment() {
    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    private val registerViewModel: RegisterViewModel by viewModels()

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
    private fun initListeners (){
        binding.btnSend.setOnClickListener {
            validadeData()
        }
    }
    //função para recuperar e validar os dados digitados pelo usuário
    private fun validadeData() {
        //recuperando os dados digitados, tranformando em string e retirando os espaços em branco
        val name = binding.edtName.text.toString().trim()
        val email = binding.edtEmail.text.toString().trim()
        val mobileNumber = binding.edtMobileNumber.text.toString().trim()
        val password = binding.edtPassword.text.toString().trim()
        val confirmPassword = binding.edtConfirmPassword.text.toString().trim()

        if (name.isNotEmpty()) {
            if (email.isNotEmpty()) {
                if (mobileNumber.isNotEmpty()){
                    if (password.isNotEmpty()){
                        if (confirmPassword.isNotEmpty()){

                            val user = User(name, email, mobileNumber, password, confirmPassword)
                            registerUser(user)

                        }else{
                            showBottomSheet(message = getString(R.string.confirmPassword))
                        }
                    }else{
                        showBottomSheet(message = getString(R.string.password))
                    }

                }else{
                    showBottomSheet(message = getString(R.string.mobileNumber))
                }
            } else {
                showBottomSheet(message = getString(R.string.email))
            }
        } else {
            showBottomSheet(message = getString(R.string.name))
        }
    }

    private fun registerUser(user: User) {

        registerViewModel.register(user).observe(viewLifecycleOwner) { stateView ->

            when (stateView) {
                is StateView.Loading -> {
                    binding.progressBar.isVisible = true
                }

                is StateView.Sucess -> {
                    binding.progressBar.isVisible = false
                    findNavController().navigate(R.id.action_global_homeFragment)

                }

                is StateView.Error -> {
                    binding.progressBar.isVisible = false
                    showBottomSheet(message = getString(R.string.criateError))
                }
            }

        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}