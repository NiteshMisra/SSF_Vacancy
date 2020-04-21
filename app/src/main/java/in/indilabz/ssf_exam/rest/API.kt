package `in`.indilabz.ssf_exam.rest

import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.*

/**
 * Created by mjdem on 06-05-2018.
 */

interface API {

    @FormUrlEncoded
    @POST("home/android/user")
    fun home(
        @Field("id") user: Int
    ): Call<String>

    @FormUrlEncoded
    @POST("auth/user/")
    fun login(
        @Field("mobile_number") contact_number: String
    ): Call<String>

    @Multipart
    @POST("user/update/{id}")
    fun registerWithOutPayment(
        @Path("id") id: Int,
        @Part("applicant_name") applicant_name: String,
        @Part("father_name") father_name: String,
        @Part("mother_name") mother_name: String,
        @Part("dob") dob: String,
        @Part("gender") gender: String,
        @Part("mobile_number") mobile_number: String,
        @Part("mail_id") mail_id: String,
        @Part("address") address: String,
        @Part("martial_status") martial_status: String,
        @Part("type") type: String,
        @Part("action_id") exam_id: Int,
        @Part("high_school") high_school: String,
        @Part("intermediate") intermediate: String,
        @Part("graduation") graduation: String,
        @Part("other_diploma") other_diploma: String,
        @Part user: MultipartBody.Part?,
        @Part signature: MultipartBody.Part?,
        @Part aadhaar_img: MultipartBody.Part?,
        @Part high_school_img: MultipartBody.Part?,
        @Part intermediate_img: MultipartBody.Part?,
        @Part graduation_img: MultipartBody.Part?,
        @Part other_diploma_img: MultipartBody.Part?
    ): Call<String>

    @Multipart
    @POST("user/update/{id}")
    fun registerWithPayment(
        @Path("id") id: Int,
        @Part("applicant_name") applicant_name: String,
        @Part("father_name") father_name: String,
        @Part("mother_name") mother_name: String,
        @Part("dob") dob: String,
        @Part("gender") gender: String,
        @Part("mobile_number") mobile_number: String,
        @Part("mail_id") mail_id: String,
        @Part("address") address: String,
        @Part("martial_status") martial_status: String,
        @Part("type") type: String,
        @Part("action_id") exam_id: Int,
        @Part("transaction") transaction: String,
        @Part("high_school") high_school: String,
        @Part("intermediate") intermediate: String,
        @Part("graduation") graduation: String,
        @Part("other_diploma") other_diploma: String,
        @Part user: MultipartBody.Part?,
        @Part signature: MultipartBody.Part?,
        @Part aadhaar_img: MultipartBody.Part?,
        @Part high_school_img: MultipartBody.Part?,
        @Part intermediate_img: MultipartBody.Part?,
        @Part graduation_img: MultipartBody.Part?,
        @Part other_diploma_img: MultipartBody.Part?
    ): Call<String>

    @FormUrlEncoded
    @POST("otp")
    fun otp(
        @Field("mobile_number") contact_number: String,
        @Field("otp") otp: String
    ): Call<String>


    @GET("exam/get/{id}")
    fun sports(
        @Path("id") id: Int
    ): Call<String>

    @GET("exam/applied/{id}")
    fun applied(
        @Path("id") id: Int
    ): Call<String>

    @GET("question/exam/{subject_id}")
    fun questions(
        @Path("subject_id") offset: String
    ): Call<String>

    @FormUrlEncoded
    @POST("result/create")
    fun saveResult(
        @Field("student_name") student_name: String,
        @Field("student_id") student_id: Int,
        @Field("quiz_id") quiz_id: String,
        @Field("all_results") all_result: String
    ): Call<String>

    @FormUrlEncoded
    @POST("exam/eligibility")
    fun examEligibility(
        @Field("student_id") student_id: String,
        @Field("quiz_id") quiz_id: String
    ): Call<String>

}
