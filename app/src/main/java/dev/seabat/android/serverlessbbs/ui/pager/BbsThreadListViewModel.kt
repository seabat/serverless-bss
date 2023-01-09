package dev.seabat.android.serverlessbbs.ui.pager

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.seabat.android.serverlessbbs.data.BbsThread

class BbsThreadListViewModel : ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "This is A Bbs thread List Fragment"
    }
    val text get() = _text

    private val _bbsThreadList = MutableLiveData<List<BbsThread>>().apply {
        //TODO: DataSource からデータを取得する
        value = listOf(
            BbsThread(true, true, true, "タイトル1", "詳細1", "2022/12/03", "2022/12/22"),
            BbsThread(false, true, true, "タイトル2", "詳細2", "2022/12/04", "2022/12/23"),
            BbsThread(false, false, true, "タイトル3", "詳細3", "2022/12/05", "2022/12/24"),
            BbsThread(false, true, true, "タイトル4", "詳細4", "2022/12/06", "2022/12/25")
        )
    }
    val bbsThreadList get() = _bbsThreadList


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

    fun updateText(text : String) {
        _text.value = text
    }
}