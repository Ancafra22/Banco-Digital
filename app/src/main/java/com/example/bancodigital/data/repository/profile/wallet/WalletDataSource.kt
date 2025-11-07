package com.example.bancodigital.data.repository.profile.wallet

import com.example.bancodigital.data.model.Wallet

interface WalletDataSource {
    suspend fun initWallet(wallet: Wallet)
}