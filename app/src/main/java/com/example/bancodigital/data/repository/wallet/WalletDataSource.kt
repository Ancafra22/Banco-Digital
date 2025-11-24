package com.example.bancodigital.data.repository.wallet

import com.example.bancodigital.data.model.Wallet

interface WalletDataSource {
    suspend fun initWallet(wallet: Wallet)
}