package hit.scarlet.sun.daily

import android.content.Intent
import com.squareup.timessquare.CalendarPickerView
import kotlinx.android.synthetic.main.activity_date.*
import java.text.SimpleDateFormat
import java.util.*

class DateActivity(override val layoutId: Int = R.layout.activity_date) : BaseActivity() {
    override fun init() {
        val nextDay = Calendar.getInstance()
        nextDay.add(Calendar.DAY_OF_YEAR, 1)

        calendar_view.init(Date(113, 4, 19), nextDay.time).withSelectedDate(Calendar.getInstance().time)
        calendar_view.setOnDateSelectedListener(object : CalendarPickerView.OnDateSelectedListener {
            override fun onDateSelected(date: Date) {
                setResult(10086, Intent(this@DateActivity, MainActivity::class.java)
                        .putExtra("data", SimpleDateFormat("yyyyMMdd", Locale.US).format(date.time)))
                finish()
            }

            override fun onDateUnselected(date: Date) {}
        })
    }
}