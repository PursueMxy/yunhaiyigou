package com.xdys.yhyg.popup

import android.content.Context
import android.text.TextUtils
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.xdys.library.extension.loadRoundCornerImage
import com.xdys.yhyg.R
import com.xdys.yhyg.adapte.goods.OnSpecValueClickListener
import com.xdys.yhyg.adapte.goods.ProductSpecAdapter
import com.xdys.yhyg.databinding.PopupProductSpecBinding
import com.xdys.yhyg.entity.goods.GoodsDetailEntity
import com.xdys.yhyg.entity.goods.skuEntity
import com.xdys.yhyg.entity.goods.specs
import razerdp.basepopup.BasePopupWindow

class ProductSpecPopupWindow(
    context: Context, val default: specs,
    val onSend: (selectedSpec: specs, number: Int, type: Int) -> Unit,
) : BasePopupWindow(context) {

    var specAdapter: ProductSpecAdapter? = null
    private val builder = StringBuilder()
    private val formatBuilder = StringBuilder()
    var selectedSpec: specs = default
    var specAttrList: MutableList<skuEntity> = mutableListOf()
    var specList: MutableList<specs> = mutableListOf()
    private var number: Int = 1

    var type: Int = 0

    private lateinit var binding: PopupProductSpecBinding

    init {
        contentView = createPopupById(R.layout.popup_product_spec)
    }


    override fun onViewCreated(contentView: View) {
        binding = PopupProductSpecBinding.bind(contentView)
        // 设置属性Adapter
        with(binding.rvSpec) {
            layoutManager = LinearLayoutManager(context)
            specAdapter = ProductSpecAdapter(object : OnSpecValueClickListener {
                override fun onValueClick(specIndex: Int, valueIndex: Int) {
                    val valueList = specAttrList[specIndex].specs
                    for (value in valueList.indices) {
                        valueList[value].selected = valueIndex == value
                    }
                    selectedSpec = chooseSelectedSpec()
                }
            })
            adapter = specAdapter
        }

        binding.ivGoods?.loadRoundCornerImage(R.mipmap.du_kang_jiu)
        binding.tvPrice?.text = "￥599"
        binding.tvProductNumber?.text = "商品编号:20211123558"
        binding.ivSubtract.setOnClickListener {
            if (number > 1) {
                --number
                binding.tvNumber?.text = number.toString()
            }
        }
        binding.ivAdd.setOnClickListener {
            ++number
            binding.tvNumber?.text = number.toString()

        }
        binding.tvConfirm.setOnClickListener {
            dismiss()
            onSend?.invoke(selectedSpec, number, type)
        }
        binding.ivClose.setOnClickListener {
            dismiss()
        }
    }

    fun setData(goods: GoodsDetailEntity, types: Int): ProductSpecPopupWindow {
        type = types
        goods.skus?.let {
            specAttrList = it
        }
        goods.skus?.let {
            if (it.size > 0) {
                specList = it[0].specs
            }
        }
        specAdapter?.setNewInstance(goods.skus)
        specAdapter?.notifyDataSetChanged()
        selectedSpec = chooseSelectedSpec()
        return this
    }

    /**
     * 筛选出已选中的属性集合，若不是集合，则返回默认的属性集合
     */
    private fun chooseSelectedSpec(): specs {
        if (specAttrList.size > 0) {
            builder.setLength(0)
            formatBuilder.setLength(0)
            formatBuilder.append("已选：")
            for (spec in specAttrList) {
                loop@ for (value in spec.specs) {
                    if (value.selected) {
                        builder.append(value.specId + ":").append(value.specId).append(";")
                        formatBuilder.append("“${value.specValueName}”").append(" ")
                        break@loop
                    }
                }
            }
            if (builder.isEmpty()) return default
            val valueId = builder.deleteCharAt(builder.length - 1).toString()
            for (specPrice in specList) {
                return specPrice
            }
        } else {
            var specPrice = specList[0]
            return specPrice
        }
        return default
    }

}
