package today.pathos.android.portfolio.data.datasource.remote

import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import today.pathos.android.portfolio.data.datasource.remote.dto.res.ResError
import today.pathos.android.portfolio.data.datasource.remote.dto.res.ResErrorInfo
import today.pathos.android.portfolio.data.datasource.remote.dto.res.ResServer
import today.pathos.android.portfolio.data.datasource.remote.service.DNFService

class NetworkDataSourceTest {
    @get:Rule
    val mockServerRule = MockServerRule()

    private val json = Json {
        ignoreUnknownKeys = true
        isLenient = true
    }

    private val apiService = Retrofit.Builder()
        .baseUrl(mockServerRule.url("/"))
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .build()
        .create(DNFService::class.java)

    private val dataSource = NetworkDataSource(apiService)

    @Test
    fun errorNotFoundApikey() = runTest {
        mockServerRule.enqueue("401_not_found_apikey.json", 401)

        val expectedError = ResError(
            ResErrorInfo(
                status = 401,
                code = "API000",
                message = "NOT_FOUND_APIKEY"
            )
        )

        try {
            dataSource.getServerList()
        } catch (e: HttpException) {
            val error = e.response()?.errorBody()?.string()?.let { json.decodeFromString<ResError>(it) }
            assertEquals(expectedError, error)
        }
    }

    @Test
    fun errorNotFoundCharacter() = runTest {
        mockServerRule.enqueue("404_not_found_character.json", 404)

        val expectedError = ResError(
            ResErrorInfo(
                status = 404,
                code = "DNF001",
                message = "NOT_FOUND_CHARACTER"
            )
        )

        try {
            dataSource.getServerList()
        } catch (e: HttpException) {
            val error = e.response()?.errorBody()?.string()?.let { json.decodeFromString<ResError>(it) }
            assertEquals(expectedError, error)
        }
    }

    @Test
    fun getServerList() = runTest {
        mockServerRule.enqueue("getServerList.json")

        val expectedServerCount = 8
        val expectedFirstServer = ResServer("cain", "카인")

        val result = dataSource.getServerList()

        assertEquals(expectedServerCount, result.rows.size)
        assertEquals(expectedFirstServer, result.rows.first())
    }

    @Test
    fun getCharacterFame() = runTest {
        mockServerRule.enqueue("getCharacterFame.json")

        val serverId = "all"
        val expectedServerCount = 2
        val expectedFirstCharacterName = "사용자이름"

        val result = dataSource.getCharacterFame(
            serverId = serverId,
            limit = 200
        )

        assertEquals(expectedServerCount, result.rows.size)
        assertEquals(expectedFirstCharacterName, result.rows.first().characterName)
    }

    @Test
    fun searchCharacter() = runTest {
        mockServerRule.enqueue("searchCharacter.json")

        val serverId = "all"
        val characterName = "사용"
        val expectedServerCount = 2
        val expectedFirstCharacterName = "사용자이름"

        val result = dataSource.searchCharacter(
            serverId = serverId,
            characterName = characterName,
            limit = 200
        )

        assertEquals(expectedServerCount, result.rows.size)
        assertEquals(expectedFirstCharacterName, result.rows.first().characterName)
    }

    @Test
    fun getCharacterInfo() = runTest {
        mockServerRule.enqueue("getCharacterInfo.json")

        val serverId = "SERVER_ID"
        val characterId = "CHARACTER_ID"
        val expectedCharacterName = "사용자이름"
        val expectedLevel = 100

        val result = dataSource.getCharacterInfo(
            serverId = serverId,
            characterId = characterId,
        )

        assertEquals(expectedCharacterName, result.characterName)
        assertEquals(expectedLevel, result.level)
    }

    @Test
    fun getCharacterEquipment() = runTest {
        mockServerRule.enqueue("getCharacterEquipment.json")

        val serverId = "SERVER_ID"
        val characterId = "CHARACTER_ID"
        val expectedCharacterName = "사용자이름"
        val expectedLevel = 100
        val expectedEquipmentCount = 13

        val result = dataSource.getCharacterEquipment(
            serverId = serverId,
            characterId = characterId,
        )

        assertEquals(expectedCharacterName, result.characterName)
        assertEquals(expectedLevel, result.level)
        assertEquals(expectedEquipmentCount, result.equipment.size)
    }

    @Test
    fun getCharacterAvatar() = runTest {
        mockServerRule.enqueue("getCharacterAvatar.json")

        val serverId = "SERVER_ID"
        val characterId = "CHARACTER_ID"
        val expectedCharacterName = "사용자이름"
        val expectedLevel = 100
        val expectedAvatarCount = 9

        val result = dataSource.getCharacterAvatar(
            serverId = serverId,
            characterId = characterId,
        )

        assertEquals(expectedCharacterName, result.characterName)
        assertEquals(expectedLevel, result.level)
        assertEquals(expectedAvatarCount, result.avatar.size)
    }

    @Test
    fun getItemInfo() = runTest {
        mockServerRule.enqueue("getItemInfo.json")

        val itemId = "ITEM_ID"
        val expectedItemName = "무지강한 클로"
        val expectedItemType = "무기"

        val result = dataSource.getItemInfo(
            itemId = itemId,
        )

        assertEquals(expectedItemName, result.itemName)
        assertEquals(expectedItemType, result.itemType)
    }
}
