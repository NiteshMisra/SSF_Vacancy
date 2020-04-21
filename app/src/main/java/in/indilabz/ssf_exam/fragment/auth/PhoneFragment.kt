package `in`.indilabz.ssf_exam.fragment.auth

import `in`.indilabz.review_application.rest.RetrofitInstance
import `in`.indilabz.ssf_exam.INDIMaster
import `in`.indilabz.ssf_exam.R
import `in`.indilabz.ssf_exam.activity.AuthActivity
import `in`.indilabz.ssf_exam.databinding.FragmentPhoneBinding
import `in`.indilabz.ssf_exam.utils.Toaster
import `in`.indilabz.yorneeds.utils.INDIPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dmax.dialog.SpotsDialog
import java.security.SecureRandom
import java.text.DecimalFormat


class PhoneFragment : BottomSheetDialogFragment(){

    private lateinit var binding: FragmentPhoneBinding
    private lateinit var parentView: View

    val builder: SpotsDialog.Builder = SpotsDialog.Builder()
    private lateinit var dialog: android.app.AlertDialog


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_phone, container, false)
        parentView = binding.root

        dialog = builder.setContext(context!!).build()!!


        return parentView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.back.setOnClickListener {
            activity!!.onBackPressed()
        }

        binding.submit.setOnClickListener {

            if (binding.phone.text.isNotEmpty()){
                val otp: String = DecimalFormat("000000").format(SecureRandom().nextInt(999999))

                INDIPreferences.phone(binding.phone.text.toString())
                INDIPreferences.otp(otp)

                Log.d("TAG_OTP", otp)

                sendOTP()
            }
        }
    }

    private fun sendOTP(){

        dialog.show()

        RetrofitInstance.getRetrofit(
            INDIMaster.api().otp(
                binding.phone.text.toString(),
                INDIPreferences.otp()!!
            ),
            result)
    }

    private val result = { _:Int, bool:Boolean, value:String ->


        dialog.dismiss()


        if(bool){
            Log.d("TAG_OTP_RESPONSE", value)

            (activity as AuthActivity).change(R.id.nav_otp)
        }
        else{
            Toaster.longt(value)
        }
    }
}