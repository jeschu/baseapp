package info.maila.baseapp.domain.music

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

    @Test
    @Disabled
    fun `scan dir for music files and insert them into database`() {
        val result = trackService.saveDirs(musicDir)
    }

    /*
    @Test
    fun `save and findTrackById`() {
        val track = Track(path = "test.mp3")
        val saved = trackService.save(track)
        assertNotNull(saved.id)
        val found = trackService.findTrackById(saved.id!!)
        assertEquals(saved, found)
    }

    @Test
    fun `findAll returns page`() {
        val track = Track(path = "foo.mp3")
        trackService.save(track)
        val page = trackService.findAll(PageRequest.of(0, 10))
        assertTrue(page.content.isNotEmpty())
    }

    @Test
    fun `update modifies track`() {
        val track = Track(path = "bar.mp3")
        val saved = trackService.save(track)
        val updated = saved.copy(dbVersion = 0, path = "baz.mp3")
        val result = trackService.update(saved.id!!, updated)
        assertEquals("baz.mp3", result.path)
    }

    @Test
    fun `delete removes track`() {
        val track = Track(path = "del.mp3")
        val saved = trackService.save(track)
        trackService.delete(saved.id!!)
        assertThrows(EntityNotFoundException::class.java) {
            trackService.findTrackById(saved.id!!)
        }
    }

    @Test
    fun `findTrackById throws for missing`() {
        assertThrows(EntityNotFoundException::class.java) {
            trackService.findTrackById(-1L)
        }
    }
    */

}