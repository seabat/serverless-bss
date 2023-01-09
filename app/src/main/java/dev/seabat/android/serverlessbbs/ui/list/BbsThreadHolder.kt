package dev.seabat.android.serverlessbbs.ui.list

import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import dev.seabat.android.serverlessbbs.data.BbsThread
import dev.seabat.android.serverlessbbs.databinding.CardBbsThreadBinding

class BbsThreadHolder(val binding: CardBbsThreadBinding, val onClick: (BbsThread) -> Unit) : RecyclerView.ViewHolder(binding.root) {
    private var bbsThread: BbsThread? = null
    init {
        binding.cardView.setOnClickListener {
            bbsThread?.let {
                onClick(it)
            }
        }
    }

    fun bind(bbsThread: BbsThread) {
        this.bbsThread = bbsThread
        binding.apply {
            imageUnread.isVisible = bbsThread.image_unread_visible
            imageNew.isVisible = bbsThread.image_new_visible
            imageFollow.isVisible = bbsThread.image_follow_visible
            textTitle.text = bbsThread.text_title
            textDetail.text = bbsThread.text_detail
            textCreateDate.text = bbsThread.text_create_date
            textEditDate.text = bbsThread.text_edit_date
        }
    }
}