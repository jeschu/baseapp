package info.maila.baseapp.domain.music.repository

import info.maila.baseapp.domain.music.model.TrackArtwork
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface TrackArtworkRepository : CrudRepository<TrackArtwork, Long> {

    fun findByTrackIdAndDbOrder(trackId: Long, dbOrder: Int): TrackArtwork

}