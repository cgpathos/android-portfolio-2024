package today.pathos.android.portfolio.data.datasource.remote.service

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import today.pathos.android.portfolio.data.datasource.remote.dto.res.ResCharacter
import today.pathos.android.portfolio.data.datasource.remote.dto.res.ResItem
import today.pathos.android.portfolio.data.datasource.remote.dto.res.ResRows
import today.pathos.android.portfolio.data.datasource.remote.dto.res.ResServer

interface DNFService {
    @GET("servers/")
    suspend fun getServerList(): ResRows<ResServer>

    @GET("servers/{serverId}/characters-fame")
    suspend fun getCharacterFame(
        @Path("serverId") serverId: String,
        @Query("limit") limit: Int,
    ): ResRows<ResCharacter>

    @GET("servers/{serverId}/characters/{characterId}")
    suspend fun searchCharacter(
        @Path("serverId") serverId: String,
        @Query("characterName") characterName: String,
        @Query("limit") limit: Int,
        @Query("wordType") wordType: String = "full",
    ): ResRows<ResCharacter>

    @GET("servers/{serverId}/characters/{characterId}")
    suspend fun getCharacterInfo(
        @Path("serverId") serverId: String,
        @Path("characterId") characterId: String,
    ): ResCharacter

    @GET("servers/{serverId}/characters/{characterId}/equip/equipment")
    suspend fun getCharacterEquipment(
        @Path("serverId") serverId: String,
        @Path("characterId") characterId: String,
    ): ResCharacter

    @GET("servers/{serverId}/characters/{characterId}/equip/avatar")
    suspend fun getCharacterAvatar(
        @Path("serverId") serverId: String,
        @Path("characterId") characterId: String,
    ): ResCharacter

    @GET("items/{itemId}")
    suspend fun getItemInfo(
        @Path("itemId") itemId: String,
    ): ResItem
}
