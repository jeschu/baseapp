package info.maila.baseapp.tag

import org.jaudiotagger.audio.AudioFileIO
import org.springframework.stereotype.Service
import java.io.File

@Service
class TagService {

    private val scannableFileExtensions = setOf("mp3", "m4a", "m4p", "flac", "aac", "ogg", "wav", "wma", "dsf")

    fun scanDirs(vararg dirs: String, callback: (ReadTagResult) -> Unit) {
        dirs.forEach { dir ->
            File(dir)
                .walkTopDown()
                .filter { it.isScannable() }
                .map { it.readTag() }
                .forEach { callback(it) }
        }
    }

    private fun File.isScannable(): Boolean = isFile && extension in scannableFileExtensions

    fun File.readTag(): ReadTagResult {
        val audioFile = AudioFileIO.read(this)
        val tag = audioFile.tag
        return ReadTagResult(path, tag)
    }

}
