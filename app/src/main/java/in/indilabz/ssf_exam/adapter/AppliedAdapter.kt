package `in`.indilabz.ssf_exam.adapter

import `in`.indilabz.ssf_exam.R
import `in`.indilabz.ssf_exam.model.ItemHome
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class AppliedAdapter(
    var data: ArrayList<ItemHome>,
    var click: (Int) -> Unit
) : RecyclerView.Adapter<AppliedAdapter.AppliedVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppliedVH {

        val view:View = LayoutInflater.from(parent.context)
                                      .inflate(R.layout.item_shop, parent, false)

        return AppliedVH(view)
    }

    override fun getItemCount(): Int {
        return  data.size
    }

    override fun onBindViewHolder(holder: AppliedVH, position: Int) {

        val shop = data[position]

        holder.title.text = shop.name

        Glide.with(holder.preview.context)
            .load(shop.image)
            .into(holder.preview)

        holder.bookNow.setOnClickListener{
            click.invoke(position)
        }

        holder.bookNow.setBackgroundResource(R.drawable.button_orange)

        holder.bookNow.text = "Applied"
    }

    class AppliedVH(itemView: View): RecyclerView.ViewHolder(itemView){

        val parent: CardView = itemView.findViewById(R.id.parent)
        val preview:ImageView = itemView.findViewById(R.id.preview)
        val title:TextView = itemView.findViewById(R.id.title)
        val bookNow:TextView = itemView.findViewById(R.id.bookNow)
    }

}
