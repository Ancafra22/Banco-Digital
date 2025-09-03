package com.example.bancodigital.domain.auth

import com.example.bancodigital.data.repository.auth.AuthFirebaseDataSourceImpl

class RecoverUsecase (
    private val authFirebaseDataSourceImpl: AuthFirebaseDataSourceImpl
){
    suspend operator fun invoke (email: String){
        return authFirebaseDataSourceImpl.recover(email)
    }
}