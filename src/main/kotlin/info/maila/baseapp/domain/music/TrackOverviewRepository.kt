package info.maila.baseapp.domain.music

import info.maila.baseapp.database.TableRequestRepository
import org.springframework.stereotype.Repository

@Repository
interface TrackOverviewRepository : TableRequestRepository<TrackOverview, Long>