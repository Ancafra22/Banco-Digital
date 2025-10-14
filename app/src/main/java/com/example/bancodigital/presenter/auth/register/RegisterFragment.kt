package com.example.bancodigital.presenter.auth.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.bancodigital.databinding.FragmentRegisterBinding
import com.example.bancodigital.util.initToolbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : Fragment() {
    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

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
                            Toast.makeText(requireContext(), "Login realizado com sucesso!", Toast.LENGTH_SHORT).show()
                        }else{
                            Toast.makeText(requireContext(), "Confirme a sua senha", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }else{
                        Toast.makeText(requireContext(), "Crie a sua senha", Toast.LENGTH_SHORT).show()
                    }

                }else{
                    Toast.makeText(requireContext(), "Digito o seu número de telefone", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(requireContext(), "Digite o seu email", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(requireContext(), "Digite seu nome", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}