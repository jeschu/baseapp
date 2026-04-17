package info.maila.baseapp.domain.music

import io.github.oshai.kotlinlogging.KotlinLogging
import org.junit.jupiter.api.Test
import java.io.File
import java.util.TreeMap
import java.util.logging.Level
import java.util.logging.Logger

class TagServiceTest {

    private val logger = KotlinLogging.logger {}

    @Test
    fun `scan dirs`() {
        Logger.getLogger("org.jaudiotagger").setLevel(Level.OFF)
        val extensions = TreeMap<String, Int>()
        var fileCountScanned = 0
        var fileCountWithTitle = 0
        TagService.scanDirs(musicDir) {
            val path = requireNotNull(it.path) { "File path is null" }
            val extension = File(path).extension
            if (extensions.containsKey(extension))
                extensions[extension] = extensions[extension]!! + 1
            else
                extensions[extension] = 1

            fileCountScanned++
            if (it.title != null) fileCountWithTitle++

            if ((fileCountScanned % 1_000) == 0) {
                logger.info { "%6d files scanned ...".format(fileCountScanned) }
            }
        }
        logger.info { "$fileCountScanned files scanned - $fileCountWithTitle with title" }
        extensions.forEach { (extension, count) ->
            logger.info { "%s: %6d".format(extension, count) }
        }
    }

}