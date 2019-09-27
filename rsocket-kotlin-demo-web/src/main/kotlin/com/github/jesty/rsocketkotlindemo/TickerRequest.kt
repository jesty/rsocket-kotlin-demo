data class TickerRequest(val value: Int)

operator fun TickerRequest.times(v: Int) = v * this.value // it's ok

operator fun Int.rem(v: TickerRequest) = this % v.value //don't do this