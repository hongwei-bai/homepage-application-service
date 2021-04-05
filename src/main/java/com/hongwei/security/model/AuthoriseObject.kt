package com.hongwei.security.model

data class AuthoriseObject(
        val validated: Boolean = false,
        val validatedUntil: Long,
        val userName: String,
        val role: String,
        val preferenceJson: String,
        val privilegeJson: String,
        val blogEntry: Boolean = false,
        val blogCreate: Boolean = false,
        val blogModifyAll: Boolean = false
)

const val LONG_TERM = -1L