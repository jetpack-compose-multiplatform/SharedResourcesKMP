package org.lizardoreyes.sharedresourceskmp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform