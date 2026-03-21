package com.sagar.rwelocme.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.sagar.rwelocme.R
import com.sagar.rwelocme.callback.OnOptionClickListener

class GalleryCameraBottomSheet : BottomSheetDialogFragment() {

    var listener: OnOptionClickListener? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = inflater.inflate(R.layout.gallary_camera_bottom_sheet, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val ctx = requireContext()

        view.findViewById<View>(R.id.iv_open_gallary).setOnClickListener {
            Toast.makeText(ctx, "gallery Clicked", Toast.LENGTH_SHORT).show()
            listener?.onGalleryClick()
            dismiss()
        }

        view.findViewById<View>(R.id.iv_open_camera).setOnClickListener {
            Toast.makeText(ctx, "camera Clicked", Toast.LENGTH_SHORT).show()
            listener?.onCameraClick()
            dismiss()
        }


    }
}