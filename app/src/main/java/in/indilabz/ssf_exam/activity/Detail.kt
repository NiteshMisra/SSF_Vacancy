package `in`.indilabz.ssf_exam.activity

import `in`.indilabz.ssf_exam.R
import `in`.indilabz.ssf_exam.databinding.ActivityDetailBinding
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil

class Detail : AppCompatActivity() {

    private lateinit var binding : ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_detail)

        val actionId = intent.getIntExtra("id", 0)
        val type = intent.getStringExtra("type")
        val name = intent.getStringExtra("name")

        binding.detailBack.setOnClickListener {
            onBackPressed()
        }

        binding.enroll.setOnClickListener {
            val intent = Intent(Intent(this, RegistrationActivity::class.java))
            intent.putExtra("type",type)
            intent.putExtra("id", actionId)
            intent.putExtra("name", name)
            startActivity(intent)
        }

    }
}
