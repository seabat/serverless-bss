package dev.seabat.android.serverlessbss.ui.pager

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ThreadListViewModel : ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "This is A Thread List Fragment"
    }
    val text get() = _text

    fun updateText(text : String) {
        _text.value = text
    }
}