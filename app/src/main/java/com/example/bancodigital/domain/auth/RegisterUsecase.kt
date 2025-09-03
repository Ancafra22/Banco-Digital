package com.example.bancodigital.domain.auth

import com.example.bancodigital.data.repository.auth.AuthFirebaseDataSourceImpl

class RegisterUsecase (
    private val authFirebaseDataSourceImpl: AuthFirebaseDataSourceImpl
){
    suspend operator fun invoke (name: String,
                                 email: String,
                                 mobilePhone: String,
                                 password: String){
        return authFirebaseDataSourceImpl.register(name, email, mobilePhone, password)
    }
}