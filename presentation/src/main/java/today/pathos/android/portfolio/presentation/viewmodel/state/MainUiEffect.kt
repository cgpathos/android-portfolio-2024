package today.pathos.android.portfolio.presentation.viewmodel.state

@Deprecated("Result<T> 추가로 삭제")
sealed class MainUiEffect {
    data object Idle : MainUiEffect()
    data object Loading : MainUiEffect()
    data class Error(val e: Throwable, val callback: ((e: Throwable) -> Unit)? = null) : MainUiEffect()
}
