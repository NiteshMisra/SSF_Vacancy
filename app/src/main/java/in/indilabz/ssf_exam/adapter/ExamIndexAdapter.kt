package `in`.indilabz.ssf_exam.adapter


import `in`.indilabz.ssf_exam.R
import `in`.indilabz.ssf_exam.model.exam.ExamNav
import `in`.indilabz.ssf_exam.utils.Constants
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.*


/**
 * Created by Dell on 12-Mar-18.
 */

class ExamIndexAdapter(
    private val integers: ArrayList<ExamNav>,
    private val uniqueAns: HashMap<Int, String>,
    private val mark: ArrayList<Int>,
    private var currentItem: Int,
    private val onExamClick: (pos :Int, id: Int)->Unit?) : RecyclerView.Adapter<ExamIndexAdapter.QuestionIndexViewHolder>(), Constants {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionIndexViewHolder {

        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_question_nav, parent, false)

        return QuestionIndexViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: QuestionIndexViewHolder, position: Int) {

        holder.index.text = (integers[position].index!! + 1).toString() + ""

        holder.parent.setBackgroundResource(R.drawable.question_not_attempted)

        for ((key, value) in uniqueAns.entries) {

            if (key == integers[position].questionId  && value!="0") {

                holder.parent.setBackgroundResource(R.drawable.question_attempted)
            }
        }

        if (mark.contains(integers[position].questionId!!)) {

            holder.parent.setBackgroundResource(R.drawable.question_review)
        }

        Log.d("TAG_REC_ITEM", "${currentItem}  ${position}")

        if (currentItem == position) {

            currentItem = position
            holder.parent.setBackgroundResource(R.drawable.question_current)
        }

        holder.parent.setOnClickListener {
            onExamClick.invoke(position, integers[position].index!!)
        }
    }

    override fun getItemCount(): Int {
        return integers.size
    }

    fun notifyItem(currentItem: Int){

        this.currentItem = currentItem
        notifyDataSetChanged()
    }

    inner class QuestionIndexViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var index: TextView = view.findViewById<View>(R.id.index) as TextView
        var parent: RelativeLayout = view.findViewById<View>(R.id.parent) as RelativeLayout

    }
}
