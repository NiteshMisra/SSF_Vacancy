package `in`.indilabz.ssf_exam.dialog

import `in`.indilabz.review_application.rest.RetrofitInstance
import `in`.indilabz.ssf_exam.INDIMaster
import `in`.indilabz.ssf_exam.R
import `in`.indilabz.ssf_exam.activity.MainActivity
import `in`.indilabz.ssf_exam.databinding.DialogRegistrationBinding
import `in`.indilabz.ssf_exam.model.User
import `in`.indilabz.ssf_exam.rest.APIHelper
import `in`.indilabz.ssf_exam.utils.Constants
import `in`.indilabz.ssf_exam.utils.Toaster
import `in`.indilabz.ssf_exam.utils.Validator
import `in`.indilabz.yorneeds.utils.INDIPreferences
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.bumptech.glide.Glide
import com.esafirm.imagepicker.features.ImagePicker
import com.esafirm.imagepicker.features.ReturnMode
import com.esafirm.imagepicker.model.Image
import dmax.dialog.SpotsDialog
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.json.JSONException
import org.json.JSONObject
import java.io.File
import java.net.URLEncoder
import java.security.SecureRandom
import java.text.DecimalFormat
import java.util.*
import kotlin.collections.ArrayList


class RegistrationDialog: DialogFragment() {

    private lateinit var parentView: View
    private lateinit var binding: DialogRegistrationBinding

    private var type: String = ""
    private var actionId: String = ""

    val builder: SpotsDialog.Builder = SpotsDialog.Builder()
    private lateinit var dialog: android.app.AlertDialog

    private var isProfile : Boolean = true

    private var profile : ArrayList<Image> = ArrayList()
    private var signature : ArrayList<Image> = ArrayList()

    private var user: User = INDIPreferences.user()!!

    private lateinit var done: (Boolean) -> Unit

    fun showForm(
        manager: FragmentManager, type: String,
        actionId: String,
        done: (Boolean) -> Unit
    ){

        this.type = type
        this.actionId = actionId
        this.done = done
        this.setStyle(STYLE_NORMAL, R.style.DialogFragment);
        this.show(manager, manager.toString())
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.dialog_registration, container, false)
        parentView = binding.root

        dialog = builder.setContext(context!!).build()!!

        return parentView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbar.setNavigationOnClickListener{
            this.dismissAllowingStateLoss()
        }

        binding.mobile.text = INDIPreferences.phone()

        binding.gender.setOnClickListener {
            gender()
        }

        binding.martialStatus.setOnClickListener {
            martialStatus()
        }

        binding.submit.setOnClickListener {


            if(Validator.validateName(binding.applicantName)){

                if(Validator.validateName(binding.fatherName)){

                    if(Validator.validateName(binding.motherName)){

                        if(Validator.validateLength(binding.dob, 10)){

                            if(!binding.gender.text.toString().contains("gender")){

                                if(Validator.validateEmail(binding.mail)) {

                                    if (!binding.martialStatus.text.toString().contains("status")) {

                                        if(Validator.validateAddress(binding.address)){

                                            if(profile.size>0){

                                                if(signature.size>0){

                                                    submitForm()
                                                }
                                                else{
                                                    Toaster.longt("Invalid signature image")
                                                }

                                            }else{
                                                Toaster.longt("Invalid profile image")
                                            }
                                        }
                                    }
                                    else{
                                        Toaster.longt("Invalid martial status")
                                    }
                                }
                            }
                            else{
                                Toaster.longt("Invalid gender")
                            }
                        }
                    }
                }
            }
        }

        binding.clickProfileAction.setOnClickListener {

            if(binding.clickProfileAction.text.contains("remove")){
                binding.clickProfileAction.text = ("Click to add profile image")

                Glide.with(context!!)
                    .load(R.color.md_grey_300)
                    .into(binding.profilePreview)
            }
            else{
                openProfile()
            }

        }

        binding.clickSignatureAction.setOnClickListener {

            if(binding.clickSignatureAction.text.contains("remove")){
                binding.clickSignatureAction.text = "Click to add signature image"

                Glide.with(context!!)
                    .load(R.color.md_grey_300)
                    .into(binding.signaturePreview)
            }
            else{
                openSignature()
            }

        }

        setData()
    }

    private fun setData(){

        if(actionId.equals("1")){

            binding.title.text = ("PTI Exam")
        }
        else if(actionId.equals("2")){

            binding.title.text = ("Asst. PTI Exam")
        }
        else{

            binding.title.text = (actionId)
        }

        binding.applicantName.setText(user.applicant_name)
        binding.fatherName.setText(user.father_name)
        binding.motherName.setText(user.mother_name)
        binding.dob.setText(user.dob)
        binding.gender.setText(user.gender)
        binding.mail.setText(user.mail_id)
        binding.address.setText(user.address)
        binding.martialStatus.setText(user.martial_status)

        /*Glide.with(context!!)
            .load("${IMAGE_URL}${user.user_meta.profile_photo}")
            .into(binding.profilePreview)

        Glide.with(context!!)
            .load("${IMAGE_URL}${user.user_meta.signature_photo}")
            .into(binding.signaturePreview)*/
    }

    private fun submitForm(){

        dialog.show()

        val profileUpload = MultipartBody.Part.createFormData(
            "profile_photo",
            URLEncoder.encode(profile.get(0).getName(), "utf-8"),
            RequestBody.create(
                MediaType.parse("image/*"),
                File(profile.get(0).getPath())
            )
        )

        val signatureUpload = MultipartBody.Part.createFormData(
            "signature_photo",
            URLEncoder.encode(signature.get(0).getName(), "utf-8"),
            RequestBody.create(
                MediaType.parse("image/*"),
                File(signature.get(0).getPath())
            )
        )

        /*RetrofitInstance.getRetrofit(
            INDIMaster.api().registerWithOutPayment(
                INDIPreferences.user()!!.id,
                (binding.applicantName.text.toString()),
                (binding.fatherName.text.toString()),
                (binding.motherName.text.toString()),
                (binding.dob.text.toString()),
                (binding.gender.text.toString()),
                (binding.mail.text.toString()),
                (binding.address.text.toString()),
                (binding.martialStatus.text.toString()),
                type,
                actionId,
                profileUpload,
                signatureUpload
            )
            , result)*/
    }

    private fun gender(){

        val builder = AlertDialog.Builder(context!!)
        builder.setTitle("Select gender")

        val gender = arrayOf("Male", "Female")
        builder.setItems(gender) { dialog, which ->
            when (which) {
                0 -> { binding.gender.setText("Male") }
                1 -> { binding.gender.setText("Female") }
            }
        }

        val dialog = builder.create()
        dialog.show()
    }

    private fun martialStatus(){

        val builder = AlertDialog.Builder(context!!)
        builder.setTitle("Select martial status")

        val gender = arrayOf("Married", "Single", "Divorced")
        builder.setItems(gender) { dialog, which ->
            when (which) {
                0 -> { binding.martialStatus.text = ("Married") }
                1 -> { binding.martialStatus.text = ("Single") }
                2 -> { binding.martialStatus.text = ("Divorced") }
            }
        }

        val dialog = builder.create()
        dialog.show()
    }

    private fun getData(){

        dialog.show()

        RetrofitInstance.getRetrofit(
            INDIMaster.api().login(
                INDIPreferences.phone()!!
            )
            , authResult)
    }

    private fun openProfile() {

        isProfile = true

        ImagePicker.create(this)
            .returnMode(ReturnMode.ALL)
            .folderMode(false)
            .toolbarFolderTitle("Folder")
            .toolbarImageTitle("Tap to select")
            .toolbarArrowColor(Color.WHITE)
            .single()
            .showCamera(true)
            .enableLog(false)
            .start()
    }

    private fun openSignature() {

        isProfile = false

        ImagePicker.create(this)
            .returnMode(ReturnMode.ALL)
            .folderMode(false)
            .toolbarFolderTitle("Folder")
            .toolbarImageTitle("Tap to select")
            .toolbarArrowColor(Color.WHITE)
            .single()
            .showCamera(true)
            .enableLog(false)
            .start()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (ImagePicker.shouldHandle(requestCode, resultCode, data)) {

            val images =
                ImagePicker.getImages(data)

            if(isProfile){

                if (images.size > 0) {
                    profile.clear()
                    profile.addAll(images)

                    binding.clickProfileAction.text = ("Click to remove image")
                    Glide.with(context!!)
                        .applyDefaultRequestOptions(INDIMaster.requestImage()!!)
                        .load(images.get(0).getPath())
                        .into(binding.profilePreview)
                }
            }
            else{

                if (images.size > 0) {

                    signature.clear()
                    signature.addAll(images)

                    binding.clickSignatureAction.text = ("Click to remove image")
                    Glide.with(context!!)
                        .applyDefaultRequestOptions(INDIMaster.requestImage()!!)
                        .load(images.get(0).getPath())
                        .into(binding.signaturePreview)
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }


    private fun success(){

        val dialog:AlertDialog = AlertDialog.Builder(context!!).create()
        dialog.setTitle("Successful")
        dialog.setMessage("Your registration has been completed successfully")
        dialog.setCancelable(false)
        dialog.setButton(AlertDialog.BUTTON_POSITIVE, "Ok") { dialogInterface: DialogInterface, i: Int ->
            dialog.dismiss()
            dismissAllowingStateLoss()
            done.invoke(true)
        }
        dialog.show()
    }

    private fun failed(){

        val dialog:AlertDialog = AlertDialog.Builder(context!!).create()
        dialog.setTitle("Failed")
        dialog.setMessage("Error while registering, please try again later!")
        dialog.setCancelable(false)
        dialog.setButton(AlertDialog.BUTTON_POSITIVE, "Ok") { dialogInterface: DialogInterface, i: Int ->
            dialog.dismiss()
            dismissAllowingStateLoss()
        }
        dialog.show()
    }

    private val result = { _:Int, bool: Boolean, value: String ->

        dialog.dismiss()

        if(bool){

            if(actionId == "1" || actionId == "2"){

                pay(actionId)
            }
            else{
                getData()
            }

        }
        else{
            failed()
        }
    }


    private val authResult = { _: Int, bool:Boolean, value:String ->


        dialog.dismiss()

        if(bool){

            val user = INDIMaster.gson.fromJson(APIHelper.result(value), User::class.java)

            INDIPreferences.user(user)
            success()
        }
        else{
            failed()
        }
    }

    private fun pay(string: String){

        var product = ""

        product = if(string.equals("1")){
            "PTI EXAM"
        } else{
            "Asst. PTI Exam"
        }

        val obj = JSONObject()

        val random : String = INDIMaster.getRandomString(22)!!

        try {
            obj.put("merchant_id", "314950505369260")
            obj.put("access_token", Constants.APP_ACCESS_TOKEN)
            obj.put("customer_name", binding.applicantName.text.toString())
            obj.put("customer_email", binding.mail.text.toString())
            obj.put("customer_phone", binding.mobile.text.toString())
            obj.put("product_name", product)
            obj.put(
                "order_no",
                random
            ) // order no. should have 10 to 30 character in numeric format
            obj.put("amount", "10") // minimum amount should be 10
            obj.put("isLive", true) // need to send false if you are in sandbox mode
        } catch (e: JSONException) {

            Toaster.longt(e.message!!)
        }

        //PaykunApiCall.Builder((activity as MainActivity)).sendJsonObject(obj)
    }


}

/*
@Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
public fun getResults(message: Events.PaymentMessage) {

    if(message.results.equals(PaykunHelper.MESSAGE_SUCCESS, ignoreCase = true)){
        // do your stuff here
        // message.getTransactionId() will return your failed or succeed transaction id
        */
/* if you want to get your transaction detail call message.getTransactionDetail()
         *  getTransactionDetail return all the field from server and you can use it here as per your need
         *  For Example you want to get Order id from detail use message.getTransactionDetail().order.orderId */// if(!TextUtils.isEmpty(message.getTransactionId())) {
//
// dialog.show()
//
// val profileUpload = MultipartBody.Part.createFormData(
// "profile_photo",
// URLEncoder.encode(profile.get(0).getName(), "utf-8"),
// RequestBody.create(
// MediaType.parse("image/*"),
// File(profile.get(0).getPath())
// )
// )
//
// val signatureUpload = MultipartBody.Part.createFormData(
// "signature_photo",
// URLEncoder.encode(signature.get(0).getName(), "utf-8"),
// RequestBody.create(
// MediaType.parse("image/*"),
// File(signature.get(0).getPath())
// )
// )
//
// RetrofitInstance.getRetrofit(
// INDIMaster.api().registerWithPayment(
// INDIPreferences.user()!!.id,
// (binding.applicantName.text.toString()),
// (binding.fatherName.text.toString()),
// (binding.motherName.text.toString()),
// (binding.dob.text.toString()),
// (binding.gender.text.toString()),
// (binding.mail.text.toString()),
// (binding.address.text.toString()),
// (binding.martialStatus.text.toString()),
// (type),
// actionId,
// message.getTransactionDetail().order.orderId,
// profileUpload,
// signatureUpload
// )
// , result)
//
// actionId = ""
//
// Log.v(" TRANSACTION_ID ",message.getTransactionDetail().order.orderId);
// }
// }
// else{
// failed()
// Toaster.longt(message.getResults())
// }
// }
//
// override fun onStart() {
// super.onStart()
// // Register this activity to listen to event.
// GlobalBus.getBus().register(this)
// }
//
// override fun onStop() {
// super.onStop()
// // Unregister from activity
// GlobalBus.getBus().unregister(this)
// }*