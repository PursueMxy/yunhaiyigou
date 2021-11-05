package com.xdys.yhyg.adapte.classify

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.xdys.yhyg.entity.classify.CateEntity
import com.xdys.yhyg.ui.classify.CateChildFragment

class CateRightAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    var list = mutableListOf<CateEntity>()
        set(value) {
            field.clear()
            field.addAll(value)
            notifyDataSetChanged()
        }

    override fun getItemCount() = list.size

    override fun createFragment(position: Int): Fragment {
        return CateChildFragment.brandInstance()
    }
}