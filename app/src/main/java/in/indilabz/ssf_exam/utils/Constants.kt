package `in`.indilabz.ssf_exam.utils

interface Constants {

    companion object {

        const val ROOT_URL = "https://ssfvacancy.com/android/"
        //const val ROOT_URL = "http://192.168.1.111:88/"
        const val IMAGE_URL = "http://ssfvacancy.com/android/data/"
        //private const val APP_URL = "http://192.168.1.111/Online-Exam/android/index.php/v1"
        const val APP_URL = "${ROOT_URL}index.php/v1"

        const val PROFILE_URL = "http://ssfvacancy.com/ssf-web/profile.php?from=app&s_id="
        //String APP_URL = "http://192.168.43.30/Hackplanet-Ads/index.php/v1";
        const val BASE_URL = "$APP_URL/"
        const val APP_NAME = "STUDENT_ADMIN"
        const val INDIAN_RUPEE = "₹"

        const val ACCESS_TOKEN = "648D6D4E7F7BB3924FAAB192BB9364E5"
        const val API_KEY = "621E06A2B74DAEB8A20DFE9587F618D8"

        //// TEST
        //const val MERCHANT = "829001879255224";
        //const val APP_ACCESS_TOKEN = "7DDB1C0ACC7705BAAB8D280507B9593D"
        //const val IS_LIVE = false

        //// LIVE
        const val MERCHANT = "314950505369260"
        const val APP_ACCESS_TOKEN = "7006A3B7CB62F0C6AE37EF224D19583D"
        const val IS_LIVE = true

        const val EXAM_1 = "Yoga Instructor/Self Defense Instructor"
        const val EXAM_2 = "Assistance Yoga Instructor/Assistance Self Defense Instructor"

        var SURL = "https://www.payumoney.com/mobileapp/payumoney/success.php"
        var FURL = "https://www.payumoney.com/mobileapp/payumoney/failure.php"

        var MERCHANT_KEY = "7g846S6M" //"7g846S6M"
        var MERCHANT_ID = "ikhhizA8lR" //"ikhhizA8lR"
        var DEBUG = false
    }
}
