package app.elite.mynote.utils

const val API_VERSION = "/v1"

const val USERS = "$API_VERSION/users"
const val USER_LOGIN = "$USERS/login"
const val USER_CREATE = "$USERS/create"

const val NOTES = "$API_VERSION/notes"


const val STORE_NAME = "local_ds"

var token: String = ""
var cookies: String = ""


enum class NOTE_STATUS {
    SYNC,
    NOT_SYNC,
    ALL
}
