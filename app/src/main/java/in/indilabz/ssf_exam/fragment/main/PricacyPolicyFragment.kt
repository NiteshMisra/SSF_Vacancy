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
            "Thank you for visiting Sports And Scout Federation SSF, National Headquarters SSF website. Visitors are guaranteed privacy and any information collected is kept private and never shared with other organizations. It is used only by SSF to administer your request, if any. Thank you for visiting Sports And Scout Federation SSF, National Headquarters SSF website. Visitors are guaranteed privacy and any information collected is kept private and never shared with other organizations. It is used only by SSF to administer your request, if any. If you choose to provide us with your personal information, like names or e-mail addresses, when you visit our website, we use it only to fulfill your request for information.\n" +
                    "\n" +
                    "We do not sell or share any personally identifiable information volunteered on this site to any third party (public/private. This site has security measures in place to protect the loss, misuse and alteration of the information under our control. Unauthorized attempts to upload or change information (or otherwise cause damage to this website) are strictly prohibited.\n" +
                    "\n" +
                    "We gather certain information about the User, such as Internet protocol (IP) address, domain name, browser type, operating system, the date and time of the visit and the pages visited. We make no attempt to link these addresses with the identity of individuals visiting our site unless an attempt to damage the site has been detected or unsolicited or disrespectful remarks have been made.\n" +
                    "\n" +
                    "We're using government and other organisations logo/images only for informatively in our website"

        binding.tac.text = tac
    }
}