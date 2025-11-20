package com.example.bancodigital.presenter.wallet

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.bancodigital.data.model.Wallet
import com.example.bancodigital.domain.wallet.InitWalletUseCase
import com.example.bancodigital.util.StateView
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class WalletViewModel @Inject constructor(
    private val initWalletUseCase: InitWalletUseCase
) : ViewModel() {

    fun initWallet(wallet: Wallet) = liveData(Dispatchers.IO) {
        try {
            emit(StateView.Loading())

            initWalletUseCase.invoke(wallet)
            emit(StateView.Success(null))

        } catch (ex: Exception) {
            emit(StateView.Error(ex.message))
        }
    }

}