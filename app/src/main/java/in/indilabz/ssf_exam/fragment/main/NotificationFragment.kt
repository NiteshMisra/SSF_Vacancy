package `in`.indilabz.ssf_exam.fragment.main

import `in`.indilabz.ssf_exam.R
import `in`.indilabz.ssf_exam.databinding.FragmentNotificationBinding
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment

class NotificationFragment : Fragment(){

    private lateinit var binding: FragmentNotificationBinding
    private lateinit var parentView: View


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_notification, container, false)
        parentView = binding.root

        return parentView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding. pdf.fromAsset("noti.pdf")
            .enableAnnotationRendering(true)
            .spacing(10) // in dp
            .load()
    }
}