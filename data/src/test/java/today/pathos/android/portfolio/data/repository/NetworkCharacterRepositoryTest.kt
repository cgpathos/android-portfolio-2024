package today.pathos.android.portfolio.data.repository

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import today.pathos.android.portfolio.data.datasource.remote.NetworkDataSource
import today.pathos.android.portfolio.data.datasource.remote.dto.res.ResAvatar
import today.pathos.android.portfolio.data.datasource.remote.dto.res.ResCharacter
import today.pathos.android.portfolio.data.datasource.remote.dto.res.ResClone
import today.pathos.android.portfolio.data.datasource.remote.dto.res.ResEquipment
import today.pathos.android.portfolio.entity.AvatarSlotId
import today.pathos.android.portfolio.entity.EquipmentSlotId

class NetworkCharacterRepositoryTest {
    private val dataSource: NetworkDataSource = mockk()
    private val testDispatcher = StandardTestDispatcher()

    private lateinit var repository: NetworkCharacterRepository

    @Before
    fun setUp() {
        repository = NetworkCharacterRepository(
            dataSource = dataSource,
            dispatcher = testDispatcher
        )
    }

    @Test
    fun getCharacterInfo() = runTest(testDispatcher) {
        val serverId = "SERVER_ID"
        val characterId = "CHARACTER_ID"

        coEvery {
            dataSource.getCharacter(serverId, characterId)
        } returns ResCharacter(
            serverId = serverId,
            characterId = characterId,
            characterName = "characterName",
            level = 100,
            jobId = "jobId",
            jobGrowId = "jobGrowId",
            jobName = "jobName",
            jobGrowName = "jobGrowName",
        )
        val expectedServerId = "SERVER_ID"
        val expectedCharacterId = "CHARACTER_ID"

        val result = repository.getCharacter(serverId, characterId)

        assertEquals(expectedServerId, result.serverId)
        assertEquals(expectedCharacterId, result.characterId)

        coVerify { dataSource.getCharacter(serverId, characterId) }
    }

    @Test
    fun getCharacterEquipment() = runTest(testDispatcher) {
        val serverId = "SERVER_ID"
        val characterId = "CHARACTER_ID"

        val dummyEquipment = ResEquipment(
            slotId = EquipmentSlotId.AMULET,
            slotName = "slotName",
            itemId = "itemId",
            itemName = "itemName",
            itemTypeId = "itemTypeId",
            itemType = "itemType",
            itemTypeDetailId = "itemTypeDetailId",
            itemTypeDetail = "itemTypeDetail",
            itemAvailableLevel = 50,
            itemRarity = "itemRarity",
            reinforce = 5,
            refine = 0,
        )

        coEvery {
            dataSource.getCharacterEquipment(serverId, characterId)
        } returns ResCharacter(
            serverId = serverId,
            characterId = characterId,
            characterName = "characterName",
            level = 100,
            jobId = "jobId",
            jobGrowId = "jobGrowId",
            jobName = "jobName",
            jobGrowName = "jobGrowName",
            equipment = listOf(dummyEquipment, dummyEquipment, dummyEquipment, dummyEquipment)
        )
        val expectedEquipmentCount = 4

        val result = repository.getCharacterEquipment(serverId, characterId)

        assertEquals(expectedEquipmentCount, result.size)

        coVerify { dataSource.getCharacterEquipment(serverId, characterId) }
    }

    @Test
    fun getCharacterAvatar() = runTest(testDispatcher) {
        val serverId = "SERVER_ID"
        val characterId = "CHARACTER_ID"

        val dummyAvatar = ResAvatar(
            slotId = AvatarSlotId.HAIR,
            slotName = "slotName",
            itemId = "itemId",
            itemName = "itemName",
            itemRarity = "itemRarity",
            clone = ResClone(null, null),
            optionAbility = null,
        )

        coEvery {
            dataSource.getCharacterAvatar(serverId, characterId)
        } returns ResCharacter(
            serverId = serverId,
            characterId = characterId,
            characterName = "characterName",
            level = 100,
            jobId = "jobId",
            jobGrowId = "jobGrowId",
            jobName = "jobName",
            jobGrowName = "jobGrowName",
            avatar = listOf(dummyAvatar, dummyAvatar, dummyAvatar, dummyAvatar, dummyAvatar)
        )
        val expectedEquipmentCount = 5

        val result = repository.getCharacterAvatar(serverId, characterId)

        assertEquals(expectedEquipmentCount, result.size)

        coVerify { dataSource.getCharacterAvatar(serverId, characterId) }
    }
}
