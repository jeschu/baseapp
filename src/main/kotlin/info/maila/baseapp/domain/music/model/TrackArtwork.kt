package info.maila.baseapp.domain.music.model

import jakarta.validation.constraints.NotNull
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.Version
import org.springframework.data.relational.core.mapping.Table

@Table(name = "track_artwork", schema = "baseapp")
data class TrackArtwork(
    @Id
    val id: Long? = null,
    @Version
    val dbVersion: Int? = null,
    @NotNull
    val trackId: Long? = null,
    @NotNull
    val dbOrder: Int? = null,
    val mimeType: String? = null,
    val description: String? = null,
    val height: Int? = null,
    val width: Int? = null,
    val linked: Boolean? = null,
    val imageUrl: String? = null,
    val pictureType: Int? = null,
    val binaryData: ByteArray? = null
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as TrackArtwork

        if (id != other.id) return false
        if (dbVersion != other.dbVersion) return false
        if (trackId != other.trackId) return false
        if (dbOrder != other.dbOrder) return false
        if (height != other.height) return false
        if (width != other.width) return false
        if (linked != other.linked) return false
        if (pictureType != other.pictureType) return false
        if (mimeType != other.mimeType) return false
        if (description != other.description) return false
        if (imageUrl != other.imageUrl) return false
        if (!binaryData.contentEquals(other.binaryData)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + (dbVersion ?: 0)
        result = 31 * result + (trackId?.hashCode() ?: 0)
        result = 31 * result + (dbOrder ?: 0)
        result = 31 * result + (height ?: 0)
        result = 31 * result + (width ?: 0)
        result = 31 * result + (linked?.hashCode() ?: 0)
        result = 31 * result + (pictureType ?: 0)
        result = 31 * result + (mimeType?.hashCode() ?: 0)
        result = 31 * result + (description?.hashCode() ?: 0)
        result = 31 * result + (imageUrl?.hashCode() ?: 0)
        result = 31 * result + (binaryData?.contentHashCode() ?: 0)
        return result
    }

    override fun toString(): String {
        return "TrackArtwork(" +
                "id=$id, " +
                "trackId=$trackId, " +
                "dbOrder=$dbOrder, " +
                "mimeType=$mimeType, " +
                "height=$height, " +
                "width=$width, " +
                "binaryData=${binaryData?.size ?: 0} bytes" +
                ")"
    }

}