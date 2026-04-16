package info.maila.baseapp.tag

import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest(classes = [TagService::class])
class TagServiceTest {
    @Autowired
    lateinit var service: TagService

    @Test
    fun `context loads`() {
        assertNotNull(service)
    }

    @Test
    fun `scan dirs`() {
        service.scanDirs("/Users/jens/Music/Music/Media.localized/Music") {
            println(it)
        }
    }

}
