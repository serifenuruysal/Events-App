package com.androidapp.eventsapp.data.network.paging

/**
 * Created by S.Nur Uysal on 2020-02-10.
 */

class NetworkState(status: Status, msg: String) {
    enum class Status {
        RUNNING, SUCCESS, FAILED
    }

    private val status: Status = status
    private val msg: String = msg

    companion object {
        var LOADED: NetworkState? = null
        var LOADING: NetworkState? = null

        init {
            LOADED = NetworkState(Status.SUCCESS, "Success")
            LOADING = NetworkState(Status.RUNNING, "Running")
        }
    }

    fun getStatus(): Status {
        return status
    }

    fun getMsg(): String {
        return msg
    }

}
