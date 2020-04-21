package `in`.indilabz.yorneeds.utils

import `in`.indilabz.ssf_exam.INDIMaster
import `in`.indilabz.ssf_exam.model.User
import `in`.indilabz.ssf_exam.model.exam.ExamQuestion
import `in`.indilabz.ssf_exam.model.exam.ExamSubject
import `in`.indilabz.ssf_exam.utils.Constants
import android.app.Activity
import android.content.SharedPreferences
import com.google.gson.Gson
import java.util.*
import kotlin.collections.ArrayList


class INDIPreferences : Constants {
    companion object {

        private val preferences: SharedPreferences
            get() = INDIMaster.applicationContext()!!.getSharedPreferences(Constants.APP_NAME, Activity.MODE_PRIVATE)

        fun preferenceEditor(): SharedPreferences.Editor {
            return INDIMaster.applicationContext()!!.getSharedPreferences(Constants.APP_NAME, Activity.MODE_PRIVATE).edit()
        }

        private val qpe: SharedPreferences
            get() = INDIMaster.applicationContext()!!.getSharedPreferences("QUESTIONS", Activity.MODE_PRIVATE)

        fun qpe(): SharedPreferences.Editor {
            return INDIMaster.applicationContext()!!.getSharedPreferences("QUESTIONS", Activity.MODE_PRIVATE).edit()
        }

        //// Add following lines to store and retrieve String

        fun session(value: Boolean) {
            val editor = preferences.edit()
            editor.putBoolean("APP_SESSION", value)
            editor.commit()
        }

        fun session(): Boolean {
            val mSharedPreferences = preferences
            return mSharedPreferences.getBoolean("APP_SESSION", false)
        }

        fun otp(value: String) {
            val editor = preferences.edit()
            editor.putString("USER_OTP", value)
            editor.commit()
        }

        fun otp(): String? {
            val mSharedPreferences = preferences
            return mSharedPreferences.getString("USER_OTP", "")
        }

        fun token(): String? {
            val mSharedPreferences = preferences
            return mSharedPreferences.getString("USER_TOKEN", "bd53e7b8-2c5f-11ea-babf-44a84246acd5")
        }

        fun token(value: String) {
            val editor = preferences.edit()
            editor.putString("USER_TOKEN", value)
            editor.commit()
        }

        fun phone(): String? {
            val mSharedPreferences = preferences
            return mSharedPreferences.getString("phone", "")
        }

        fun phone(value: String) {
            val editor = preferences.edit()
            editor.putString("phone", value)
            editor.commit()
        }

        fun backpress(value: Boolean) {
            val editor = preferences.edit()
            editor.putBoolean("BACK_PRESS", value)
            editor.commit()
        }

        fun backpress(): Boolean {
            val mSharedPreferences = preferences
            return mSharedPreferences.getBoolean("BACK_PRESS", false)
        }

        fun questions(questions: java.util.ArrayList<ExamQuestion>) {

            val editor = preferences.edit()
            val gson = Gson()
            val jsonDatum = gson.toJson(questions)
            editor.putString("questions", jsonDatum)
            editor.commit()
        }

        fun questions(): java.util.ArrayList<ExamQuestion>? {

            val settings = preferences
            var modelQues: List<ExamQuestion>

            if (settings.contains("questions")) {
                val jsonDatum = settings.getString("questions", "[]")
                val gson = Gson()
                val favoriteItems = gson.fromJson<Array<ExamQuestion>>(jsonDatum,
                    Array<ExamQuestion>::class.java)

                modelQues = Arrays.asList(*favoriteItems)
                modelQues = ArrayList<ExamQuestion>(modelQues)
            } else
                return null

            return modelQues as java.util.ArrayList<ExamQuestion>
        }

        fun question(option: ExamQuestion) {
            val editor =  preferences.edit()
            editor.putString("questions_to", INDIMaster.gson.toJson(option))
            editor.commit()
        }

        fun question(): ExamQuestion? {
            val settings = preferences

            if (settings.contains("questions_to")) {
                val jsonDatum = settings.getString("questions_to", "{}")
                val gson = Gson()
                val datum = gson.fromJson(jsonDatum,
                    ExamQuestion::class.java)

                return datum
            } else
                return null
        }

        fun subjects(questions: java.util.ArrayList<ExamSubject>) {

            val editor = preferences.edit()
            val gson = Gson()
            val jsonDatum = gson.toJson(questions)
            editor.putString("subjects", jsonDatum)
            editor.commit()
        }

        fun subjects(): java.util.ArrayList<ExamSubject>? {

            val settings = preferences
            var modelQues: List<ExamSubject>

            if (settings.contains("questions")) {
                val jsonDatum = settings.getString("subjects", "[]")
                val gson = Gson()
                val favoriteItems = gson.fromJson<Array<ExamSubject>>(jsonDatum,
                    Array<ExamSubject>::class.java)

                modelQues = Arrays.asList(*favoriteItems)
                modelQues = ArrayList<ExamSubject>(modelQues)
            } else
                return null

            return modelQues as java.util.ArrayList<ExamSubject>
        }

        ////////////////////////////// USER

        fun user(questions: User) {

            val editor = preferences.edit()
            val gson = Gson()
            val jsonDatum = gson.toJson(questions)
            editor.putString("user", jsonDatum)
            editor.commit()
        }

        fun user(): User? {

            val settings = preferences

            if (settings.contains("user")) {
                val jsonDatum = settings.getString("user", "{}")
                val gson = Gson()
                val datum = gson.fromJson(jsonDatum,
                       User::class.java)

               return datum
            } else
                return null

        }


        ////////////////////////////// FRAGMENT NAME
        fun fragmentName(value: String) {
            val editor = preferences.edit()
            editor.putString("FRAGMENT_NAME", value)
            editor.commit()
        }

        fun fragmentName(): String? {
            val mSharedPreferences = preferences
            return mSharedPreferences.getString("FRAGMENT_NAME", "")
        }

        fun image(value: String?) {
            val editor = preferences.edit()
            editor.putString("IMAGE", value)
            editor.commit()
        }

        fun image(): String? {
            val mSharedPreferences = preferences
            return mSharedPreferences.getString("IMAGE", "")
        }
        
        fun email(value: String?) {
            val editor = preferences.edit()
            editor.putString("EMAIL", value)
            editor.commit()
        }

        fun email(): String? {
            val mSharedPreferences = preferences
            return mSharedPreferences.getString("EMAIL", "")
        }


        fun totalTestTime(value: String) {
            val editor =  preferences.edit()
            editor.putString("test_time", value)
            editor.commit()
        }

        fun totalTestTime(): String? {
            val mSharedPreferences =  preferences
            return mSharedPreferences.getString("test_time", "")
        }

        fun exam(value: String) {
            val editor =  preferences.edit()
            editor.putString("exam", value)
            editor.commit()
        }

        fun exam(): String? {
            val mSharedPreferences =  preferences
            return mSharedPreferences.getString("exam", "")
        }

        fun logout(): Boolean{

            return preferences.edit().clear().commit()
        }

        fun questionId(value: String) {
            val editor =  preferences.edit()
            editor.putString("question", value)
            editor.commit()
        }

        fun questionId(): String? {
            val mSharedPreferences =  preferences
            return mSharedPreferences.getString("question", "")
        }

        fun questionAns(qid: String, option: String) {
            val editor =  qpe.edit()
            editor.putString(qid, option)
            editor.commit()
        }

        fun questionAns(qid: String): String? {
            val mSharedPreferences =  qpe
            return mSharedPreferences.getString(qid, "0")
        }

        fun time(value: Int) {
            val editor =  preferences.edit()
            editor.putInt("time", value)
            editor.commit()
        }

        fun time(): Int? {
            val mSharedPreferences =  preferences
            return mSharedPreferences.getInt("time", 0)
        }

        fun language(value: String) {
            val editor =  preferences.edit()
            editor.putString("language", value)
            editor.commit()
        }

        fun language(): String? {
            val mSharedPreferences =  preferences
            return mSharedPreferences.getString("language", "en")
        }
    }
}