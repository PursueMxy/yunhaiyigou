package com.xdys.library.network

import java.io.IOException

class HttpStatusException(val code: Int, val msg: String?) : IOException()