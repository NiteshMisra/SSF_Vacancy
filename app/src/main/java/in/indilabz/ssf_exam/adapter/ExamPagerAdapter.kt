package `in`.indilabz.ssf_exam.adapter

import `in`.indilabz.ssf_exam.model.exam.ExamQuestion
import `in`.indilabz.ssf_exam.utils.Constants
import `in`.indilabz.ssf_exam.fragment.QuestionFragment
import `in`.indilabz.yorneeds.utils.INDIPreferences
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import java.util.*

/**
 * Created by Dell on 11-Mar-18.
 */


class ExamPagerAdapter(fm: FragmentManager, questions: ArrayList<ExamQuestion>, currentItem: String) : FragmentStatePagerAdapter(fm),
    Constants {

    var modelQuestions = ArrayList<ExamQuestion>()
    var currentItem : String

    init {
        this.modelQuestions = questions
        this.currentItem = currentItem

    }

    override fun getItem(position: Int): Fragment {

        INDIPreferences.question(modelQuestions[position])

        return QuestionFragment().setFragmentPosition(position,
                (position+1).toString())
    }


    override fun getCount(): Int {
        return modelQuestions.size
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        super.destroyItem(container, position, `object`)
    }
}