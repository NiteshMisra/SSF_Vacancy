package `in`.indilabz.ssf_exam.dialog

import `in`.indilabz.ssf_exam.INDIMaster
import `in`.indilabz.ssf_exam.R
import `in`.indilabz.ssf_exam.activity.ExamActivity
import `in`.indilabz.ssf_exam.databinding.DialogInstructionBinding
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment


class DialogInstruction : DialogFragment() {

    private var binding: DialogInstructionBinding? = null
    var parentView: View? = null

    fun instruction() : DialogInstruction {

        this.setStyle(STYLE_NORMAL, R.style.DialogFragmentTheme)
        return this
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.dialog_instruction, container, false)
        dialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        parentView = binding!!.root

        return parentView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val number: Array<String?> = resources.getStringArray(R.array.instructions)
        val adapter: ArrayAdapter<String> = ArrayAdapter<String>(INDIMaster.applicationContext(), R.layout.item_instruction, number)

        binding!!.list!!.adapter = adapter

        binding!!.toolbar!!.setNavigationOnClickListener{

            this.dismiss()
        }

        binding!!.submit!!.setOnClickListener {

            this.dismissAllowingStateLoss()
            startActivity(Intent(INDIMaster.applicationContext(), ExamActivity::class.java))
        }
    }
}
