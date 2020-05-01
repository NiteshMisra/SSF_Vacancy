package `in`.indilabz.ssf_exam.fragment.main

import `in`.indilabz.ssf_exam.R
import `in`.indilabz.ssf_exam.databinding.FragmentTextBinding
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment

class RefundPolicyFragment : Fragment() {

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
            "It is the sole responsibility of the user enrolling into a Program to check the accuracy of, and evaluate the suitability. \n" +
                    "Unless otherwise expressly agreed in writing by Hackplanet, full payment for participation in a Program is required at the time of registration. \n" +
                    "\n" +
                    "Returns Our policy lasts 7 days. If 7 days have gone by since your purchase, unfortunately we can’t offer you a refund. To be eligible for a return, your item must be unused and in the same condition that you received it. It must also be in the original packaging. Several types of goods are exempt from being returned. Perishable goods such as Training Kit , Certificate , Online Training cannot be returned. Additional non-returnable items: \uF0B7Downloadable software products \uF0B7Online Workshops \uF0B7Online Training To complete your return, we require a receipt or proof of purchase. There are certain situations where only partial refunds are granted: (if applicable) Book with obvious signs of use CD, DVD, VHS tape, software, record that has been opened. Any item not in its original condition, is damaged or missing parts for reasons not due to our error. Any item that is returned more than 7 days after delivery Refunds (if applicable) Once your return is received and inspected, we will send you an email to notify you that we have received your returned item. We will also notify you of the approval or rejection of your refund. If you are approved, then your refund will be processed, and a credit will automatically be applied to your original method of payment, within a certain amount of days. Late or missing refunds (if applicable) If you haven’t received a refund yet, first check your bank account again. Then contact your Bank, it may take some time before your refund is officially posted. Next contact your bank. There is often some processing time before a refund is posted. If you’ve done all of this and you still have not received your refund yet, please contact us at info@hackplanet.in. Sale items (if applicable) Only regular priced items may be refunded, unfortunately sale items cannot be refunded. Exchanges (if applicable) We only replace items if they are damaged.  If you need to exchange it for the same item, send us an email at info@hackplanet.in and send your item to:E-160, 3rd Floor Sector - 63 Noida 201301 (U.P.) . Shipping To return your product, you should mail your product to: E-160, 3rd Floor Sector - 63 Noida 201301 (U.P.) You will be responsible for paying for your own shipping costs for returning your item. Shipping costs are non-refundable. If you receive a refund, the cost of return shipping will be deducted from your refund."
        binding.tac.text = tac
    }

}