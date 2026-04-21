package info.maila.baseapp.domain.music

import jakarta.validation.constraints.NotNull
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.Transient
import org.springframework.data.annotation.Version
import org.springframework.data.relational.core.mapping.Table
import java.util.*
import kotlin.math.round
import kotlin.time.Duration
import kotlin.time.DurationUnit.SECONDS
import kotlin.time.toDuration

@Table(name = "track", schema = "baseapp")
data class TrackOverview(
    @Id
    val id: Long? = null,
    @NotNull
    val path: String? = null,
    val encodingType: String? = null,
    val bitRate: Long? = null,
    val sampleRate: Int? = null,
    val format: String? = null,
    val channels: String? = null,
    val isVariableBitRate: Boolean? = null,
    val trackLength: Double? = null,
    val bitsPerSample: Int? = null,
    val isLossless: Boolean? = null,
    val noOfSamples: Long? = null,
    val album: String? = null,
    val albumArtist: String? = null,
    val albumArtistSort: String? = null,
    val albumArtists: String? = null,
    val albumArtistsSort: String? = null,
    val albumSort: String? = null,
    val albumYear: String? = null,
    val arranger: String? = null,
    val arrangerSort: String? = null,
    val artist: String? = null,
    val artists: String? = null,
    val artistsSort: String? = null,
    val artistSort: String? = null,
    val bpm: String? = null,
    val comment: String? = null,
    val composer: String? = null,
    val composerSort: String? = null,
    val copyright: String? = null,
    val country: String? = null,
    val coverArt: String? = null,
    val discNo: String? = null,
    val discSubtitle: String? = null,
    val discTotal: String? = null,
    val genre: String? = null,
    val isSoundtrack: String? = null,
    val isCompilation: String? = null,
    val itunesGrouping: String? = null,
    val language: String? = null,
    val lyricist: String? = null,
    val lyricistSort: String? = null,
    val lyrics: String? = null,
    val orchestraSort: String? = null,
    val originalAlbum: String? = null,
    val originalreleasedate: String? = null,
    val originalArtist: String? = null,
    val originalLyricist: String? = null,
    val originalYear: String? = null,
    val part: String? = null,
    val partNumber: String? = null,
    val partType: String? = null,
    val performer: String? = null,
    val performerName: String? = null,
    val performerNameSort: String? = null,
    val producer: String? = null,
    val producerSort: String? = null,
    val quality: String? = null,
    val ranking: String? = null,
    val rating: String? = null,
    val tempo: String? = null,
    @NotNull
    val title: String? = null,
    val titleSort: String? = null,
    val track: String? = null,
    val trackTotal: String? = null,
    val year: String? = null,
    val version: String? = null,
    @Version
    val dbVersion: Int? = null
) {

    override fun toString() = "%s(id=%s, path='%s')"
        .format(TrackOverview::class.simpleName, Objects.toString(id, "-"), path)

    @Transient
    val channelsInt: Int? = when (channels?.lowercase()) {
        "Stereo", "Joint Stereo", "Dual", "2" -> 2
        "Mono", "1" -> 1
        else -> null
    }

    @Transient
    val discNoInt: Int? = discNo?.toIntOrNull()

    @Transient
    val discTotalInt: Int? = discTotal?.toIntOrNull()

    @Transient
    val trackInt: Int? = track?.toIntOrNull()

    @Transient
    val trackTotalInt: Int? = trackTotal?.toIntOrNull()

    @Transient
    val trackLengthDuration: Duration? = trackLength?.toDuration(SECONDS)

    @Transient
    val trackLengthString: String? = trackLengthDuration?.let { d ->
        "%d:%02d".format(
            d.inWholeMinutes,
            trackLength!!.mod(60.0).let { s -> round(s).toInt() })
    }

}