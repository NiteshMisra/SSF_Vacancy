package `in`.indilabz.ssf_exam.fragment.registration

import `in`.indilabz.ssf_exam.INDIMaster
import `in`.indilabz.ssf_exam.R
import `in`.indilabz.ssf_exam.activity.RegistrationActivity
import `in`.indilabz.ssf_exam.databinding.FragmentExamCurriculumBinding
import `in`.indilabz.ssf_exam.databinding.FragmentExamDocumentBinding
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.esafirm.imagepicker.features.ImagePicker
import com.esafirm.imagepicker.features.ReturnMode
import com.esafirm.imagepicker.model.Image

class DocumentFragment : Fragment(){

    private lateinit var parentView: View
    private lateinit var binding: FragmentExamDocumentBinding

    private lateinit var type: String

    private var isProfile = false
    private var isSignature = false
    private var isHighSchool = false
    private var isIntermediate = false
    private var isGraduation = false
    private var isDiploma = false
    private var isAadhaar = false

    var profile : ArrayList<Image> = ArrayList()
    var signature : ArrayList<Image> = ArrayList()
    var highschool : ArrayList<Image> = ArrayList()
    var intermediate : ArrayList<Image> = ArrayList()
    var graduation : ArrayList<Image> = ArrayList()
    var diploma : ArrayList<Image> = ArrayList()
    var aadhaar : ArrayList<Image> = ArrayList()

    fun document(): DocumentFragment{
        return this
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_exam_document, container, false)
        parentView = binding.root

        return parentView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.submit.setOnClickListener {
            (activity!! as RegistrationActivity).submitClick()
        }

        binding.profilePreview.setOnClickListener {

            /*if(binding.clickProfile.text.contains("remove")){
                binding.clickProfile.text = ("Click to add profile image")

                Glide.with(context!!)
                    .load(R.color.md_grey_300)
                    .into(binding.profilePreview)
            }
            else{

                setBool()
                isProfile = true
                openPicker()
            }*/

            setBool()
            isProfile = true
            openPicker()
        }

        binding.signaturePreview.setOnClickListener {

            /*if(binding.clickSignature.text.contains("remove")){
                binding.clickSignature.text = "Click to add signature image"

                Glide.with(context!!)
                    .load(R.color.md_grey_300)
                    .into(binding.signaturePreview)
            }
            else{

                setBool()
                isSignature = true
                openPicker()
            }*/

            setBool()
            isSignature = true
            openPicker()
        }

        binding.highSchoolPreview.setOnClickListener {

            /*if(binding.clickHighSchool.text.contains("remove")){
                binding.clickHighSchool.text = "Click to add HighSchool image"

                Glide.with(context!!)
                    .load(R.color.md_grey_300)
                    .into(binding.highSchoolPreview)
            }
            else{

                setBool()
                isHighSchool = true
                openPicker()
            }*/

            setBool()
            isHighSchool = true
            openPicker()
        }

        binding.intermediatePreview.setOnClickListener {

            /*if(binding.clickIntermediate.text.contains("remove")){
                binding.clickIntermediate.text = "Click to add Intermediate image"

                Glide.with(context!!)
                    .load(R.color.md_grey_300)
                    .into(binding.intermediatePreview)
            }
            else{

                setBool()
                isIntermediate = true
                openPicker()
            }*/

            setBool()
            isIntermediate = true
            openPicker()
        }

        binding.graduationPreview.setOnClickListener {

            /*if(binding.clickGraduation.text.contains("remove")){
                binding.clickGraduation.text = "Click to add Graduation image"

                Glide.with(context!!)
                    .load(R.color.md_grey_300)
                    .into(binding.graduationPreview)
            }
            else{
                setBool()
                isGraduation= true
                openPicker()
            }*/

            setBool()
            isGraduation= true
            openPicker()
        }

        binding.diplomaPreview.setOnClickListener {

            /*if(binding.clickDiploma.text.contains("remove")){
                binding.clickDiploma.text = "Click to add Diploma image"

                Glide.with(context!!)
                    .load(R.color.md_grey_300)
                    .into(binding.diplomaPreview)
            }
            else{
                setBool()
                isDiploma = true
                openPicker()
            }*/

            setBool()
            isDiploma = true
            openPicker()
        }

        binding.aadhaarPreview.setOnClickListener {

            /*if(binding.clickAadhaar.text.contains("remove")){
                binding.clickAadhaar.text = "Click to add Aadhaar image"

                Glide.with(context!!)
                    .load(R.color.md_grey_300)
                    .into(binding.aadhaarPreview)
            }
            else{

                setBool()
                isAadhaar = true
                openPicker()
            }*/

            setBool()
            isAadhaar = true
            openPicker()

        }
    }

    private fun openPicker() {

        ImagePicker.create(this)
            .returnMode(ReturnMode.ALL)
            .folderMode(false)
            .toolbarFolderTitle("Folder")
            .toolbarImageTitle("Tap to select")
            .toolbarArrowColor(Color.WHITE)
            .single()
            .showCamera(true)
            .enableLog(false)
            .start()
    }

    private fun setBool(){
        isProfile = false
        isSignature = false
        isHighSchool = false
        isIntermediate = false
        isGraduation = false
        isDiploma = false
        isAadhaar = false
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (ImagePicker.shouldHandle(requestCode, resultCode, data)) {

            val images =
                ImagePicker.getImages(data)

            if(isProfile){

                if (images.size > 0) {
                    profile.clear()
                    profile.addAll(images)

                    //binding.clickProfile.text = ("Click to remove image")
                    Glide.with(context!!)
                        .applyDefaultRequestOptions(INDIMaster.requestImage()!!)
                        .load(images.get(0).getPath())
                        .into(binding.profilePreview)
                }
            }

            if(isSignature){

                if (images.size > 0) {

                    signature.clear()
                    signature.addAll(images)

                    //binding.clickSignature.text = ("Click to remove image")
                    Glide.with(context!!)
                        .applyDefaultRequestOptions(INDIMaster.requestImage()!!)
                        .load(images.get(0).getPath())
                        .into(binding.signaturePreview)
                }
            }

            if(isHighSchool){

                if (images.size > 0) {

                    highschool.clear()
                    highschool.addAll(images)

                    //binding.clickHighSchool.text = ("Click to remove image")
                    Glide.with(context!!)
                        .applyDefaultRequestOptions(INDIMaster.requestImage()!!)
                        .load(images.get(0).getPath())
                        .into(binding.highSchoolPreview)
                }
            }

            if(isIntermediate){

                if (images.size > 0) {

                    intermediate.clear()
                    intermediate.addAll(images)

                    //binding.clickIntermediate.text = ("Click to remove image")
                    Glide.with(context!!)
                        .applyDefaultRequestOptions(INDIMaster.requestImage()!!)
                        .load(images.get(0).getPath())
                        .into(binding.intermediatePreview)
                }
            }

            if(isGraduation){

                if (images.size > 0) {

                    graduation.clear()
                    graduation.addAll(images)

                    //binding.clickGraduation.text = ("Click to remove image")
                    Glide.with(context!!)
                        .applyDefaultRequestOptions(INDIMaster.requestImage()!!)
                        .load(images.get(0).getPath())
                        .into(binding.graduationPreview)
                }
            }

            if(isDiploma){

                if (images.size > 0) {

                    diploma.clear()
                    diploma.addAll(images)

                    //binding.clickDiploma.text = ("Click to remove image")
                    Glide.with(context!!)
                        .applyDefaultRequestOptions(INDIMaster.requestImage()!!)
                        .load(images.get(0).getPath())
                        .into(binding.diplomaPreview)
                }
            }

            if(isAadhaar){

                if (images.size > 0) {

                    aadhaar.clear()
                    aadhaar.addAll(images)

                    //binding.clickAadhaar.text = ("Click to remove image")
                    Glide.with(context!!)
                        .applyDefaultRequestOptions(INDIMaster.requestImage()!!)
                        .load(images.get(0).getPath())
                        .into(binding.aadhaarPreview)
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }
}