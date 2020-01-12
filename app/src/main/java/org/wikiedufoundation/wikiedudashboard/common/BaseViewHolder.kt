package org.wikiedufoundation.wikiedudashboard.common

import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.RecyclerView

/**
 * A [RecyclerView] View Holder.
 */
class BaseViewHolder<T>(
    private val binding: ViewDataBinding
) : RecyclerView.ViewHolder(binding.root) {

    /**
     * Use [bind] to bind the object with the respective one in the layout
     * @param obj generic type representing the object
     * it will load the binding only after everything succeed (executePendingBindings)
     ***/
    fun bind(obj: T) {
        binding.setVariable(BR.obj, obj)
        binding.executePendingBindings()
    }

    /**
     * Use [bindAdp] to bind the adapter with the respective one in the layout
     * @param adp generic type representing the adapter
     * it will load the binding only after everything succeed (executePendingBindings)
     ***/
    fun bindAdp(adp: BaseAdapter<T>) {
        binding.setVariable(BR.adp, adp)
        binding.executePendingBindings()
    }
}