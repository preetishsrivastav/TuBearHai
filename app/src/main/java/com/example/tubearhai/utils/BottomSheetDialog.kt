package com.example.tubearhai.utils

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.tubearhai.R
import com.example.tubearhai.databinding.BottomSheetDialogBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.lang.reflect.Array.set

class BottomSheetDialog(private val firstBrewed:String,private val description:String,private val foodPairing:List<String>): BottomSheetDialogFragment() {
     private lateinit var mBinding:BottomSheetDialogBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding= BottomSheetDialogBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

     mBinding.firstBrewed.text= resources.getString(R.string.first_brewed ) + firstBrewed
        mBinding.description.text= resources.getString(R.string.description)+description
        val textValue= foodPairing.joinToString("\n")
        mBinding.foodPairing.text = resources.getString(R.string.food_pairing) + textValue

    }


}