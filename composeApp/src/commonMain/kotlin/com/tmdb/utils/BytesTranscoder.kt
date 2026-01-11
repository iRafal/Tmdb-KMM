package com.tmdb.utils

import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

interface BytesTranscoder {
    fun encode(value: ByteArray): ByteArray?
    fun decode(value: ByteArray): ByteArray?
}

class BytesTranscoderImpl : BytesTranscoder {
    @OptIn(ExperimentalEncodingApi::class)
    override fun encode(value: ByteArray): ByteArray? = try {
        Base64.encodeToByteArray(value)
    } catch (_: IndexOutOfBoundsException) {
        null
    } catch (_: IllegalArgumentException) {
        null
    }

    @OptIn(ExperimentalEncodingApi::class)
    override fun decode(value: ByteArray): ByteArray? = try {
        Base64.decode(value)
    } catch (_: IndexOutOfBoundsException) {
        null
    } catch (_: IllegalArgumentException) {
        null
    }
}
