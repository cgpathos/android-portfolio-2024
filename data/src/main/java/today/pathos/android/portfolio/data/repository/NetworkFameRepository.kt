package today.pathos.android.portfolio.data.repository

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import today.pathos.android.portfolio.data.datasource.remote.NetworkDataSource
import today.pathos.android.portfolio.data.datasource.remote.dto.res.ResCharacter
import today.pathos.android.portfolio.common.di.IoDispatcher
import today.pathos.android.portfolio.domain.repository.FameRepository
import today.pathos.android.portfolio.entity.Character
import javax.inject.Inject

class NetworkFameRepository @Inject constructor(
    private val dataSource: NetworkDataSource,
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
) : FameRepository {
    override fun getFameCharacterListFlow(): Flow<List<Character>> {
        TODO("Not yet implemented")
    }

    override suspend fun getFameCharacterList(): List<Character> = withContext(dispatcher) {
        dataSource.getCharacterFame().rows.toEntity()
    }
}

private fun ResCharacter.toEntity() = Character(
    serverId = checkNotNull(serverId),
    characterId = characterId,
    characterName = characterName,
    level = level,
    jobId = jobId,
    jobGrowId = jobGrowId,
    jobName = jobName,
    jobGrowName = jobGrowName,
    fame = fame ?: 0,
    adventureName = adventureName,
    guildId = guildId,
    guildName = guildName,
)

private fun List<ResCharacter>.toEntity() = map { it.toEntity() }
