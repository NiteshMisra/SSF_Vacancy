package `in`.indilabz.ssf_exam.fragment.registration

import `in`.indilabz.ssf_exam.R
import `in`.indilabz.ssf_exam.activity.RegistrationActivity
import `in`.indilabz.ssf_exam.databinding.DialogRegistrationBinding
import `in`.indilabz.ssf_exam.databinding.FragmentExamCurriculumBinding
import `in`.indilabz.ssf_exam.databinding.FragmentExamPersonalBinding
import `in`.indilabz.ssf_exam.model.User
import `in`.indilabz.ssf_exam.utils.Toaster
import `in`.indilabz.ssf_exam.utils.Validator
import `in`.indilabz.yorneeds.utils.INDIPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment

class PersonalFragment : Fragment(){

    private lateinit var parentView: View
    private lateinit var binding: FragmentExamPersonalBinding

    private var user: User = INDIPreferences.user()!!

    fun personal(): PersonalFragment{
        return this
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_exam_personal, container, false)
        parentView = binding.root

        return parentView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.gender.setOnClickListener {
            gender()
        }

        binding.martialStatus.setOnClickListener {
            martialStatus()
        }

        binding.submit.setOnClickListener {
            (activity!! as RegistrationActivity).submitClick()
        }

        setData()
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

    fun submit(): Boolean{

        if(Validator.validateName(binding.applicantName)){

            if(Validator.validateName(binding.fatherName)){

                if(Validator.validateName(binding.motherName)){

                    if(Validator.validateLength(binding.dob, 10)){

                        if(!binding.gender.text.toString().contains("gender")){

                            if(Validator.validateEmail(binding.mail)) {

                                if (!binding.martialStatus.text.toString().contains("status")) {

                                    if(Validator.validateAddress(binding.address)){

                                        return true
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

        return false
    }

    private fun setData(){

        binding.title.setText(user.registration_number)
        binding.applicantName.setText(user.applicant_name)
        binding.fatherName.setText(user.father_name)
        binding.motherName.setText(user.mother_name)
        binding.dob.setText(user.dob)
        binding.gender.setText(user.gender)
        binding.mail.setText(user.mail_id)
        binding.mobile.setText(user.mobile_number)
        binding.address.setText(user.address)
        binding.martialStatus.setText(user.martial_status)

        /*Glide.with(context!!)
            .load("${IMAGE_URL}${user.user_meta.profile_photo}")
            .into(binding.profilePreview)

        Glide.with(context!!)
            .load("${IMAGE_URL}${user.user_meta.signature_photo}")
            .into(binding.signaturePreview)*/
    }

    fun applicantName(): String{

        if(submit()){
            return  binding.applicantName.text.toString()
        }
        return  ""
    }

    fun fatherName(): String{

        if(submit()){
            return  binding.fatherName.text.toString()
        }
        return  ""
    }

    fun motherName(): String{

        if(submit()){
            return  binding.motherName.text.toString()
        }
        return  ""
    }

    fun dob(): String{

        if(submit()){
            return  binding.dob.text.toString()
        }
        return  ""
    }

    fun getGender(): String{

        if(submit()){
            return  binding.gender.text.toString()
        }
        return  ""
    }

    fun mobile(): String{

        if(submit()){
            return  binding.mobile.text.toString()
        }
        return  ""
    }

    fun email(): String{

        if(submit()){
            return  binding.mail.text.toString()
        }
        return  ""
    }

    fun martial(): String{

        if(submit()){
            return  binding.martialStatus.text.toString()
        }
        return  ""
    }

    fun address(): String{

        if(submit()){
            return  binding.address.text.toString()
        }
        return  ""
    }
}