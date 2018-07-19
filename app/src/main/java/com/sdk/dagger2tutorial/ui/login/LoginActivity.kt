package com.sdk.dagger2tutorial.ui.login

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import com.sdk.dagger2tutorial.R

class LoginActivity : AppCompatActivity(),
        MobileNoFragment.MobileNoFragmentListener, PinNoFragment.PinNoFragmentListener {

    private val mobileNoFragment: MobileNoFragment = MobileNoFragment.newInstance()
    private val pinNoFragment: PinNoFragment = PinNoFragment.newInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        supportFragmentManager.beginTransaction().add(R.id.container, mobileNoFragment).commit()
    }

    override fun invalidMobileNoErrorMessage(): String {
        return getString(R.string.invalid_mobile_no)
    }

    override fun onValidMobileNo(fragment: MobileNoFragment, mobileNo: String) {
        supportFragmentManager.beginTransaction()
                .setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left)
                .replace(R.id.container, pinNoFragment).commit()
    }

    override fun onCancelPinNo(fragment: PinNoFragment) {
        supportFragmentManager.beginTransaction()
                .setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right)
                .replace(R.id.container, mobileNoFragment).commit()
    }

    override fun onValidPinNo(fragment: PinNoFragment, pinNo: String) {
        fragment.view?.let { Snackbar.make(it, "Bingo", Snackbar.LENGTH_SHORT).show() }
    }
}
