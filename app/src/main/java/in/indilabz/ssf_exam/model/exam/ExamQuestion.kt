package `in`.indilabz.ssf_exam.model.exam

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



/**
 * Created by Dell on 11-Mar-18.
 */

class ExamQuestion {

    @SerializedName("id")
    @Expose
    val id: Int? = null
    @SerializedName("subject_id")
    @Expose
    val subjectId: Int? = null
    @SerializedName("passage_en")
    @Expose
    val passageEn: String? = null
    @SerializedName("passage_hi")
    @Expose
    val passageHi: String? = null
    @SerializedName("question_en")
    @Expose
    val questionEn: String? = null
    @SerializedName("question_hi")
    @Expose
    val questionHi: String? = null
    @SerializedName("option1")
    @Expose
    val option1: String? = null
    @SerializedName("option2")
    @Expose
    val option2: String? = null
    @SerializedName("option3")
    @Expose
    val option3: String? = null
    @SerializedName("option4")
    @Expose
    val option4: String? = null
    @SerializedName("option5")
    @Expose
    val option5: String? = null
    @SerializedName("option_a")
    @Expose
    val optionA: String? = null
    @SerializedName("option_b")
    @Expose
    val optionB: String? = null
    @SerializedName("option_c")
    @Expose
    val optionC: String? = null
    @SerializedName("option_d")
    @Expose
    val optionD: String? = null
    @SerializedName("option_e")
    @Expose
    val optionE: String? = null
    @SerializedName("correct_answer")
    @Expose
    val correctAnswer: String? = null
    @SerializedName("weightage")
    @Expose
    val weightage: Int? = null
    @SerializedName("negative_marks")
    @Expose
    val negativeMarks: Int? = null
    @SerializedName("solution_en")
    @Expose
    val solutionEn: String? = null
    @SerializedName("solution_hi")
    @Expose
    val solutionHi: String? = null
    @SerializedName("status")
    @Expose
    val status: String? = null
    @SerializedName("ip")
    @Expose
    val ip: String? = null
    @SerializedName("date")
    @Expose
    val date: String? = null
    @SerializedName("selected")
    @Expose
    val selected: String? = null
}
