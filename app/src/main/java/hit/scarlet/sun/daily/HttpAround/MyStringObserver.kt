package hit.scarlet.sun.daily.HttpAround

import android.util.Log

import io.reactivex.Observer
import io.reactivex.disposables.Disposable

/**
 * Project ZhihuDaily.
 * Created by 旭 on 2017/6/12.
 */

abstract class MyStringObserver protected constructor() : Observer<String> {

    override fun onNext(t: String) {
        Log.d(TAG, "onNext: ")
    }

    override fun onSubscribe(d: Disposable) {
        Log.d(TAG, "onSubscribe: ")
    }

    override fun onError(e: Throwable) {
        Log.e(TAG, "onError: ", e)
    }

    override fun onComplete() {
        Log.d(TAG, "onComplete: ")
    }

    companion object {
        private val TAG = "MyStringObserver"
    }
}
