package `in`.indilabz.ssf_exam.fragment.auth

import `in`.aabhasjindal.otptextview.OTPListener
import `in`.indilabz.review_application.rest.RetrofitInstance
import `in`.indilabz.ssf_exam.INDIMaster
import `in`.indilabz.ssf_exam.R
import `in`.indilabz.ssf_exam.activity.AuthActivity
import `in`.indilabz.ssf_exam.activity.MainActivity
import `in`.indilabz.ssf_exam.databinding.FragmentOtpBinding
import `in`.indilabz.ssf_exam.databinding.FragmentPhoneBinding
import `in`.indilabz.ssf_exam.model.User
import `in`.indilabz.ssf_exam.rest.APIHelper
import `in`.indilabz.ssf_exam.utils.Toaster
import `in`.indilabz.yorneeds.utils.INDIPreferences
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import dmax.dialog.SpotsDialog
import java.security.SecureRandom
import java.text.DecimalFormat

class OTPFragment : Fragment(){

    private lateinit var binding: FragmentOtpBinding
    private lateinit var parentView: View
    private var userOtp : String = ""

    val builder: SpotsDialog.Builder = SpotsDialog.Builder()
    private lateinit var dialog: android.app.AlertDialog


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_otp, container, false)
        parentView = binding.root

        binding.halfNo.text = INDIPreferences.phone()!!.substring(0,5)

        dialog = builder.setContext(context!!).build()!!

        return parentView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.resend.setOnClickListener {

            val otp2: String = DecimalFormat("000000").format(SecureRandom().nextInt(999999))

            INDIPreferences.otp(otp2)

            sendOTP()

        }

        binding.otp.otpListener = object : OTPListener{
            override fun onOTPComplete(otp : String) {
                userOtp = otp
            }

            override fun onInteractionListener() {

            }
        }

        binding.submit.setOnClickListener {

            if(userOtp.isNotEmpty()){

                val o : String? = INDIPreferences.otp()
                if (o != null){
                    if (userOtp == INDIPreferences.otp()!!){

                        dialog.show()

                        RetrofitInstance.getRetrofit(
                            INDIMaster.api().login(
                                INDIPreferences.phone()!!
                            )
                            , authResult)

                    }else{

                        Toaster.longt("Invalid OTP")

                        Log.d("TAG_OTP", INDIPreferences.otp()!!)

                    }
                }else{

                    Toaster.longt("Invalid OTP")

                    Log.d("TAG_OTP", INDIPreferences.otp()!!)

                }
            }
            else{

                Toaster.longt("Invalid OTP")

                Log.d("TAG_OTP", INDIPreferences.otp()!!)
            }
        }
    }

    private fun sendOTP(){

        dialog.show()

        RetrofitInstance.getRetrofit(
            INDIMaster.api().otp(
                INDIPreferences.phone()!!,
                INDIPreferences.otp()!!
            ),
            result)
    }

    private val authResult = { _: Int, bool:Boolean, value:String ->

        dialog.dismiss()

        if(bool){

            val user = INDIMaster.gson.fromJson(APIHelper.result(value), User::class.java)

            if(user.id>0){

                INDIPreferences.session(true)
                INDIPreferences.user(user)
                startActivity(Intent(context, MainActivity::class.java))
                activity!!.finish()

            }
            else{

                Toaster.longt("Unable to verify user, try again later!")

                startActivity(Intent(context, AuthActivity::class.java))
                activity!!.finish()
            }
        }
        else{

            Toaster.longt("Try again later!")
        }
    }

    private val result = { _:Int, bool:Boolean, value:String ->

        dialog.dismiss()

        if(bool){

            Toaster.longt("OTP sent successfully!")
        }
        else{
            Toaster.longt(value)
        }
    }
}