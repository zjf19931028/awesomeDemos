package com.awesome.retrofitdemo.kotlin.exception;

import java.io.IOException

class HttpResultException(val code: Int, val msg: String) : IOException()