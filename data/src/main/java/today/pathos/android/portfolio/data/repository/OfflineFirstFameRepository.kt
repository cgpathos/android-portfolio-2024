package today.pathos.android.portfolio.data.repository

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import today.pathos.android.portfolio.common.di.IoDispatcher
import today.pathos.android.portfolio.data.datasource.local.LocalDataSource
import today.pathos.android.portfolio.data.datasource.local.db.table.FameTbl
import today.pathos.android.portfolio.data.datasource.remote.NetworkDataSource
import today.pathos.android.portfolio.domain.repository.FameRepository
import today.pathos.android.portfolio.entity.Character
import javax.inject.Inject

class OfflineFirstFameRepository @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val networkDataSource: NetworkDataSource,
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
) : FameRepository {
    override fun getFameCharacterListFlow(): Flow<List<Character>> =
        localDataSource.getFameList()
            .onStart {
                if (localDataSource.isFameListEmpty()) {
                    networkDataSource.getCharacterFame()
                        .rows
                        .let { localDataSource.createFameList(it) }
                }
            }
            .map {
                it.toEntity()
            }.flowOn(dispatcher)
}

private fun FameTbl.toEntity() = Character(
    serverId = serverId,
    characterId = characterId,
    characterName = characterName,
    level = level,
    jobId = jobId,
    jobGrowId = jobGrowId,
    jobName = jobName,
    jobGrowName = jobGrowName,
    fame = fame,
)

private fun List<FameTbl>.toEntity() = map { it.toEntity() }
