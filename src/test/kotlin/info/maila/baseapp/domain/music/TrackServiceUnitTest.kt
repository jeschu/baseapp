package info.maila.baseapp.domain.music

import info.maila.baseapp.common.model.TablePage
import info.maila.baseapp.common.model.TablePageable
import info.maila.baseapp.database.EntityNotFoundException
import info.maila.baseapp.domain.music.model.Track
import info.maila.baseapp.domain.music.model.TrackOverview
import info.maila.baseapp.domain.music.repository.TrackArtworkMetadataRepository
import info.maila.baseapp.domain.music.repository.TrackArtworkRepository
import info.maila.baseapp.domain.music.repository.TrackOverviewRepository
import info.maila.baseapp.domain.music.repository.TrackRepository
import info.maila.baseapp.domain.music.service.TrackService
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.util.*

class TrackServiceUnitTest {

    private lateinit var trackRepository: TrackRepository
    private lateinit var trackArtworkRepository: TrackArtworkRepository
    private lateinit var trackArtworkMetadataRepository: TrackArtworkMetadataRepository
    private lateinit var trackOverviewRepository: TrackOverviewRepository
    private lateinit var trackService: TrackService

    @BeforeEach
    fun setUp() {
        trackRepository = mockk()
        trackArtworkRepository = mockk()
        trackArtworkMetadataRepository = mockk()
        trackOverviewRepository = mockk()
        trackService = TrackService(
            trackRepository,
            trackArtworkRepository,
            trackArtworkMetadataRepository,
            trackOverviewRepository
        )
    }

    @Test
    fun `findTrackById returns track if found`() {
        val track = Track(id = 1L, dbVersion = 1, path = "foo.mp3")
        every { trackRepository.findById(1L) } returns Optional.of(track)
        val result = trackService.findTrackById(1L)
        assertEquals(track, result)
    }

    @Test
    fun `findTrackById throws if not found`() {
        every { trackRepository.findById(2L) } returns Optional.empty()
        assertThrows(EntityNotFoundException::class.java) {
            trackService.findTrackById(2L)
        }
    }

    @Test
    fun `findAll delegates to trackOverviewRepository`() {
        val pageable = TablePageable(offset = 0, limit = 10)
        val overview = TrackOverview(id = 1L, path = "foo.mp3")
        every {
            trackOverviewRepository.findAll(pageable, TrackOverview::class)
        } returns TablePage(listOf(overview))
        val result = trackService.findAll(pageable)
        assertEquals(1, result.rows.size)
        assertEquals(overview, result.rows[0])
    }

}