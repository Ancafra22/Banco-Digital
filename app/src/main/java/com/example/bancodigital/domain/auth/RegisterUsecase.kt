package com.example.bancodigital.domain.auth

import com.example.bancodigital.data.model.User
import com.example.bancodigital.data.repository.auth.AuthFirebaseDataSourceImpl
import javax.inject.Inject

class RegisterUsecase @Inject constructor(
    private val authFirebaseDataSourceImpl: AuthFirebaseDataSourceImpl
){
    suspend operator fun invoke(user: User): User {
        return authFirebaseDataSourceImpl.register(user)
    }
}