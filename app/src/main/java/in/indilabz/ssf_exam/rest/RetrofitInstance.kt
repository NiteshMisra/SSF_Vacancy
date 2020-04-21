package `in`.indilabz.review_application.rest

import `in`.indilabz.ssf_exam.INDIMaster
import `in`.indilabz.ssf_exam.activity.SplashActivity
import android.content.Intent
import android.os.AsyncTask

import java.util.concurrent.TimeUnit

import `in`.indilabz.ssf_exam.utils.Constants
import `in`.indilabz.yorneeds.utils.INDIPreferences
import android.util.Log
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory


class RetrofitInstance : Constants {

    private class RetrofitAPI (private val retrofitListener: (Int, Boolean, String) -> Unit?, calls: Call<String>?) : AsyncTask<String, String, String>() {

        init {
            call = calls
        }

        override fun doInBackground(vararg params: String?): String? {

            call!!.clone().enqueue(object : Callback<String> {

                override fun onResponse(call: Call<String>?, response: Response<String>?) {

                    if (response!!.isSuccessful) {

                        try {

                            Log.d("TAG_RETROFIT_RESULT", response.body()!!)

                            retrofitListener.invoke(response.code(), true, response.body()!!)

                        } catch (e: Exception) {

                            Log.d("TAG_RETROFIT_ERROR", e.toString())

                            retrofitListener.invoke(response.code(), false, "Error while getting data")
                        }

                    } else {

                       try {
                            Log.d("TAG_REAL_ERROR", response.errorBody()!!.string())
                        } catch (e: Exception) {

                            Log.d("TAG_REAL_ERROR_EX", e.message!!)
                        }

                        try {

                            //Toaster.longToast(response.errorBody()!!.string())

                            val apiError = INDIMaster.gson.fromJson(response.errorBody()!!.string(), ModelAPIError::class.java)

                            if (apiError.error == "AUTH_ERROR") {

                                if (INDIPreferences.preferenceEditor().clear().commit()) {

                                    INDIMaster.applicationContext().startActivity(Intent(INDIMaster.applicationContext(), SplashActivity::class.java))
                                    INDIPreferences.session(false)
                                    INDIPreferences.backpress(false)
                                }

                            } else {

                                retrofitListener.invoke(response.code(),
                                        false, apiError.error!!)
                            }
                        } catch (e: Exception) {

                           /// Log.d("TAG_EXCEPTION_ERROR", e.toString())

                            try{
                                retrofitListener.invoke(response.code(),
                                        false, "Error while fetching data!")
                            }
                            catch (e : Exception){
                                e.printStackTrace()
                            }
                        }

                    }

                }

                override fun onFailure(call: Call<String>, t: Throwable) {

                    //Log.d("TAG_RETROFIT_THROW", t.message)
                    try{
                        retrofitListener.invoke(404, false, "Please check your internet connection")
                    }catch (e : Exception){
                        e.printStackTrace()
                    }
                    //Toaster.LongToast("Unable to connect to internet");
                }
            })

            return null
        }


        override fun onPostExecute(result: String?) {

        }

        override fun onPreExecute() {

        }

    }

    companion object {

        private var retrofit: Retrofit? = null
        private var client: OkHttpClient? = null
        private var call: Call<String>? = null

        fun instance(): Retrofit {

            client = OkHttpClient.Builder()
                    .connectTimeout(10, TimeUnit.MINUTES)
                    .readTimeout(10, TimeUnit.MINUTES)
                    .writeTimeout(10, TimeUnit.MINUTES)
                    .addInterceptor { chain ->
                        val newRequest = chain.request().newBuilder()
                                .build()
                        chain.proceed(newRequest)
                    }.build()


            retrofit = Retrofit.Builder()
                    .client(client!!)
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

            return retrofit as Retrofit
        }

        fun session(): Retrofit {

            client = OkHttpClient.Builder()
                    .connectTimeout(5, TimeUnit.MINUTES)
                    .readTimeout(5, TimeUnit.MINUTES)
                    .writeTimeout(5, TimeUnit.MINUTES)
                    .retryOnConnectionFailure(true)
                    .addInterceptor { chain ->
                        val newRequest = chain.request().newBuilder()
                                .addHeader("AUTH-TOKEN", INDIPreferences.token()!!)
                                .build()
                        chain.proceed(newRequest)
                    }.build()


            retrofit = Retrofit.Builder()
                    .client(client!!)
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

            return retrofit as Retrofit
        }

        fun getRetrofit(call: Call<String>?, retrofitListener: (Int, Boolean, String) -> Unit?) {
            RetrofitAPI(retrofitListener, call).execute()

        }
    }
}
