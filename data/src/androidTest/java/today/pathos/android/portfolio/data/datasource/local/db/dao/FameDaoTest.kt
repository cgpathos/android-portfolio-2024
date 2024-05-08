package today.pathos.android.portfolio.data.datasource.local.db.dao

import app.cash.turbine.test
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import today.pathos.android.portfolio.data.datasource.local.db.rule.DatabaseRule
import today.pathos.android.portfolio.data.datasource.local.db.table.FameTbl

class FameDaoTest {
    @get:Rule
    val rule = DatabaseRule()

    private val fameDao = rule.database.fameDao()

    @Before
    fun setUp() {
        createTestData()
    }

    private fun createTestData() = runTest {
        val testData = listOf(
            FameTbl(
                serverId = "SERVER_ID1",
                characterId = "CHARACTER_ID",
                characterName = "CHARACTER_NAME_1",
                level = 99,
                jobId = "JOB_ID",
                jobGrowId = "JOB_GROW_ID",
                jobName = "JOB_NAME",
                jobGrowName = "JOB_GROW_NAME",
                fame = 999,
            ),
            FameTbl(
                serverId = "SERVER_ID2",
                characterId = "CHARACTER_ID",
                characterName = "CHARACTER_NAME",
                level = 99,
                jobId = "JOB_ID",
                jobGrowId = "JOB_GROW_ID",
                jobName = "JOB_NAME",
                jobGrowName = "JOB_GROW_NAME",
                fame = 999,
            ),
        )
        fameDao.createFame(testData)
    }

    @Test
    fun fameListCount() = runTest {
        val fameListCount = fameDao.fameListCount()
        val expectedCount = 2
        assertEquals(expectedCount, fameListCount)
    }

    @Test
    fun firstFameInFameList() = runTest {
        val expectedFirstCharacterName = "CHARACTER_NAME_1"

        fameDao.getFameList()
            .test {
                assertEquals(expectedFirstCharacterName, awaitItem().first().characterName)
            }
    }
}
