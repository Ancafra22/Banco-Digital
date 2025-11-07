package com.example.bancodigital.domain.profile

import com.example.bancodigital.data.model.User
import com.example.bancodigital.data.repository.profile.ProfileDataSourceImpl
import javax.inject.Inject

class SaveProfileUseCase @Inject constructor(
    private val profileRepositoryImpl: ProfileDataSourceImpl
) {

    suspend operator fun invoke(user: User) {
        return profileRepositoryImpl.saveProfile(user)
    }
}