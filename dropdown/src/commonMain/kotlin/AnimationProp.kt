// [AnimationProp] is properties of animation as compose has experimental apis we can change the internal code when needed.
data class AnimationProp(
    val enterDuration: Int,
    val exitDuration: Int,
    val delay: Int = 0,
    val easing: Easing,
    val initialAlpha: Float = 0.92f,
)
