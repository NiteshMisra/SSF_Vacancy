package `in`.indilabz.ssf_exam.adapter

import `in`.indilabz.ssf_exam.R
import `in`.indilabz.ssf_exam.activity.Detail
import `in`.indilabz.ssf_exam.model.ItemHome
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton

class CertificateAdapter(var context: Context,var list : ArrayList<ItemHome>, var isViewAll : Boolean) : RecyclerView.Adapter<CertificateAdapter.CertHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CertHolder {
        return if (isViewAll){
            val view = LayoutInflater.from(parent.context).inflate(R.layout.element_certificate2,parent,false)
            CertHolder(view)
        }else{
            val view = LayoutInflater.from(parent.context).inflate(R.layout.element_certificate,parent,false)
            CertHolder(view)
        }
    }

    override fun getItemCount(): Int {
        return if (isViewAll){
            list.size
        }else{
            10
        }
    }

    override fun onBindViewHolder(holder: CertHolder, position: Int) {
        val uploadCurrent = list[position]
        holder.name.text = uploadCurrent.name
        if (uploadCurrent.bool){
            holder.apply.text = ("Applied")
        }else{
            holder.apply.text = ("Apply")
        }

        holder.apply.setOnClickListener {
            val intent = Intent(Intent(context, Detail::class.java))
            intent.putExtra("type", position.toString())
            intent.putExtra("id", uploadCurrent.id)
            intent.putExtra("name", uploadCurrent.name)
            context.startActivity(intent)
        }
    }


    class CertHolder(view : View) : RecyclerView.ViewHolder(view){

        val name : TextView = view.findViewById(R.id.examName)
        val apply : MaterialButton = view.findViewById(R.id.applyNow)

    }

}