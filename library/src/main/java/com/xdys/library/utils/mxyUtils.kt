package com.xdys.library.utils

import okhttp3.internal.and
import java.security.MessageDigest

class mxyUtils {
    //MD5加密不可逆
    object MD5Utils {
        /*对密码进行加密
     *参数：密码
     *返回：密文
     */
        fun digest(password: String): String {
            return try {
                val digest: MessageDigest = MessageDigest.getInstance("MD5")
                val bytes: ByteArray = digest.digest(password.toByteArray())
                val sb = StringBuilder()
                for (b in bytes) {
                    val c: Int = b and 0xff //负数转换成正数
                    val result = Integer.toHexString(c) //把十进制的数转换成十六进制的书
                    if (result.length < 2) {
                        sb.append(0) //让十六进制全部都是两位数
                    }
                    sb.append(result)
                }
                sb.toString() //返回加密后的密文
            } catch (ex: Exception) {
                ex.printStackTrace()
                ""
            }
        }
    }
}