package com.example.bancodigital.data.repository.profile

import com.example.bancodigital.data.model.User

interface ProfileDataSource {
    suspend fun saveProfile(user: User)
}