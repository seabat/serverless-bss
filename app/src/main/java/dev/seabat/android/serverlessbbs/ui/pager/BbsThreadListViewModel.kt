package dev.seabat.android.serverlessbbs.ui.pager

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.seabat.android.serverlessbbs.data.BbsThread
import dev.seabat.android.serverlessbbs.data.BbsThreadResult
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

    //プログレスバーの表示/非表示
    private var _isProgressBarVisible = MutableLiveData<Boolean>().also { it.value = false }
    val isProgressBarVisible get() = _isProgressBarVisible

    //トーストの表示/非表示
    private var _toastMessage = MutableLiveData<String>().also { it.value = "" }
    val toastMessage get() = _toastMessage

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
                when(it) {
                    is BbsThreadResult.Loading -> {
                        this@BbsThreadListViewModel._isProgressBarVisible.postValue(it.isLoading)
                    }
                    is BbsThreadResult.Success -> {
                        this@BbsThreadListViewModel._bbsThreadList.postValue(it.data)
                        this@BbsThreadListViewModel._isProgressBarVisible.postValue(false)
                    }
                    is BbsThreadResult.Failure -> {
                        this@BbsThreadListViewModel._toastMessage.postValue(it.errorMessage)
                        this@BbsThreadListViewModel._isProgressBarVisible.postValue(false)
                    }
                }
            }
        }
    }

    fun updateText(text : String) {
        _text.value = text
    }
}