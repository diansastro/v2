package com.widi.movieapp.view

import android.app.Activity
import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.view.OnApplyWindowInsetsListener
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.jaeger.library.StatusBarUtil
import com.ncapdevi.fragnav.FragNavController
import com.ncapdevi.fragnav.FragNavSwitchController
import com.ncapdevi.fragnav.FragNavTransactionOptions
import com.ncapdevi.fragnav.tabhistory.UniqueRootTabHistoryStrategy
import com.widi.movieapp.R
import com.widi.movieapp.base.BaseFragment
import com.widi.movieapp.base.BaseMvpActivity
import com.widi.movieapp.view.favorite.FavoriteFragment
import com.widi.movieapp.view.home.HomeFragment
import com.widi.movieapp.view.search.SearchActivity
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import kotlinx.android.synthetic.main.activity_movie.*
import kotlinx.android.synthetic.main.top_nav_home.*
import org.jetbrains.anko.intentFor
import java.util.ArrayList
import javax.inject.Inject

open class MovieActivity: BaseMvpActivity<MoviePresenter>(), MovieContract.View, HasAndroidInjector {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    @Inject
    override lateinit var presenter: MoviePresenter

    private lateinit var fragNavController: FragNavController

    override var doubleBackExit: Boolean = true

    override fun initPresenterView() {
        presenter.view = this
    }

    override fun injectView() {
        AndroidInjection.inject(this)
    }

    override fun setup() {
        initView()
        initTab()
        initAction()
    }

    override fun getLayout(): Int = R.layout.activity_movie

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fragNavController.initialize(FragNavController.TAB1, savedInstanceState)
    }

    override fun onBackPressed() {
        if(supportFragmentManager.backStackEntryCount == 0) super.onBackPressed()
        else supportFragmentManager.popBackStack()
    }

    override fun androidInjector(): AndroidInjector<Any> = androidInjector

    private fun initAction() {
        ivHome.setOnClickListener { selectTab(TabList.Home) }
        ivFavorite.setOnClickListener { selectTab(TabList.Favorite) }
        txtSearch.setOnClickListener {
            startActivity(intentFor<SearchActivity>())
        }
    }

    private fun initView() {
        if (isDarkTheme(this)) {
            StatusBarUtil.setColor(this, ContextCompat.getColor(this, R.color.transparent), 0)
            StatusBarUtil.setLightMode(this)
        } else {
            StatusBarUtil.setColor(this, ContextCompat.getColor(this, R.color.white_smoke), 0)
            StatusBarUtil.setLightMode(this)
        }
    }

    private fun initTab(){
        ViewCompat.setOnApplyWindowInsetsListener(mainFrame,
            object : OnApplyWindowInsetsListener {
                override fun onApplyWindowInsets(v: View, insets: WindowInsetsCompat): WindowInsetsCompat {
                    var insets = insets
                    insets = ViewCompat.onApplyWindowInsets(v, insets)

                    if (insets.isConsumed) {
                        return insets
                    }
                    var consumed = false
                    var i = 0
                    val count = mainFrame.childCount
                    while (i < count) {
                        ViewCompat.dispatchApplyWindowInsets(mainFrame.getChildAt(i), insets)
                        if (insets.isConsumed) {
                            consumed = true
                        }
                        i++
                    }
                    return if (consumed) insets.consumeSystemWindowInsets() else insets
                }
            })

        fragNavController = FragNavController(supportFragmentManager, R.id.mainFrame)
        fragNavController.navigationStrategy = UniqueRootTabHistoryStrategy(object: FragNavSwitchController {
            override fun switchTab(index: Int, transactionOptions: FragNavTransactionOptions?) {
                fragNavController.switchTab(index, transactionOptions)
            }
        })
        fragNavController.fragmentHideStrategy = FragNavController.HIDE
        fragNavController.createEager = true
        val fragments = ArrayList<BaseFragment>()
        fragments.add(HomeFragment.newInstance())
        fragments.add(FavoriteFragment.newInstance())
        fragNavController.rootFragments = fragments
        refreshTab()
    }

    private fun refreshTab() {
        when(fragNavController.currentStackIndex) {
            TabList.Home -> {
                ivHome.setImageResource(R.drawable.home_active)
                ivFavorite.setImageResource(R.drawable.fav_inactive)
            }
            TabList.Favorite -> {
                ivHome.setImageResource(R.drawable.home_inactive)
                ivFavorite.setImageResource(R.drawable.fav_active)
            }
        }
    }

    private fun selectTab(tabIndex: Int) {
        if (tabIndex >= 0 && null != fragNavController.rootFragments && tabIndex < fragNavController.rootFragments!!.size) {
            fragNavController.switchTab(tabIndex)
            fragNavController.switchTab(tabIndex)
            refreshTab()
        }
    }

    private fun isDarkTheme(activity: Activity): Boolean {
        return activity.resources.configuration.uiMode and
                Configuration.UI_MODE_NIGHT_MASK == Configuration.UI_MODE_NIGHT_YES
    }
}