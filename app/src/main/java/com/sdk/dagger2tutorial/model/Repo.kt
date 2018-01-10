package com.sdk.dagger2tutorial.model

import java.util.*

/**
 * @author kevin.adesara on 02/01/18.
 */

data class Repo(val name: String, val owner: Owner, val createdAt: Date, val updatedAt: Date, val htmlUrl: String)