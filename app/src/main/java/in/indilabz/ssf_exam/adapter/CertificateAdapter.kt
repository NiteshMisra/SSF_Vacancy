package `in`.indilabz.ssf_exam.adapter

import `in`.indilabz.ssf_exam.R
import `in`.indilabz.ssf_exam.activity.Detail
import `in`.indilabz.ssf_exam.model.ItemHome
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.button.MaterialButton
import com.google.gson.Gson

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


        holder.image.scaleType = ImageView.ScaleType.FIT_XY
        Glide.with(context).load(uploadCurrent.image).into(holder.image)

        holder.apply.setOnClickListener {
            val intent = Intent(Intent(context, Detail::class.java))
            intent.putExtra("type", position.toString())
            intent.putExtra("id", uploadCurrent.id)
            intent.putExtra("price","1250")
            intent.putExtra("GSON", Gson().toJson(uploadCurrent))
            context.startActivity(intent)
        }
    }


    class CertHolder(view : View) : RecyclerView.ViewHolder(view){

        val name : TextView = view.findViewById(R.id.examName)
        val apply : Button = view.findViewById(R.id.applyNow)
        val image : ImageView = view.findViewById(R.id.image)

    }

}