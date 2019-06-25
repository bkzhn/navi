package com.marvel.stark.ui.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.marvel.stark.R
import com.marvel.stark.di.factory.Injectable
import com.marvel.stark.di.factory.ViewModelFactory
import com.marvel.stark.models.AddWalletEntity
import com.marvel.stark.models.Status.*
import com.marvel.stark.utils.toastMessage
import com.marvel.stark.utils.visible
import kotlinx.android.synthetic.main.dialog_add_wallet.*
import javax.inject.Inject

/**Created by Jahongir on 6/18/2019.*/

class AddWalletDialog : BottomSheetDialogFragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val walletViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(AddWalletViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.dialog_add_wallet, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewModelObservers()
        wallet_fab.setOnClickListener { onAddWallet() }
    }

    private fun setupViewModelObservers() {
        walletViewModel.addWalletResult.observe(this, Observer {
            when (it.status) {
                SUCCESS -> dismiss()
                ERROR -> toastMessage(context, it.message)
                LOADING -> {
                }
            }
            showLoadingAnimation(it.status == LOADING)
        })
    }

    private fun onAddWallet() {
        if (!isWalletAddressValid())
            return
        val walletAddress = wallet_address.text.toString()
        val walletName = if (!wallet_name.text.isNullOrBlank()) {
            wallet_name.text.toString()
        } else {
            ""
        }
        val walletEntity = AddWalletEntity(walletAddress, walletName)
        walletViewModel.onAddWallet(walletEntity)
    }

    private fun isWalletAddressValid(): Boolean {
        wallet_layout.error = null
        if (wallet_address.text.isNullOrBlank()) {
            wallet_layout.error = getString(R.string.error_field_required)
            wallet_address.requestFocus()
            return false
        }
        return true
    }

    private fun showLoadingAnimation(show: Boolean) {
        wallet_loader.visible(show)
    }

}