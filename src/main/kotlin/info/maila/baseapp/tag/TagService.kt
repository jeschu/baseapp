package info.maila.baseapp.tag

import org.jaudiotagger.audio.AudioFileIO
import java.io.File

object TagService {

    fun scanDirs(vararg dirs: String, callback: (ReadTagResult) -> Unit) {
        dirs.forEach { dir -> scanDir(dir, callback) }
    }

    fun scanDir(dir: String, callback: (ReadTagResult) -> Unit) {
        File(dir)
            .walkTopDown()
            .filter { it.isScannable() }
            .map { it.readTag() }
            .forEach { callback(it) }
    }

    fun File.readTag(): ReadTagResult {
        val audioFile = AudioFileIO.read(this)
        val tag = audioFile.tag
        return ReadTagResult(path, tag)
    }

    private val scannableFileExtensions = setOf("mp3", "m4a", "m4p", "flac", "aac", "ogg", "wav", "wma", "dsf")
    private fun File.isScannable(): Boolean = isFile && extension in scannableFileExtensions

}
