package today.pathos.android.portfolio.data.repository

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import today.pathos.android.portfolio.core.di.IoDispatcher
import today.pathos.android.portfolio.data.datasource.remote.NetworkDataSource
import today.pathos.android.portfolio.data.datasource.remote.dto.res.ResCharacter
import today.pathos.android.portfolio.domain.repository.FameRepository
import today.pathos.android.portfolio.entity.Character
import javax.inject.Inject

class NetworkFameRepository @Inject constructor(
    private val dataSource: NetworkDataSource,
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
) : FameRepository {
    override suspend fun getTop5Fame(): List<Character> = withContext(dispatcher) {
        dataSource.getCharacterFame(limit = 5).rows.toEntity()
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
    fame = fame,
    adventureName = adventureName,
    guildId = guildId,
    guildName = guildName,
)

private fun List<ResCharacter>.toEntity() = map { it.toEntity() }
