package dev.seabat.android.serverlessbbs.ui.pager

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.seabat.android.serverlessbbs.data.BbsThread
import dev.seabat.android.serverlessbbs.data.repo.BbsThreadRepository
import kotlinx.coroutines.launch

class BbsThreadListViewModel : ViewModel() {
    private val bbsThreadListRepository: BbsThreadRepository = BbsThreadRepository()
    private val _text = MutableLiveData<String>().apply {
        value = "This is A Bbs thread List Fragment"
    }
    val text get() = _text

    private val _bbsThreadList = MutableLiveData<List<BbsThread>>()
    val bbsThreadList get() = _bbsThreadList

    init {
        fetchAllBbsThreadList()
    }

    private fun addBbsThread(bbsThread: BbsThread) {
        // TODO: DataSource に bbsThread を追加
        val currentList = bbsThreadList.value
        currentList?.let {
            val updatedList = it.toMutableList()
            updatedList.add(0, bbsThread)
            bbsThreadList.postValue(updatedList)
        }?: run {
            bbsThreadList.postValue(listOf(bbsThread))
        }
    }

    private fun replaceBbsThreadList(bbsThreadList: List<BbsThread>) {
        // TODO: DataSource に bbsThread を追加
        this.bbsThreadList.postValue(bbsThreadList)
    }

    private fun fetchAllBbsThreadList() {
        viewModelScope.launch {
            bbsThreadListRepository.fetch().collect {
                this@BbsThreadListViewModel._bbsThreadList.postValue(it)
            }
        }
    }

    fun updateText(text : String) {
        _text.value = text
    }
}