package `in`.indilabz.ssf_exam.activity

import `in`.indilabz.review_application.rest.RetrofitInstance
import `in`.indilabz.ssf_exam.INDIMaster
import `in`.indilabz.ssf_exam.R
import `in`.indilabz.ssf_exam.model.User
import `in`.indilabz.ssf_exam.rest.APIHelper
import `in`.indilabz.ssf_exam.utils.Toaster
import `in`.indilabz.yorneeds.utils.INDIPreferences
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        if(INDIPreferences.session()){

            RetrofitInstance.getRetrofit(
                INDIMaster.api().login(
                    INDIPreferences.phone()!!
                )
                , authResult)
        }
        else {
            run.run()
        }
    }

    private val authResult = { _: Int, bool:Boolean, value:String ->

        if(bool){

            val user = INDIMaster.gson.fromJson(APIHelper.result(value), User::class.java)

            if(user.id>0){
                INDIPreferences.user(user)
                INDIPreferences.session(true)
                startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                finish()
            }
            else{

                INDIPreferences.session(false)
                run.run()
            }
        }
        else{

            run.run()
        }
    }

    private val run = Runnable {

        startActivity(Intent(this@SplashActivity, AuthActivity::class.java))
        finish()

    }
}
