package info.maila.baseapp.domain.music

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface TrackRepository : CrudRepository<Track, Long>