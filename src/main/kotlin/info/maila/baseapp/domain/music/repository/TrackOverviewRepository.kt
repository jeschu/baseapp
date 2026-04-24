package info.maila.baseapp.domain.music.repository

import info.maila.baseapp.database.repository.TablePageableRepository
import info.maila.baseapp.domain.music.model.TrackOverview
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface TrackOverviewRepository :
    CrudRepository<TrackOverview, Long>, TablePageableRepository<TrackOverview>