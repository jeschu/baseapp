package info.maila.baseapp.domain.music.model

import jakarta.validation.constraints.NotNull
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.Transient
import org.springframework.data.annotation.Version
import org.springframework.data.relational.core.mapping.Table
import kotlin.math.round
import kotlin.time.Duration
import kotlin.time.DurationUnit.SECONDS
import kotlin.time.toDuration

@Table(name = "track", schema = "baseapp")
data class Track(
    @Id
    val id: Long? = null,
    @Version
    val dbVersion: Int? = null,
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
    val acoustidFingerprint: String? = null,
    val acoustidId: String? = null,
    val album: String? = null,
    val albumArtist: String? = null,
    val albumArtistSort: String? = null,
    val albumArtists: String? = null,
    val albumArtistsSort: String? = null,
    val albumSort: String? = null,
    val albumYear: String? = null,
    val amazonId: String? = null,
    val arranger: String? = null,
    val arrangerSort: String? = null,
    val artist: String? = null,
    val artists: String? = null,
    val artistsSort: String? = null,
    val artistSort: String? = null,
    val barcode: String? = null,
    val bpm: String? = null,
    val catalogNo: String? = null,
    val classicalCatalog: String? = null,
    val classicalNickname: String? = null,
    val choir: String? = null,
    val choirSort: String? = null,
    val comment: String? = null,
    val composer: String? = null,
    val composerSort: String? = null,
    val conductor: String? = null,
    val conductorSort: String? = null,
    val copyright: String? = null,
    val country: String? = null,
    val coverArt: String? = null,
    val custom1: String? = null,
    val custom2: String? = null,
    val custom3: String? = null,
    val custom4: String? = null,
    val custom5: String? = null,
    val discNo: String? = null,
    val discSubtitle: String? = null,
    val discTotal: String? = null,
    val djmixer: String? = null,
    val djmixerSort: String? = null,
    val encoder: String? = null,
    val engineer: String? = null,
    val engineerSort: String? = null,
    val ensemble: String? = null,
    val ensembleSort: String? = null,
    val fbpm: String? = null,
    val genre: String? = null,
    val group: String? = null,
    val grouping: String? = null,
    val instrument: String? = null,
    val involvedpeople: String? = null,
    val ipi: String? = null,
    val isrc: String? = null,
    val iswc: String? = null,
    val isClassical: String? = null,
    val isGreatestHits: String? = null,
    val isHd: String? = null,
    val isLive: String? = null,
    val isSoundtrack: String? = null,
    val isCompilation: String? = null,
    val itunesGrouping: String? = null,
    val jaikozId: String? = null,
    val key: String? = null,
    val language: String? = null,
    val lyricist: String? = null,
    val lyricistSort: String? = null,
    val lyrics: String? = null,
    val media: String? = null,
    val mixer: String? = null,
    val mixerSort: String? = null,
    val mood: String? = null,
    val moodAcoustic: String? = null,
    val moodAggressive: String? = null,
    val moodArousal: String? = null,
    val moodDanceability: String? = null,
    val moodElectronic: String? = null,
    val moodHappy: String? = null,
    val moodInstrumental: String? = null,
    val moodParty: String? = null,
    val moodRelaxed: String? = null,
    val moodSad: String? = null,
    val moodValence: String? = null,
    val movement: String? = null,
    val movementNo: String? = null,
    val movementTotal: String? = null,
    val musicbrainzArtistid: String? = null,
    val musicbrainzDiscId: String? = null,
    val musicbrainzOriginalReleaseId: String? = null,
    val musicbrainzRecordingWork: String? = null,
    val musicbrainzRecordingWorkId: String? = null,
    val musicbrainzReleaseartistid: String? = null,
    val musicbrainzReleaseid: String? = null,
    val musicbrainzReleaseCountry: String? = null,
    val musicbrainzReleaseGroupId: String? = null,
    val musicbrainzReleaseStatus: String? = null,
    val musicbrainzReleaseTrackId: String? = null,
    val musicbrainzReleaseType: String? = null,
    val musicbrainzTrackId: String? = null,
    val musicbrainzWork: String? = null,
    val musicbrainzWorkId: String? = null,
    val musicbrainzWorkPartLevel1: String? = null,
    val musicbrainzWorkPartLevel1Id: String? = null,
    val musicbrainzWorkPartLevel1Type: String? = null,
    val musicbrainzWorkPartLevel2: String? = null,
    val musicbrainzWorkPartLevel2Id: String? = null,
    val musicbrainzWorkPartLevel2Type: String? = null,
    val musicbrainzWorkPartLevel3: String? = null,
    val musicbrainzWorkPartLevel3Id: String? = null,
    val musicbrainzWorkPartLevel3Type: String? = null,
    val musicbrainzWorkPartLevel4: String? = null,
    val musicbrainzWorkPartLevel4Id: String? = null,
    val musicbrainzWorkPartLevel4Type: String? = null,
    val musicbrainzWorkPartLevel5: String? = null,
    val musicbrainzWorkPartLevel5Id: String? = null,
    val musicbrainzWorkPartLevel5Type: String? = null,
    val musicbrainzWorkPartLevel6: String? = null,
    val musicbrainzWorkPartLevel6Id: String? = null,
    val musicbrainzWorkPartLevel6Type: String? = null,
    val musicipId: String? = null,
    val occasion: String? = null,
    val opus: String? = null,
    val orchestra: String? = null,
    val orchestraSort: String? = null,
    val originalAlbum: String? = null,
    val originalreleasedate: String? = null,
    val originalArtist: String? = null,
    val originalLyricist: String? = null,
    val originalYear: String? = null,
    val overallWork: String? = null,
    val part: String? = null,
    val partNumber: String? = null,
    val partType: String? = null,
    val performer: String? = null,
    val performerName: String? = null,
    val performerNameSort: String? = null,
    val period: String? = null,
    val producer: String? = null,
    val producerSort: String? = null,
    val quality: String? = null,
    val ranking: String? = null,
    val rating: String? = null,
    val recordLabel: String? = null,
    val recordingdate: String? = null,
    val recordingstartdate: String? = null,
    val recordingenddate: String? = null,
    val recordinglocation: String? = null,
    val remixer: String? = null,
    val roonalbumtag: String? = null,
    val roontracktag: String? = null,
    val section: String? = null,
    val script: String? = null,
    val singleDiscTrackNo: String? = null,
    val songkongId: String? = null,
    val subtitle: String? = null,
    val tags: String? = null,
    val tempo: String? = null,
    val timbre: String? = null,
    @NotNull
    val title: String? = null,
    val titleSort: String? = null,
    val titleMovement: String? = null,
    val tonality: String? = null,
    val track: String? = null,
    val trackTotal: String? = null,
    val urlDiscogsArtistSite: String? = null,
    val urlDiscogsReleaseSite: String? = null,
    val urlLyricsSite: String? = null,
    val urlOfficialArtistSite: String? = null,
    val urlOfficialReleaseSite: String? = null,
    val urlWikipediaArtistSite: String? = null,
    val urlWikipediaReleaseSite: String? = null,
    val work: String? = null,
    val workType: String? = null,
    val year: String? = null,
    val version: String? = null
) {

    @Transient
    val channelsInt: Int? = when (channels?.lowercase()) {
        "stereo", "joint stereo", "dual", "2" -> 2
        "mono", "1" -> 1
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