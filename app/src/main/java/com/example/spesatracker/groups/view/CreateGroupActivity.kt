package com.example.spesatracker.groups.view

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.launch
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import com.example.spesatracker.R
import com.example.spesatracker.base.BaseActivity
import com.example.spesatracker.databinding.CreateGroupActivityBinding
import com.squareup.picasso.Picasso

class CreateGroupActivity : BaseActivity() {
    private lateinit var binding: CreateGroupActivityBinding

    private val pickImageResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val imageUri: Uri? = result.data?.data
            if (imageUri != null) {
                // Use Picasso to load the selected image into the ImageView via binding
                Picasso.get().load(imageUri).into(binding.groupIcon)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.create_group_activity)

        imageupload()
    }

    private fun imageupload() {
        binding.groupIcon.setOnClickListener {
            showImagePickDialog()
        }
        binding.changePhotoText.setOnClickListener {
            showImagePickDialog()

        }
    }


    private fun showImagePickDialog() {
        val options = arrayOf("Take Photo", "Choose from Gallery", "Cancel")
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Add Photo")
        builder.setItems(options) { dialog, which ->
            when (which) {
                0 -> { // Take Photo
                    // Intent to open the camera (Requires camera permissions and setup)
                    Toast.makeText(this, "Camera functionality coming soon!", Toast.LENGTH_SHORT)
                        .show()
                }

                1 -> { // Choose from Gallery
                    val galleryIntent =
                        Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                    pickImageResultLauncher.launch(galleryIntent)
                }

                2 -> { // Cancel
                    dialog.dismiss()
                }
            }
        }
        builder.show()
    }

}