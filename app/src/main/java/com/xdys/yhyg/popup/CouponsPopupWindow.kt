package com.xdys.yhyg.popup

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.xdys.yhyg.R
import com.xdys.yhyg.adapte.mine.CouponsAdapter
import razerdp.basepopup.BasePopupWindow

class CouponsPopupWindow(
    context: Context, private val confirm: () -> Unit,
) : BasePopupWindow(context) {

    override fun onCreateContentView(): View = createPopupById(R.layout.popup_coupons)
    override fun onViewCreated(contentView: View) {

    }

    fun setData():CouponsPopupWindow{
        with(findViewById<RecyclerView>(R.id.rvCanReceive)) {
            adapter = canReceiveAdapter
        }
        canReceiveAdapter.setNewInstance(mutableListOf("", ""))
        return this
    }


    private val canReceiveAdapter by lazy { CouponsAdapter() }
}

