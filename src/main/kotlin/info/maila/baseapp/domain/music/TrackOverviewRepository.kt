package info.maila.baseapp.domain.music

import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository

@Repository
interface TrackOverviewRepository : PagingAndSortingRepository<TrackOverview, Long>