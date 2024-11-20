package com.core.commons.extensions

import java.net.URLDecoder
import java.net.URLEncoder

fun String.encode() = URLEncoder.encode(this, "UTF-8")
fun String.decode() = URLDecoder.decode(this, "UTF-8")