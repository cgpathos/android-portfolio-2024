package today.pathos.android.portfolio.data.datasource.local.db.table

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(
    tableName = "CHARACTER_TBL",
    primaryKeys = ["server_id", "character_id"]
)
data class CharacterTbl(
    @ColumnInfo(name = "server_id") val serverId: String,
    @ColumnInfo(name = "character_id") val characterId: String,
    @ColumnInfo(name = "character_name") val characterName: String,
    @ColumnInfo(name = "level") val level: Int,
    @ColumnInfo(name = "job_id") val jobId: String,
    @ColumnInfo(name = "job_grow_id") val jobGrowId: String,
    @ColumnInfo(name = "job_name") val jobName: String,
    @ColumnInfo(name = "job_grow_name") val jobGrowName: String,
    @ColumnInfo(name = "fame") val fame: Int = 0,
    @ColumnInfo(name = "adventure_name") val adventureName: String? = null,
    @ColumnInfo(name = "guild_id") val guildId: String? = null,
    @ColumnInfo(name = "guild_name") val guildName: String? = null,
)
