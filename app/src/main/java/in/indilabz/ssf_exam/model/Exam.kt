package `in`.indilabz.ssf_exam.model

data class Exam(
    val created_at: String,
    val exam_id: Int,
    val id: Int,
    val student_id: Int,
    val transaction: String,
    val updated_at: String,
    val status: String
)