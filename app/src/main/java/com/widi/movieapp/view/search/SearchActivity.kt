package com.widi.movieapp.view.search

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.res.Configuration
import android.view.View
import android.view.View.OnFocusChangeListener
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.jaeger.library.StatusBarUtil
import com.widi.movieapp.R
import com.widi.movieapp.base.BaseMvpActivity
import com.widi.movieapp.data.response.MovieListResponse
import com.widi.movieapp.extension.isEmpty
import com.widi.movieapp.model.MovieData
import com.widi.movieapp.view.adapter.MovieListAdapter
import com.widi.movieapp.view.detail.MovieDetailActivity
import dagger.android.AndroidInjection
import dagger.android.DispatchingAndroidInjector
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.top_nav_search.*
import javax.inject.Inject


open class SearchActivity: BaseMvpActivity<SearchPresenter>(), SearchContract.View {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    @Inject
    override lateinit var presenter: SearchPresenter

    private var query: String = ""
    private var movieData = arrayListOf<MovieData>()
    private lateinit var movieListAdapter: MovieListAdapter
    private lateinit var dialog: AlertDialog

    override fun initPresenterView() {
        presenter.view = this
    }

    override fun injectView() {
        AndroidInjection.inject(this)
    }

    override fun setup() {
        initView()
        openSoftKeyboard(this)
        initAction()
    }

    override fun getLayout(): Int = R.layout.activity_search

    private fun initView() {
        clSearchResult.visibility = View.GONE
        clSearchResult.isEnabled = false
        if (isDarkTheme(this)) {
            StatusBarUtil.setColor(this, ContextCompat.getColor(this, R.color.transparent), 0)
            StatusBarUtil.setLightMode(this)
        } else {
            StatusBarUtil.setColor(this, ContextCompat.getColor(this, R.color.white_smoke), 0)
            StatusBarUtil.setLightMode(this)
        }
    }

    private fun initAction() {
        tvSubmitSearch.setOnClickListener {
            if (!edtSearch.isEmpty()) {
                query = edtSearch.text.toString()
                presenter.execSearchMovie(query)
                showLoading()
            }
        }
    }

    override fun searchMovie(movieListResponse: MovieListResponse?) {
        movieData.clear()
        clSearchResult.visibility = View.VISIBLE
        clSearchResult.isEnabled = true

        movieData.addAll(movieListResponse?.results!!)

        tvSearchResult.text = getString(R.string.search_result, movieData.size.toString())

        movieListAdapter = MovieListAdapter(movieData)
        movieListAdapter.apply {
            setOnItemClickListener { adapter, view, position ->
                val p = movieData[position]
                MovieDetailActivity.newInstance(this@SearchActivity, p.id!!, p.title!!, p.overview!!)
            }
            notifyDataSetChanged()
        }

        rvMovieListResult.apply {
            adapter = movieListAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            isNestedScrollingEnabled = false
        }
        dismissLoading()
    }

    private fun openSoftKeyboard(context: Context) {
        edtSearch.requestFocus()
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(edtSearch, InputMethodManager.SHOW_IMPLICIT)
    }

    private fun isDarkTheme(activity: Activity): Boolean {
        return activity.resources.configuration.uiMode and
                Configuration.UI_MODE_NIGHT_MASK == Configuration.UI_MODE_NIGHT_YES
    }
}