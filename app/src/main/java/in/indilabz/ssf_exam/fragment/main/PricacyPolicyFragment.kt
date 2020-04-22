package `in`.indilabz.ssf_exam.fragment.main

import `in`.indilabz.ssf_exam.R
import `in`.indilabz.ssf_exam.databinding.FragmentTextBinding
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment

class PricacyPolicyFragment : Fragment() {

    private lateinit var binding: FragmentTextBinding
    private lateinit var parentView: View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_text, container, false)

        parentView = binding.root

        return parentView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tac =
            "This Privacy Policy (“Privacy Policy”) describes how we, Hackplanet Technologies Private Limited (referred to as “Hackplanet” or “we” or “us”), collect, use and share information about our users (“you”) through our Platform. Please review our Terms of Use and Privacy Policy (collectively referred to as the “Terms”) that govern the use of the Platform and the Programs. This Privacy Policy shall form a part of the Terms by way of reference. All capitalised terms not defined in this Privacy Policy have the respective meanings set forth in the Terms of Use. This Privacy Policy applies only to the part of the Platform provided by Hackplanet, and we are not responsible for the practices of persons, companies, institutions or websites that Hackplanet does not control, manage or employ.\n\n" +
                    "Please understand that by using our Platform or submitting any Personally Identifiable Information (defined below) to us, you consent and agree that we may collect, use, disclose and retain your information (including but not limited to your Personally Identifiable Information) in accordance with this Privacy Policy and our Terms of Use, and as permitted or required by law. Hackplanet reserves the right to share the information collected hereunder with its own affiliates. In the event of sale or transfer of all or a portion of our business assets, consumer information may be one of the business assets that are transferred as part of the transaction. If you do not agree with these Terms, then please do not provide any Personally Identifiable Information to us. If you refuse or withdraw your consent, or if you choose not to provide us with any required Personally Identifiable Information, we may not be able to provide you with the services that can be offered on our Platform.\n"

        binding.tac.text = tac
    }
}