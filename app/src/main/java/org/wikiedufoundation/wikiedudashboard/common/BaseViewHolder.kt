package org.wikiedufoundation.wikiedudashboard.common

import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.RecyclerView

class BaseViewHolder(
        private val binding: ViewDataBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(obj: Any) {
        binding.setVariable(BR.obj, obj)
        binding.executePendingBindings()
    }

    fun bindAdp(adp: BaseAdapter) {
        binding.setVariable(BR.adp, adp)
        binding.executePendingBindings()
    }

}