package `in`.indilabz.ssf_exam.rest

import `in`.indilabz.yorneeds.utils.JSONParser


object APIHelper {

    fun error(string: String): String {

        val datum =  JSONParser.getObject(string, "error")

        datum?.let {
            return it
        }
        return ""
    }

    fun result(string: String): String{

        val datum =  JSONParser.getJSONObject(string, "result")

        datum?.let {
            return it
        }
        return ""
    }

    fun custom(string: String, key: String): String{

        val datum =  JSONParser.getJSONObject(string, key)

        datum?.let {
            return it
        }
        return ""
    }
}