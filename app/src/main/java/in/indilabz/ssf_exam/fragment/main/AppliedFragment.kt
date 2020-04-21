package `in`.indilabz.ssf_exam.fragment.main

import `in`.indilabz.review_application.rest.RetrofitInstance
import `in`.indilabz.ssf_exam.INDIMaster
import `in`.indilabz.ssf_exam.R
import `in`.indilabz.ssf_exam.activity.WebActivity
import `in`.indilabz.ssf_exam.adapter.AppliedAdapter
import `in`.indilabz.ssf_exam.databinding.FragmentAppliedBinding
import `in`.indilabz.ssf_exam.dialog.CameraInstruction
import `in`.indilabz.ssf_exam.dialog.DialogInstruction
import `in`.indilabz.ssf_exam.model.Exam
import `in`.indilabz.ssf_exam.model.ItemHome
import `in`.indilabz.ssf_exam.model.Sport
import `in`.indilabz.ssf_exam.model.exam.ExamQuestion
import `in`.indilabz.ssf_exam.model.exam.ExamSubject
import `in`.indilabz.ssf_exam.rest.APIHelper
import `in`.indilabz.ssf_exam.utils.Toaster
import `in`.indilabz.yorneeds.utils.INDIPreferences
import `in`.indilabz.yorneeds.utils.JSONParser
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import dmax.dialog.SpotsDialog


class AppliedFragment : Fragment(){

    private lateinit var binding: FragmentAppliedBinding
    private lateinit var parentView: View

    private lateinit var examId: String
    private var home: ArrayList<ItemHome> = ArrayList()
    private lateinit var adapter: AppliedAdapter

    private val builder: SpotsDialog.Builder = SpotsDialog.Builder()
    private lateinit var dialog: android.app.AlertDialog

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_applied, container, false)
        parentView = binding.root

        dialog = builder.setContext(context!!).build()!!

        adapter = AppliedAdapter(home, click)

        return parentView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recycler.layoutManager = INDIMaster.getGridLayoutManager(2)
        binding.recycler.adapter = adapter
        binding.recycler.setHasFixedSize(true)

        getData()
    }


    private fun getData(){

        binding.swipe.isRefreshing = true

        RetrofitInstance.getRetrofit(
            INDIMaster.api().applied(
                INDIPreferences.user()!!.id
            )
            , response)
    }

    private fun names(string: String): Int{

        val games = arrayOf(
            "Boxing",
            "Karate",
            "Kabaddi",
            "Kho Kho",
            "Kick Boxing",
            "Race",
            "Rugby",
            "Tennis",
            "Volley Ball",
            "Weight lifting",
            "Wrestling",
            "Yoga")

        for ((i, each) in games.withIndex()){
            if(each == string){

                return i
            }
        }

        return 0
    }

    private fun image(string: String): Int{

        val images= arrayOf(
            R.drawable.boxing,
            R.drawable.karate,
            R.drawable.kabaddi,
            R.drawable.kho_kho,
            R.drawable.kick_boxing,
            R.drawable.race,
            R.drawable.rug,
            R.drawable.tennis,
            R.drawable.voli,
            R.drawable.wl,
            R.drawable.wrestling,
            R.drawable.yoga)

        return images[names(string)]
    }

    private val response = { _:Int, bool:Boolean, value: String ->

        binding.swipe.isRefreshing = false

        if(bool){

            val result = APIHelper.result(value)
            val exam = APIHelper.custom(result, "exam")

            val examList: Array<Exam> = INDIMaster.gson.fromJson(exam, Array<Exam>::class.java)


            for (each in examList){

                if(each.exam_id == 1){
                    home.add(ItemHome(
                        1,
                        "Yoga/Self Defense Instructor",
                        R.drawable.logo,
                        each.status != "inactive"
                    ))
                }

                if(each.exam_id == 2){
                    home.add(ItemHome(
                        2,
                        "Assistance Yoga/Self Defense Instructor",
                        R.drawable.logo,
                        each.status != "inactive"
                    ))
                }
            }

            adapter.notifyDataSetChanged()
        }
        else{
            Toaster.longt("Error while getting data")
        }
    }

    private val click = { pos: Int ->

        if(pos==0){

            if(home[pos].bool){

                dialog.show()

                INDIPreferences.exam("1")

                eligibility("1")
            }
            else{

                startActivity(Intent(context!!, WebActivity::class.java))
            }

        }

        if(pos==1){

            if(home[pos].bool){

                dialog.show()

                INDIPreferences.exam("2")

                eligibility("2")
            }
            else{

                startActivity(Intent(context!!, WebActivity::class.java))
            }

        }
    }

    private fun eligibility(exam: String){

        this.examId = exam

        RetrofitInstance.getRetrofit(
            INDIMaster.session().examEligibility(
                INDIPreferences.user()!!.id.toString(),
                exam), examEligibility)
    }

    private fun infoDialog(message: String){

        val dialog: AlertDialog = AlertDialog.Builder(context!!).create()
        dialog.setTitle("Info")
        dialog.setMessage(message)
        dialog.setCancelable(false)
        dialog.setButton(AlertDialog.BUTTON_POSITIVE, "Ok") { dialogInterface: DialogInterface, i: Int ->
            dialog.dismiss()
        }
        dialog.show()
    }

    private val examEligibility = {code:Int, bool:Boolean, value:String ->

        dialog.dismiss()

        if(bool){

            if(APIHelper.result(value) == "false"){

                dialog.dismiss()

                RetrofitInstance.getRetrofit(
                    INDIMaster.session().questions(
                        examId
                    ), questions)
            }
            else{

                infoDialog(
                    "You have already taken the exam." +
                            " Wait for the results to be out!")
            }
        }
        else{

            infoDialog(
                "Failed to get exam details!")
        }
    }

    private val questions = {_:Int, bool:Boolean, value:String ->

        if(bool){

            val datumQue:Array<ExamQuestion> = INDIMaster.gson.fromJson(
                JSONParser.getObject(APIHelper.result(value), "question"),
                Array<ExamQuestion>::class.java)

            val datumSub:Array<ExamSubject> = INDIMaster.gson.fromJson(JSONParser.getObject(APIHelper.result(value), "subject"),
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

    private val cancel = {bool: Boolean->

        if(bool){
            DialogInstruction().instruction().show(childFragmentManager,"")
        }
    }
}