package today.pathos.android.portfolio.data.datasource.local.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import today.pathos.android.portfolio.data.datasource.local.db.table.FameTbl

@Dao
abstract class FameDao {
    @Insert
    abstract suspend fun createFame(fameList: List<FameTbl>)

    @Query("SELECT count(*) FROM FAME_TBL")
    abstract suspend fun fameListCount(): Int

    @Query("SELECT * FROM FAME_TBL")
    abstract fun getFameList(): Flow<List<FameTbl>>

}
