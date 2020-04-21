package `in`.indilabz.ssf_exam.dialog

import `in`.indilabz.ssf_exam.INDIMaster
import `in`.indilabz.ssf_exam.R
import `in`.indilabz.ssf_exam.activity.ExamActivity
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.DialogFragment

class CameraInstruction: DialogFragment(){

    private lateinit var cancel: (Boolean) -> Unit

    fun camera(cancel: (Boolean) -> Unit) : CameraInstruction {

        this.cancel = cancel
        this.setStyle(STYLE_NORMAL, R.style.DialogFragmentTheme)
        return this
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view : View = inflater.inflate(R.layout.dialog_camera, container, false);

        view.findViewById<Button>(R.id.submit).setOnClickListener {

            cancel.invoke(true)
            this.dismiss()

        }

        return  view
    }


    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        cancel.invoke(false)
    }
}

