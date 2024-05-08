package today.pathos.android.portfolio.presentation.viewmodel

enum class Screens(
    val route: String,
) {
    NavigateUp(route = "navigate_up"), // 뒤로가기 처리용
    Splash(route = "splash"),
    Main(route = "main"),
    CharacterInfo(route = "character_info/{serverId}/{characterId}")
}
