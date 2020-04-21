package `in`.indilabz.ssf_exam.adapter

import `in`.indilabz.ssf_exam.R
import `in`.indilabz.ssf_exam.model.ItemHome
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.button.MaterialButton

class ShopAdapter(
    var context : Context,
    var data: ArrayList<ItemHome>,
    var click: (Int) -> Unit
) : RecyclerView.Adapter<ShopAdapter.ShopVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopVH {

        val view:View = LayoutInflater.from(parent.context)
                                      .inflate(R.layout.item_shop, parent, false)

        return ShopVH(view)
    }

    override fun getItemCount(): Int {
        return  data.size
    }

    override fun onBindViewHolder(holder: ShopVH, position: Int) {

        val shop = data[position]

        holder.title.text = shop.name

        Glide.with(holder.preview.context)
            .load(shop.image)
            .into(holder.preview)

        holder.bookNow.setOnClickListener{
            click.invoke(position)
        }

        if(shop.bool){
            holder.bookNow.text = "Applied"
        }
        else{
            holder.bookNow.text = "Apply"
        }

        /*if(position == 0 || position == 1){
            holder.bookNow.setBackgroundResource(R.drawable.button_orange)
        }
        else{
            holder.bookNow.setBackgroundResource(R.drawable.button_green)
        }*/
        //holder.bookNow.setBackgroundResource(R.drawable.button_orange)

        holder.bookNow.setTextColor(Color.parseColor("#F57B00"))
        holder.bookNow.strokeColor = ContextCompat.getColorStateList(context,R.color.colorPrimaryDark)

    }

    class ShopVH(itemView: View): RecyclerView.ViewHolder(itemView){

        val parent: CardView = itemView.findViewById(R.id.parent)
        val preview:ImageView = itemView.findViewById(R.id.preview)
        val title:TextView = itemView.findViewById(R.id.title)
        val bookNow: MaterialButton = itemView.findViewById(R.id.bookNow)
    }

}
