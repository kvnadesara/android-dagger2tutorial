package com.sdk.dagger2tutorial.ui.login

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sdk.dagger2tutorial.R
import kotlinx.android.synthetic.main.fragment_pin_no.*

class PinNoFragment : Fragment() {

    private var mListener: PinNoFragmentListener? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_pin_no, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnPinNoContinue.setOnClickListener({ validateMobileNoAndDelegate() })
        btnPinNoCancel.setOnClickListener({ mListener?.onCancelPinNo(this) })
    }

    private fun validateMobileNoAndDelegate() {
        tilPinNo.error = null
        val pinNo = txtPinNo.text.toString().trim()
        if (TextUtils.isEmpty(pinNo)) {
            tilPinNo.error = "Please enter valid pin no."
            return
        }

        mListener?.onValidPinNo(this, pinNo)
    }

    fun setInvalidPinError(errorMessage: String) {
        tilPinNo.error = errorMessage
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is PinNoFragmentListener) {
            mListener = context
        } else {
            throw RuntimeException(context.toString() + " must implement PinNoFragmentListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }

    interface PinNoFragmentListener {
        fun onCancelPinNo(fragment: PinNoFragment)
        fun onValidPinNo(fragment: PinNoFragment, pinNo: String)
    }

    companion object {
        fun newInstance(): PinNoFragment {
            return PinNoFragment()
        }
    }
}
