package com.widi.movieapp.view.detail

import android.content.Context
import android.widget.Toast
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerSupportFragment
import com.widi.movieapp.R
import com.widi.movieapp.base.BaseMvpActivity
import com.widi.movieapp.body.MarkAsFavouriteBody
import com.widi.movieapp.data.response.MovieTrailerResponse
import com.widi.movieapp.model.MovieTrailerData
import com.widi.movieapp.objects.Params
import com.widi.movieapp.view.MovieActivity
import com.widi.movieapp.view.TabList
import dagger.android.AndroidInjection
import dagger.android.DispatchingAndroidInjector
import kotlinx.android.synthetic.main.activity_movie_detail.*
import org.jetbrains.anko.intentFor
import javax.inject.Inject

open class MovieDetailActivity: BaseMvpActivity<MovieDetailPresenter>(), MovieDetailContract.View, YouTubePlayer.OnInitializedListener {

    companion object {
        fun newInstance(context: Context, movieId: Int, title: String, desc: String) {
            context.startActivity(context.intentFor<MovieDetailActivity>(Params.MOVIE_ID to movieId, Params.MOVIE_TITLE to title, Params.MOVIE_DESC to desc))
        }
    }

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    @Inject
    override lateinit var presenter: MovieDetailPresenter

    private var movieId: Int = 0
    private var title: String = ""
    private var desc: String = ""
    private var accountId: Int = 0
    private val mData = arrayListOf<MovieTrailerData>()
    private val RECOVERY_DIALOG_REQUEST = 1
    private var videoUrl: String? = ""

    override fun initPresenterView() {
        presenter.view = this
    }

    override fun injectView() {
        AndroidInjection.inject(this)
    }

    override fun setup() {
        showLoading()
        initBundle()
        presenter.execMovieTrailer(movieId)
        initAction()
    }

    override fun getLayout(): Int = R .layout.activity_movie_detail

    override fun onBackPressed() {
        if(supportFragmentManager.backStackEntryCount == 0) super.onBackPressed()
        else supportFragmentManager.popBackStack()
    }

    private fun initBundle() {
        intent?.extras?.let {
            movieId = it.getInt(Params.MOVIE_ID)
            title = it.getString(Params.MOVIE_TITLE, "")
            desc = it.getString(Params.MOVIE_DESC, "")
        }
    }

    private fun initView() {
        val youtubeFragment = supportFragmentManager.findFragmentById(R.id.ytPlayer) as YouTubePlayerSupportFragment?
        youtubeFragment?.initialize("AIzaSyC09yy5vASiOlNHfHRRkbgaHSVnmuuq04g", this)

        tvTitle.text = getString(R.string.title, title)
        tvMovieDesc.text = desc
    }

    private fun initAction() {
        tvFavourite.setOnClickListener {
            showLoading()
            accountId = 11951863
            presenter.execPostFavourite(accountId, MarkAsFavouriteBody("movie", movieId, true))
            dismissLoading()
        }
    }

    override fun getMovieTrailer(movieTrailerResponse: MovieTrailerResponse?) {
        if (movieTrailerResponse?.results?.size!! > 0) {
            mData.addAll(movieTrailerResponse.results)
        }

        movieTrailerResponse.results.forEach {
            it.let { videoUrl = it.key }
        }

        initView()
        dismissLoading()
    }

    override fun onSuccess() {
        Toast.makeText(this, "Success add to Favourite", Toast.LENGTH_SHORT).show()
        onBackPressed()
    }

    override fun onInitializationSuccess(provider: YouTubePlayer.Provider, youTubePlayer: YouTubePlayer, wasResored: Boolean) {
        if(!wasResored) {
            println("Video Url: $videoUrl")
            youTubePlayer.cueVideo(videoUrl)
        }
    }

    override fun onInitializationFailure(provider: YouTubePlayer.Provider,youTubeInitializationResult: YouTubeInitializationResult) {
        if (youTubeInitializationResult.isUserRecoverableError) {
            youTubeInitializationResult.getErrorDialog(this, RECOVERY_DIALOG_REQUEST).show()
        } else {
            val errorMessage = String.format("There was an error initializing the YouTubePlayer (%1\$s)", youTubeInitializationResult.toString())
            Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show()
        }
    }
}