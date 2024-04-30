package today.pathos.android.portfolio.domain.repository

import today.pathos.android.portfolio.entity.Character

interface FameRepository {
    suspend fun getFameCharacterList(): List<Character>
}
