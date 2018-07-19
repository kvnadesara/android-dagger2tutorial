package com.sdk.dagger2tutorial.ui.login

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sdk.dagger2tutorial.R
import kotlinx.android.synthetic.main.fragment_mobile_no.*

class MobileNoFragment : Fragment() {

    private var mListener: MobileNoFragmentListener? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_mobile_no, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnMobileNoContinue.setOnClickListener({ validateMobileNoAndDelegate() })
    }

    private fun validateMobileNoAndDelegate() {
        tilPinNo.error = null
        val mobileNo = txtMobileNo.text.toString().trim()
        if (!Patterns.PHONE.matcher(mobileNo).matches()) {
            tilPinNo.error = mListener?.invalidMobileNoErrorMessage()
            return
        }

        mListener?.onValidMobileNo(this, mobileNo)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is MobileNoFragmentListener) {
            mListener = context
        } else {
            throw RuntimeException(context.toString() + " must implement MobileNoFragmentListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }

    interface MobileNoFragmentListener {
        fun invalidMobileNoErrorMessage(): String
        fun onValidMobileNo(fragment: MobileNoFragment, mobileNo: String)
    }

    companion object {
        fun newInstance(): MobileNoFragment {
            return MobileNoFragment()
        }
    }
}
