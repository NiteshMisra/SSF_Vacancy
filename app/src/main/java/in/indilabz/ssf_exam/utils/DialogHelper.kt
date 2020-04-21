package `in`.indilabz.ssf_exam.utils

import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class DialogHelper {

    public fun infoDialog(activity: AppCompatActivity, string: String){

        val dialogBuilder = AlertDialog.Builder(activity)

        dialogBuilder.setTitle("Info")
        dialogBuilder.setMessage(string)
        dialogBuilder.setPositiveButton("OK") { dialog, whichButton ->
            dialog.dismiss()
        }
        val b = dialogBuilder.create()
        b.show()
    }

    public fun infoDialog(activity: AppCompatActivity, string: String,  dialogListener: (Boolean) -> Unit?){

        val dialogBuilder = AlertDialog.Builder(activity)

        dialogBuilder.setTitle("Info")
        dialogBuilder.setMessage(string)
        dialogBuilder.setPositiveButton("OK") { dialog, whichButton ->
            dialogListener.invoke(true)
        }
        val b = dialogBuilder.create()
        b.show()
    }
}