package com.widi.movieapp.view.favorite

import androidx.recyclerview.widget.LinearLayoutManager
import com.widi.movieapp.R
import com.widi.movieapp.base.BaseMvpFragment
import com.widi.movieapp.data.response.FavouriteMovieResponse
import com.widi.movieapp.model.FavouriteMovieData
import com.widi.movieapp.view.adapter.FavouriteMovieAdapter
import com.widi.movieapp.view.detail.MovieDetailActivity
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_favorite.*
import javax.inject.Inject

open class FavoriteFragment: BaseMvpFragment<FavoritePresenter>(), FavoriteContract.View {

    @Inject
    override lateinit var presenter: FavoritePresenter

    private val accountId = 11951863
    private val favouriteMovieData = arrayListOf<FavouriteMovieData>()
    private lateinit var movieListAdapter: FavouriteMovieAdapter

    companion object {
        fun newInstance() = FavoriteFragment()
    }

    override fun initPresenterView() {
        presenter.view = this
    }

    override fun injectView() {
        AndroidSupportInjection.inject(this)
    }

    override fun setup() {
        showLoading()
        presenter.execFavouriteMovie(accountId)
    }

    override fun getLayout(): Int = R.layout.fragment_favorite

    override fun getFavouriteMovie(favouriteMovieResponse: FavouriteMovieResponse?) {
        favouriteMovieData.addAll(favouriteMovieResponse?.results!!)

        movieListAdapter = FavouriteMovieAdapter(favouriteMovieData)
        movieListAdapter.apply {
            setOnItemClickListener { adapter, view, position ->
                activity?.let {
                    val p = favouriteMovieData[position]
                    MovieDetailActivity.newInstance(it, p.id!!, p.title!!, p.overview!!)
                }
            }
            notifyDataSetChanged()
        }

        rvFavoriteMovie.apply {
            adapter = movieListAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            isNestedScrollingEnabled = false
        }
        dismissLoading()
    }
}