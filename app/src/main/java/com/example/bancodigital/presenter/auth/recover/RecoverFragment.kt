package com.example.bancodigital.presenter.auth.recover

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.bancodigital.databinding.FragmentRecoverBinding
import com.example.bancodigital.util.StateView
import com.example.bancodigital.util.initToolbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint

class RecoverFragment : Fragment() {
    private var _binding: FragmentRecoverBinding? = null
    private val binding get() = _binding!!

    private val recoverViewModel: RecoverViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecoverBinding.inflate(inflater, container, false)
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
            validadeData()
        }
    }

    //função para recuperar e validar os dados digitados pelo usuário
    private fun validadeData() {
        //recuperando os dados digitados, tranformando em string e retirando os espaços em branco
        val email = binding.edtEmail.text.toString().trim()

        if (email.isNotEmpty()) {
            recoverAccount(email)
        } else {
            Toast.makeText(requireContext(), "Digite seu email", Toast.LENGTH_SHORT).show()
        }
    }

    private fun recoverAccount(email: String) {

        recoverViewModel.recover(email).observe(viewLifecycleOwner) { stateView ->

            when (stateView) {
                is StateView.Loading -> {
                    binding.progressBar.isVisible = true
                }

                is StateView.Sucess -> {
                    binding.progressBar.isVisible = false

                    Toast.makeText(
                        requireContext(),
                        "Email enviado com sucesso",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                is StateView.Error -> {
                    binding.progressBar.isVisible = false
                    Toast.makeText(
                        requireContext(), "Email ou senha inválido",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}