package `in`.indilabz.ssf_exam.activity


import `in`.indilabz.review_application.rest.RetrofitInstance
import `in`.indilabz.ssf_exam.INDIMaster
import `in`.indilabz.ssf_exam.R
import `in`.indilabz.ssf_exam.adapter.ExamPagerAdapter
import `in`.indilabz.ssf_exam.model.exam.ExamAnswer
import `in`.indilabz.ssf_exam.model.exam.ExamNav
import `in`.indilabz.ssf_exam.model.exam.ExamQuestion
import `in`.indilabz.ssf_exam.model.exam.ExamSubject
import `in`.indilabz.ssf_exam.rest.APIHelper
import `in`.indilabz.ssf_exam.utils.*
import `in`.indilabz.ssf_exam.adapter.ExamIndexAdapter
import `in`.indilabz.yorneeds.utils.INDIPreferences
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.google.android.material.navigation.NavigationView
import com.google.android.material.tabs.TabLayout
import dmax.dialog.SpotsDialog
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class ExamActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener,
    Constants, OnCountdownFinish {

    private var navigationView: NavigationView? = null
    private var drawer: DrawerLayout? = null
    private var mViewPager: ViewPager? = null
    private var navView: View? = null
    private var questionRecyclerNav: RecyclerView? = null
    private var subjecLayout: TabLayout? = null
    private var chronometer: TextView? = null

    private var gridLayoutManager: GridLayoutManager? = null
    private var prev: Button? = null
    private var nxt: Button? = null
    private var submit: Button? = null
    private var mark_for_review: Button? = null
    private var dialog_view: AlertDialog? = null

    private val questionIds = ArrayList<ExamNav>()
    //private val correctAnswers = ArrayList<ExamAnswer>()
    private var questions = ArrayList<ExamQuestion>()
    private val pagerQuestions = ArrayList<ExamQuestion>()
    private val subjectQuestions = ArrayList<ExamQuestion>()

    private val mark_review = ArrayList<Int>()
    private val subjects = ArrayList<ExamSubject>()

    private val uniqueAns: HashMap<Int, String> = HashMap()
    private val attempted: HashMap<Int, String> = HashMap()


    private var layoutManager: LinearLayoutManager? = null

    /*private var initial: Long = 0
    private var total: Long = 0*/

    private var min : Int = 0
    private var max : Int = 0

    private var intResume = 0
    private var intPause = 0

    private var questionPagerAdapter: ExamPagerAdapter? = null
    private var questionIndexAdapter: ExamIndexAdapter? = null

    private var NAV_ITEM = 0

    private lateinit var countdownTimer: CountdownTimer

    private lateinit var dialog: android.app.AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exam)

        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        dialog = SpotsDialog.Builder().setContext(this@ExamActivity)
                .build()

        drawer = findViewById<View>(R.id.drawer_layout) as DrawerLayout
        val toggle = ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)

        drawer!!.setDrawerListener(toggle)

        toolbar.inflateMenu(R.menu.question)
        toolbar.navigationIcon = resources.getDrawable(R.drawable.ic_arrow_back)
        toolbar.setNavigationOnClickListener { exitDialog() }

        subjecLayout = findViewById(R.id.subjecLayout)

        prev = findViewById<View>(R.id.prev) as Button
        nxt = findViewById<View>(R.id.nxt) as Button
        submit = findViewById<View>(R.id.submit) as Button
        mark_for_review = findViewById<View>(R.id.review) as Button

        navigationView = findViewById<View>(R.id.nav_view) as NavigationView
        navigationView!!.setNavigationItemSelectedListener(this)

        questionRecyclerNav = findViewById(R.id.recycle)
        chronometer = findViewById(R.id.count_down_timer)

        questions = INDIPreferences.questions()!!

        layoutManager = LinearLayoutManager(this@ExamActivity)
        layoutManager!!.orientation = LinearLayoutManager.VERTICAL


        val time = INDIPreferences.time()!!

        //Log.d("TAG_TIME", "$time");

        val seconds = time *  60

        countdownTimer = CountdownTimer(this@ExamActivity,
                seconds, chronometer, toolbar)

        countdownTimer.setOnCountdownFinish(this);

        countdownTimer.start()


        /*countdownTimer.setOnCountdownFinish {

        }*/


        /* //initial = System.currentTimeMillis()

         val txtInput = INDIPreferences.time()!!
         val timeInput = txtInput.toLong() * 1000
         //timeMil = timeInput

         timer = object: CountDownTimer(timeInput, 1000) {

             override fun onTick(millisUntilFinished: Long) {


                 count_down_timer.text = (String.format("%02d:%02d:%02d",
                         TimeUnit.MILLISECONDS.toHours(millisUntilFinished),
                         TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished),
                         TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) -
                                 TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))))

                 toolbar.title = (String.format("%02d:%02d:%02d",
                         TimeUnit.MILLISECONDS.toHours(millisUntilFinished),
                         TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished),
                         TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) -
                                 TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))))

                 *//*remainingTime = millisUntilFinished
                var seconds = millisUntilFinished / 1000
                var minutes = seconds / 60
                val hours = minutes / 60

                if (minutes > 0)
                    seconds = seconds % 60
                if (hours > 0)
                    minutes = minutes % 60
                val time = formatNumber(hours.toString()) + ":" + formatNumber(minutes.toString()) + ":" +
                        formatNumber(seconds.toString())

                toolbar.title = time*//*
            }

            override fun onFinish() {

                infoDialog((this@ExamActivity),
                        "Time up, submitting data")

                //saveData()
            }
        }


        timer.start()*/

        onClick()

        populateQuestions()

        populateQuestionsIds()

        populateSubjects()

    }

    @Throws(Exception::class)
    private fun populateQuestions() {

        questionPagerAdapter = ExamPagerAdapter(supportFragmentManager, pagerQuestions, getCurrent())

        mViewPager = findViewById<View>(R.id.pager) as ViewPager
        mViewPager!!.offscreenPageLimit = 0
        mViewPager!!.adapter = (questionPagerAdapter)
        mViewPager!!.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {

                updateAdapter()
            }

            override fun onPageScrollStateChanged(state: Int) {


            }
        })
        questionPagerAdapter!!.notifyDataSetChanged()


    }

    @Throws(Exception::class)
    private fun getCurrent(): String{

        return try{
            (mViewPager!!.currentItem+1).toString()
        }
        catch (e: Exception){
            "1"
        }
    }

    @Throws(Exception::class)
    private fun updateAdapter() {

        questionIndexAdapter = ExamIndexAdapter(
                questionIds, attempted, mark_review,
                NAV_ITEM, indexClick)

        questionRecyclerNav!!.adapter = questionIndexAdapter

        nxt!!.visibility = View.VISIBLE
        prev!!.visibility = View.VISIBLE

        if (mViewPager!!.currentItem == pagerQuestions.size) {

            nxt!!.text = "Submit"
        } else {

            nxt!!.text = "NEXT >>"
        }

        questionRecyclerNav!!.scrollToPosition(mViewPager!!.currentItem)
    }

    @Throws(Exception::class)
    private fun populateQuestionsIds() {

        questionIndexAdapter =
                ExamIndexAdapter(questionIds, uniqueAns,
                        mark_review,
                        mViewPager!!.currentItem,
                        indexClick)

        gridLayoutManager = INDIMaster.getGridLayoutManager(4)

        questionRecyclerNav!!.setHasFixedSize(true)
        questionRecyclerNav!!.layoutManager = gridLayoutManager
        questionRecyclerNav!!.itemAnimator = DefaultItemAnimator()
        questionRecyclerNav!!.adapter = questionIndexAdapter!!

    }

    @Throws(Exception::class)
    private fun populateSubjects(){

        subjects.clear()

        subjects.addAll(INDIPreferences.subjects()!!)

        val subjects = subjects.sortedWith(compareBy({ it.id }, { it.id }))

        for (subject in subjects){

            subjecLayout!!.addTab(subjecLayout!!.newTab().setText(subject.subject))
        }

        val suffled = ArrayList<ExamQuestion>()

        suffled.addAll(questions)

        suffled.shuffle()

        questions.clear()
        questions.addAll(suffled)

        questions.forEach{element ->

            uniqueAns[element.id!!] = "0"
        }


        val organised = suffled.sortedWith(compareBy({ it.subjectId }, { it.subjectId }))

        questions.clear()
        questions.addAll(organised)

        subjecLayout!!.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabReselected(p0: TabLayout.Tab?) {

            }

            override fun onTabUnselected(p0: TabLayout.Tab?) {

            }

            override fun onTabSelected(p0: TabLayout.Tab?) {

                classifyQuestions(subjects[p0!!.position].id)
            }

        })

        if(subjects.isNotEmpty()){
            classifyQuestions(subjects[0].id)
        }

        saveStartData()
    }

    @Throws(Exception::class)
    private fun classifyQuestions(pos: Int){

        //Log.d("TAG_SHUFFLED", INDIMaster.gson.toJson(questions))

        var current = -1

        subjectQuestions.clear()

        pagerQuestions.clear()

        questionIds.clear()

        pagerQuestions.addAll(questions)

        for(i in 0 until  questions.size){

            if(pos == questions[i].subjectId){

                if(current == -1){

                    current = i
                }

                max = i

                var qid = ExamNav()
                qid.index = i
                qid.questionId = questions[i].id
                questionIds.add(qid)

                subjectQuestions.add(questions[i])

                Log.d("TAG_SELECTED_SUBJECT", "${ questions[i].subjectId}")

            }
        }

        min = current

        mViewPager!!.currentItem = min

        NAV_ITEM = 0

        questionIndexAdapter!!.notifyItem(NAV_ITEM)
        questionIndexAdapter!!.notifyDataSetChanged()
        questionPagerAdapter!!.notifyDataSetChanged()

    }

    @Throws(Exception::class)
    private fun onClick() {


        prev!!.setOnClickListener {

            if (mViewPager!!.currentItem == min) {

                Toaster.longt("No questions to display!")
            } else {

                NAV_ITEM -= 1

                questionIndexAdapter!!.notifyItem(NAV_ITEM)

                Handler().postDelayed({

                    mViewPager!!.currentItem-1

                    mViewPager!!.currentItem = mViewPager!!.currentItem - 1
                }, 300)
            }

        }

        mark_for_review!!.setOnClickListener {

            if (mark_review.size == 0) {

                mark_review.add(questionIds[NAV_ITEM].questionId!!)

            } else {

                if (mark_review.contains(questionIds[NAV_ITEM].questionId!!)) {

                    Log.d("TAG_CHK", "TRUE")

                    mark_review.removeAt(getMarkPosition(NAV_ITEM))

                } else {

                    Log.d("TAG_CHK", "FALSE")

                    mark_review.add(questionIds[NAV_ITEM].questionId!!)
                }
            }

            questionIndexAdapter!!.notifyDataSetChanged()
        }

        nxt!!.setOnClickListener {

            if (mViewPager!!.currentItem == max) {

                submit()

            } else {

                NAV_ITEM += 1

                questionIndexAdapter!!.notifyItem(NAV_ITEM)

                Handler().postDelayed({
                    mViewPager!!.currentItem = mViewPager!!.currentItem + 1
                }, 300)
            }
        }

        submit!!.setOnClickListener { submit() }
    }

    @Throws(Exception::class)
    private fun getMarkPosition(value: Int): Int {

        for (i in questionIds.indices) {

            if (questionIds[i].questionId!! == value) {

                return i
            }
        }

        return 0
    }

    @Throws(Exception::class)
    fun addAnswer(qid: Int, value: String) : Boolean{


        INDIPreferences.questionAns(qid.toString(), value)

        Log.d("TAG_VAL_PUT", qid.toString())

        uniqueAns[qid] = value

        attempted[qid] = value

        Log.d("TAG_HASH_ANS", INDIMaster.gson.toJson(uniqueAns))

        return true
    }

    @Throws(Exception::class)
    private fun submit() {

        try {

            dialog_view!!.cancel()
        } catch (e: Exception) {

            Log.d("", e.toString())
        }

        val dialogBuilder = AlertDialog.Builder(this@ExamActivity)
        val inflater = layoutInflater
        val dialogView = inflater.inflate(R.layout.dialog_submit, null)
        dialogBuilder.setView(dialogView)
        dialogBuilder.setCancelable(true)
        dialogBuilder.setView(dialogView)
        dialogBuilder.setCancelable(false)

        val submit = dialogView.findViewById<Button>(R.id.submit)
        val close = dialogView.findViewById<Button>(R.id.close)


        close.setOnClickListener { dialog_view!!.cancel() }

        submit.setOnClickListener {

            saveData()

            dialog_view!!.dismiss()
        }

        dialog_view = dialogBuilder.create()
        dialog_view!!.show()
    }

    @Throws(Exception::class)
    private fun saveData(){

        countdownTimer.stop()


        val keyList: List<Int>  = Collections.list(Collections.enumeration(uniqueAns.keys))
        val valueList: List<String>  = Collections.list(Collections.enumeration(uniqueAns.values))

        val ans: ArrayList<ExamAnswer> = ArrayList()

        for(i in keyList.indices){

            val exam = ExamAnswer()
            exam.question_id = keyList[i]
            exam.option = valueList[i]

            ans.add(exam)
        }

        Log.d("TAG_HASH_ALL", INDIMaster.gson.toJson(ans))


        dialog.show()

        RetrofitInstance.getRetrofit(
                INDIMaster.session().saveResult(
                        INDIPreferences.user()!!.applicant_name,
                        INDIPreferences.user()!!.id!!,
                        INDIPreferences.exam()!!,
                        INDIMaster.gson.toJson(ans)
                ),
                resultListener
        )



        /*val mAPIService = RetrofitInstance.session().create(API::class.java)

        val call = mAPIService.saveResult(INDIPreferences.user()!!.name!!,
                INDIPreferences.user()!!.id!!,
                INDIPreferences.exam()!!,
                INDIMaster.gson.toJson(ans))

        call.enqueue(object : Callback<String> {

            override fun onResponse(call: Call<String>, response: Response<String>) {
                Toaster.shortToast(response.body()!!)
            }

            override fun onFailure(call: Call<String>, t: Throwable) {

                Toaster.shortToast(t.message!!)
            }


        })*/
    }

    @Throws(Exception::class)
    private fun saveStartData(){

        val keyList: List<Int>  = Collections.list(Collections.enumeration(uniqueAns.keys))
        val valueList: List<String>  = Collections.list(Collections.enumeration(uniqueAns.values))

        val ans: ArrayList<ExamAnswer> = ArrayList()

        for(i in keyList.indices){

            val exam = ExamAnswer()
            exam.question_id = keyList[i]
            exam.option = valueList[i]

            ans.add(exam)
        }

         /*RetrofitInstance.getRetrofit(
                 INDIMaster.session().saveResult(
                         INDIPreferences.user()!!.name!!,
                         INDIPreferences.user()!!.id!!,
                         INDIPreferences.exam()!!,
                         INDIMaster.gson.toJson(ans)
                 ),
                 startResultListener
         )*/
    }

    private val indexClick = {pos:Int, index: Int ->

        mViewPager!!.setCurrentItem(index, true)

        NAV_ITEM = pos

        questionIndexAdapter!!.notifyItem(pos)

        drawer!!.closeDrawer(GravityCompat.END)
    }


    private val resultListener = { code:Int, bool:Boolean, value:String ->

        try{
            dialog.dismiss()

            if(bool){

                var error = APIHelper.error(value)

                if(error == "NO_ERROR"){

                    DialogHelper().infoDialog((this@ExamActivity),
                            "Thanks for taking exam. Your results will be out soon!"
                    ) {
                        startActivity(Intent(this, MainActivity::class.java))
                        finish()
                        //this.exitDialog()
                    }
                }
                else{
                    Toaster.longt(APIHelper.result(value).replace("_", " "))
                }
            }
            else{
                Toaster.longt(APIHelper.result(value).replace("_", " "))
            }
        }
        catch(e: Exception){

            e.printStackTrace()
        }
    }

    private val startResultListener = { code:Int, bool:Boolean, value:String ->

    }

    private fun exitDialog() {

        val dialogBuilder = AlertDialog.Builder(this@ExamActivity)
        val inflater = layoutInflater
        val dialogView = inflater.inflate(R.layout.dialog_exit, null)
        dialogBuilder.setView(dialogView)
        dialogBuilder.setCancelable(true)
        dialogBuilder.setView(dialogView)
        dialogBuilder.setCancelable(false)

        val exit = dialogView.findViewById<View>(R.id.exit) as Button
        val cancel = dialogView.findViewById<View>(R.id.close) as Button


        exit.setOnClickListener {
            countdownTimer.stop()
            finish()
        }

        cancel.setOnClickListener {
            dialog_view!!.cancel()
        }

        dialog_view = dialogBuilder.create()
        dialog_view!!.show()
    }


    private fun showCheatingAlert(){

        DialogHelper().infoDialog(this@ExamActivity,
                "Please don't change tabs. That will be considered as cheating." +
                " As a result of continuing such practice your exam will be submitted at the very instance without anymore warnings.")
    }

    private fun cheatingSubmit(){

        saveData()

        DialogHelper().infoDialog(this, "Since, you have surpassed the threshold of APP switching you are kicked out of exam")
    }

    override fun onBackPressed() {

        if (drawer!!.isDrawerOpen(GravityCompat.START)) {
            drawer!!.closeDrawer(GravityCompat.START)
        } else {
            exitDialog()
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {

        menuInflater.inflate(R.menu.question, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val id = item.itemId

        if (id == R.id.nav_open) {

            drawer!!.openDrawer(Gravity.RIGHT)

            return true
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onPause() {
        super.onPause()

        intPause++

    }

    override fun onResume() {
        super.onResume()

        intResume++

        if(intResume > 3 ){

            cheatingSubmit()
        }

        if(intResume in 2..3){
            showCheatingAlert()
        }

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        val id = item.itemId


        drawer!!.closeDrawer(GravityCompat.END)
        return true
    }

    override fun onCountdownFinish() {


        DialogHelper().infoDialog((this@ExamActivity),
                "Time up, submitting data")

        saveData()

    }
}
