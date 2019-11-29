package com.marvel.stark.ui.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.marvel.stark.R
import com.marvel.stark.di.factory.Injectable

/**Created by Jahongir on 6/15/2019.*/

class WalletSettingsFragment : Fragment(), Injectable {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_wallet_settings, container, false)
    }
}