package hit.scarlet.sun.daily.Adapter

import android.content.Context
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import hit.scarlet.sun.daily.R
import hit.scarlet.sun.daily.bean.Question

/**
 * Project HealthFlowers.
 * Created by 旭 on 2017/5/15.
 */

class InfoAdapter(context: Context, override val layoutId: Int = R.layout.recycler_main_info) : BaseAdapter<Question>(context) {

    override fun onBindViewHolder(holder: BaseAdapter<Question>.BaseViewHolder, position: Int) {
        val question = myData[position]

        (holder.getView(R.id.question_title) as TextView).text = question.title
        (holder.getView(R.id.daily_title) as TextView).text = question.id.toString()
        if (question.uri != null)
            Glide.with(context).load(question.uri).into(holder.getView(R.id.thumbnail_image) as ImageView)
        else
            Glide.with(context).load(R.mipmap.ic_launcher_round).into(holder.getView(R.id.thumbnail_image) as ImageView)
    }

}
