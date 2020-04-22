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
            "Unless otherwise expressly agreed in writing by Hackplanet, full payment for participation in a Program is required at the time of registration. In the case of Programs that include a trial window, such trial window shall be made available only after the full payment. During the trial window, upon a request by the Student, and to the satisfaction of Hackplanet, Hackplanet may choose to refund the Program Fees paid, subject to such charges as may be decided from time to time. Once the trial window has closed, there will be no refund allowed for any reason whatsoever.\n\n" +
                    "It is the sole responsibility of the user enrolling into a Program to check the accuracy of, and evaluate the suitability and relevance of, the Program elected. The enrolment into a Program is non-transferable.\n" +
                    "To make payment for any Program or to purchase any services or products offered by Hackplanet through the Platform, you must have internet access and a current valid accepted payment method as indicated during sign-up (\"Payment Method\"). Hackplanet does not store any of your credit card information or such other information restricted by the Reserve Bank of India (RBI) for processing payment and has partnered with payment gateways for the payment towards the services. By using a third-party payment provider, you agree to abide by the terms of such a payment provider. You agree that in case Hackplanet  third-party payment provider stores any such information, Hackplanet will not be responsible for such storage, and it will be solely at your discretion to allow the third party to store such information. Any loss of such information or any loss incurred by you due to the usage of such information will be solely a loss incurred by you, and Hackplanet  is in no way liable for any such losses and is neither responsible to reimburse / make good such losses in any manner whatsoever. You also agree to pay the applicable fees for the payments made through the Platform.\n\n" +
                    "Failure to pay may result in withdrawal of your access to a Program. Depending on where you transact with us, the type of payment method used and where your payment was issued, your transaction with us may be subject to foreign exchange fees or exchange rates. Hackplanet does not support all payment methods, currencies or locations for payment. All applicable taxes are calculated based on the billing information you provide to us at the time of enrolment/purchase.\n" +
                    "You may cancel your enrolment at any time. No refund will be made once the payment (in part or whole) for a Program has been made. We do not provide refunds for the lack of usage or dissatisfaction.\n"
        binding.tac.text = tac
    }

}