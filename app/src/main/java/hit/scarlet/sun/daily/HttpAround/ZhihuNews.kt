package hit.scarlet.sun.daily.HttpAround

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Project ZhihuDaily.
 * Created by 旭 on 2017/5/18.
 */

interface ZhihuNews {

    //get latest news
    @get:GET("news/latest")
    val latest: Observable<String>

    //get news before date
    @GET("news/before/{date}")
    fun getBefore(@Path("date") currentDate: String): Observable<String>

    //get news before date
    @GET("theme/{id}")
    fun getTheme(@Path("id") themeId: Int): Observable<String>
}