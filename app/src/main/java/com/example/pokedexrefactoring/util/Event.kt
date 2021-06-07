package com.example.pokedexrefactoring.util

/** livedata + Viewmodel의 이중 처리를 막기 위한 event wrapper class
 *  livedata의 타입에 wrapping하여 사용한다.
 *
 * 사용 예)
 * livedata의 flag를 이용해서 view를 갱신할 경우 livedata.value = true 후에 livedata.value = false로
 * 다시 변경해주는 이중 작업을 해야할 경우에 사용하면
 * livedata.value = true후 false로 값을 변경해주지 않아도 된다.
 * (hasBeenHandled flag를 통해 이중처리를 막는다.)
 *
 * c
 * */
class Event<T>(private val content: T) {
    //이벤트가 처리 되었는가?
    var hasBeenHandled = false
        private set
    /**
     * 이벤트를 가져 오기 위한 함수 (이미 처리 되었다면 null을 리턴한다.)
     */
    fun getContentIfNotHandled(): T? =
        if (hasBeenHandled) { //이벤트가 이미 처리되었다면 null
            null
        } else { //이벤트가 처리되지 않았다면 값을 반환한다.
            hasBeenHandled = true
            content
        }
    /**
     * 이벤트를 처리 하기 위한 함수 (이미 처리 되었다면 처리 하지 않는다.)
     */
    fun runOnEventIfNotHandled(block: (T) -> Unit) {
        //이벤트가 처리 되었다면 block을 실행하지 않는다.
        if (hasBeenHandled) return
        //이벤트가 처리 되지 않았을 경우에만 block을 실행한다.
        hasBeenHandled = true
        block(content)
    }
    /**
     * 이벤트 처리 여부와 관련 없이 content를 가져오기 위한 함수
     */
    fun peekContent(): T = content
}