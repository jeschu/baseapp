package info.maila.baseapp.tag

import java.io.File
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import org.springframework.stereotype.Service

@Service
class TagService {

    private val scannableFileExtensions = setOf("mp3", "m4a", "m4p", "flac", "aac", "ogg", "wav", "wma", "dsf")

    fun scanDirs(
        vararg dirs: String,
        timeout: Long = 1,
        timeoutUnit: TimeUnit = TimeUnit.HOURS,
        callback: TagCallback
    ) {
        val files = dirs
            .map(::File)
            .flatMap { dir ->
                dir.walkTopDown().filter { file -> file.isFile && file.extension in scannableFileExtensions }.toList()
            }

        val executor = Executors.newFixedThreadPool(threadCount(0.75))
        try {
            files.forEach {
                executor.submit {
                    callback.scanResult(
                        scanFile(it.absolutePath)
                    )
                }
            }
        } finally {
            executor.shutdown()
            executor.awaitTermination(timeout, timeoutUnit)
        }
    }

    fun scanFile(path: String): ScanResult {
        return ScanResult(path)
    }


}

@Suppress("SameParameterValue")
private fun threadCount(factor: Double): Int = Runtime.getRuntime().availableProcessors().let { processors ->
    maxOf(1, (processors * factor).toInt())
}
