package `in`.indilabz.ssf_exam.fragment.main

import `in`.indilabz.review_application.rest.RetrofitInstance
import `in`.indilabz.ssf_exam.INDIMaster
import `in`.indilabz.ssf_exam.R
import `in`.indilabz.ssf_exam.activity.Detail
import `in`.indilabz.ssf_exam.activity.RegistrationActivity
import `in`.indilabz.ssf_exam.activity.ViewAll
import `in`.indilabz.ssf_exam.adapter.CategoryAdapter
import `in`.indilabz.ssf_exam.adapter.CertificateAdapter
import `in`.indilabz.ssf_exam.adapter.ShopAdapter
import `in`.indilabz.ssf_exam.databinding.FragmentHomeBinding
import `in`.indilabz.ssf_exam.model.CategoryModel
import `in`.indilabz.ssf_exam.model.Exam
import `in`.indilabz.ssf_exam.model.ItemHome
import `in`.indilabz.ssf_exam.model.Sport
import `in`.indilabz.ssf_exam.rest.APIHelper
import `in`.indilabz.ssf_exam.utils.Constants.Companion.EXAM_1
import `in`.indilabz.ssf_exam.utils.Constants.Companion.EXAM_2
import `in`.indilabz.ssf_exam.utils.Toaster
import `in`.indilabz.yorneeds.utils.INDIPreferences
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson


class HomeFragment : Fragment(){

    private lateinit var binding: FragmentHomeBinding
    private lateinit var parentView: View

    private val datum: ArrayList<ItemHome> = ArrayList()
    private lateinit var adapter: ShopAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        parentView = binding.root

        adapter = ShopAdapter(activity!!,datum, click)

        return parentView
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recycler.layoutManager = INDIMaster.getGridLayoutManager(2)
        binding.recycler.adapter = adapter
        binding.recycler.setHasFixedSize(true)

        binding.swipe.setOnRefreshListener {
            getData()
        }

        getData()
    }

    private fun getData(){

        binding.swipe.isRefreshing = true

        binding.all.text = "..."
        binding.registered.text = "..."
        binding.attempted.text  = "..."


        Log.e("home",INDIPreferences.user()!!.id.toString())

        RetrofitInstance.getRetrofit(
            INDIMaster.api().home(
                INDIPreferences.user()!!.id
            )
            , result)
    }

    private fun addData(

        titles: ArrayList<String>, ids: ArrayList<Int>) {

        datum.clear()

        datum.add(
            ItemHome(
                3,
                "Basic Computer Course (BCC)",
                R.drawable.logo,
                titles.contains("3")
            )
        )

        datum.add(
            ItemHome(
                4,
                "Cerficate In Visual Basic",
                R.drawable.logo,
                titles.contains("4")
            )
        )

        datum.add(
            ItemHome(
                5,
                "Certificate Training in HTML",
                R.drawable.logo,
                titles.contains("5")
            )
        )

        datum.add(
            ItemHome(
                6,
                "Certificate in English Typing",
                R.drawable.logo,
                titles.contains("6")
            )
        )

        datum.add(
            ItemHome(
                7,
                "Certificate in Hindi Typing",
                R.drawable.logo,
                titles.contains("7")
            )
        )

        datum.add(
            ItemHome(
                8,
                "Certificate Course in Java",
                R.drawable.logo,
                titles.contains("8")
            )
        )

        datum.add(
            ItemHome(
                9,
                "Certificate Course in Computer Application",
                R.drawable.logo,
                titles.contains("9")
            )
        )

        datum.add(
            ItemHome(
                10,
                "Certificate Course in Software Engineering",
                R.drawable.logo,
                titles.contains("10")
            )
        )

        datum.add(
            ItemHome(
                11,
                "Certificate Course in Web Development",
                R.drawable.logo,
                titles.contains("11")
            )
        )

        datum.add(
            ItemHome(
                12,
                "Certificate Course in Information Technology",
                R.drawable.logo,
                titles.contains("12")
            )
        )

        datum.add(
            ItemHome(
                13,
                "Certificate in Hardware",
                R.drawable.logo,
                titles.contains("13")
            )
        )

        datum.add(
            ItemHome(
                14,
                "Certificate in Ethical Hacking",
                R.drawable.logo,
                titles.contains("14")
            )
        )

        datum.add(
            ItemHome(
                15,
                "Certificate in Hardware &amp; Networking",
                R.drawable.logo,
                titles.contains("15")
            )
        )

        datum.add(
            ItemHome(
                16,
                "Certificate in Computer Teacher Training",
                R.drawable.logo,
                titles.contains("16")
            )
        )

        datum.add(
            ItemHome(
                17,
                "Advance Diploma in Computer Science",
                R.drawable.logo,
                titles.contains("17")
            )
        )

        datum.add(
            ItemHome(
                18,
                "Advance Diploma in Computer Application",
                R.drawable.logo,
                titles.contains("18")
            )
        )

        datum.add(
            ItemHome(
                19,
                "Advance Diploma in Computer Language",
                R.drawable.logo,
                titles.contains("19")
            )
        )

        datum.add(
            ItemHome(
                20,
                "Advance Diploma in Computer Hardware & Networking",
                R.drawable.logo,
                titles.contains("20")
            )
        )

        datum.add(
            ItemHome(
                21,
                "Diploma In Computer Aided Designing",
                R.drawable.logo,
                titles.contains("21")
            )
        )

        datum.add(
            ItemHome(
                22,
                "Advance Diploma in Web Designing",
                R.drawable.logo,
                titles.contains("22")
            )
        )

        datum.add(
            ItemHome(
                23,
                "Advance Diploma in 2-d Animation",
                R.drawable.logo,
                titles.contains("23")
            )
        )

        datum.add(
            ItemHome(
                24,
                "Advance Diploma in 3-d Animation",
                R.drawable.logo,
                titles.contains("24")
            )
        )

        datum.add(
            ItemHome(
                25,
                "Advance Diploma in Computer Teacher Training",
                R.drawable.logo,
                titles.contains("25")
            )
        )


        adapter.notifyDataSetChanged()

        val list : ArrayList<CategoryModel> = ArrayList()
        list.add(CategoryModel("Information Security Certificate",datum))

        binding.header.layoutManager = LinearLayoutManager(activity!!)

        val cAdapter = CategoryAdapter(activity!!,list)
        binding.header.adapter = cAdapter
        cAdapter.notifyDataSetChanged()

        /*binding.examOne.text = EXAM_1
        binding.examTwo.text = EXAM_2

        binding.applyNowOne.text = if(ids.contains(1)) "Applied" else "Not applied"
        binding.applyNowTwo.text = if(ids.contains(2)) "Applied" else "Not applied"

        binding.applyNowOne.setOnClickListener {

            if(!ids.contains(1)){

                val intent = Intent(Intent(context, Detail::class.java))
                intent.putExtra("type", "1")
                intent.putExtra("id", (1))
                intent.putExtra("name", EXAM_1)
                startActivity(intent)
            }

            else{
                Toaster.longt("Already applied")
            }
        }

        binding.applyNowTwo.setOnClickListener {

            if(!ids.contains(2)){

                val intent = Intent(Intent(context, Detail::class.java))
                intent.putExtra("type", "2")
                intent.putExtra("id", (2))
                intent.putExtra("name", EXAM_2)
                startActivity(intent)
            }
            else{
                Toaster.longt("Already applied")
            }
        }*/

    }

    private val result = { _:Int, bool:Boolean, value:String ->

        binding.swipe.isRefreshing = false

        if(bool){

            val obj: String = APIHelper.result(value)

            binding.all.text = 2.toString()

            val sports: Array<Sport> = INDIMaster.gson.fromJson(APIHelper.custom(obj, "sports"), Array<Sport>::class.java)
            val exams: Array<Exam> = INDIMaster.gson.fromJson(APIHelper.custom(obj, "exams"), Array<Exam>::class.java)

            val titles: ArrayList<String> = ArrayList()
            val ids: ArrayList<Int> = ArrayList()

            for (sport in sports){
                titles.add(sport.sport_name)
            }

            for (exam in exams){
                ids.add(exam.exam_id)
            }

            addData(titles, ids)

            binding.registered.text = "${sports.size+exams.size}"
            binding.attempted.text  = APIHelper.custom(obj, "attempted")

        }
        else{

            binding.all.text = "0"
            binding.registered.text = "0"
            binding.attempted.text  = "0"
        }
    }

    private val click = { pos: Int ->

        if(!datum[pos].bool){
            val intent = Intent(Intent(context, Detail::class.java))
            intent.putExtra("type", pos.toString())
            intent.putExtra("id", (pos+3))
            intent.putExtra("name", datum.get(pos).name)
            startActivity(intent)

        }
        else{
            Toaster.longt("Already applied!")
        }

    }

    /*private val done = { bool: Boolean->

        if(bool){
            getData()
        }
    }*/

    override fun onResume() {
        super.onResume()

        getData()
    }
}