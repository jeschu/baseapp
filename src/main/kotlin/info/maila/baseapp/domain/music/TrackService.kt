package info.maila.baseapp.domain.music

import info.maila.baseapp.database.EntityNotFoundException
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class TrackService(
    private val trackRepository: TrackRepository,
    private val trackOverviewRepository: TrackOverviewRepository
) {

    private val logger = KotlinLogging.logger { }

    fun findTrackById(id: Long): Track = trackRepository.findById(id)
        .orElseThrow { EntityNotFoundException(Track::class, id) }

    fun findAll(pageable: Pageable) = trackOverviewRepository.findAll(pageable)

    fun save(track: Track): Track {
        require(track.id == null) { "id must be null for new entities" }
        require(track.dbVersion == null) { "dbVersion must be null for new entities" }
        return trackRepository.save(track)
    }

    fun update(id: Long, track: Track): Track {
        require(id == track.id) { "id missmatch" }
        requireNotNull(track.dbVersion) { "dbVersion is null" }
        val existing = findTrackById(id)
        // TODO: Es gibt keine updateWith-Methode, daher wird das Objekt direkt gespeichert
        return trackRepository.save(track)
    }

    fun delete(id: Long) = trackRepository.deleteById(id)

    @Transactional
    fun saveDirs(vararg dirs: String): SaveDirsResult {
        val result = SaveDirsResult()
        var logCount = 0
        TagService.scanDirs(*dirs) { track ->
            val saved = trackRepository.save(track)
            if (saved.id != null)
                result.addInserted()
            else
                result.addFailed()
            logger.trace { "scanned ${++logCount} files ..." }
        }
        return result
    }

}

class SaveDirsResult(inserted: Int = 0, failed: Int = 0) {

    var inserted: Int = inserted
        private set

    var failed: Int = failed
        private set

    internal fun addInserted(add: Int = 1) {
        inserted += add
    }

    internal fun addFailed(add: Int = 1) {
        failed += add
    }

}