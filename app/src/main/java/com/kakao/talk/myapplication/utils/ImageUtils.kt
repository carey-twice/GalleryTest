package com.kakao.talk.myapplication.utils

import android.content.ContentResolver
import android.net.Uri
import java.io.IOException
import java.io.InputStream
import java.nio.ByteBuffer
import java.nio.ByteOrder

object ImageUtils {

    @Throws(Exception::class)
    fun getImageFormat(
        contentResolver: ContentResolver,
        uri: Uri,
        defaultFormat: ImageFormat?,
    ): ImageFormat? {
        var format: ImageFormat = ImageFormat.UNKNOWN
        contentResolver.openInputStream(uri)?.use { inputStream ->
            format = getImageFormat(inputStream)
        }
        return if (format == ImageFormat.UNKNOWN) defaultFormat else format
    }

    /**
     * read first 2 bytes from inputStream and determine the format
     *
     * @return [ImageFormat]
     */
    fun getImageFormat(inputStream: InputStream): ImageFormat {
        var b1 = -1
        var b2 = -1
        try {
            b1 = inputStream.read() and 0xff
            b2 = inputStream.read() and 0xff
        } catch (_: IOException) {
        }
        if (b1 == 0x47 && b2 == 0x49) {
            return ImageFormat.GIF
        } else if (b1 == 0x89 && b2 == 0x50) {
            return ImageFormat.PNG
        } else if (b1 == 0xff && b2 == 0xd8) {
            return ImageFormat.JPEG
        } else if (b1 == 0x52 && b2 == 0x49) {
            return ImageFormat.WEBP
        } else if (b1 == 0x42 && b2 == 0x4d) {
            return ImageFormat.BMP
        } else if (b1 == 0x0a && b2 < 0x06) {
            return ImageFormat.PCX
        } else if (b1 == 0x46 && b2 == 0x4f) {
            return ImageFormat.IFF
        } else if (b1 == 0x59 && b2 == 0xa6) {
            return ImageFormat.RAS
        } else if (b1 == 0x50 && b2 >= 0x31 && b2 <= 0x36) {
            return ImageFormat.PNM
        } else if (b1 == 0x38 && b2 == 0x42) {
            return ImageFormat.PSD
        } else if (b1 == 0x46 && b2 == 0x57) {
            return ImageFormat.SWF
        }
        if (b1 == 0x49 && b2 == 0x49 || b1 == 0x4d && b2 == 0x4d) {
            val bytes = ByteArray(2)
            try {
                val readed = inputStream.read(bytes)
                if (readed == 2) {
                    val bb = ByteBuffer.wrap(bytes)
                    if (b1 == 0x49) {
                        bb.order(ByteOrder.LITTLE_ENDIAN)
                    } else {
                        bb.order(ByteOrder.BIG_ENDIAN)
                    }
                    if (bb.getShort().toInt() == 42) {
                        return ImageFormat.TIFF
                    }
                }
            } catch (_: IOException) {
            }
        }
        return ImageFormat.UNKNOWN
    }

    enum class ImageFormat(val extension: String) {
        GIF("gif"),
        PNG("png"),
        JPEG("jpg"),
        WEBP("webp"),
        BMP("bmp"),
        PCX("pcx"),
        IFF("iff"),
        RAS("ras"),
        PNM("pnm"),
        PSD("psd"),
        SWF("swf"),
        TIFF("tif"),
        UNKNOWN(""),
        NONE("none");
    }
}