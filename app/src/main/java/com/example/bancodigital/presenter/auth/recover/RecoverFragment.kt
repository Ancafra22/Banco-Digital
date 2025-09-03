package com.example.bancodigital.presenter.auth.recover

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.bancodigital.R
import com.example.bancodigital.databinding.FragmentRecoverBinding

class RecoverFragment : Fragment() {
    private var _binding: FragmentRecoverBinding? = null
    private val binding get() = _binding!!

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
            Toast.makeText(requireContext(), "Login realizado com sucesso!", Toast.LENGTH_SHORT)
                .show()
        } else {
            Toast.makeText(requireContext(), "Digite seu email", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}