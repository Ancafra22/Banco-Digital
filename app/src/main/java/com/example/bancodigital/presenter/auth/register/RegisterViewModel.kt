package com.example.bancodigital.presenter.auth.register

import androidx.lifecycle.liveData
import com.example.bancodigital.data.model.User
import com.example.bancodigital.domain.auth.RegisterUsecase
import com.example.bancodigital.util.StateView
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerUsecase: RegisterUsecase
) {
    fun register(user: User) = liveData(Dispatchers.IO) {
        try {
            emit(StateView.Loading())

            registerUsecase.invoke(user)
            emit(StateView.Sucess(user))

        } catch (ex: Exception) {
            emit(StateView.Error(ex.message))
        }
    }
}