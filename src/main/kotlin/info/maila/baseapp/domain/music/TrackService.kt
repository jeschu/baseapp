package info.maila.baseapp.domain.music

import info.maila.baseapp.database.EntityNotFoundException
import io.github.oshai.kotlinlogging.KotlinLogging
import org.jaudiotagger.tag.images.Artwork
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class TrackService(
    private val trackRepository: TrackRepository,
    private val trackArtworkRepository: TrackArtworkRepository,
    private val trackArtworkMetadataRepository: TrackArtworkMetadataRepository,
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

        fun Artwork.toTrackArtwork(trackId: Long, dbOrder: Int) = TrackArtwork(
            trackId = trackId,
            dbOrder = dbOrder,
            mimeType = mimeType,
            description = description,
            height = height,
            width = width,
            linked = isLinked,
            imageUrl = imageUrl,
            pictureType = pictureType,
            binaryData = binaryData
        )

        val result = SaveDirsResult()
        var logCount = 0
        TagService.scanDirs(*dirs) { (track, artworks) ->
            val savedTrackId = trackRepository.save(track).id
            if (savedTrackId != null) {

                result.addTracksInserted()
                val savedArtworks = trackArtworkRepository.saveAll(
                    artworks
                        .mapIndexed { index, artwork ->
                            artwork.toTrackArtwork(savedTrackId, index)
                        }
                )
                savedArtworks.forEach {
                    if (it.id != null) result.addArtworksInserted() else result.addArtworksFailed()
                }

            } else {
                result.addTracksFailed()
                result.addArtworksFailed(artworks.size)
            }
            if (logger.isTraceEnabled()) {
                if (++logCount % 100 == 0) {
                    logger.trace { "scanned $logCount files ..." }
                }
            }
        }
        return result
    }

    fun findTrackArtworkMetadata(trackId: Long) =
        trackArtworkMetadataRepository.findByTrackId(trackId)

    fun findTrackArtworks(trackId: Long, index: Int) =
        trackArtworkRepository.findByTrackIdAndDbOrder(trackId, index)

}

class SaveDirsResult(
    tracksInserted: Int = 0,
    tracksFailed: Int = 0,
    artworksInserted: Int = 0,
    artworksFailed: Int = 0
) {

    var tracksInserted: Int = tracksInserted
        private set

    var tracksFailed: Int = tracksFailed
        private set

    var artworksInserted: Int = artworksInserted
        private set

    var artworksFailed: Int = artworksFailed
        private set

    internal fun addTracksInserted(add: Int = 1) {
        tracksInserted += add
    }

    internal fun addTracksFailed(add: Int = 1) {
        tracksFailed += add
    }

    internal fun addArtworksInserted(add: Int = 1) {
        artworksInserted += add
    }

    internal fun addArtworksFailed(add: Int = 1) {
        artworksFailed += add
    }

}