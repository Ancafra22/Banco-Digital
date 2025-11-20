package com.example.bancodigital.data.model

import com.google.firebase.database.FirebaseDatabase

data class User(
    var id: String = "",
    val name: String = "",
    val email: String = "",
    val mobilePhone: String = "",
    val password: String = "",
    val confirmPassword: String = ""
) {
    init {
        this.id = FirebaseDatabase.getInstance().reference.push().key ?: ""
    }
}
