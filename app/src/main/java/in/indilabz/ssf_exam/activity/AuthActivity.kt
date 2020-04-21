package `in`.indilabz.ssf_exam.activity

import `in`.indilabz.ssf_exam.R
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController

class AuthActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        navController = findNavController(R.id.nav_host_fragment)
    }

    fun change(destination: Int){
        navController.navigate(destination)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return super.onSupportNavigateUp()
    }
}
