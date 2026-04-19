package info.maila.baseapp.domain.music

import org.springframework.stereotype.Repository
import info.maila.baseapp.database.TableRequestRepository

@Repository
interface TrackOverviewRepository : TableRequestRepository<TrackOverview, Long>