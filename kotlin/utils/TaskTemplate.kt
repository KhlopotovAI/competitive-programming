package utils

import java.io.DataInputStream
import java.io.FileInputStream
import java.io.PrintWriter

fun main(args: Array<String>) {
    val n = inp.nextInt()
    for (i in 0 until n) {
        out.println(inp.nextInt())
    }

    flushAndClose()
}

/*--------------------------------------------------------------------------------------------------------------------*/
/*                                                        UTILS                                                       */
/*--------------------------------------------------------------------------------------------------------------------*/

private val inp = InputReader()
private val out = PrintWriter(System.out)

private fun flushAndClose() {
    out.flush()
    inp.close()
}

private class InputReader : AutoCloseable {
    private var din: DataInputStream
    private var buffer: ByteArray
    private var bufferPointer: Int = 0
    private var bytesRead: Int = 0

    constructor() {
        din = DataInputStream(System.`in`)
        buffer = ByteArray(BUFFER_SIZE)
        bytesRead = 0
        bufferPointer = bytesRead
    }

    constructor(file_name: String) {
        din = DataInputStream(FileInputStream(file_name))
        buffer = ByteArray(BUFFER_SIZE)
        bytesRead = 0
        bufferPointer = bytesRead
    }

    fun readLine(): String {
        val buf = ByteArray(64) // line length
        var cnt = 0
        var c: Int
        while (read().toInt().also { c = it } != -1) {
            if (c == '\n'.toInt())
                break
            buf[cnt++] = c.toByte()
        }
        return String(buf, 0, cnt)
    }

    fun nextInt(): Int {
        var ret = 0
        var c = read()
        while (c <= ' '.toByte())
            c = read()
        val neg = c == '-'.toByte()
        if (neg)
            c = read()
        do {
            ret = ret * 10 + c - '0'.toInt()
        } while (read().also { c = it } >= '0'.toByte() && c <= '9'.toByte())

        return if (neg) -ret else ret
    }


    fun nextLong(): Long {
        var ret: Long = 0
        var c = read()
        while (c <= ' '.toByte())
            c = read()
        val neg = c == '-'.toByte()
        if (neg)
            c = read()
        do {
            ret = ret * 10 + c - '0'.toLong()
        } while (read().also { c = it } >= '0'.toByte() && c <= '9'.toByte())
        return if (neg) -ret else ret
    }

    fun nextDouble(): Double {
        var ret = 0.0
        var div = 1.0
        var c = read()
        while (c <= ' '.toByte())
            c = read()
        val neg = c == '-'.toByte()
        if (neg)
            c = read()

        do {
            ret = ret * 10 + c - '0'.toDouble()
        } while (read().also { c = it } >= '0'.toByte() && c <= '9'.toByte())

        if (c == '.'.toByte()) {
            while (read().also { c = it } >= '0'.toByte() && c <= '9'.toByte()) {
                div *= 10.0
                ret += (c - '0'.toByte()) / div
            }
        }

        return if (neg) -ret else ret
    }


    private fun fillBuffer() {
        bytesRead = din.read(buffer, bufferPointer, BUFFER_SIZE)
        if (bytesRead == -1)
            buffer[0] = -1
    }


    private fun read(): Byte {
        if (bufferPointer == bytesRead)
            fillBuffer()
        return buffer[bufferPointer++]
    }


    override fun close() {
        din.close()
    }

    companion object {
        private val BUFFER_SIZE = 1 shl 16
    }
}