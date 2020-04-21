package `in`.indilabz.ssf_exam.fragment.main

import `in`.indilabz.review_application.rest.RetrofitInstance
import `in`.indilabz.ssf_exam.INDIMaster
import `in`.indilabz.ssf_exam.R
import `in`.indilabz.ssf_exam.databinding.FragmentDemoBinding
import `in`.indilabz.ssf_exam.dialog.CameraInstruction
import `in`.indilabz.ssf_exam.dialog.DialogInstruction
import `in`.indilabz.ssf_exam.model.exam.ExamQuestion
import `in`.indilabz.ssf_exam.model.exam.ExamSubject
import `in`.indilabz.ssf_exam.rest.APIHelper
import `in`.indilabz.ssf_exam.utils.Toaster
import `in`.indilabz.yorneeds.utils.INDIPreferences
import `in`.indilabz.yorneeds.utils.JSONParser
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import dmax.dialog.SpotsDialog

class DemoFragment : Fragment(){

    private lateinit var binding : FragmentDemoBinding
    private lateinit var parentView: View

    private lateinit var dialog: android.app.AlertDialog
    private val builder: SpotsDialog.Builder = SpotsDialog.Builder()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_demo, container, false)
        parentView = binding.root

        dialog = builder.setContext(context!!).build()!!


        binding.submit.setOnClickListener {

            dialog.show()

            RetrofitInstance.getRetrofit(
                INDIMaster.api().questions(
                    "2"
                ), questions)

        }

        return parentView
    }

    private val cancel = {bool: Boolean->

        if(bool){
            DialogInstruction().instruction().show(childFragmentManager,"")
        }
    }

    private val questions = {code:Int, bool:Boolean, value:String ->

        if(bool){

            val datumQue:Array<ExamQuestion> = INDIMaster.gson.fromJson(
                JSONParser.getObject(APIHelper.result(value), "question"),
                Array<ExamQuestion>::class.java)

            val datumSub:Array<ExamSubject> = INDIMaster.gson.fromJson(
                JSONParser.getObject(APIHelper.result(value), "subject"),
                Array<ExamSubject>::class.java)

            val questionList: ArrayList<ExamQuestion> = ArrayList()
            val subjects: ArrayList<ExamSubject> = ArrayList()

            questionList.addAll(datumQue)
            subjects.addAll(datumSub)

            INDIPreferences.questions(questionList)
            INDIPreferences.subjects(subjects)
            INDIPreferences.time(60)

            dialog.dismiss()

            if(questionList.size>0){

                INDIPreferences.qpe().clear().commit()
                CameraInstruction().camera(cancel).show(childFragmentManager,"")
            }
            else{

                Toaster.longt("No questions found!")
            }
        }
        else{

            Toaster.longt(value.replace("_"," "))
        }

        dialog.dismiss()

    }
}