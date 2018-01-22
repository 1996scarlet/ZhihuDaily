package hit.scarlet.sun.daily.Adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Project AndroidCA.
 * Created by 旭 on 2017/5/27.
 */

abstract class BaseAdapter<T> internal constructor(protected var context: Context) : RecyclerView.Adapter<BaseAdapter<T>.BaseViewHolder>() {

    fun getMyDataAt(position: Int): T = myData[position]

    protected var myData: MutableList<T> = mutableListOf()

//    fun setItemOnclickListener(itemOnclickListener: ItemOnclickListener) {
//        this.itemOnclickListener = itemOnclickListener
//    }

    lateinit var itemOnclickListener: ItemOnclickListener

//    fun setMyData(myData: MutableList<T>) {
//        this.myData = myData
//        notifyDataSetChanged()
//    }

    fun resetMyData(myData: MutableList<T>) {
        this.myData = myData
        notifyDataSetChanged()
    }

    fun updateMyData(myData: List<T>) {
        this.myData.addAll(myData)
        notifyDataSetChanged()
    }

    interface ItemOnclickListener {
        fun onItemClick(v: View, pos: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder =
            BaseViewHolder(LayoutInflater.from(parent.context).inflate(layoutId, parent, false))

    override fun getItemCount(): Int = myData.size

    abstract val layoutId: Int

    inner class BaseViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val mViewMap: SparseArray<View> = SparseArray<View>()

        internal fun getView(id: Int): View {
            var view: View? = mViewMap.get(id)
            if (view == null) {
                view = itemView.findViewById<View>(id)
                mViewMap.put(id, view)
                view!!.setOnClickListener { v -> itemOnclickListener.onItemClick(v, layoutPosition) }
            }
            return view
        }
    }
}
