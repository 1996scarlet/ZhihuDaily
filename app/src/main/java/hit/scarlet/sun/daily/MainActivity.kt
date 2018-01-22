package hit.scarlet.sun.daily

import android.content.Intent
import android.support.v7.app.AppCompatDelegate
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.jcodecraeer.xrecyclerview.XRecyclerView
import hit.scarlet.sun.daily.Adapter.BaseAdapter
import hit.scarlet.sun.daily.Adapter.InfoAdapter
import hit.scarlet.sun.daily.HttpAround.JsonParse
import hit.scarlet.sun.daily.HttpAround.MyStringObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity(override val layoutId: Int = R.layout.activity_main) : BaseActivity() {
    private lateinit var infoAdapter: InfoAdapter
    override fun init() {
        infoAdapter = InfoAdapter(this)
        setSupportActionBar(main_toolbar)
        initRecyclerView()
        initData()
        initItemClick()
    }

    private fun initItemClick() {
        infoAdapter.itemOnclickListener = object : BaseAdapter.ItemOnclickListener {
            override fun onItemClick(v: View, pos: Int) {
                startActivity(Intent(this@MainActivity, NewViewActivity::class.java)
                        .putExtra("newsId", infoAdapter.getMyDataAt(pos - 1).id.toString()))
                overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_calendar -> startActivityForResult(Intent(this, DateActivity::class.java), 10086)
            R.id.action_search -> {
                if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_NO)
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                else AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

                finish()
                startActivity(intent)
            }
            R.id.action_2 -> themeData(2)
            R.id.action_4 -> themeData(4)
            R.id.action_6 -> themeData(6)
            R.id.action_10 -> themeData(10)
            R.id.action_12 -> themeData(12)
            R.id.action_13 -> themeData(13)
        }
        return true
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when (resultCode) {
            10086 -> {
                infoAdapter.resetMyData(mutableListOf())
                updateData(data!!.getStringExtra("data"))
            }
        }
    }

    private fun initRecyclerView() {

        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.adapter = infoAdapter

        recycler_view.setLoadingListener(object : XRecyclerView.LoadingListener {
            override fun onRefresh() {
                initData()
                recycler_view.refreshComplete()
            }

            override fun onLoadMore() {
                updateData()
                recycler_view.loadMoreComplete()
            }
        })
    }

    private fun initData() {

        zhihuNews.latest
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : MyStringObserver() {
                    override fun onNext(t: String) {
                        super.onNext(t)
                        infoAdapter.resetMyData(JsonParse.QuestionListParse(t))
                    }
                })
    }

    private fun updateData(date: String = JsonParse.responseDate) {

        zhihuNews.getBefore(date)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : MyStringObserver() {
                    override fun onNext(t: String) {
                        super.onNext(t)
                        infoAdapter.updateMyData(JsonParse.QuestionListParse(t))
                    }
                })
    }

    private fun themeData(themeId: Int) {

        zhihuNews.getTheme(themeId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : MyStringObserver() {
                    override fun onNext(t: String) {
                        super.onNext(t)
                        infoAdapter.resetMyData(JsonParse.ThemeListParse(t))
                    }
                })
    }
}