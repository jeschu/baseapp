package info.maila.baseapp.domain.music

import info.maila.baseapp.database.repository.EntityCache
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles

@SpringBootTest
@ActiveProfiles("test")
class TrackServiceSpringBootTest {

    @Autowired
    lateinit var trackService: TrackService

    @Autowired
    lateinit var trackRepository: TrackRepository

    @Autowired
    lateinit var trackOverviewRepository: TrackOverviewRepository

    @Autowired
    lateinit var entityCache: EntityCache

    @Test
    @Disabled
    fun `scan dir for music files and insert them into database`() {
        val result = trackService.saveDirs(musicDir)
    }

}