package `in`.indilabz.ssf_exam.model


data class User(
    val applicant_name: String,
    val address: String,
    val created_at: String,
    val dob: String,
    val father_name: String,
    val gender: String,
    val id: Int,
    val mail_id: String,
    val martial_status: String,
    val mobile_number: String,
    val mother_name: String,
    val registration_number: String,
    val high_school: String,
    val intermediate: String,
    val graduation: String,
    val other_diploma: String,
    val updated_at: String,
    val user_meta: UserMeta
)