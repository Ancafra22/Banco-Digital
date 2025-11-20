package com.example.bancodigital.presenter.auth.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.bancodigital.domain.auth.LoginUseCase
import com.example.bancodigital.util.StateView
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUsecase: LoginUseCase
) : ViewModel() {
    fun login(email: String, password: String) = liveData(Dispatchers.IO) {
        try {
            emit(StateView.Loading())

            loginUsecase.invoke(email, password)
            emit(StateView.Sucess(null))

        } catch (ex: Exception) {
            emit(StateView.Error(ex.message))
        }
    }
}