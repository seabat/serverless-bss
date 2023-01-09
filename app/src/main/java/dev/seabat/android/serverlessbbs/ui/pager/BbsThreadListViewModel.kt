package dev.seabat.android.serverlessbbs.ui.pager

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BbsThreadListViewModel : ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "This is A Bbs thread List Fragment"
    }
    val text get() = _text

    fun updateText(text : String) {
        _text.value = text
    }
}