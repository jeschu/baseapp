package info.maila.baseapp.domain.music

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface TrackArtworkRepository : CrudRepository<TrackArtwork, Long> {

    fun findByTrackIdAndDbOrder(trackId: Long, dbOrder: Int): List<TrackArtwork>

}