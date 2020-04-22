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
            INDIMaster.homeApi().home(
                INDIPreferences.user()!!.id
            )
            , result)
    }

    private fun addData(

        titles: ArrayList<String>, ids: ArrayList<Int>) {

        datum.clear()

        datum.add(
            ItemHome(
                11,
                "Marketing Strategy",
                R.drawable.logo,
                titles.contains("11")
            )
        )

        datum.add(
            ItemHome(
                12,
                "Website Traffic",
                R.drawable.logo,
                titles.contains("12")
            )
        )

        datum.add(
            ItemHome(
                13,
                "Local SEO",
                R.drawable.logo,
                titles.contains("13")
            )
        )

        datum.add(
            ItemHome(
                14,
                "Facebook Marketing",
                R.drawable.logo,
                titles.contains("14")
            )
        )

        datum.add(
            ItemHome(
                15,
                "Instagram Marketing",
                R.drawable.logo,
                titles.contains("15")
            )
        )

        datum.add(
            ItemHome(
                16,
                "YouTube Marketing",
                R.drawable.logo,
                titles.contains("16")
            )
        )

        datum.add(
            ItemHome(
                17,
                "Lead Generation",
                R.drawable.logo,
                titles.contains("17")
            )
        )

        datum.add(
            ItemHome(
                18,
                "Landing Page Optimization",
                R.drawable.logo,
                titles.contains("18")
            )
        )

        datum.add(
            ItemHome(
                19,
                "Development",
                R.drawable.logo,
                titles.contains("19")
            )
        )

        datum.add(
            ItemHome(
                20,
                "Python",
                R.drawable.logo,
                titles.contains("20")
            )
        )

        datum.add(
            ItemHome(
                21,
                "Data Science",
                R.drawable.logo,
                titles.contains("21")
            )
        )

        datum.add(
            ItemHome(
                22,
                "JavaScript",
                R.drawable.logo,
                titles.contains("22")
            )
        )

        datum.add(
            ItemHome(
                23,
                "Machine Learning",
                R.drawable.logo,
                titles.contains("23")
            )
        )

        datum.add(
            ItemHome(
                24,
                "Java",
                R.drawable.logo,
                titles.contains("24")
            )
        )

        datum.add(
            ItemHome(
                25,
                "PHP",
                R.drawable.logo,
                titles.contains("25")
            )
        )

        datum.add(
            ItemHome(
                26,
                "C#",
                R.drawable.logo,
                titles.contains("26")
            )
        )

        datum.add(
            ItemHome(
                27,
                "Data Analysis",
                R.drawable.logo,
                titles.contains("27")
            )
        )

        datum.add(
            ItemHome(
                28,
                "Node.Js",
                R.drawable.logo,
                titles.contains("28")
            )
        )

        datum.add(
            ItemHome(
                29,
                "CSS",
                R.drawable.logo,
                titles.contains("29")
            )
        )

        datum.add(
            ItemHome(
                30,
                "iOS Development",
                R.drawable.logo,
                titles.contains("30")
            )
        )

        datum.add(
            ItemHome(
                31,
                "Deep Learning",
                R.drawable.logo,
                titles.contains("31")
            )
        )

        datum.add(
            ItemHome(
                32,
                "C++",
                R.drawable.logo,
                titles.contains("32")
            )
        )

        datum.add(
            ItemHome(
                33,
                "Google Flutter",
                R.drawable.logo,
                titles.contains("33")
            )
        )

        datum.add(
            ItemHome(
                34,
                "Swift",
                R.drawable.logo,
                titles.contains("34")
            )
        )

        datum.add(
            ItemHome(
                35,
                "React",
                R.drawable.logo,
                titles.contains("35")
            )
        )

        datum.add(
            ItemHome(
                36,
                "Native",
                R.drawable.logo,
                titles.contains("36")
            )
        )

        datum.add(
            ItemHome(
                37,
                "Kotlin",
                R.drawable.logo,
                titles.contains("37")
            )
        )

        datum.add(
            ItemHome(
                38,
                "Ionic",
                R.drawable.logo,
                titles.contains("38")
            )
        )

        datum.add(
            ItemHome(
                39,
                "Redux",
                R.drawable.logo,
                titles.contains("39")
            )
        )

        datum.add(
            ItemHome(
                40,
                "Framework",
                R.drawable.logo,
                titles.contains("40")
            )
        )

        datum.add(
            ItemHome(
                41,
                "Xamarin",
                R.drawable.logo,
                titles.contains("41")
            )
        )

        datum.add(
            ItemHome(
                42,
                "Spring",
                R.drawable.logo,
                titles.contains("42")
            )
        )

        datum.add(
            ItemHome(
                43,
                "Hibernate",
                R.drawable.logo,
                titles.contains("43")
            )
        )

        datum.add(
            ItemHome(
                44,
                "Selenium",
                R.drawable.logo,
                titles.contains("44")
            )
        )

        datum.add(
            ItemHome(
                45,
                "Web Driver Software",
                R.drawable.logo,
                titles.contains("45")
            )
        )

        datum.add(
            ItemHome(
                46,
                "Testing Java",
                R.drawable.logo,
                titles.contains("46")
            )
        )

        datum.add(
            ItemHome(
                47,
                "Testing API",
                R.drawable.logo,
                titles.contains("47")
            )
        )

        datum.add(
            ItemHome(
                48,
                "Testing REST",
                R.drawable.logo,
                titles.contains("48")
            )
        )

        datum.add(
            ItemHome(
                49,
                "Web Design",
                R.drawable.logo,
                titles.contains("49")
            )
        )

        datum.add(
            ItemHome(
                50,
                "Word Press",
                R.drawable.logo,
                titles.contains("50")
            )
        )

        datum.add(
            ItemHome(
                51,
                "Photoshop",
                R.drawable.logo,
                titles.contains("51")
            )
        )

        datum.add(
            ItemHome(
                52,
                "HTML",
                R.drawable.logo,
                titles.contains("52")
            )
        )

        datum.add(
            ItemHome(
                53,
                "HTML5",
                R.drawable.logo,
                titles.contains("53")
            )
        )

        datum.add(
            ItemHome(
                54,
                "Bootstrap",
                R.drawable.logo,
                titles.contains("54")
            )
        )

        datum.add(
            ItemHome(
                55,
                "Dreamweaver",
                R.drawable.logo,
                titles.contains("55")
            )
        )

        datum.add(
            ItemHome(
                56,
                "Web Development",
                R.drawable.logo,
                titles.contains("56")
            )
        )

        datum.add(
            ItemHome(
                57,
                "CSS Animations",
                R.drawable.logo,
                titles.contains("57")
            )
        )

        datum.add(
            ItemHome(
                58,
                "Graphic Design",
                R.drawable.logo,
                titles.contains("58")
            )
        )

        datum.add(
            ItemHome(
                59,
                "Drawing",
                R.drawable.logo,
                titles.contains("59")
            )
        )

        datum.add(
            ItemHome(
                60,
                "Digital Painting",
                R.drawable.logo,
                titles.contains("60")
            )
        )

        datum.add(
            ItemHome(
                61,
                "CorelDRAW",
                R.drawable.logo,
                titles.contains("61")
            )
        )

        datum.add(
            ItemHome(
                62,
                "Sketch Software",
                R.drawable.logo,
                titles.contains("62")
            )
        )

        datum.add(
            ItemHome(
                63,
                "Digital Art",
                R.drawable.logo,
                titles.contains("63")
            )
        )

        datum.add(
            ItemHome(
                64,
                "Illustration",
                R.drawable.logo,
                titles.contains("64")
            )
        )

        datum.add(
            ItemHome(
                65,
                "Digital Marketing",
                R.drawable.logo,
                titles.contains("65")
            )
        )

        datum.add(
            ItemHome(
                66,
                "Google Ads",
                R.drawable.logo,
                titles.contains("66")
            )
        )

        datum.add(
            ItemHome(
                67,
                "Social Media Marketing",
                R.drawable.logo,
                titles.contains("67")
            )
        )

        datum.add(
            ItemHome(
                68,
                "Google Analytics",
                R.drawable.logo,
                titles.contains("68")
            )
        )

        datum.add(
            ItemHome(
                69,
                "Email Marketing",
                R.drawable.logo,
                titles.contains("69")
            )
        )

        datum.add(
            ItemHome(
                70,
                "Facebook Ads",
                R.drawable.logo,
                titles.contains("70")
            )
        )

        datum.add(
            ItemHome(
                71,
                "Search Engine Optimization",
                R.drawable.logo,
                titles.contains("71")
            )
        )

        datum.add(
            ItemHome(
                72,
                "PPC",
                R.drawable.logo,
                titles.contains("72")
            )
        )

        datum.add(
            ItemHome(
                73,
                "Management",
                R.drawable.logo,
                titles.contains("73")
            )
        )

        datum.add(
            ItemHome(
                74,
                "TikTok Marketing",
                R.drawable.logo,
                titles.contains("74")
            )
        )

        datum.add(
            ItemHome(
                75,
                "Twitter Marketing",
                R.drawable.logo,
                titles.contains("75")
            )
        )

        datum.add(
            ItemHome(
                76,
                "Linux",
                R.drawable.logo,
                titles.contains("76")
            )
        )

        datum.add(
            ItemHome(
                77,
                "Windows Server",
                R.drawable.logo,
                titles.contains("77")
            )
        )

        datum.add(
            ItemHome(
                78,
                "Linux Administration",
                R.drawable.logo,
                titles.contains("78")
            )
        )

        datum.add(
            ItemHome(
                79,
                "Shell Scripting",
                R.drawable.logo,
                titles.contains("79")
            )
        )

        datum.add(
            ItemHome(
                80,
                "Active Directory",
                R.drawable.logo,
                titles.contains("80")
            )
        )

        datum.add(
            ItemHome(
                81,
                "PowerShell",
                R.drawable.logo,
                titles.contains("81")
            )
        )

        datum.add(
            ItemHome(
                82,
                "Command Line",
                R.drawable.logo,
                titles.contains("82")
            )
        )

        datum.add(
            ItemHome(
                83,
                "Ubuntu",
                R.drawable.logo,
                titles.contains("83")
            )
        )

        datum.add(
            ItemHome(
                84,
                "Virtualization",
                R.drawable.logo,
                titles.contains("84")
            )
        )

        datum.add(
            ItemHome(
                85,
                "Server Administration",
                R.drawable.logo,
                titles.contains("85")
            )
        )

        datum.add(
            ItemHome(
                86,
                "Computer Repair",
                R.drawable.logo,
                titles.contains("86")
            )
        )

        adapter.notifyDataSetChanged()

        val list : ArrayList<CategoryModel> = ArrayList()
        val firstList : ArrayList<ItemHome> = ArrayList()
        firstList.add(ItemHome(1,"Ethical Hacking",R.drawable.ethi,false))
        firstList.add(ItemHome(2,"Cyber Security",R.drawable.cyber,false))
        firstList.add(ItemHome(3,"Network Security",R.drawable.security,false))
        firstList.add(ItemHome(4,"Penetration Testing",R.drawable.penetration,false))
        firstList.add(ItemHome(5,"Digital Marketing",R.drawable.digital,false))
        firstList.add(ItemHome(6,"Social Media Marketing",R.drawable.social,false))
        firstList.add(ItemHome(7,"Android Development",R.drawable.android,false))
        firstList.add(ItemHome(8,"Software Testing",R.drawable.software,false))
        firstList.add(ItemHome(9,"SEO",R.drawable.seo,false))
        firstList.add(ItemHome(10,"Web Development",R.drawable.web,false))
        list.add(CategoryModel("Information Security Certificate",firstList))

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
            intent.putExtra("price","1050")
            intent.putExtra("GSON", Gson().toJson(datum[pos]))
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