package `in`.indilabz.ssf_exam.adapter

import `in`.indilabz.ssf_exam.fragment.registration.CurriculumFragment
import `in`.indilabz.ssf_exam.fragment.registration.DocumentFragment
import `in`.indilabz.ssf_exam.fragment.registration.PersonalFragment
import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.FragmentStatePagerAdapter

private val TAB_TITLES = arrayOf(
        "Personal Info",
        "Curriculum",
        "Documents"
)

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
class RegistrationAdapter(private val list: ArrayList<Fragment>, fm: FragmentManager)
    : FragmentStatePagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        return  list[position]
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return TAB_TITLES[position]
    }

    override fun getCount(): Int {
        // Show 2 total pages.
        return list.size
    }
}