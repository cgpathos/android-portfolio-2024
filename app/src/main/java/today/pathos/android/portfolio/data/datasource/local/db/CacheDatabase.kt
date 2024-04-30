package today.pathos.android.portfolio.data.datasource.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import today.pathos.android.portfolio.data.datasource.local.db.dao.CharacterDao
import today.pathos.android.portfolio.data.datasource.local.db.dao.FameDao
import today.pathos.android.portfolio.data.datasource.local.db.table.AvatarTbl
import today.pathos.android.portfolio.data.datasource.local.db.table.CharacterTbl
import today.pathos.android.portfolio.data.datasource.local.db.table.EquipmentTbl
import today.pathos.android.portfolio.data.datasource.local.db.table.FameTbl

@Database(
    entities = [
        FameTbl::class,
        CharacterTbl::class,
        EquipmentTbl::class,
        AvatarTbl::class
    ],
    version = 1
)
abstract class CacheDatabase : RoomDatabase() {
    abstract fun fameDao(): FameDao
    abstract fun characterDao(): CharacterDao
}
