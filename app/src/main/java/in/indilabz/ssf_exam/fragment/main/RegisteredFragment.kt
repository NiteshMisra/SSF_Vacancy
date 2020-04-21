package `in`.indilabz.ssf_exam.fragment.main

import `in`.indilabz.review_application.rest.RetrofitInstance
import `in`.indilabz.ssf_exam.INDIMaster
import `in`.indilabz.ssf_exam.R
import `in`.indilabz.ssf_exam.activity.WebActivity
import `in`.indilabz.ssf_exam.adapter.AppliedAdapter
import `in`.indilabz.ssf_exam.databinding.FragmentAppliedBinding
import `in`.indilabz.ssf_exam.model.ItemHome
import `in`.indilabz.ssf_exam.model.Sport
import `in`.indilabz.ssf_exam.rest.APIHelper
import `in`.indilabz.ssf_exam.utils.Constants.Companion.EXAM_1
import `in`.indilabz.ssf_exam.utils.Constants.Companion.EXAM_2
import `in`.indilabz.ssf_exam.utils.Toaster
import `in`.indilabz.yorneeds.utils.INDIPreferences
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import dmax.dialog.SpotsDialog


class RegisteredFragment : Fragment(){

    private lateinit var binding: FragmentAppliedBinding
    private lateinit var parentView: View

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
            INDIMaster.api().sports(
                INDIPreferences.user()!!.id
            )
            , response)
    }
    


    private fun names(pos: Int): String{

        val games = arrayOf(
            EXAM_1,
            EXAM_1,
            EXAM_2,
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

        /*for ((i, each) in games.withIndex()){
            if(each == string){

                return i
            }
        }
*/

        return games[pos]
    }

    private fun image(pos: Int): Int{

        val images= arrayOf(
            R.mipmap.ic_launcher,
            R.mipmap.ic_launcher,
            R.mipmap.ic_launcher,
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

        return images[pos]
    }

    private val response = { _:Int, bool:Boolean, value: String ->

        binding.swipe.isRefreshing = false

        if(bool){

            val result = APIHelper.result(value)
            val sport = APIHelper.custom(result, "sport")
            //val exam = APIHelper.custom(result, "exam")

            //val examList: Array<Exam> = INDIMaster.gson.fromJson(exam, Array<Exam>::class.java)
            val sportList: Array<Sport> = INDIMaster.gson.fromJson(sport, Array<Sport>::class.java)

            for (each in sportList){

                home.add(
                    ItemHome(
                        each.id,
                        names(each.sport_name.toInt()),
                        image(each.sport_name.toInt()),
                        true
                ))
            }

            adapter.notifyDataSetChanged()
        }
        else{
            Toaster.longt("Error while getting data")
        }
    }

    private val click = { pos: Int ->

        /*if(home[pos].name.contains("Asst. PTI")){

            dialog.show()

            RetrofitInstance.getRetrofit(
                INDIMaster.api().questions(
                    "2"
                ), questions)
        }

        if(home[pos].name.contains("PTI")){

            dialog.show()

            RetrofitInstance.getRetrofit(
                INDIMaster.api().questions(
                    "10"
                ), questions)
        }*/

        startActivity(Intent(context!!, WebActivity::class.java))
    }
}