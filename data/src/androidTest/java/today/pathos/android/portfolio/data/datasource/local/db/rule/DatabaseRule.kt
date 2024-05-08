package today.pathos.android.portfolio.data.datasource.local.db.rule

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import org.junit.rules.TestWatcher
import org.junit.runner.Description
import today.pathos.android.portfolio.data.datasource.local.db.CacheDatabase

class DatabaseRule : TestWatcher() {
    val database = Room.inMemoryDatabaseBuilder(
        ApplicationProvider.getApplicationContext(),
        CacheDatabase::class.java
    ).allowMainThreadQueries()
        .build()

    override fun finished(description: Description?) {
        super.finished(description)
        database.close()
    }
}
