package com.sdk.dagger2tutorial.model

import com.google.gson.annotations.SerializedName

/**
 * @author kevin.adesara on 05/01/18.
 */
data class Owner(@SerializedName("login") val name: String, val avatarUrl: String)