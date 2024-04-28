package today.pathos.android.portfolio.data.datasource.remote

import today.pathos.android.portfolio.data.datasource.remote.dto.res.ResCharacter
import today.pathos.android.portfolio.data.datasource.remote.dto.res.ResItem
import today.pathos.android.portfolio.data.datasource.remote.dto.res.ResRows
import today.pathos.android.portfolio.data.datasource.remote.dto.res.ResServer
import today.pathos.android.portfolio.data.datasource.remote.service.DNFService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkDataSource @Inject constructor(
    private val apiService: DNFService,
) {
    suspend fun getServerList(): ResRows<ResServer> = apiService.getServerList()

    suspend fun getCharacterFame(
        serverId: String = "all",
        limit: Int = 200,
    ): ResRows<ResCharacter> = apiService.getCharacterFame(serverId, limit)

    suspend fun searchCharacter(
        serverId: String,
        characterName: String,
        limit: Int,
    ): ResRows<ResCharacter> = apiService.searchCharacter(serverId, characterName, limit)

    suspend fun getCharacterInfo(
        serverId: String,
        characterId: String,
    ): ResCharacter = apiService.getCharacterInfo(serverId, characterId)

    suspend fun getCharacterEquipment(
        serverId: String,
        characterId: String,
    ): ResCharacter = apiService.getCharacterEquipment(serverId, characterId)

    suspend fun getCharacterAvatar(
        serverId: String,
        characterId: String,
    ): ResCharacter = apiService.getCharacterAvatar(serverId, characterId)

    suspend fun getItemInfo(
        itemId: String,
    ): ResItem = apiService.getItemInfo(itemId)
}
