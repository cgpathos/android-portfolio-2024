package today.pathos.android.portfolio.domain.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import today.pathos.android.portfolio.domain.repository.CharacterRepository
import today.pathos.android.portfolio.entity.Character
import javax.inject.Inject

class GetCharacterInfoFlowUseCase @Inject constructor(
    private val characterRepository: CharacterRepository,
) {
    operator fun invoke(
        serverId: String,
        characterId: String,
    ): Flow<Character> = combine(
        characterRepository.getCharacterFlow(serverId, characterId),
        characterRepository.getCharacterEquipmentFlow(serverId, characterId),
        characterRepository.getCharacterAvatarFlow(serverId, characterId)
    ) { characterInfo, equipment, avatar ->
        characterInfo.copy(
            serverId = serverId,
            equipment = equipment,
            avatar = avatar
        )
    }
}
