package com.example.bancodigital.presenter.auth.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.bancodigital.domain.auth.RegisterUseCase
import com.example.bancodigital.util.StateView
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase
) : ViewModel() {
    fun register(
        name: String,
        email: String,
        mobilePhone: String,
        password: String,
        confirmPassword: String
    ) = liveData(Dispatchers.IO) {
        try {
            emit(StateView.Loading())

            val user = registerUseCase.invoke(name, email, mobilePhone, password, confirmPassword)
            emit(StateView.Success(user))

        } catch (ex: Exception) {
            emit(StateView.Error(ex.message))
        }
    }
}