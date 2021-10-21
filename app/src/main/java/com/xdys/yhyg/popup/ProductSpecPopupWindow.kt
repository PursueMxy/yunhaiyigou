package com.xdys.yhyg.popup

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.xdys.yhyg.R
import com.xdys.library.extension.loadRoundCornerImage
import razerdp.basepopup.BasePopupWindow

class ProductSpecPopupWindow(
    context: Context, val default: String,
    val onSend: (selectedSpec: String, number: Int, type: Int) -> Unit,
) : BasePopupWindow(context) {
    var ivGoods: ImageView? = null
    private var tvNumber: TextView? = null
    private var tvConfirm: TextView? = null
    private var tvPrice: TextView? = null
    private var tvProductNumber: TextView? = null
    private var number: Int = 1

    var type: Int? = 0
    fun onCreateContentView(): View = createPopupById(R.layout.popup_product_spec)

    override fun onViewCreated(contentView: View) {
        // 设置属性Adapter
        with(findViewById<RecyclerView>(R.id.rvSpec)) {
            layoutManager = LinearLayoutManager(context)
        }
        ivGoods = findViewById<ImageView>(R.id.ivGoods)
        tvNumber = findViewById(R.id.tvNumber)
        tvConfirm = findViewById(R.id.tvConfirm)
        tvPrice = findViewById(R.id.tvPrice)
        tvProductNumber = findViewById(R.id.tvProductNumber)
        ivGoods?.loadRoundCornerImage(R.mipmap.du_kang_jiu)
        tvPrice?.text = "￥599"
        tvProductNumber?.text = "商品编号:20211123558"
        findViewById<ImageView>(R.id.ivSubtract).setOnClickListener {
            if (number > 1) {
                --number
                tvNumber?.text = number.toString()
            }
        }
        findViewById<ImageView>(R.id.ivAdd).setOnClickListener {
            ++number
            tvNumber?.text = number.toString()

        }
        findViewById<TextView>(R.id.tvConfirm).setOnClickListener {

        }
    }

}
