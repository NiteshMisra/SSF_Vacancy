@file:Suppress("DEPRECATION")

package `in`.indilabz.ssf_exam.fragment.main

import `in`.indilabz.ssf_exam.R
import `in`.indilabz.ssf_exam.databinding.FragmentProfileBinding
import `in`.indilabz.ssf_exam.model.User
import `in`.indilabz.yorneeds.utils.INDIPreferences
import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import java.io.ByteArrayOutputStream
import java.lang.Exception

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private lateinit var parentView: View

    private var user: User = INDIPreferences.user()!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false)
        parentView = binding.root

        setData()

        binding.imageCard.setOnClickListener {
            selectImage()
        }

        return  parentView
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 10 && resultCode == Activity.RESULT_OK && data != null) {
            val path: Uri = data.data!!
            try {
                val bitmap: Bitmap = MediaStore.Images.Media.getBitmap(activity!!.contentResolver, path)!!
                val byteStream = ByteArrayOutputStream()
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteStream)
                val imgBytes = byteStream.toByteArray()
                val image = Base64.encodeToString(imgBytes, Base64.DEFAULT)
                binding.profileImage.scaleType = ImageView.ScaleType.FIT_XY
                binding.profileImage.setImageBitmap(bitmap)
                INDIPreferences.image(image)

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun selectImage() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(intent, 10)
    }

    private fun setData(){

        val image: String = INDIPreferences.image()!!
        if (image.isNotEmpty()){
            val decodeBytes = Base64.decode(image, 0)
            val bitmap = BitmapFactory.decodeByteArray(decodeBytes, 0, decodeBytes.size)
            binding.profileImage.scaleType = ImageView.ScaleType.FIT_XY
            binding.profileImage.setImageBitmap(bitmap)
        }


        binding.applicantName.text = user.applicant_name
        binding.fatherName.text = user.father_name
        binding.motherName.text = user.mother_name
        binding.dob.text = user.dob
        binding.gender.text = user.gender
        binding.mail.text = user.mail_id
        binding.address.text = user.address
        binding.martialStatus.text = user.martial_status
        binding.mobile.text = user.mobile_number


       /*Glide.with(context!!)
            .load("${IMAGE_URL}${user.user_meta.profile_photo}")
            .into(binding.profilePreview)

        Glide.with(context!!)
            .load("${IMAGE_URL}${user.user_meta.signature_photo}")
            .into(binding.signaturePreview)*/
    }
}
