package com.xdys.yhyg.adapte.address

import com.xdys.library.widget.wheel.adapter.WheelAdapter
import com.xdys.library.widget.wheel.interf.IPickerViewData

class RegionAdapter(
    private var list: List<IPickerViewData>
) : WheelAdapter<IPickerViewData> {
    override fun getItemsCount(): Int {
        return list.size
    }

    override fun getItem(index: Int): IPickerViewData {
        return list[index]
    }

    override fun indexOf(o: IPickerViewData?): Int {
        return list.indexOf(o)
    }

    fun updateList(list: List<IPickerViewData>) {
        this.list = list
    }
}