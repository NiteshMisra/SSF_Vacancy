package `in`.indilabz.ssf_exam.fragment


import `in`.indilabz.ssf_exam.R
import `in`.indilabz.ssf_exam.activity.ExamActivity
import `in`.indilabz.ssf_exam.model.exam.ExamQuestion
import `in`.indilabz.ssf_exam.utils.Constants
import `in`.indilabz.ssf_exam.utils.Constants.Companion.ROOT_URL
import `in`.indilabz.yorneeds.utils.INDIPreferences
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import im.delight.android.webview.AdvancedWebView


class QuestionFragment : Fragment(), Constants {

    private lateinit var parentView: View
    private var language: TextView? = null
    private var passage: AdvancedWebView? = null
    private var question: AdvancedWebView? = null
    private var opt_a: AdvancedWebView? = null
    private var opt_b: AdvancedWebView? = null
    private var opt_c: AdvancedWebView? = null
    private var opt_d: AdvancedWebView? = null
    private var opt_e: AdvancedWebView? = null
    private lateinit var modelQuestion : ExamQuestion
    private var opt_chk_a: CheckBox? = null
    private var opt_chk_b: CheckBox? = null
    private var opt_chk_c: CheckBox? = null
    private var opt_chk_d: CheckBox? = null
    private var opt_chk_e: CheckBox? = null
    private lateinit var positive: TextView
    private lateinit var negative: TextView
    private lateinit var questionLayoutE: RelativeLayout
    private var position: Int = 0

    private var questionInd: String = ""

    fun setFragmentPosition(position: Int,
                            questionInd:String): Fragment {

        this.position = position
        this.modelQuestion = INDIPreferences.question()!!
        this.questionInd = questionInd

        return this
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        parentView = inflater.inflate(R.layout.fragment_question, container, false)

        language = parentView!!.findViewById<View>(R.id.language) as TextView
        positive = parentView.findViewById(R.id.positive)
        negative = parentView.findViewById(R.id.negative)
        passage = parentView!!.findViewById<View>(R.id.passage) as AdvancedWebView
        question = parentView!!.findViewById<View>(R.id.question) as AdvancedWebView
        opt_chk_a = parentView!!.findViewById<View>(R.id.opt_chk_a) as CheckBox
        opt_chk_b = parentView!!.findViewById<View>(R.id.opt_chk_b) as CheckBox
        opt_chk_c = parentView!!.findViewById<View>(R.id.opt_chk_c) as CheckBox
        opt_chk_d = parentView!!.findViewById<View>(R.id.opt_chk_d) as CheckBox
        opt_chk_e = parentView!!.findViewById<View>(R.id.opt_chk_e) as CheckBox
        opt_a = parentView!!.findViewById<View>(R.id.option_a) as AdvancedWebView
        opt_b = parentView!!.findViewById<View>(R.id.option_b) as AdvancedWebView
        opt_c = parentView!!.findViewById<View>(R.id.option_c) as AdvancedWebView
        opt_d = parentView!!.findViewById<View>(R.id.option_d) as AdvancedWebView
        opt_e = parentView!!.findViewById<View>(R.id.option_e) as AdvancedWebView
        questionLayoutE = parentView!!.findViewById(R.id.questionLayoutE) as RelativeLayout

        question!!.setInitialScale(2)
        question!!.settings.loadWithOverviewMode = true
        question!!.settings.useWideViewPort = true

        opt_a!!.settings.loadWithOverviewMode = true
        opt_b!!.settings.useWideViewPort = true

        INDIPreferences.backpress(false)

        if(modelQuestion.option5!!.length>0){

            questionLayoutE.visibility = View.VISIBLE
        }
        else{

            questionLayoutE.visibility = View.GONE
        }

        return parentView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        language!!.setOnClickListener{
            languageList()
        }

        positive.text = "${modelQuestion.weightage!!} (Correct)"
        negative.text = "${modelQuestion.negativeMarks!!.toFloat()/100} (Incorrect)"
        
        setLanguage()

        try {
            Handler(Looper.getMainLooper()).postDelayed(Runnable {
                //Do something here
                setOption()
            }, 500)
        }
        catch (e: Exception){

            e.printStackTrace()
        }

        onCheckClicked()
    }


    private fun setLanguage(){

        if(INDIPreferences.language()!! == "en"){
            Log.d("TAG_STEP_HERE", "ENG")
            language!!.text = "English"
            setUpEnglishFragmentData()
        }

        if(INDIPreferences.language()!! == "hi"){

            Log.d("TAG_STEP_HERE", "HIN")
            language!!.text = "Hindi"
            setUpHindiFragmentData()
        }
    }

    private fun setOption(){

        val qid = (modelQuestion.id).toString()

        val option  = INDIPreferences.questionAns(qid)!!

        Log.d("TAG_VAL_GET", "$qid   $option")

        opt_chk_a!!.setOnCheckedChangeListener(null)
        opt_chk_b!!.setOnCheckedChangeListener(null)
        opt_chk_c!!.setOnCheckedChangeListener(null)
        opt_chk_d!!.setOnCheckedChangeListener(null)



        if(option == "0"){

            uncheck()

            Log.d("TAG_VALUE_DIS", "0")
        }

        if(option == "1"){

            uncheck("a")

            Log.d("TAG_VALUE_DIS", "1")
        }

        if(option == "2"){

            uncheck("b")

            Log.d("TAG_VALUE_DIS", "2")
        }

        if(option == "3"){

            uncheck("c")

            Log.d("TAG_VALUE_DIS", "3")
        }

        if(option == "4"){

            uncheck("d")

            Log.d("TAG_VALUE_DIS", "4")
        }

        if(option == "5"){

            uncheck("e")

            Log.d("TAG_VALUE_DIS", "4")
        }

        onCheckClicked()
    }

    private fun languageList(){

        val builder = AlertDialog.Builder(context!!)
        builder.setTitle("Choose a language")

        val languages = arrayOf("English", "Hindi")
        builder.setItems(languages) { dialog, which ->
            when (which) {
                0 -> {
                    language!!.text = "English"
                    INDIPreferences.language("en")
                    setUpEnglishFragmentData()
                }
                1 -> {
                    language!!.text = "Hindi"
                    INDIPreferences.language("hi")
                    setUpHindiFragmentData()
                }
            }
        }

// create and show the alert dialog
        val dialog = builder.create()
        dialog.show()
    }

    private fun setUpHindiFragmentData() {

        passage!!.loadDataWithBaseURL("http://bar",
                "<html>" +
                        "<head>" +
                        "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">" +
                        "</head>" +
                        "<body>" +
                        "<p style=\"font-size: 14pt;\">"+ getString(modelQuestion.passageHi) + "</p>\n" +
                        "<script type=\"text/x-mathjax-config\">" +
                        "  MathJax.Hub.Config({\n" +
                        "  CommonHTML: { linebreaks: { automatic: true },EqnChunk:(MathJax.Hub.Browser.isMobile?10:50) },displayAlign: \"left\",\n" +
                        "  \"HTML-CSS\": { linebreaks: { automatic: true } ," +
                        "\n" +
                        "    preferredFont: \"STIX\"}," +
                        "extensions: [\"tex2jax.js\"],messageStyle:\"none\"," +
                        "jax: [\"input/TeX\", \"input/MathML\",\"output/HTML-CSS\"]," +
                        "tex2jax: {inlineMath: [['$','$'],['\\\\(','\\\\)']]}" +
                        "});" +
                        "</script>" +
                        "<script type=\"text/javascript\" async src=\"file:///android_asset/MathJax/MathJax.js?config=TeX-AMS-MML_HTMLorMML\"></script>" +
                        "" +
                        "</body>" +
                        "</html>", "text/html", "utf-8", "")

        passage!!.loadUrl("javascript:MathJax.Hub.Queue(['Typeset',MathJax.Hub]);")


        question!!.loadDataWithBaseURL("http://bar",
                "<html>" +
                        "<head>" +
                        "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">" +
                        "</head>" +
                        "<body>" +
                        "<p style=\"font-size: 14pt;\">" +"${position+1} . "+ getString(modelQuestion.questionHi) + "</p>\n" +
                        "<script type=\"text/x-mathjax-config\">" +
                        "  MathJax.Hub.Config({\n" +
                        "  CommonHTML: { linebreaks: { automatic: true },EqnChunk:(MathJax.Hub.Browser.isMobile?10:50) },displayAlign: \"left\",\n" +
                        "  \"HTML-CSS\": { linebreaks: { automatic: true } ," +
                        "\n" +
                        "    preferredFont: \"STIX\"}," +
                        "extensions: [\"tex2jax.js\"],messageStyle:\"none\"," +
                        "jax: [\"input/TeX\", \"input/MathML\",\"output/HTML-CSS\"]," +
                        "tex2jax: {inlineMath: [['$','$'],['\\\\(','\\\\)']]}" +
                        "});" +
                        "</script>" +
                        "<script type=\"text/javascript\" async src=\"file:///android_asset/MathJax/MathJax.js?config=TeX-AMS-MML_HTMLorMML\"></script>" +
                        "" +
                        "</body>" +
                        "</html>", "text/html", "utf-8", "")

        question!!.loadUrl("javascript:MathJax.Hub.Queue(['Typeset',MathJax.Hub]);")

        opt_a!!.loadDataWithBaseURL("http://bar",
                "<html>" +
                        "<head>" +
                        "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">" +
                        "</head>" +
                        "<body>" +
                        "<p style=\"font-size: 14pt;\">" + getString(modelQuestion.optionA) + "</p>\n" +
                        "<script type=\"text/x-mathjax-config\">" +
                        "  MathJax.Hub.Config({\n" +
                        "  CommonHTML: { linebreaks: { automatic: true },EqnChunk:(MathJax.Hub.Browser.isMobile?10:50) },displayAlign: \"left\",\n" +
                        "  \"HTML-CSS\": { linebreaks: { automatic: true } ," +
                        "\n" +
                        "    preferredFont: \"STIX\"}," +
                        "extensions: [\"tex2jax.js\"],messageStyle:\"none\"," +
                        "jax: [\"input/TeX\", \"input/MathML\",\"output/HTML-CSS\"]," +
                        "tex2jax: {inlineMath: [['$','$'],['\\\\(','\\\\)']]}" +
                        "});" +
                        "</script>" +
                        "<script type=\"text/javascript\" async src=\"file:///android_asset/MathJax/MathJax.js?config=TeX-AMS-MML_HTMLorMML\"></script>" +
                        "" +
                        "</body>" +
                        "</html>", "text/html", "utf-8", "")

        opt_a!!.loadUrl("javascript:MathJax.Hub.Queue(['Typeset',MathJax.Hub]);")

        opt_b!!.loadDataWithBaseURL("http://bar",
                "<html>" +
                        "<head>" +
                        "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">" +
                        "</head>" +
                        "<body>" +
                        "<p style=\"font-size: 14pt;\">" + getString(modelQuestion.optionB) + "</p>\n" +
                        "<script type=\"text/x-mathjax-config\">" +
                        "  MathJax.Hub.Config({\n" +
                        "  CommonHTML: { linebreaks: { automatic: true },EqnChunk:(MathJax.Hub.Browser.isMobile?10:50) },displayAlign: \"left\",\n" +
                        "  \"HTML-CSS\": { linebreaks: { automatic: true } ," +
                        "\n" +
                        "    preferredFont: \"STIX\"}," +
                        "extensions: [\"tex2jax.js\"],messageStyle:\"none\"," +
                        "jax: [\"input/TeX\", \"input/MathML\",\"output/HTML-CSS\"]," +
                        "tex2jax: {inlineMath: [['$','$'],['\\\\(','\\\\)']]}" +
                        "});" +
                        "</script>" +
                        "<script type=\"text/javascript\" async src=\"file:///android_asset/MathJax/MathJax.js?config=TeX-AMS-MML_HTMLorMML\"></script>" +
                        "" +
                        "</body>" +
                        "</html>", "text/html", "utf-8", "")

        opt_b!!.loadUrl("javascript:MathJax.Hub.Queue(['Typeset',MathJax.Hub]);")

        opt_c!!.loadDataWithBaseURL("http://bar",
                "<html>" +
                        "<head>" +
                        "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">" +
                        "</head>" +
                        "<body>" +
                        "<p style=\"font-size: 14pt;\">" + getString(modelQuestion.optionC) + "</p>\n" +
                        "<script type=\"text/x-mathjax-config\">" +
                        "  MathJax.Hub.Config({\n" +
                        "  CommonHTML: { linebreaks: { automatic: true },EqnChunk:(MathJax.Hub.Browser.isMobile?10:50) },displayAlign: \"left\",\n" +
                        "  \"HTML-CSS\": { linebreaks: { automatic: true } ," +
                        "\n" +
                        "    preferredFont: \"STIX\"}," +
                        "extensions: [\"tex2jax.js\"],messageStyle:\"none\"," +
                        "jax: [\"input/TeX\", \"input/MathML\",\"output/HTML-CSS\"]," +
                        "tex2jax: {inlineMath: [['$','$'],['\\\\(','\\\\)']]}" +
                        "});" +
                        "</script>" +
                        "<script type=\"text/javascript\" async src=\"file:///android_asset/MathJax/MathJax.js?config=TeX-AMS-MML_HTMLorMML\"></script>" +
                        "" +
                        "</body>" +
                        "</html>", "text/html", "utf-8", "")

        opt_c!!.loadUrl("javascript:MathJax.Hub.Queue(['Typeset',MathJax.Hub]);")

        opt_d!!.loadDataWithBaseURL("http://bar",
                "<html>" +
                        "<head>" +
                        "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">" +
                        "</head>" +
                        "<body>" +
                        "<p style=\"font-size: 14pt;\">" + getString(modelQuestion.optionD) + "</p>\n" +
                        "<script type=\"text/x-mathjax-config\">" +
                        "  MathJax.Hub.Config({\n" +
                        "  CommonHTML: { linebreaks: { automatic: true },EqnChunk:(MathJax.Hub.Browser.isMobile?10:50) },displayAlign: \"left\",\n" +
                        "  \"HTML-CSS\": { linebreaks: { automatic: true } ," +
                        "\n" +
                        "    preferredFont: \"STIX\"}," +
                        "extensions: [\"tex2jax.js\"],messageStyle:\"none\"," +
                        "jax: [\"input/TeX\", \"input/MathML\",\"output/HTML-CSS\"]," +
                        "tex2jax: {inlineMath: [['$','$'],['\\\\(','\\\\)']]}" +
                        "});" +
                        "</script>" +
                        "<script type=\"text/javascript\" async src=\"file:///android_asset/MathJax/MathJax.js?config=TeX-AMS-MML_HTMLorMML\"></script>" +
                        "" +
                        "</body>" +
                        "</html>", "text/html", "utf-8", "")

        opt_d!!.loadUrl("javascript:MathJax.Hub.Queue(['Typeset',MathJax.Hub]);")

       /* opt_e!!.loadDataWithBaseURL("http://bar",
                "<html>" +
                        "<head>" +
                        "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">" +
                        "</head>" +
                        "<body>" +
                        "<p style=\"font-size: 14pt;\">" + modelQuestions[position].optionE + "</p>\n" +
                        "<script type=\"text/x-mathjax-config\">" +
                        "  MathJax.Hub.Config({\n" +
                        "  CommonHTML: { linebreaks: { automatic: true },EqnChunk:(MathJax.Hub.Browser.isMobile?10:50) },displayAlign: \"left\",\n" +
                        "  \"HTML-CSS\": { linebreaks: { automatic: true } ," +
                        "\n" +
                        "    preferredFont: \"STIX\"}," +
                        "extensions: [\"tex2jax.js\"],messageStyle:\"none\"," +
                        "jax: [\"input/TeX\", \"input/MathML\",\"output/HTML-CSS\"]," +
                        "tex2jax: {inlineMath: [['$','$'],['\\\\(','\\\\)']]}" +
                        "});" +
                        "</script>" +
                        "<script type=\"text/javascript\" async src=\"file:///android_asset/MathJax/MathJax.js?config=TeX-AMS-MML_HTMLorMML\"></script>" +
                        "" +
                        "</body>" +
                        "</html>", "text/html", "utf-8", "")

        opt_e!!.loadUrl("javascript:MathJax.Hub.Queue(['Typeset',MathJax.Hub]);")
*/
    }

    private fun setUpEnglishFragmentData() {

        passage!!.loadDataWithBaseURL("http://bar",
                "<html>" +
                        "<head>" +
                        "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">" +
                        "</head>" +
                        "<body>" +
                        "<p style=\"font-size: 14pt;\">"+ getString(modelQuestion.passageEn) + "</p>\n" +
                        "<script type=\"text/x-mathjax-config\">" +
                        "  MathJax.Hub.Config({\n" +
                        "  CommonHTML: { linebreaks: { automatic: true },EqnChunk:(MathJax.Hub.Browser.isMobile?10:50) },displayAlign: \"left\",\n" +
                        "  \"HTML-CSS\": { linebreaks: { automatic: true } ," +
                        "\n" +
                        "    preferredFont: \"STIX\"}," +
                        "extensions: [\"tex2jax.js\"],messageStyle:\"none\"," +
                        "jax: [\"input/TeX\", \"input/MathML\",\"output/HTML-CSS\"]," +
                        "tex2jax: {inlineMath: [['$','$'],['\\\\(','\\\\)']]}" +
                        "});" +
                        "</script>" +
                        "<script type=\"text/javascript\" async src=\"file:///android_asset/MathJax/MathJax.js?config=TeX-AMS-MML_HTMLorMML\"></script>" +
                        "" +
                        "</body>" +
                        "</html>", "text/html", "utf-8", "")

        passage!!.loadUrl("javascript:MathJax.Hub.Queue(['Typeset',MathJax.Hub]);")


        question!!.loadDataWithBaseURL(
                "http://bar",
                "<html>" +
                        "<head>" +
                        "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">" +
                        "</head>" +
                        "<body>" +
                        "<p style=\"font-size: 14pt;\">" +"${position+1} . "+ getString(modelQuestion.questionEn) + "</p>\n" +
                        "<script type=\"text/x-mathjax-config\">" +
                        "  MathJax.Hub.Config({\n" +
                        "  CommonHTML: { linebreaks: { automatic: true },EqnChunk:(MathJax.Hub.Browser.isMobile?10:50) },displayAlign: \"left\",\n" +
                        "  \"HTML-CSS\": { linebreaks: { automatic: true } ," +
                        "\n" +
                        "    preferredFont: \"STIX\"}," +
                        "extensions: [\"tex2jax.js\"],messageStyle:\"none\"," +
                        "jax: [\"input/TeX\", \"input/MathML\",\"output/HTML-CSS\"]," +
                        "tex2jax: {inlineMath: [['$','$'],['\\\\(','\\\\)']]}" +
                        "});" +
                        "</script>" +
                        "<script type=\"text/javascript\" async src=\"file:///android_asset/MathJax/MathJax.js?config=TeX-AMS-MML_HTMLorMML\"></script>" +
                        "" +
                        "</body>" +
                        "</html>", "text/html", "utf-8", "")

        question!!.loadUrl("javascript:MathJax.Hub.Queue(['Typeset',MathJax.Hub]);")

        opt_a!!.loadDataWithBaseURL("http://bar",
                "<html>" +
                        "<head>" +
                        "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">" +
                        "</head>" +
                        "<body>" +
                        "<p style=\"font-size: 14pt;\">" + getString(modelQuestion.option1) + "</p>\n" +
                        "<script type=\"text/x-mathjax-config\">" +
                        "  MathJax.Hub.Config({\n" +
                        "  CommonHTML: { linebreaks: { automatic: true },EqnChunk:(MathJax.Hub.Browser.isMobile?10:50) },displayAlign: \"left\",\n" +
                        "  \"HTML-CSS\": { linebreaks: { automatic: true } ," +
                        "\n" +
                        "    preferredFont: \"STIX\"}," +
                        "extensions: [\"tex2jax.js\"],messageStyle:\"none\"," +
                        "jax: [\"input/TeX\", \"input/MathML\",\"output/HTML-CSS\"]," +
                        "tex2jax: {inlineMath: [['$','$'],['\\\\(','\\\\)']]}" +
                        "});" +
                        "</script>" +
                        "<script type=\"text/javascript\" async src=\"file:///android_asset/MathJax/MathJax.js?config=TeX-AMS-MML_HTMLorMML\"></script>" +
                        "" +
                        "</body>" +
                        "</html>", "text/html", "utf-8", "")

        opt_a!!.loadUrl("javascript:MathJax.Hub.Queue(['Typeset',MathJax.Hub]);")

        opt_b!!.loadDataWithBaseURL("http://bar",
                "<html>" +
                        "<head>" +
                        "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">" +
                        "</head>" +
                        "<body>" +
                        "<p style=\"font-size: 14pt;\">" + getString(modelQuestion.option2) + "</p>\n" +
                        "<script type=\"text/x-mathjax-config\">" +
                        "  MathJax.Hub.Config({\n" +
                        "  CommonHTML: { linebreaks: { automatic: true },EqnChunk:(MathJax.Hub.Browser.isMobile?10:50) },displayAlign: \"left\",\n" +
                        "  \"HTML-CSS\": { linebreaks: { automatic: true } ," +
                        "\n" +
                        "    preferredFont: \"STIX\"}," +
                        "extensions: [\"tex2jax.js\"],messageStyle:\"none\"," +
                        "jax: [\"input/TeX\", \"input/MathML\",\"output/HTML-CSS\"]," +
                        "tex2jax: {inlineMath: [['$','$'],['\\\\(','\\\\)']]}" +
                        "});" +
                        "</script>" +
                        "<script type=\"text/javascript\" async src=\"file:///android_asset/MathJax/MathJax.js?config=TeX-AMS-MML_HTMLorMML\"></script>" +
                        "" +
                        "</body>" +
                        "</html>", "text/html", "utf-8", "")

        opt_b!!.loadUrl("javascript:MathJax.Hub.Queue(['Typeset',MathJax.Hub]);")

        opt_c!!.loadDataWithBaseURL("http://bar",
                "<html>" +
                        "<head>" +
                        "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">" +
                        "</head>" +
                        "<body>" +
                        "<p style=\"font-size: 14pt;\">" + getString(modelQuestion.option3) + "</p>\n" +
                        "<script type=\"text/x-mathjax-config\">" +
                        "  MathJax.Hub.Config({\n" +
                        "  CommonHTML: { linebreaks: { automatic: true },EqnChunk:(MathJax.Hub.Browser.isMobile?10:50) },displayAlign: \"left\",\n" +
                        "  \"HTML-CSS\": { linebreaks: { automatic: true } ," +
                        "\n" +
                        "    preferredFont: \"STIX\"}," +
                        "extensions: [\"tex2jax.js\"],messageStyle:\"none\"," +
                        "jax: [\"input/TeX\", \"input/MathML\",\"output/HTML-CSS\"]," +
                        "tex2jax: {inlineMath: [['$','$'],['\\\\(','\\\\)']]}" +
                        "});" +
                        "</script>" +
                        "<script type=\"text/javascript\" async src=\"file:///android_asset/MathJax/MathJax.js?config=TeX-AMS-MML_HTMLorMML\"></script>" +
                        "" +
                        "</body>" +
                        "</html>", "text/html", "utf-8", "")

        opt_c!!.loadUrl("javascript:MathJax.Hub.Queue(['Typeset',MathJax.Hub]);")

        opt_d!!.loadDataWithBaseURL("http://bar",
                "<html>" +
                        "<head>" +
                        "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">" +
                        "</head>" +
                        "<body>" +
                        "<p style=\"font-size: 14pt;\">" + getString(modelQuestion.option4) + "</p>\n" +
                        "<script type=\"text/x-mathjax-config\">" +
                        "  MathJax.Hub.Config({\n" +
                        "  CommonHTML: { linebreaks: { automatic: true },EqnChunk:(MathJax.Hub.Browser.isMobile?10:50) },displayAlign: \"left\",\n" +
                        "  \"HTML-CSS\": { linebreaks: { automatic: true } ," +
                        "\n" +
                        "    preferredFont: \"STIX\"}," +
                        "extensions: [\"tex2jax.js\"],messageStyle:\"none\"," +
                        "jax: [\"input/TeX\", \"input/MathML\",\"output/HTML-CSS\"]," +
                        "tex2jax: {inlineMath: [['$','$'],['\\\\(','\\\\)']]}" +
                        "});" +
                        "</script>" +
                        "<script type=\"text/javascript\" async src=\"file:///android_asset/MathJax/MathJax.js?config=TeX-AMS-MML_HTMLorMML\"></script>" +
                        "" +
                        "</body>" +
                        "</html>", "text/html", "utf-8", "")

        opt_d!!.loadUrl("javascript:MathJax.Hub.Queue(['Typeset',MathJax.Hub]);")

        opt_e!!.loadDataWithBaseURL("http://bar",
                "<html>" +
                        "<head>" +
                        "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">" +
                        "</head>" +
                        "<body>" +
                        "<p style=\"font-size: 14pt;\">" + getString(modelQuestion.option5) + "</p>\n" +
                        "<script type=\"text/x-mathjax-config\">" +
                        "  MathJax.Hub.Config({\n" +
                        "  CommonHTML: { linebreaks: { automatic: true },EqnChunk:(MathJax.Hub.Browser.isMobile?10:50) },displayAlign: \"left\",\n" +
                        "  \"HTML-CSS\": { linebreaks: { automatic: true } ," +
                        "\n" +
                        "    preferredFont: \"STIX\"}," +
                        "extensions: [\"tex2jax.js\"],messageStyle:\"none\"," +
                        "jax: [\"input/TeX\", \"input/MathML\",\"output/HTML-CSS\"]," +
                        "tex2jax: {inlineMath: [['$','$'],['\\\\(','\\\\)']]}" +
                        "});" +
                        "</script>" +
                        "<script type=\"text/javascript\" async src=\"file:///android_asset/MathJax/MathJax.js?config=TeX-AMS-MML_HTMLorMML\"></script>" +
                        "" +
                        "</body>" +
                        "</html>", "text/html", "utf-8", "")

        opt_e!!.loadUrl("javascript:MathJax.Hub.Queue(['Typeset',MathJax.Hub]);")

    }


    private fun getString(data: String?): String{

        return data!!.replace("../question_files", "$ROOT_URL/question_files")
    }

    private fun onCheckClicked() {

        opt_chk_a!!.setOnCheckedChangeListener { buttonView, b ->

            uncheck("a")

            opt_chk_a!!.isChecked = b
            (activity as ExamActivity).addAnswer(modelQuestion.id!!, optionA)

        }

        opt_chk_b!!.setOnCheckedChangeListener { buttonView, b ->

            uncheck("b")

            opt_chk_b!!.isChecked = b
            (activity as ExamActivity).addAnswer(modelQuestion.id!!, optionB)
        }

        opt_chk_c!!.setOnCheckedChangeListener { buttonView, b ->

            uncheck("c")

            opt_chk_c!!.isChecked = b
            (activity as ExamActivity).addAnswer(modelQuestion.id!!, optionC)
        }

        opt_chk_d!!.setOnCheckedChangeListener { buttonView, b ->

            uncheck("d")

            opt_chk_d!!.isChecked = b
            (activity as ExamActivity).addAnswer(modelQuestion.id!!, optionD)
        }

        opt_chk_e!!.setOnCheckedChangeListener { buttonView, b ->

            uncheck("e")

            opt_chk_e!!.isChecked = b
            (activity as ExamActivity).addAnswer(modelQuestion.id!!, optionE)
        }
    }

    private fun uncheck(option: String){

        opt_chk_a!!.isChecked = false
        opt_chk_b!!.isChecked = false
        opt_chk_c!!.isChecked = false
        opt_chk_d!!.isChecked = false

        if(option == "a"){
            opt_chk_a!!.isChecked = true
        }

        if(option == "b"){
            opt_chk_b!!.isChecked = true
        }

        if(option == "c"){
            opt_chk_c!!.isChecked = true
        }

        if(option == "d"){
            opt_chk_d!!.isChecked = true
        }

        if(option == "e"){
            opt_chk_e!!.isChecked = true
        }
    }

    private fun uncheck(){

        opt_chk_a!!.isChecked = false
        opt_chk_b!!.isChecked = false
        opt_chk_c!!.isChecked = false
        opt_chk_d!!.isChecked = false
        opt_chk_e!!.isChecked = false
    }


    val optionA: String
        get() = if (opt_chk_a!!.isChecked) {

            "1"
        } else NO_DATA

    val optionB: String
        get() = if (opt_chk_b!!.isChecked) {

            "2"
        } else NO_DATA

    val optionC: String
        get() = if (opt_chk_c!!.isChecked) {

            "3"
        } else NO_DATA

    val optionD: String
        get() = if (opt_chk_d!!.isChecked) {

            "4"
        } else NO_DATA

    val optionE: String
        get() = if (opt_chk_e!!.isChecked) {

            "5"
        } else NO_DATA


    companion object {
        private val NO_DATA = "0"
    }


    override fun setUserVisibleHint(visible: Boolean) {
        super.setUserVisibleHint(visible)
        if (visible && isResumed) { //Only manually call onResume if fragment is already visible
//Otherwise allow natural fragment lifecycle to call onResume
            onResume()
        }
    }

    override fun onResume() {
        super.onResume()
        if (!userVisibleHint) {
            return
        }
        setLanguage()

        setOption()
    }
}