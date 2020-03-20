package com.jess.myrealtrip.common.extension

import android.view.Gravity
import androidx.databinding.BindingAdapter
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.beloo.widget.chipslayoutmanager.ChipsLayoutManager
import com.jess.myrealtrip.R
import com.jess.myrealtrip.common.base.BaseRecyclerViewAdapter
import com.jess.myrealtrip.common.util.tryCatch
import com.jess.myrealtrip.databinding.TagViewBinding

/**
 * RecyclerView Adapter
 *
 * @param items
 * @param isClear
 */
@BindingAdapter(value = ["items", "isClear"], requireAll = false)
fun RecyclerView.addItems(
    items: List<Any>?,
    isClear: Boolean = true
) {
    tryCatch {
//        this.post {
            (this.adapter as? BaseRecyclerViewAdapter<Any, ViewDataBinding>)?.run {
                if (isClear) {
                    this.clear()
                }

                if (!items.isNullOrEmpty()) {
                    this.addItems(items)
                }
            }
//        }
    }
}

/**
 * RecyclerView Adapter for Tag
 *
 * @param items
 */
@BindingAdapter("tags")
fun RecyclerView.addTags(
    items: List<String>?
) {
    tryCatch {
//        this.post {
            layoutManager = ChipsLayoutManager.newBuilder(context)
                .setChildGravity(Gravity.LEFT)
                .setScrollingEnabled(false)
                .setOrientation(ChipsLayoutManager.HORIZONTAL)
                .setRowStrategy(ChipsLayoutManager.STRATEGY_DEFAULT)
                .withLastRow(true)
                .build()

            adapter = object :
                BaseRecyclerViewAdapter<String, TagViewBinding>(R.layout.tag_view) {
            }.apply {
//                clear()
                addItems(items)
            }
//        }

    }
}