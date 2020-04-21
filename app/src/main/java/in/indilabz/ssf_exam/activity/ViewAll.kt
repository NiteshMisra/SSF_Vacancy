@file:Suppress("DEPRECATION")

package `in`.indilabz.ssf_exam.activity

import `in`.indilabz.ssf_exam.R
import `in`.indilabz.ssf_exam.adapter.CertificateAdapter
import `in`.indilabz.ssf_exam.databinding.ActivityViewAllBinding
import `in`.indilabz.ssf_exam.model.ItemHome
import android.graphics.PorterDuff
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ViewAll : AppCompatActivity() {

    private lateinit var binding : ActivityViewAllBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_view_all)

        setSupportActionBar(binding.toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        val value = intent.getStringExtra("list")
        val type = object : TypeToken<ArrayList<ItemHome>>(){}.type
        val list : ArrayList<ItemHome> = Gson().fromJson(value,type)

        binding.viewAllRcv.layoutManager = GridLayoutManager(this,2)
        binding.viewAllRcv.setHasFixedSize(true)

        val cAdapter = CertificateAdapter(this,list,true)
        binding.viewAllRcv.adapter = cAdapter
        cAdapter.notifyDataSetChanged()

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home){
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
