package org.wikiedufoundation.wikiedudashboard.common

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

/**
 * An abstract class used as [BaseAdapter] for the [RecyclerView].
 */
abstract class BaseAdapter<T> : RecyclerView.Adapter<BaseViewHolder<T>>() {

    internal var data = emptyList<T>()

    /**
     * Use [setData] to update the data from the adapter
     * @param edited A list of the generic type
     ***/
    fun setData(list: List<T>) {
        data = list
        notifyDataSetChanged()
    }

    /**
     * Use [getItem] to item per position
     * @param position A position's list
     ***/
    fun getItem(position: Int) = data[position]

    override fun getItemCount(): Int = data.size

    /**
     * Use [onCreateViewHolder] to bind the adapter
     * defining a [ViewDataBinding] for the layout
     ***/
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<T> {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ViewDataBinding>(inflater, viewType, parent, false)
        val vh = BaseViewHolder<T>(binding)
        vh.bindAdp(this)

        return vh
    }

    /**
     * Use [onBindViewHolder] to bind the data item from the list
     ***/
    override fun onBindViewHolder(holder: BaseViewHolder<T>, position: Int) {
        val obj = getItem(position)
        holder.bind(obj)
    }

    override fun getItemViewType(position: Int): Int = getLayoutIdForPosition(position)

    /**
     * Use [getLayoutIdForPosition] to receive the layout id
     * @param position layout position
     ***/
    protected abstract fun getLayoutIdForPosition(position: Int): Int
}