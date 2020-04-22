package `in`.indilabz.ssf_exam.fragment.main

import `in`.indilabz.ssf_exam.R
import `in`.indilabz.ssf_exam.databinding.FragmentTextBinding
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment

class TACFragment : Fragment(){

    private lateinit var binding : FragmentTextBinding
    private lateinit var parentView : View

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

        val tac = "The website www.hackplanet.in and mobile apps (collectively referred to as the “Platform”) and the information, services and other materials contained therein are provided and operated by Hackplanet Technologies Private Limited (referred to as “we”, “us” or “Hackplanet”). Hackplanet offers curated and specially designed higher education and industry-relevant diploma and certification programs online (“Programs”).\n\n" +
                "Please review our Terms of Use, Privacy Policy and other policies available on the Platform (collectively referred to as the “Terms”) that govern the use of the Platform and Programs. Each Program may have a separate set of terms dealing with refunds, deferrals, payments, etc. governing such Programs, and our corporate clients may have executed separate written agreements with us, which, in the event of a conflict, will supersede these Terms to the extent of the conflicting provisions.\n\n" +
                "These Terms shall apply to Hackplanet hosted mobile apps, WhatsApp groups, Facebook groups, Instagram pages, Facebook pages, email/SMS/phone communications and other social media forums hosted by Hackplanet , which shall be deemed to be part of the ‘Platform’ by reference. You acknowledge that certain parts of the Platform, as mentioned above, are provided by third-party service providers, and you agree to abide by their terms and conditions. Hackplanet shall not be responsible for any disruption of services caused by such third-party service providers.\n\n" +
                "Please note that the use of the Platform and Programs constitutes an unconditional agreement to follow and be bound by the Terms. Although you may \"bookmark\" a particular portion of the Platform and thereby bypass these Terms, your use of the Platform still binds you to these Terms.\n" +
                "We may change these Terms from time to time without prior notice. You should review this page regularly. Your continued use of the Platform and Programs after changes have been made will be taken to indicate that you have read and accepted those changes. You should not use the Platform or Programs if you are not happy with any changes to these Terms.\n" +
                "\n" +
                "Support …\n" +
                "+91-9953953395  , info@hackplanet.in\n"

        binding.tac.movementMethod = LinkMovementMethod.getInstance()

        binding.tac.text = tac
    }
}