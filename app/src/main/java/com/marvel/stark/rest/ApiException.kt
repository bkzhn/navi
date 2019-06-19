package com.marvel.stark.rest

/**Created by Jahongir on 6/17/2019.*/


internal open class ApiException : Exception {
    var errorCode = 500
    var errorMessage: String? = null

    constructor() : super() {}

    constructor(message: String?) : super(message) {
        this.errorMessage = message
    }

    constructor(message: String, cause: Throwable) : super(message, cause) {
        this.errorMessage = message
    }

    constructor(cause: Throwable) : super(cause) {}

    constructor(message: String?, errorCode: Int) : super(message) {
        this.errorCode = errorCode
        this.errorMessage = message
    }

}