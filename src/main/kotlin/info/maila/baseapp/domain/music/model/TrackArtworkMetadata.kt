package info.maila.baseapp.domain.music.model

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table(name = "track_artwork", schema = "baseapp")
data class TrackArtworkMetadata(
    @Id
    val id: Long,
    val trackId: Long,
    val dbOrder: Long,
    val mimeType: String,
    val description: String,
    val height: Int,
    val width: Int,
    val linked: Boolean,
    val imageUrl: String,
    val pictureType: Int,
    val size: Int
)