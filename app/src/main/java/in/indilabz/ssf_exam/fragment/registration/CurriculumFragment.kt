package `in`.indilabz.ssf_exam.fragment.registration

import `in`.indilabz.ssf_exam.INDIMaster
import `in`.indilabz.ssf_exam.R
import `in`.indilabz.ssf_exam.activity.RegistrationActivity
import `in`.indilabz.ssf_exam.databinding.DialogRegistrationBinding
import `in`.indilabz.ssf_exam.databinding.FragmentExamCurriculumBinding
import `in`.indilabz.ssf_exam.model.Curriculum
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_exam_curriculum.*

class CurriculumFragment : Fragment(){

    private lateinit var parentView: View
    private lateinit var binding: FragmentExamCurriculumBinding

    private lateinit var highSchool: Curriculum
    private lateinit var intermediate: Curriculum
    private lateinit var graduation: Curriculum
    private lateinit var otherDiploma: Curriculum

    fun curriculum(): CurriculumFragment{
        return this
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_exam_curriculum, container, false)
        parentView = binding.root

        binding.submit.setOnClickListener {
            (activity!! as RegistrationActivity).submitClick()
        }

        binding.highSchoolLayout.visibility = View.GONE
        binding.highSchoolArrow.setImageResource(R.drawable.ic_arrow_drop_down)

        binding.highSchool.setOnClickListener {
            if (binding.highSchoolLayout.visibility == View.VISIBLE){
                binding.highSchoolLayout.visibility = View.GONE
                binding.highSchoolArrow.setImageResource(R.drawable.ic_arrow_drop_down)
            }else{
                binding.highSchoolLayout.visibility = View.VISIBLE
                binding.highSchoolArrow.setImageResource(R.drawable.ic_arrow_drop_up)
                binding.intermediateLayout.visibility = View.GONE
                binding.interMediateArrow.setImageResource(R.drawable.ic_arrow_drop_down)
                binding.graduationLayout.visibility = View.GONE
                binding.graduationArrow.setImageResource(R.drawable.ic_arrow_drop_down)
                binding.diplomaLayout.visibility = View.GONE
                binding.diplomaArrow.setImageResource(R.drawable.ic_arrow_drop_down)
            }
        }

        binding.intermediateLayout.visibility = View.GONE
        binding.interMediateArrow.setImageResource(R.drawable.ic_arrow_drop_down)

        binding.interMediate.setOnClickListener {
            if (binding.intermediateLayout.visibility == View.VISIBLE){
                binding.intermediateLayout.visibility = View.GONE
                binding.interMediateArrow.setImageResource(R.drawable.ic_arrow_drop_down)
            }else{
                binding.highSchoolLayout.visibility = View.GONE
                binding.highSchoolArrow.setImageResource(R.drawable.ic_arrow_drop_down)
                binding.intermediateLayout.visibility = View.VISIBLE
                binding.interMediateArrow.setImageResource(R.drawable.ic_arrow_drop_up)
                binding.graduationLayout.visibility = View.GONE
                binding.graduationArrow.setImageResource(R.drawable.ic_arrow_drop_down)
                binding.diplomaLayout.visibility = View.GONE
                binding.diplomaArrow.setImageResource(R.drawable.ic_arrow_drop_down)
            }
        }

        binding.graduationLayout.visibility = View.GONE
        binding.graduationArrow.setImageResource(R.drawable.ic_arrow_drop_down)

        binding.graduation.setOnClickListener {
            if (binding.graduationLayout.visibility == View.VISIBLE){
                binding.graduationLayout.visibility = View.GONE
                binding.graduationArrow.setImageResource(R.drawable.ic_arrow_drop_down)
            }else{
                binding.highSchoolLayout.visibility = View.GONE
                binding.highSchoolArrow.setImageResource(R.drawable.ic_arrow_drop_down)
                binding.intermediateLayout.visibility = View.GONE
                binding.interMediateArrow.setImageResource(R.drawable.ic_arrow_drop_down)
                binding.graduationLayout.visibility = View.VISIBLE
                binding.graduationArrow.setImageResource(R.drawable.ic_arrow_drop_up)
                binding.diplomaLayout.visibility = View.GONE
                binding.diplomaArrow.setImageResource(R.drawable.ic_arrow_drop_down)
            }
        }

        binding.diplomaLayout.visibility = View.GONE
        binding.diplomaArrow.setImageResource(R.drawable.ic_arrow_drop_down)

        binding.diploma.setOnClickListener {
            if (binding.diplomaLayout.visibility == View.VISIBLE){
                binding.diplomaLayout.visibility = View.GONE
                binding.diplomaArrow.setImageResource(R.drawable.ic_arrow_drop_down)
            }else{
                binding.highSchoolLayout.visibility = View.GONE
                binding.highSchoolArrow.setImageResource(R.drawable.ic_arrow_drop_down)
                binding.intermediateLayout.visibility = View.GONE
                binding.interMediateArrow.setImageResource(R.drawable.ic_arrow_drop_down)
                binding.graduationLayout.visibility = View.GONE
                binding.graduationArrow.setImageResource(R.drawable.ic_arrow_drop_down)
                binding.diplomaLayout.visibility = View.VISIBLE
                binding.diplomaArrow.setImageResource(R.drawable.ic_arrow_drop_up)
            }
        }

        return parentView
    }

    fun highSchoolCurriculum(): String{

        highSchool = Curriculum(binding.hsNOU.text.toString().trim(),
            binding.hsDOP.text.toString().trim(),
            binding.hsA.text.toString().trim(),
            binding.hsRN.text.toString().trim(),
            binding.hsTM.text.toString().trim(),
            binding.hsOM.text.toString().trim())

        return INDIMaster.gson.toJson(highSchool)
    }

    fun interCurriculum(): String{

        intermediate= Curriculum(binding.iNOU.text.toString().trim(),
            binding.iDOP.text.toString().trim(),
            binding.iA.text.toString().trim(),
            binding.iRN.text.toString().trim(),
            binding.iTM.text.toString().trim(),
            binding.iOM.text.toString().trim())

        return INDIMaster.gson.toJson(intermediate)
    }

    fun gradCurriculum(): String{

        graduation = Curriculum(binding.graNOU.text.toString().trim(),
            binding.graDOP.text.toString().trim(),
            binding.graA.text.toString().trim(),
            binding.graRN.text.toString().trim(),
            binding.graTM.text.toString().trim(),
            binding.graOM.text.toString().trim())


        return INDIMaster.gson.toJson(graduation)
    }

    fun dipCurriculum(): String{

        otherDiploma = Curriculum(binding.dipNOU.text.toString().trim(),
            binding.dipDOP.text.toString().trim(),
            binding.dipA.text.toString().trim(),
            binding.dipRN.text.toString().trim(),
            binding.dipTM.text.toString().trim(),
            binding.dipOM.text.toString().trim())

        return INDIMaster.gson.toJson(otherDiploma)
    }
}