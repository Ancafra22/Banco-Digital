package com.example.bancodigital.data.repository.profile

import com.example.bancodigital.data.model.User
import com.example.bancodigital.util.FirebaseHelper
import com.google.firebase.database.FirebaseDatabase
import javax.inject.Inject
import kotlin.coroutines.suspendCoroutine

class ProfileRepositoryImpl @Inject constructor(
    database: FirebaseDatabase
) : ProfileRepository {

    private val profileReference = database.reference
        .child("profile")
        .child(FirebaseHelper.getUserId())

    override suspend fun saveProfile(user: User) {
        return suspendCoroutine { continuation ->
            profileReference.setValue(user).addOnCompleteListener { task ->

                if (task.isSuccessful) {
                    continuation.resumeWith(Result.success(Unit))
                } else {
                    task.exception?.let {
                        continuation.resumeWith(Result.failure(it))
                    }

                }

            }
        }
    }
}