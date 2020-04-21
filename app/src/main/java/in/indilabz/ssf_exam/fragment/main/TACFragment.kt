package `in`.indilabz.ssf_exam.fragment.main

import `in`.indilabz.ssf_exam.R
import `in`.indilabz.ssf_exam.databinding.FragmentTextBinding
import android.os.Bundle
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

        val tac = "VISITORS TO THIS WEB SITE ARE BOUND BY THE FOLLOWING TERMS AND CONDITIONS (\"TERMS\") SO PLEASE READ THE FOLLOWING TERMS CAREFULLY BEFORE CONTINUING TO USE THIS SITE. IF YOU DO NOT AGREE WITH ANY OF THESE TERMS, PLEASE DO NOT USE THIS SITE.\n" +
                "\n" +
                "This Site is designed, developed and maintained by Sports And Scout Federation SSF, National Headquarters\n" +
                "\n" +
                "Unless otherwise indicated, all of the content or information featured, contained, or displayed on this Site, including, but not limited to, design, text, articles, graphics, public service announcements, illustrations, photographs, images, data, video, moving images, sound, audio, music, software, applications, and the selection and arrangement thereof (“Site Content”) and logos are the proprietary property of and owned and/or controlled by Sports And Scout Federation SSF, National Headquarters, Rohtak, India.\n" +
                "\n" +
                "Though all efforts have been made to ensure the accuracy of the content on this website, the same should not be construed as a statement of law or used for any legal purposes. In case of any ambiguity or doubts, users are advised to verify/check with the SSF or other source(s).\n" +
                "\n" +
                "Under no circumstances will Sports And Scout Federation SSF, National Headquarters or its officials, employees or volunteer leaders associated with SSF will be liable for any expense, loss or damage including, without limitation, indirect or consequential loss or damage, or any expense, loss or damage whatsoever arising from use, or loss of use, of data, arising out of or in connection with the use of this website.\n" +
                "\n" +
                "The information posted on this website could include hypertext links or pointers to information created and maintained by other organization. SSF is providing these links and pointers solely for your information and convenience. When you select a link to an external website, you are leaving SSF website and are subject to the privacy and security policies of the owners/ sponsors of the external website. SSF does not guarantee availability of linked pages at all times.\n" +
                "\n" +
                "Material featured on this website may be reproduced free of charge in any format or media without specific permission. However, the material has to be reproduced accurately and not be used in a derogatory manner or in a misleading context. Wherever such material is being reproduced, the source must be prominently acknowledged. The above permission to reproduce any material featured on this website shall not extend to any material featured on this website which is explicitly identified as being the copyrighted property of a third party. Authorization to reproduce such copyrighted material must be obtained from the copyright holders concerned.\n" +
                "\n" +
                "SSF makes no commitment to update or correct any Information that appears on the Internet or on this web site.\n" +
                "\n" +
                "SSF controls and operates this web site from Rohtak, Haryana and makes no representation that the materials are appropriate or will be available for use in other locations. If you use this web site from outside the India, you are entirely responsible for compliance with all applicable local laws.\n" +
                "\n" +
                "Information that SSF publishes on this website may contain references or cross references about policy or programmes of State Associations of Sports And Scout Federation SSF that are not announced or available at the website of State Associations of Sports And Scout Federation SSF. Such references do not imply that SSF, intends to announce or approve such program or policy of State Associations. Consult your Local/District associations for information regarding program or policy that may be available for you.\n" +
                "\n" +
                "If you are member of Sports And Scout Federation SSF, you may:\n" +
                "• Use and display the materials only on your personal computer only for personal use. SSF grants you a limited, personal, non-exclusive and non- transferable right only for such use.\n" +
                "• Print copies of the information on this site for your personal use and store the files on your computer for personal use.\n" +
                "\n" +
                "You may not:\n" +
                "• Using the Site or the Site Content other than for its intended purpose\n" +
                "• Manipulating or framing any photo, badges or logo of Sports And Scout Federation SSF, the Site Content, or any portion of the Site.\n" +
                "• Copy (whether by printing off onto paper, storing on disk, downloading or in any other way), distribute (including distributing copies), download, display, perform, reproduce, distribute, modify, edit, alter, enhance, broadcast or tamper with in any way or otherwise use any material contained in the web site except as set out under \"You may\". These restrictions apply in relation to all or part of the material on the web site;\n" +
                "• Copy and distribute this information on any other server, or modify or re-use text or graphics on this system or another system. No reproduction of any part of the web site may be sold or distributed for commercial gain nor shall it be modified or incorporated in any other work, publication or web site, whether in hard copy or electronic format, including postings to any other web site.\n" +
                "• Remove any copyright, trade mark or other intellectual property notices contained in the original material from any material copied or printed off from the web site;\n" +
                "• Link to this web site; without express written consent from SSF.\n" +
                "• Restrict or inhibit any other user from using or enjoying this web site, including without limitation by means of \"hacking\" or defacing any portion of this web site;\n" +
                "• Transmit any information or software that contains a virus, worm, time bomb, Trojan horse or other harmful or disruptive component;\n" +
                "• Submit particulars that you do not have the right or authority to submit under any law or any contractual or fiduciary relationship (such as trade secrets and confidential information);\n" +
                "• Post or transmit to or from this web site any unlawful, threatening, libelous, defamatory, obscene, pornographic, offensive or other similar material;\n" +
                "• Use the web site to conduct, or solicit the performance of, any unlawful activity or other activity which infringes the rights of others;\n" +
                "• Interfere with or disrupt the web site or any services provided thereon or thereby, or any servers or networks connected to the web site, or disobey any requirements, procedures, policies or regulations of networks connected to the web site;\n" +
                "• Submit particulars that you do not have the right or authority to submit under any law or any contractual or fiduciary relationship (such as trade secrets and confidential information);\n" +
                "• Post or transmit to or from this web site any unlawful, threatening, libelous, defamatory, obscene, pornographic, offensive or other similar material;\n" +
                "• Use the web site to conduct, or solicit the performance of, any unlawful activity or other activity which infringes the rights of others;\n" +
                "• Interfere with or disrupt the web site or any services provided thereon or thereby, or any servers or networks connected to the web site, or disobey any requirements, procedures, policies or regulations of networks connected to the web site;\n" +
                "• Use any robot, spider, site search/retrieval application, or other manual or automatic device or process to retrieve, index, \"data mine,\" or in any way reproduce or circumvent the navigational structure or presentation of the web site or the Materials provided on this web site; or\n" +
                "• Violate any law, regulation or contract.\n" +
                "• You will not impersonate any person or entity, misrepresent your affiliation with a person or entity, or misrepresent the origin of any content posted on or distributed through the Site.\n" +
                "Sports And Scout Federation SSF does not routinely monitor your postings to the web site but reserves the right to do so. If SSF becomes aware of inappropriate use of the web site or any of its Services, SSF will respond in any way that, in its sole discretion, SSF deems appropriate. You acknowledge that SSF will have the right to report to law enforcement authorities any actions that may be considered illegal, as well as any information it receives of such illegal conduct.\n" +
                "\n" +
                "SSF reserves the right to terminate access to this web site at any time and without notice. Further this limited license terminates automatically, without notice to you, if you breach any of these Terms. Upon termination, you must immediately destroy any downloaded and printed materials.\n" +
                "\n" +
                "SSF may change the format and content of this web site at any time. SSF may suspend the operation of this web site for support or maintenance work, in order to update the content or for any other reason.\n" +
                "\n" +
                "Personal details provided to SSF through this web site will only be used in accordance with our privacy policy. Please read this carefully before going on. By providing your personal details to us you are consenting to its use in accordance with our privacy policy.\n" +
                "\n" +
                "Indemnification\n" +
                "You agree to indemnify, defend and hold SSF, and/or their Employees, Officials, Volunteer Leaders harmless from and against any and all claims, damages, losses, costs (including without limitation reasonable attorneys' fees) or other expenses that arise directly or indirectly out of or from (i) your breach of these Terms and Conditions; (ii) your violation of the undertaking, representation and covenants mentioned above; and/or (iii) your activities in connection with this web site.\n" +
                "\n" +
                "Miscellaneous\n" +
                "In the event any provision of these terms and conditions is determined by a court of competent jurisdiction to be invalid, illegal or otherwise unenforceable such provision shall be deemed to have been deleted from these terms and conditions, while the remaining provisions shall remain in full force and effect. SSF failure to insist upon or enforce strict performance of any provision of these terms and conditions shall not be construed as a waiver of any provision or right.\n" +
                "\n" +
                "Applicable Law and Jurisdiction\n" +
                "These terms and conditions are SSF by and to be interpreted in accordance with laws of SSF, without regard to the choice or conflicts of law provisions of any jurisdiction. You agree, in the event of any dispute arising in relation to these terms and conditions or any dispute arising in relation to the web site whether in contract or tort or otherwise, to submit to the jurisdiction of the courts located at Rohtak, Haryana for the resolution of all such disputes.\n" +
                "\n" +
                "SSF may at any time revise the Terms by updating the then-current version thereof posted on this web site. By using this web site, you agree to be bound by any such revisions and should therefore periodically visit this page of this web site to determine the most current version of the Terms to which you are bound. Certain provisions of the Terms may be superseded by expressly designated legal notices or terms located on particular pages at this web site.\n" +
                "\n" +
                "If you have a question or complaint, please contact us at:\n" +
                "Sports And Scout Federation SSF\n" +
                "National Headquarters\n" +
                "Baniyani, District Rohtak (Haryana) 124411,\n" +
                "\n" +
                "Disclaimer\n" +
                "Sports And Scout Federation SSF has taken all reasonable care in developing the website, and we believe that all information is accurate at the time of publication or last modification. Sports And Scout Federation SSF reserves the right to make changes to this site without notice and will not be liable for any damages arising from the use of this site. Nor is it responsible for the content of any other websites linked from here.\n" +
                "\n" +
                "We're using government and other organisations logo/images only for informatively in our website."

        binding.tac.text = tac
    }
}