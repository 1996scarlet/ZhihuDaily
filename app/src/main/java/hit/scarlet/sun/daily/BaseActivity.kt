package hit.scarlet.sun.daily

import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v7.app.AppCompatActivity
import com.tbruyelle.rxpermissions2.RxPermissions
import hit.scarlet.sun.daily.HttpAround.ZhihuNews
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

/**
 * Project HealthFlowers.
 * Created by 旭 on 2017/5/16.
 */

abstract class BaseActivity : AppCompatActivity() {

    val BASE_URL = "https://news-at.zhihu.com/api/4/"
    protected var zhihuNews = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(ScalarsConverterFactory.create())
//            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build().create(ZhihuNews::class.java)

    protected lateinit var rxPermissions: RxPermissions
    protected lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId)
        init()
    }

    protected fun initSharedPreferences() {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
    }

    protected abstract val layoutId: Int

    protected abstract fun init()
}
