package com.sagar.rwelocme.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.sagar.rwelocme.R


class AddOptionsBottomSheet : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = inflater.inflate(R.layout.bottom_sheet_add, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val ctx = requireContext()

        view.findViewById<View>(R.id.ivAddText).setOnClickListener {
            Toast.makeText(ctx, "Add Text Clicked", Toast.LENGTH_SHORT).show()
            dismiss()
        }

        view.findViewById<View>(R.id.ivAddImage).setOnClickListener {
            Toast.makeText(ctx, "Add Image Clicked", Toast.LENGTH_SHORT).show()
            dismiss()
        }

        view.findViewById<View>(R.id.ivAddMusic).setOnClickListener {
            Toast.makeText(ctx, "Add Music Clicked", Toast.LENGTH_SHORT).show()
            dismiss()
        }
    }
}
