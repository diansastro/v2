package com.widi.movieapp.view.home

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.widi.movieapp.R
import com.widi.movieapp.base.BaseMvpFragment
import com.widi.movieapp.data.response.GenreListResponse
import com.widi.movieapp.data.response.MovieListResponse
import com.widi.movieapp.data.response.PopularMovieResponse
import com.widi.movieapp.model.GenreListData
import com.widi.movieapp.model.MovieData
import com.widi.movieapp.model.PopularMovieData
import com.widi.movieapp.view.adapter.GenreListAdapter
import com.widi.movieapp.view.adapter.MovieListAdapter
import com.widi.movieapp.view.adapter.PopularMovieAdapter
import com.widi.movieapp.view.detail.MovieDetailActivity
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_home.*
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem
import javax.inject.Inject

open class HomeFragment: BaseMvpFragment<HomePresenter>(), HomeContract.View {

    @Inject
    override lateinit var presenter: HomePresenter

    companion object {
        fun newInstance() = HomeFragment()
    }

    private val popularMovieData = arrayListOf<PopularMovieData>()
    private val genreListData = arrayListOf<GenreListData>()
    private val movieData = arrayListOf<MovieData>()
    private val carouselList = mutableListOf<CarouselItem>()
    private val imgPath: String = "https://image.tmdb.org/t/p/original/"
    private lateinit var genreListAdapter: GenreListAdapter
    private lateinit var popularMovieAdapter: PopularMovieAdapter
    private lateinit var movieListAdapter: MovieListAdapter

    override fun initPresenterView() {
        presenter.view = this
    }

    override fun injectView() {
        AndroidSupportInjection.inject(this)
    }

    override fun setup() {
        showLoading()
        presenter.execPopularMovie()
        presenter.execGenreList()
    }

    override fun getLayout(): Int = R.layout.fragment_home

    override fun getPopularMovie(popularMovieResponse: PopularMovieResponse?) {
        val res = popularMovieResponse?.results
        if (res?.size!! > 0) {
            popularMovieData.addAll(res)
        }

        res.forEach {
            carouselList.add(CarouselItem(imageUrl = imgPath.plus(it.backdrop_path), caption = it.title))
        }
        carousel.setData(carouselList)

        popularMovieAdapter = PopularMovieAdapter(popularMovieData)
        popularMovieAdapter.apply {
            setOnItemClickListener { adapter, view, position ->
                activity?.let {
                    val p = popularMovieData[position]
                    MovieDetailActivity.newInstance(it, p.id!!, p.title!!, p.overview!!)
                }
            }
            notifyDataSetChanged()
        }

        rvMovieList.apply {
            adapter = popularMovieAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            isNestedScrollingEnabled = false
        }

        dismissLoading()
    }

    override fun getGenreList(genreListResponse: GenreListResponse?) {
        genreListData.addAll(genreListResponse?.genres!!)

        genreListAdapter = GenreListAdapter(genreListData)
        genreListAdapter.apply {
            setOnItemClickListener { adapter, view, position ->
                movieData.clear()
                showLoading()
                presenter.execMovieByGenre(genreListData[position].id!!)
                activity?.let {
                    val snack = Snackbar.make(view, getString(R.string.current_genre, genreListData[position].name), Snackbar.LENGTH_SHORT)
                    snack.view.setBackgroundColor(context?.getColor(R.color.light_blue)!!)
                    snack.show()
                }
            }
            notifyDataSetChanged()
        }

        rvGenreList.apply {
            adapter = genreListAdapter
            layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
            isNestedScrollingEnabled = false
        }

        dismissLoading()
    }

    override fun getMovieByGenre(movieListResponse: MovieListResponse?) {
        movieData.addAll(movieListResponse?.results!!)

        movieListAdapter = MovieListAdapter(movieData)
        movieListAdapter.apply {
            setOnItemClickListener { adapter, view, position ->
                activity?.let {
                    val p = movieData[position]
                    MovieDetailActivity.newInstance(it, p.id!!, p.title!!, p.overview!!)
                }
            }
            notifyDataSetChanged()
        }

        rvMovieList.apply {
            adapter = movieListAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            isNestedScrollingEnabled = false
        }
        dismissLoading()
    }
}