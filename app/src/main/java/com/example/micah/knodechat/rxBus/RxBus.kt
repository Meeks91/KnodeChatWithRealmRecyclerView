package com.example.micah.knodechat.rxBus

import io.reactivex.subjects.PublishSubject

/**
 * Created by Micah on 22/08/2017.
 *
 * A singleton which should be used to bus events around to any observers
 */
object RxBus {

   val bus = PublishSubject.create<RxBusEvent>()
}

/**
 * The type of events that should be published by the the RxBus
 */
enum class RxBusNotificationType {

    messageReceived
}

/**
 *
 * A model used to hold the data being bussed by the RxBus.
 * The [type] is the type of update  RxBusEvent is carrying, it
 * should be used to identify bus event's the observer is interested in.
 * The [data] parameter holds the data being sent in the bus event.
 */
data class RxBusEvent(val type: RxBusNotificationType, val data: Any){


}