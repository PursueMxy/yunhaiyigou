package com.xdys.library.utils

import android.content.Context
import java.io.File

object FileUtils {

    /**
     * 获取当前文件(夹)的长度
     */
    fun getFolderSize(file: File): Long {
        var size: Long = 0
        file.listFiles()?.forEach {
            size += if (it.isDirectory) getFolderSize(it)
            else it.length()
        }
        return size
    }

    /**
     * 清理文件(夹)
     */
    fun deleteFile(file: File): Boolean {
        if (file.isDirectory) file.list()?.forEach {
            if (!deleteFile(File(file, it))) return false
        }
        return file.delete()
    }

}