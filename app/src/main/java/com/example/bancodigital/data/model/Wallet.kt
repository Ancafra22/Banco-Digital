package com.example.bancodigital.data.model

import com.google.firebase.database.FirebaseDatabase

data class Wallet(
    var id: String,
    var userId: String,
    var balance: Float = 0f
) {
    init {
        this.id = FirebaseDatabase.getInstance().reference.push().key ?: ""
    }
}