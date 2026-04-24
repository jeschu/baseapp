package info.maila.baseapp.domain.music.repository

import info.maila.baseapp.domain.music.model.TrackArtworkMetadata
import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface TrackArtworkMetadataRepository : CrudRepository<TrackArtworkMetadata, Long> {

    @Query("""
        SELECT id, track_id, db_order, mime_type, description, height, width, linked, image_url,
               picture_type, length(binary_data) AS size
        FROM baseapp.track_artwork
        WHERE track_id = :trackId
        ORDER BY db_order
    """)
    fun findByTrackId(trackId: Long): List<TrackArtworkMetadata>

}