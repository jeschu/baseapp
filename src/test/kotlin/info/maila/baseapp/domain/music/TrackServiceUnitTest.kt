package info.maila.baseapp.domain.music

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import java.util.*
import info.maila.baseapp.database.EntityNotFoundException

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
        val pageable = PageRequest.of(0, 10)
        val overview = TrackOverview(id = 1L, path = "foo.mp3")
        every { trackOverviewRepository.findAll(pageable) } returns PageImpl(listOf(overview))
        val result = trackService.findAll(pageable)
        assertEquals(1, result.totalElements)
        assertEquals(overview, result.content[0])
    }

    @Test
    fun `save enforces id and dbVersion null and saves`() {
        val track = Track(id = null, dbVersion = null, path = "foo.mp3")
        val saved = track.copy(id = 1L, dbVersion = 0)
        every { trackRepository.save(track) } returns saved
        val result = trackService.save(track)
        assertEquals(saved, result)
    }

    @Test
    fun `save throws if id is not null`() {
        val track = Track(id = 1L, dbVersion = null, path = "foo.mp3")
        assertThrows(IllegalArgumentException::class.java) {
            trackService.save(track)
        }
    }

    @Test
    fun `save throws if dbVersion is not null`() {
        val track = Track(id = null, dbVersion = 1, path = "foo.mp3")
        assertThrows(IllegalArgumentException::class.java) {
            trackService.save(track)
        }
    }

    @Test
    fun `update enforces id match and dbVersion not null and saves`() {
        val track = Track(id = 1L, dbVersion = 2, path = "foo.mp3")
        every { trackRepository.findById(1L) } returns Optional.of(track)
        every { trackRepository.save(track) } returns track
        val result = trackService.update(1L, track)
        assertEquals(track, result)
    }

    @Test
    fun `update throws if id mismatch`() {
        val track = Track(id = 2L, dbVersion = 2, path = "foo.mp3")
        assertThrows(IllegalArgumentException::class.java) {
            trackService.update(1L, track)
        }
    }

    @Test
    fun `update throws if dbVersion is null`() {
        val track = Track(id = 1L, dbVersion = null, path = "foo.mp3")
        assertThrows(IllegalArgumentException::class.java) {
            trackService.update(1L, track)
        }
    }

    @Test
    fun `delete delegates to repository`() {
        every { trackRepository.deleteById(1L) } returns Unit
        trackService.delete(1L)
        verify { trackRepository.deleteById(1L) }
    }

}