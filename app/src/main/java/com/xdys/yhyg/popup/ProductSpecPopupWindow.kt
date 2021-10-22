package com.xdys.yhyg.popup

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.xdys.library.extension.loadRoundCornerImage
import com.xdys.yhyg.R
import com.xdys.yhyg.adapte.goods.OnSpecValueClickListener
import com.xdys.yhyg.adapte.goods.ProductSpecAdapter
import com.xdys.yhyg.databinding.PopupProductSpecBinding
import com.xdys.yhyg.entity.goods.GoodsDetailEntity
import razerdp.basepopup.BasePopupWindow

class ProductSpecPopupWindow(
    context: Context, val default: String,
    val onSend: (selectedSpec: String, number: Int, type: Int) -> Unit,
) : BasePopupWindow(context) {

    var specAdapter: ProductSpecAdapter? = null

    private lateinit var binding: PopupProductSpecBinding

    init {
        contentView = createPopupById(R.layout.popup_product_spec)
    }


    private var number: Int = 1

    var type: Int? = 0

    override fun onViewCreated(contentView: View) {
        binding = PopupProductSpecBinding.bind(contentView)
        // 设置属性Adapter
        with(binding.rvSpec) {
            layoutManager = LinearLayoutManager(context)
            specAdapter = ProductSpecAdapter(object : OnSpecValueClickListener {
                override fun onValueClick(specIndex: Int, valueIndex: Int) {

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
            onSend?.invoke("", 5, 1)
        }
        binding.ivClose.setOnClickListener {
            dismiss()
        }
    }

    fun setData(goods: GoodsDetailEntity): ProductSpecPopupWindow {
        specAdapter?.setNewInstance(goods.skus)
        return this
    }

}
