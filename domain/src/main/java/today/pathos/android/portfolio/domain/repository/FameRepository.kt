package today.pathos.android.portfolio.domain.repository

import kotlinx.coroutines.flow.Flow
import today.pathos.android.portfolio.entity.Character

interface FameRepository {
    fun getFameCharacterListFlow(): Flow<List<Character>>
    suspend fun getFameCharacterList(): List<Character>
}
