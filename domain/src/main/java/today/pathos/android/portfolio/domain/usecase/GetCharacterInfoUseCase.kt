package today.pathos.android.portfolio.domain.usecase

import today.pathos.android.portfolio.domain.repository.CharacterRepository
import today.pathos.android.portfolio.entity.Character
import javax.inject.Inject

class GetCharacterInfoUseCase @Inject constructor(
    private val characterRepository: CharacterRepository,
) {
    suspend operator fun invoke(
        serverId: String,
        characterId: String,
    ): Character {
        val characterInfo = characterRepository.getCharacter(serverId, characterId)
        val equipment = characterRepository.getCharacterEquipment(serverId, characterId)
        val avatar = characterRepository.getCharacterAvatar(serverId, characterId)

        return characterInfo.copy(
            serverId = serverId,
            equipment = equipment,
            avatar = avatar
        )
    }
}
