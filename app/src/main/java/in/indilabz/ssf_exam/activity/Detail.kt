package `in`.indilabz.ssf_exam.activity

import `in`.indilabz.ssf_exam.R
import `in`.indilabz.ssf_exam.databinding.ActivityDetailBinding
import `in`.indilabz.ssf_exam.model.ItemHome
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.google.gson.Gson

class Detail : AppCompatActivity() {

    private lateinit var binding : ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_detail)

        val actionId = intent.getIntExtra("id", 0)
        val type = intent.getStringExtra("type")
        val price = intent.getStringExtra("price")
        val value = intent.getStringExtra("GSON")
        val itemHome = Gson().fromJson(value,ItemHome::class.java)

        binding.detailBack.setOnClickListener {
            onBackPressed()
        }

        if (itemHome.image != R.drawable.logo){
            binding.detailImage.scaleType = ImageView.ScaleType.FIT_XY
            Glide.with(this).load(itemHome.image).into(binding.detailImage)
        }else{
            binding.detailImage.scaleType = ImageView.ScaleType.CENTER_INSIDE
            Glide.with(this).load(itemHome.image).into(binding.detailImage)
            binding.detailImage.setBackgroundResource(R.drawable.login_background)
        }

        binding.certName.text = itemHome.name
        binding.certPrice.text = ("\u20B9 $price")

        binding.enroll.setOnClickListener {
            val intent = Intent(Intent(this, RegistrationActivity::class.java))
            intent.putExtra("type",type)
            intent.putExtra("id", actionId)
            intent.putExtra("name", itemHome.name)
            startActivity(intent)
        }

    }
}
