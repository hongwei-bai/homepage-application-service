package com.hongwei.model.soap.auth

enum class AccessLevel(val value: String) {
    Public("public"),
    Private("private"),
    GuestAccess("guessAccess")
}