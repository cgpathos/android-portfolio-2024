package today.pathos.android.portfolio.data.datasource.local.db.table

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(
    tableName = "FAME_TBL",
    primaryKeys = ["server_id", "character_id"]
)
data class FameTbl(
    @ColumnInfo(name = "server_id") val serverId: String,
    @ColumnInfo(name = "character_id") val characterId: String,
    @ColumnInfo(name = "character_name") val characterName: String,
    @ColumnInfo(name = "level") val level: Int,
    @ColumnInfo(name = "job_id") val jobId: String,
    @ColumnInfo(name = "job_grow_id") val jobGrowId: String,
    @ColumnInfo(name = "job_name") val jobName: String,
    @ColumnInfo(name = "job_grow_name") val jobGrowName: String,
    @ColumnInfo(name = "fame") val fame: Int = 0,
)
