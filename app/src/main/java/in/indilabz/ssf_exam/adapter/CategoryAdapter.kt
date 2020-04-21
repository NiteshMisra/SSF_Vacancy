package `in`.indilabz.ssf_exam.adapter

import `in`.indilabz.ssf_exam.R
import `in`.indilabz.ssf_exam.activity.ViewAll
import `in`.indilabz.ssf_exam.model.CategoryModel
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson

class CategoryAdapter(var context: Context, var list : ArrayList<CategoryModel>): RecyclerView.Adapter<CategoryAdapter.CategoryHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.element_cert_category,parent,false)
        return CategoryHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: CategoryHolder, position: Int) {
        val uploadCurrent: CategoryModel = list[position]
        holder.name.text = uploadCurrent.categoryName

        holder.recyclerView.layoutManager = LinearLayoutManager(context,RecyclerView.HORIZONTAL,false)
        holder.recyclerView.setHasFixedSize(true)

        val cAdapter = CertificateAdapter(context,uploadCurrent.itemList,false)
        holder.recyclerView.adapter = cAdapter
        cAdapter.notifyDataSetChanged()

        holder.viewAll.setOnClickListener {
            if (uploadCurrent.itemList.size > 0){
                val intent = Intent(context, ViewAll::class.java)
                intent.putExtra("list", Gson().toJson(uploadCurrent.itemList))
                context.startActivity(intent)
            }
        }

    }

    class CategoryHolder(view : View) : RecyclerView.ViewHolder(view) {
        val name : TextView = view.findViewById(R.id.categoryName)
        val viewAll : Button = view.findViewById(R.id.certificateViewAll)
        val recyclerView : RecyclerView = view.findViewById(R.id.certificate_rcv)
    }
}