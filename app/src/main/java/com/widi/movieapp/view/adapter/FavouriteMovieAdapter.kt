package com.widi.movieapp.view.adapter

import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.widi.movieapp.R
import com.widi.movieapp.model.FavouriteMovieData
import kotlinx.android.synthetic.main.item_movie_by_genre.view.*

class FavouriteMovieAdapter(var list: List<FavouriteMovieData>): BaseQuickAdapter<FavouriteMovieData, BaseViewHolder>(R.layout.item_movie_by_genre, list) {
    override fun convert(helper: BaseViewHolder?, item: FavouriteMovieData?) {
        val backdropPath = "https://image.tmdb.org/t/p/original"
        val radius = mContext.resources.getDimensionPixelSize(R.dimen.corner_radius)
        helper?.let { h ->
            item?.let { i ->
                if (!i.backdrop_path.isNullOrBlank()) {
                    Glide
                        .with(mContext)
                        .load(backdropPath.plus(i.backdrop_path))
                        .transform(CenterCrop(), RoundedCorners(radius))
                        .transition(DrawableTransitionOptions.withCrossFade()).into(h.itemView.thumbnail)
                }

                h.itemView.title.text = i.title
                h.itemView.release.text = mContext.getString(R.string.release, i.release_date)
                h.itemView.vote.text = mContext.getString(R.string.vote, i.vote_count.toString())
                h.itemView.rating.text = mContext.getString(R.string.rating, i.vote_average)
            }
        }
    }
}