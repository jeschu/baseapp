package info.maila.baseapp.domain.music

import jakarta.validation.constraints.NotNull
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.Version
import org.springframework.data.relational.core.mapping.Table
import java.util.Objects

@Table(name = "track", schema = "baseapp")
data class TrackOverview(
    @Id
    val id: Long? = null,
    @NotNull
    val path: String? = null,
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
    val image: ByteArray? = null,
    @Version
    val dbVersion: Int? = null
) {

    override fun toString() = "%s(id=%s, path='%s')"
        .format(TrackOverview::class.simpleName, Objects.toString(id, "-"), path)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as TrackOverview

        if (id != other.id) return false
        if (dbVersion != other.dbVersion) return false
        if (path != other.path) return false
        if (album != other.album) return false
        if (albumArtist != other.albumArtist) return false
        if (albumArtistSort != other.albumArtistSort) return false
        if (albumArtists != other.albumArtists) return false
        if (albumArtistsSort != other.albumArtistsSort) return false
        if (albumSort != other.albumSort) return false
        if (albumYear != other.albumYear) return false
        if (arranger != other.arranger) return false
        if (arrangerSort != other.arrangerSort) return false
        if (artist != other.artist) return false
        if (artists != other.artists) return false
        if (artistsSort != other.artistsSort) return false
        if (artistSort != other.artistSort) return false
        if (bpm != other.bpm) return false
        if (comment != other.comment) return false
        if (composer != other.composer) return false
        if (composerSort != other.composerSort) return false
        if (copyright != other.copyright) return false
        if (country != other.country) return false
        if (coverArt != other.coverArt) return false
        if (discNo != other.discNo) return false
        if (discSubtitle != other.discSubtitle) return false
        if (discTotal != other.discTotal) return false
        if (genre != other.genre) return false
        if (isSoundtrack != other.isSoundtrack) return false
        if (isCompilation != other.isCompilation) return false
        if (itunesGrouping != other.itunesGrouping) return false
        if (language != other.language) return false
        if (lyricist != other.lyricist) return false
        if (lyricistSort != other.lyricistSort) return false
        if (lyrics != other.lyrics) return false
        if (orchestraSort != other.orchestraSort) return false
        if (originalAlbum != other.originalAlbum) return false
        if (originalreleasedate != other.originalreleasedate) return false
        if (originalArtist != other.originalArtist) return false
        if (originalLyricist != other.originalLyricist) return false
        if (originalYear != other.originalYear) return false
        if (part != other.part) return false
        if (partNumber != other.partNumber) return false
        if (partType != other.partType) return false
        if (performer != other.performer) return false
        if (performerName != other.performerName) return false
        if (performerNameSort != other.performerNameSort) return false
        if (producer != other.producer) return false
        if (producerSort != other.producerSort) return false
        if (quality != other.quality) return false
        if (ranking != other.ranking) return false
        if (rating != other.rating) return false
        if (tempo != other.tempo) return false
        if (title != other.title) return false
        if (titleSort != other.titleSort) return false
        if (track != other.track) return false
        if (trackTotal != other.trackTotal) return false
        if (year != other.year) return false
        if (version != other.version) return false
        if (!image.contentEquals(other.image)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + (dbVersion ?: 0)
        result = 31 * result + (path?.hashCode() ?: 0)
        result = 31 * result + (album?.hashCode() ?: 0)
        result = 31 * result + (albumArtist?.hashCode() ?: 0)
        result = 31 * result + (albumArtistSort?.hashCode() ?: 0)
        result = 31 * result + (albumArtists?.hashCode() ?: 0)
        result = 31 * result + (albumArtistsSort?.hashCode() ?: 0)
        result = 31 * result + (albumSort?.hashCode() ?: 0)
        result = 31 * result + (albumYear?.hashCode() ?: 0)
        result = 31 * result + (arranger?.hashCode() ?: 0)
        result = 31 * result + (arrangerSort?.hashCode() ?: 0)
        result = 31 * result + (artist?.hashCode() ?: 0)
        result = 31 * result + (artists?.hashCode() ?: 0)
        result = 31 * result + (artistsSort?.hashCode() ?: 0)
        result = 31 * result + (artistSort?.hashCode() ?: 0)
        result = 31 * result + (bpm?.hashCode() ?: 0)
        result = 31 * result + (comment?.hashCode() ?: 0)
        result = 31 * result + (composer?.hashCode() ?: 0)
        result = 31 * result + (composerSort?.hashCode() ?: 0)
        result = 31 * result + (copyright?.hashCode() ?: 0)
        result = 31 * result + (country?.hashCode() ?: 0)
        result = 31 * result + (coverArt?.hashCode() ?: 0)
        result = 31 * result + (discNo?.hashCode() ?: 0)
        result = 31 * result + (discSubtitle?.hashCode() ?: 0)
        result = 31 * result + (discTotal?.hashCode() ?: 0)
        result = 31 * result + (genre?.hashCode() ?: 0)
        result = 31 * result + (isSoundtrack?.hashCode() ?: 0)
        result = 31 * result + (isCompilation?.hashCode() ?: 0)
        result = 31 * result + (itunesGrouping?.hashCode() ?: 0)
        result = 31 * result + (language?.hashCode() ?: 0)
        result = 31 * result + (lyricist?.hashCode() ?: 0)
        result = 31 * result + (lyricistSort?.hashCode() ?: 0)
        result = 31 * result + (lyrics?.hashCode() ?: 0)
        result = 31 * result + (orchestraSort?.hashCode() ?: 0)
        result = 31 * result + (originalAlbum?.hashCode() ?: 0)
        result = 31 * result + (originalreleasedate?.hashCode() ?: 0)
        result = 31 * result + (originalArtist?.hashCode() ?: 0)
        result = 31 * result + (originalLyricist?.hashCode() ?: 0)
        result = 31 * result + (originalYear?.hashCode() ?: 0)
        result = 31 * result + (part?.hashCode() ?: 0)
        result = 31 * result + (partNumber?.hashCode() ?: 0)
        result = 31 * result + (partType?.hashCode() ?: 0)
        result = 31 * result + (performer?.hashCode() ?: 0)
        result = 31 * result + (performerName?.hashCode() ?: 0)
        result = 31 * result + (performerNameSort?.hashCode() ?: 0)
        result = 31 * result + (producer?.hashCode() ?: 0)
        result = 31 * result + (producerSort?.hashCode() ?: 0)
        result = 31 * result + (quality?.hashCode() ?: 0)
        result = 31 * result + (ranking?.hashCode() ?: 0)
        result = 31 * result + (rating?.hashCode() ?: 0)
        result = 31 * result + (tempo?.hashCode() ?: 0)
        result = 31 * result + (title?.hashCode() ?: 0)
        result = 31 * result + (titleSort?.hashCode() ?: 0)
        result = 31 * result + (track?.hashCode() ?: 0)
        result = 31 * result + (trackTotal?.hashCode() ?: 0)
        result = 31 * result + (year?.hashCode() ?: 0)
        result = 31 * result + (version?.hashCode() ?: 0)
        result = 31 * result + (image?.contentHashCode() ?: 0)
        return result
    }

}