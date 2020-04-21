package `in`.indilabz.ssf_exam.fragment.main

import `in`.indilabz.ssf_exam.R
import `in`.indilabz.ssf_exam.databinding.FragmentTextBinding
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment

class RefundPolicyFragment : Fragment(){

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
            "We don't have any refund policy. Once, paid we are not liable to return any amount.\n" +
                    "\n" +
                    "Your satisfaction is extremely important to us. A registration of candidate for member fee will not be refund, If, for any reason after process of course registration cancel and after complete the course there is no refundable payment policy. You will receive 10 percent of your registration fee with in five days of registration if you cancel your enrollment before the start of the course. Only 10 percent refund before the start of course.   No refunds will be payment after one week at the time of registration for the selected course. To make a request for a refund, submit a written request to info@ssfederation.com. We'll notify you via e-mail of the status your refund once we've received and processed your request. You can expect a refund in the same form of payment originally used for purchase within 7 to 14 business days. For registrations made online with Event rite, a refund will not include the processing fees, these fees are charged by Event rite. As a reminder, the Event rite fee consists of the payment processing fee and the service fee combined.\n" +
                    "\n" +
                    "SSF reserves the right to cancel a course due to insufficient enrollment. Such decisions will be made at least one week before the scheduled course date. Registrants will be notified via email; SSF will make every effort will be made to avoid cancellations.  In case of a cancellation you will have the choice of registering for the next course date or receiving a refund for the full course fee."
        binding.tac.text = tac
    }

}