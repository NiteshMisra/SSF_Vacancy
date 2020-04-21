package `in`.indilabz.ssf_exam.activity

import `in`.indilabz.review_application.rest.RetrofitInstance
import `in`.indilabz.ssf_exam.INDIMaster
import `in`.indilabz.ssf_exam.R
import `in`.indilabz.ssf_exam.adapter.RegistrationAdapter
import `in`.indilabz.ssf_exam.fragment.registration.CurriculumFragment
import `in`.indilabz.ssf_exam.fragment.registration.DocumentFragment
import `in`.indilabz.ssf_exam.fragment.registration.PersonalFragment
import `in`.indilabz.ssf_exam.model.User
import `in`.indilabz.ssf_exam.rest.APIHelper
import `in`.indilabz.ssf_exam.utils.Constants
import `in`.indilabz.ssf_exam.utils.Constants.Companion.DEBUG
import `in`.indilabz.ssf_exam.utils.Constants.Companion.FURL
import `in`.indilabz.ssf_exam.utils.Constants.Companion.ROOT_URL
import `in`.indilabz.ssf_exam.utils.Constants.Companion.SURL
import `in`.indilabz.ssf_exam.utils.Toaster
import `in`.indilabz.yorneeds.utils.INDIPreferences
import android.app.Activity
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.viewpager.widget.ViewPager
import com.android.volley.NoConnectionError
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.esafirm.imagepicker.model.Image
import com.google.android.material.tabs.TabLayout
import com.payumoney.core.PayUmoneyConfig
import com.payumoney.core.PayUmoneySdkInitializer.PaymentParam
import com.payumoney.core.entity.TransactionResponse
import com.payumoney.sdkui.ui.utils.PayUmoneyFlowManager
import com.payumoney.sdkui.ui.utils.ResultModel
import dmax.dialog.SpotsDialog
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.json.JSONException
import org.json.JSONObject
import java.io.File
import java.net.URLEncoder

class RegistrationActivity : AppCompatActivity(), Constants {

    private lateinit var curriculum: CurriculumFragment
    private lateinit var document: DocumentFragment
    private lateinit var personal: PersonalFragment

    private val fragments: ArrayList<Fragment> = ArrayList()
    //private lateinit var submit: Button

    val builder: SpotsDialog.Builder = SpotsDialog.Builder()
    private lateinit var dialog: android.app.AlertDialog
    private lateinit var fragmentManager: FragmentManager
    private var currentFragment : Int = 0

    private var actionId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        //submit = findViewById(R.id.submit)

        dialog = builder.setContext(this).build()!!

        try{
            actionId = intent.getIntExtra("id", 0)
        }
        catch (e: Exception){
            Toaster.longt(e.toString())
        }

        curriculum = CurriculumFragment().curriculum()
        document = DocumentFragment().document()
        personal = PersonalFragment().personal()

        fragments.add(personal)
        fragments.add(curriculum)
        fragments.add(document)

        fragmentManager = supportFragmentManager
        fragmentManager.beginTransaction()
            .replace(R.id.view_pager,personal)
            .commit()

        /*val sectionsPagerAdapter = RegistrationAdapter(fragments,
            supportFragmentManager)
        val viewPager: ViewPager = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        viewPager.currentItem = 1*/

        /*submit.setOnClickListener {

            if(currentFragment == 0){

                currentFragment += 1
                addFragment(curriculum)
            }

            else if(currentFragment == 1){

                currentFragment += 1
                addFragment(document)
            }

            else if(currentFragment == 2){
                submit()
            }
        }*/


        /*viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {

                if(position == 0){

                    submit.text = "NEXT"
                }

                if(position == 1){
                    submit.text = "NEXT"
                }

                if(position == 2){
                    submit.text = "SUBMIT"
                }
            }

            override fun onPageSelected(position: Int) {

            }
        })*/


    }

    private fun addFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction()
            .replace(R.id.view_pager,fragment)
            .addToBackStack("Tag")
            .commit()
    }

    fun submitClick(){

        if(currentFragment == 0){

            currentFragment += 1
            addFragment(curriculum)
        }

        else if(currentFragment == 1){

            currentFragment += 1
            addFragment(document)
        }

        else if(currentFragment == 2){
            submit()
        }
    }

    private fun submit(){

        dialog.show()

        RetrofitInstance.getRetrofit(
            INDIMaster.api().registerWithOutPayment(
                INDIPreferences.user()!!.id,
                (personal.applicantName()),
                (personal.fatherName()),
                (personal.motherName()),
                (personal.dob()),
                (personal.getGender()),
                (INDIPreferences.user()!!.mobile_number),
                (personal.email()),
                (personal.address()),
                (personal.martial()),
                "",
                actionId,
                curriculum.highSchoolCurriculum(),
                curriculum.interCurriculum(),
                curriculum.gradCurriculum(),
                curriculum.dipCurriculum(),
                if(document.profile.size>0) media(document.profile[0],"profile_photo") else null,
                if(document.signature.size>0) media(document.signature[0],"signature_photo") else null,
                if(document.aadhaar.size>0) media(document.aadhaar[0],"aadhar_card_photo") else null,
                if(document.highschool.size>0) media(document.highschool[0],"high_school") else null,
                if(document.intermediate.size>0) media(document.intermediate[0],"intermediate") else null,
                if(document.graduation.size>0) media(document.graduation[0],"graduation") else null,
                if(document.diploma.size>0) media(document.diploma[0],"other_diploma") else null
            )
            , result)
    }

    private fun media(path: Image, key: String): MultipartBody.Part{

        return MultipartBody.Part.createFormData(
            key,
            URLEncoder.encode(File(path.path).getName(), "utf-8"),
            RequestBody.create(
                MediaType.parse("image/*"),
                File(File(path.path).getPath())
            )
        )
    }

    private fun getData(){

        dialog.show()

        RetrofitInstance.getRetrofit(
            INDIMaster.api().login(
                INDIPreferences.phone()!!
            )
            , authResult)
    }

    private fun success(){

        try{
            val dialog: AlertDialog = AlertDialog.Builder(this).create()
            dialog.setTitle("Successful")
            dialog.setMessage("You have successfully registered for the programme. We have sent you a conformation message.")
            dialog.setCancelable(false)
            dialog.setButton(AlertDialog.BUTTON_POSITIVE, "Ok") { dialogInterface: DialogInterface, i: Int ->
                dialog.dismiss()
                finish()
            }
            dialog.show()
        }
        catch (e: Exception){
            e.printStackTrace()
        }
    }

    private fun failed(){

       try{
           val dialog: AlertDialog = AlertDialog.Builder(this).create()
           dialog.setTitle("Failed")
           dialog.setMessage("Error while registering, please try again later!")
           dialog.setCancelable(false)
           dialog.setButton(AlertDialog.BUTTON_POSITIVE, "Ok") { dialogInterface: DialogInterface, i: Int ->
               finish()
           }
           dialog.show()
       }
       catch (e: Exception){
           e.printStackTrace()
       }
    }


    private val result = { _:Int, bool: Boolean, value: String ->

        dialog.dismiss()

        if(bool){

            if(actionId>0){

                addMoney("1034.00")
            }
            else{
                getData()
            }

            //getData()

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

    private fun addMoney(amount: String) {

        val payUmoneyConfig = PayUmoneyConfig.getInstance()
        payUmoneyConfig.payUmoneyActivityTitle = "Add money To Wallet"
        payUmoneyConfig.doneButtonText = "Add Money"
        val builder = PaymentParam.Builder()
        builder.setAmount(amount)
            .setTxnId(System.currentTimeMillis().toString() + "")
            .setPhone(INDIPreferences.user()!!.mobile_number)
            .setProductName("Wallet")
            .setEmail(personal.email())
            .setFirstName(INDIPreferences.user()!!.applicant_name)
            .setsUrl(SURL)
            .setfUrl(FURL)
            .setUdf1("")
            .setUdf2("")
            .setUdf3("")
            .setUdf4("")
            .setUdf5("")
            .setUdf6("")
            .setUdf7("")
            .setUdf8("")
            .setUdf9("")
            .setUdf10("")
            .setIsDebug(DEBUG)
            .setKey(Constants.MERCHANT_KEY)
            .setMerchantId(Constants.MERCHANT_ID)
        try {
            val mPaymentParams = builder.build()
            calculateHashInServer(mPaymentParams)
        } catch (e: java.lang.Exception) {
            Toaster.longt(e.toString())
        }
    }

    private fun calculateHashInServer(
        mPaymentParams: PaymentParam
    ) {
        val url: String = ROOT_URL + "hash.php"
        val request: StringRequest =
            object : StringRequest(
                Method.POST, url,
                Response.Listener { response: String? ->

                    var merchantHash = ""
                    try {
                        val jsonObject = JSONObject(response)
                        merchantHash = jsonObject.getString("result")
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                    if (merchantHash.isEmpty() || merchantHash == "") {
                        Toaster.longt("Failed to get requested data!")
                    } else {
                        mPaymentParams.setMerchantHash(merchantHash)
                        PayUmoneyFlowManager.startPayUMoneyFlow(
                            mPaymentParams,
                            this,
                            R.style.AppTheme,
                            true
                        )
                    }
                },
                Response.ErrorListener { error: VolleyError? ->

                    if (error is NoConnectionError) {
                        Toaster.longt("No internet connection")
                    } else {
                        Toaster.longt("Try again later")
                    }
                }
            ) {
                override fun getParams(): Map<String, String> {
                    return mPaymentParams.params
                }
            }
        Volley.newRequestQueue(this).add(request)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        try{
           /* if (requestCode == PayUmoneyFlowManager.REQUEST_CODE_PAYMENT && resultCode == Activity.RESULT_OK && data != null) {
                val transactionResponse: TransactionResponse = data.getParcelableExtra(PayUmoneyFlowManager.INTENT_EXTRA_TRANSACTION_RESPONSE)
                val resultModel: ResultModel = data.getParcelableExtra(PayUmoneyFlowManager.ARG_RESULT)
                if (transactionResponse != null && transactionResponse.getPayuResponse() != null) {
                    if (transactionResponse.transactionStatus == TransactionResponse.TransactionStatus.SUCCESSFUL) {

                        Toaster.longt("Payment Successful")

                        dialog.show()

                        RetrofitInstance.getRetrofit(
                            INDIMaster.api().registerWithPayment(
                                INDIPreferences.user()!!.id,
                                (personal.applicantName()),
                                (personal.fatherName()),
                                (personal.motherName()),
                                (personal.dob()),
                                (personal.getGender()),
                                (INDIPreferences.user()!!.mobile_number),
                                (personal.email()),
                                (personal.address()),
                                (personal.martial()),
                                "",
                                actionId,
                                transactionResponse.transactionDetails,
                                curriculum.highSchoolCurriculum(),
                                curriculum.interCurriculum(),
                                curriculum.gradCurriculum(),
                                curriculum.dipCurriculum(),
                                if(document.profile.size>0) media(document.profile[0],"profile_photo") else null,
                                if(document.signature.size>0) media(document.signature[0],"signature_photo") else null,
                                if(document.aadhaar.size>0) media(document.aadhaar[0],"aadhar_card_photo") else null,
                                if(document.highschool.size>0) media(document.highschool[0],"high_school") else null,
                                if(document.intermediate.size>0) media(document.intermediate[0],"intermediate") else null,
                                if(document.graduation.size>0) media(document.graduation[0],"graduation") else null,
                                if(document.diploma.size>0) media(document.diploma[0],"other_diploma") else null
                            )
                            , result)

                        actionId = 0


                    } else if (transactionResponse.transactionStatus == TransactionResponse.TransactionStatus.CANCELLED) {
                        //showAlert("Payment Cancelled", R.drawable.ic_exclamation, 0)
                        failed()

                    } else if (transactionResponse.transactionStatus == TransactionResponse.TransactionStatus.FAILED) {
                        //showAlert("Payment Failed", R.drawable.ic_exclamation, 0)
                        failed()
                    }
                } else if (resultModel != null && resultModel.error != null) {
                    ///showAlert("Something went wrong!", R.drawable.ic_exclamation, 0)
                    failed()

                } else {
                    //showAlert("Both objects are null", R.drawable.ic_exclamation, 0)
                    failed()
                }
            } else if (requestCode == PayUmoneyFlowManager.REQUEST_CODE_PAYMENT && resultCode == Activity.RESULT_CANCELED) {
                //showAlert("Payment Cancelled", R.drawable.ic_exclamation, 0)
                failed()
            }*/

        }
        catch (e: Exception){
            Toaster.longt("Error while parsing data")
            failed()
        }

        if (requestCode == PayUmoneyFlowManager.REQUEST_CODE_PAYMENT && resultCode == Activity.RESULT_OK && data != null) {

            val transactionResponse: TransactionResponse? =
                data.getParcelableExtra(PayUmoneyFlowManager.INTENT_EXTRA_TRANSACTION_RESPONSE)
            val resultModel: ResultModel? = data.getParcelableExtra(PayUmoneyFlowManager.ARG_RESULT)

            if (transactionResponse?.getPayuResponse() != null) {

                if (transactionResponse.transactionStatus == TransactionResponse.TransactionStatus.SUCCESSFUL) {

                    dialog.show()

                    RetrofitInstance.getRetrofit(
                        INDIMaster.api().registerWithPayment(
                            INDIPreferences.user()!!.id,
                            (personal.applicantName()),
                            (personal.fatherName()),
                            (personal.motherName()),
                            (personal.dob()),
                            (personal.getGender()),
                            (INDIPreferences.user()!!.mobile_number),
                            (personal.email()),
                            (personal.address()),
                            (personal.martial()),
                            "",
                            actionId,
                            transactionResponse!!.transactionDetails!!,
                            curriculum.highSchoolCurriculum(),
                            curriculum.interCurriculum(),
                            curriculum.gradCurriculum(),
                            curriculum.dipCurriculum(),
                            if (document.profile.size > 0) media(
                                document.profile[0],
                                "profile_photo"
                            ) else null,
                            if (document.signature.size > 0) media(
                                document.signature[0],
                                "signature_photo"
                            ) else null,
                            if (document.aadhaar.size > 0) media(
                                document.aadhaar[0],
                                "aadhar_card_photo"
                            ) else null,
                            if (document.highschool.size > 0) media(
                                document.highschool[0],
                                "high_school"
                            ) else null,
                            if (document.intermediate.size > 0) media(
                                document.intermediate[0],
                                "intermediate"
                            ) else null,
                            if (document.graduation.size > 0) media(
                                document.graduation[0],
                                "graduation"
                            ) else null,
                            if (document.diploma.size > 0) media(
                                document.diploma[0],
                                "other_diploma"
                            ) else null
                        )
                        , result
                    )

                    actionId = 0
                }
                else{
                    failed()
                }
            }
            else{
                failed()
            }

        }
        else{
            failed()
        }

    }
    /*private fun pay(){

        *//*var product = ""

        product = if(string == "1"){
            "PTI EXAM"
        } else{
            "Asst. PTI Exam"
        }*//*

        val obj = JSONObject()

        val random : String = INDIMaster.getRandomString(22)!!

        try {
            obj.put("merchant_id", MERCHANT)
            obj.put("access_token", Constants.APP_ACCESS_TOKEN)
            obj.put("customer_name", personal.applicantName())
            obj.put("customer_email", personal.email())
            obj.put("customer_phone", personal.mobile())
            obj.put("product_name", intent!!.getStringExtra("name"))
            obj.put(
                "order_no",
                random
            ) // order no. should have 10 to 30 character in numeric format
            obj.put("amount", "1000") // minimum amount should be 10
            obj.put("isLive", IS_LIVE) // need to send false if you are in sandbox mode
        } catch (e: JSONException) {

            Toaster.longt(e.message!!)
        }

        PaykunApiCall.Builder((this@RegistrationActivity)).sendJsonObject(obj)
    }*/

    /*@Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public fun getResults(message: Events.PaymentMessage) {

        if(message.results.equals(PaykunHelper.MESSAGE_SUCCESS, ignoreCase = true)){
            // do your stuff here
            // message.getTransactionId() will return your failed or succeed transaction id
            *//* if you want to get your transaction detail call message.getTransactionDetail()
             *  getTransactionDetail return all the field from server and you can use it here as per your need
             *  For Example you want to get Order id from detail use message.getTransactionDetail().order.orderId *//*
            if(!TextUtils.isEmpty(message.getTransactionId())) {

                dialog.show()

                RetrofitInstance.getRetrofit(
                    INDIMaster.api().registerWithPayment(
                        INDIPreferences.user()!!.id,
                        (personal.applicantName()),
                        (personal.fatherName()),
                        (personal.motherName()),
                        (personal.dob()),
                        (personal.getGender()),
                        (INDIPreferences.user()!!.mobile_number),
                        (personal.email()),
                        (personal.address()),
                        (personal.martial()),
                        "",
                        actionId,
                        message.getTransactionId(),
                        curriculum.highSchoolCurriculum(),
                        curriculum.interCurriculum(),
                        curriculum.gradCurriculum(),
                        curriculum.dipCurriculum(),
                        if(document.profile.size>0) media(document.profile[0],"profile_photo") else null,
                        if(document.signature.size>0) media(document.signature[0],"signature_photo") else null,
                        if(document.aadhaar.size>0) media(document.aadhaar[0],"aadhar_card_photo") else null,
                        if(document.highschool.size>0) media(document.highschool[0],"high_school") else null,
                        if(document.intermediate.size>0) media(document.intermediate[0],"intermediate") else null,
                        if(document.graduation.size>0) media(document.graduation[0],"graduation") else null,
                        if(document.diploma.size>0) media(document.diploma[0],"other_diploma") else null
                    )
                    , result)

                actionId = 0

                Log.v(" TRANSACTION_ID ",message.getTransactionDetail().order.orderId);
            }
        }
        else{
            failed()
            Toaster.longt(message.getResults())
        }
    }

    override fun onStart() {
        super.onStart()
        // Register this activity to listen to event.
        GlobalBus.getBus().register(this)
    }

    override fun onStop() {
        super.onStop()
        // Unregister from activity
        GlobalBus.getBus().unregister(this)
    }*/

    override fun onBackPressed() {
        if (currentFragment > 0){
            fragmentManager.popBackStack()
            currentFragment -= 1
        }else{
            super.onBackPressed()
        }
    }
}
