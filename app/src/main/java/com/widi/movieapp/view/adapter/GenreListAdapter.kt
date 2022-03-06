package com.widi.movieapp.view.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.widi.movieapp.R
import com.widi.movieapp.model.GenreListData
import kotlinx.android.synthetic.main.item_genre.view.*

class GenreListAdapter(var list: List<GenreListData>): BaseQuickAdapter<GenreListData, BaseViewHolder>(R.layout.item_genre, list) {
    override fun convert(helper: BaseViewHolder?, item: GenreListData?) {
        helper?.let { h ->
            item?.let { i ->
                h.itemView.tvGenreName.text = i.name?.toLowerCase()?.capitalize()
            }
        }
    }
}