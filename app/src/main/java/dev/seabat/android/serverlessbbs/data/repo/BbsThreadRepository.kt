package dev.seabat.android.serverlessbbs.data.repo

import dev.seabat.android.serverlessbbs.data.BbsThread
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class BbsThreadRepository {
    suspend fun fetch(): Flow<List<BbsThread>> {
        return flow {
            val response = this@BbsThreadRepository.loadLocalDataStore()
            emit(response)
        }.catch { e ->
            emit(listOf<BbsThread>())
        }
    }

    private suspend fun loadLocalDataStore(): List<BbsThread> {
        delay(3000) //TODO: 仮の遅延処理
        return listOf(
            BbsThread(true, true, true, "タイトル1", "詳細1", "2022/12/03", "2022/12/22"),
            BbsThread(false, true, true, "タイトル2", "詳細2", "2022/12/04", "2022/12/23"),
            BbsThread(false, false, true, "タイトル3", "詳細3", "2022/12/05", "2022/12/24"),
            BbsThread(false, true, true, "タイトル4", "詳細4", "2022/12/06", "2022/12/25")
        )
    }
}