package info.maila.baseapp.domain.music

import info.maila.baseapp.database.TablePageableRepository
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface TrackOverviewRepository :
    CrudRepository<TrackOverview, Long>, TablePageableRepository<TrackOverview>