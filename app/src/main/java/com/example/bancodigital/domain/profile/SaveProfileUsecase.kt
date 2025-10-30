package com.example.bancodigital.domain.profile

import com.example.bancodigital.data.model.User
import com.example.bancodigital.data.repository.profile.ProfileRepositoryImpl
import javax.inject.Inject

class SaveProfileUsecase @Inject constructor(
    private val profileRepositoryImpl: ProfileRepositoryImpl
) {

    suspend operator fun invoke(user: User) {
        return profileRepositoryImpl.saveProfile(user)
    }
}