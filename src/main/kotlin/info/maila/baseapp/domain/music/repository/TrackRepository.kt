package info.maila.baseapp.domain.music.repository

import info.maila.baseapp.domain.music.model.Track
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface TrackRepository : CrudRepository<Track, Long>