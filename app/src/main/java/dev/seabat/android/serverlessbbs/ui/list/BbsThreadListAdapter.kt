package dev.seabat.android.serverlessbbs.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dev.seabat.android.serverlessbbs.data.BbsThread
import dev.seabat.android.serverlessbbs.databinding.CardBbsThreadBinding

class BbsThreadListAdapter(val onClick: (BbsThread) -> Unit) : RecyclerView.Adapter<BbsThreadHolder>() {
    private var bbsThreadList: MutableList<BbsThread> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BbsThreadHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CardBbsThreadBinding.inflate(inflater, parent, false)
        return BbsThreadHolder(binding, onClick)
    }

    override fun onBindViewHolder(holder: BbsThreadHolder, position: Int) {
        return holder.bind(bbsThreadList.get(position))
    }

    override fun getItemCount(): Int {
        return bbsThreadList.size
    }

    fun submitList(list: MutableList<BbsThread>) {
        this.bbsThreadList = list
        this.notifyDataSetChanged()
    }

    fun add(bbsThread: BbsThread) {
        this.bbsThreadList.add(bbsThread)
        this.notifyItemInserted(this.bbsThreadList.size-1)
    }
}