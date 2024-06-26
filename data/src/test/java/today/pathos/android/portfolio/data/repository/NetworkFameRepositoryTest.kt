package today.pathos.android.portfolio.data.repository

import app.cash.turbine.test
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import today.pathos.android.portfolio.data.datasource.remote.NetworkDataSource
import today.pathos.android.portfolio.data.datasource.remote.dto.res.ResCharacter
import today.pathos.android.portfolio.data.datasource.remote.dto.res.ResRows

class NetworkFameRepositoryTest {
    private val dataSource: NetworkDataSource = mockk()
    private val testDispatcher = StandardTestDispatcher()

    private lateinit var repository: NetworkFameRepository

    @Before
    fun setUp() {
        repository = NetworkFameRepository(
            dataSource = dataSource,
            dispatcher = testDispatcher
        )
    }

    @Test
    fun getFameList() = runTest(testDispatcher) {
        val dummyResCharacter = ResCharacter(
            serverId = "serverId",
            characterId = "characterId",
            characterName = "characterName",
            level = 100,
            jobId = "jobId",
            jobGrowId = "jobGrowId",
            jobName = "jobName",
            jobGrowName = "jobGrowName",
        )

        coEvery { dataSource.getCharacterFame() } returns ResRows(
            rows = listOf(
                dummyResCharacter,
                dummyResCharacter,
                dummyResCharacter,
                dummyResCharacter,
                dummyResCharacter,
            )
        )

        val expectedCount = 5

        repository.getFameCharacterListFlow()
            .test {
                assertEquals(expectedCount, awaitItem().size)
                awaitComplete()
            }

        coVerify { dataSource.getCharacterFame() }
    }
}
