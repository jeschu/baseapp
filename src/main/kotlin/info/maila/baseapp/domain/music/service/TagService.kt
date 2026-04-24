package info.maila.baseapp.domain.music.service

import info.maila.baseapp.domain.music.model.Track
import io.github.oshai.kotlinlogging.KotlinLogging
import org.jaudiotagger.audio.AudioFile
import org.jaudiotagger.audio.AudioFileIO
import org.jaudiotagger.tag.FieldKey
import org.jaudiotagger.tag.FieldKey.ACOUSTID_FINGERPRINT
import org.jaudiotagger.tag.FieldKey.ACOUSTID_ID
import org.jaudiotagger.tag.FieldKey.ALBUM
import org.jaudiotagger.tag.FieldKey.ALBUM_ARTIST
import org.jaudiotagger.tag.FieldKey.ALBUM_ARTISTS
import org.jaudiotagger.tag.FieldKey.ALBUM_ARTISTS_SORT
import org.jaudiotagger.tag.FieldKey.ALBUM_ARTIST_SORT
import org.jaudiotagger.tag.FieldKey.ALBUM_SORT
import org.jaudiotagger.tag.FieldKey.ALBUM_YEAR
import org.jaudiotagger.tag.FieldKey.AMAZON_ID
import org.jaudiotagger.tag.FieldKey.ARRANGER
import org.jaudiotagger.tag.FieldKey.ARRANGER_SORT
import org.jaudiotagger.tag.FieldKey.ARTIST
import org.jaudiotagger.tag.FieldKey.ARTISTS
import org.jaudiotagger.tag.FieldKey.ARTISTS_SORT
import org.jaudiotagger.tag.FieldKey.ARTIST_SORT
import org.jaudiotagger.tag.FieldKey.BARCODE
import org.jaudiotagger.tag.FieldKey.BPM
import org.jaudiotagger.tag.FieldKey.CATALOG_NO
import org.jaudiotagger.tag.FieldKey.CHOIR
import org.jaudiotagger.tag.FieldKey.CHOIR_SORT
import org.jaudiotagger.tag.FieldKey.CLASSICAL_CATALOG
import org.jaudiotagger.tag.FieldKey.CLASSICAL_NICKNAME
import org.jaudiotagger.tag.FieldKey.COMMENT
import org.jaudiotagger.tag.FieldKey.COMPOSER
import org.jaudiotagger.tag.FieldKey.COMPOSER_SORT
import org.jaudiotagger.tag.FieldKey.CONDUCTOR
import org.jaudiotagger.tag.FieldKey.CONDUCTOR_SORT
import org.jaudiotagger.tag.FieldKey.COPYRIGHT
import org.jaudiotagger.tag.FieldKey.COUNTRY
import org.jaudiotagger.tag.FieldKey.COVER_ART
import org.jaudiotagger.tag.FieldKey.CUSTOM1
import org.jaudiotagger.tag.FieldKey.CUSTOM2
import org.jaudiotagger.tag.FieldKey.CUSTOM3
import org.jaudiotagger.tag.FieldKey.CUSTOM4
import org.jaudiotagger.tag.FieldKey.CUSTOM5
import org.jaudiotagger.tag.FieldKey.DISC_NO
import org.jaudiotagger.tag.FieldKey.DISC_SUBTITLE
import org.jaudiotagger.tag.FieldKey.DISC_TOTAL
import org.jaudiotagger.tag.FieldKey.DJMIXER
import org.jaudiotagger.tag.FieldKey.DJMIXER_SORT
import org.jaudiotagger.tag.FieldKey.ENCODER
import org.jaudiotagger.tag.FieldKey.ENGINEER
import org.jaudiotagger.tag.FieldKey.ENGINEER_SORT
import org.jaudiotagger.tag.FieldKey.ENSEMBLE
import org.jaudiotagger.tag.FieldKey.ENSEMBLE_SORT
import org.jaudiotagger.tag.FieldKey.FBPM
import org.jaudiotagger.tag.FieldKey.GENRE
import org.jaudiotagger.tag.FieldKey.GROUP
import org.jaudiotagger.tag.FieldKey.GROUPING
import org.jaudiotagger.tag.FieldKey.INSTRUMENT
import org.jaudiotagger.tag.FieldKey.INVOLVEDPEOPLE
import org.jaudiotagger.tag.FieldKey.IPI
import org.jaudiotagger.tag.FieldKey.ISRC
import org.jaudiotagger.tag.FieldKey.ISWC
import org.jaudiotagger.tag.FieldKey.IS_CLASSICAL
import org.jaudiotagger.tag.FieldKey.IS_COMPILATION
import org.jaudiotagger.tag.FieldKey.IS_GREATEST_HITS
import org.jaudiotagger.tag.FieldKey.IS_HD
import org.jaudiotagger.tag.FieldKey.IS_LIVE
import org.jaudiotagger.tag.FieldKey.IS_SOUNDTRACK
import org.jaudiotagger.tag.FieldKey.ITUNES_GROUPING
import org.jaudiotagger.tag.FieldKey.JAIKOZ_ID
import org.jaudiotagger.tag.FieldKey.KEY
import org.jaudiotagger.tag.FieldKey.LANGUAGE
import org.jaudiotagger.tag.FieldKey.LYRICIST
import org.jaudiotagger.tag.FieldKey.LYRICIST_SORT
import org.jaudiotagger.tag.FieldKey.LYRICS
import org.jaudiotagger.tag.FieldKey.MEDIA
import org.jaudiotagger.tag.FieldKey.MIXER
import org.jaudiotagger.tag.FieldKey.MIXER_SORT
import org.jaudiotagger.tag.FieldKey.MOOD
import org.jaudiotagger.tag.FieldKey.MOOD_ACOUSTIC
import org.jaudiotagger.tag.FieldKey.MOOD_AGGRESSIVE
import org.jaudiotagger.tag.FieldKey.MOOD_AROUSAL
import org.jaudiotagger.tag.FieldKey.MOOD_DANCEABILITY
import org.jaudiotagger.tag.FieldKey.MOOD_ELECTRONIC
import org.jaudiotagger.tag.FieldKey.MOOD_HAPPY
import org.jaudiotagger.tag.FieldKey.MOOD_INSTRUMENTAL
import org.jaudiotagger.tag.FieldKey.MOOD_PARTY
import org.jaudiotagger.tag.FieldKey.MOOD_RELAXED
import org.jaudiotagger.tag.FieldKey.MOOD_SAD
import org.jaudiotagger.tag.FieldKey.MOOD_VALENCE
import org.jaudiotagger.tag.FieldKey.MOVEMENT
import org.jaudiotagger.tag.FieldKey.MOVEMENT_NO
import org.jaudiotagger.tag.FieldKey.MOVEMENT_TOTAL
import org.jaudiotagger.tag.FieldKey.MUSICBRAINZ_ARTISTID
import org.jaudiotagger.tag.FieldKey.MUSICBRAINZ_DISC_ID
import org.jaudiotagger.tag.FieldKey.MUSICBRAINZ_ORIGINAL_RELEASE_ID
import org.jaudiotagger.tag.FieldKey.MUSICBRAINZ_RECORDING_WORK
import org.jaudiotagger.tag.FieldKey.MUSICBRAINZ_RECORDING_WORK_ID
import org.jaudiotagger.tag.FieldKey.MUSICBRAINZ_RELEASEARTISTID
import org.jaudiotagger.tag.FieldKey.MUSICBRAINZ_RELEASEID
import org.jaudiotagger.tag.FieldKey.MUSICBRAINZ_RELEASE_COUNTRY
import org.jaudiotagger.tag.FieldKey.MUSICBRAINZ_RELEASE_GROUP_ID
import org.jaudiotagger.tag.FieldKey.MUSICBRAINZ_RELEASE_STATUS
import org.jaudiotagger.tag.FieldKey.MUSICBRAINZ_RELEASE_TRACK_ID
import org.jaudiotagger.tag.FieldKey.MUSICBRAINZ_RELEASE_TYPE
import org.jaudiotagger.tag.FieldKey.MUSICBRAINZ_TRACK_ID
import org.jaudiotagger.tag.FieldKey.MUSICBRAINZ_WORK
import org.jaudiotagger.tag.FieldKey.MUSICBRAINZ_WORK_ID
import org.jaudiotagger.tag.FieldKey.MUSICBRAINZ_WORK_PART_LEVEL1
import org.jaudiotagger.tag.FieldKey.MUSICBRAINZ_WORK_PART_LEVEL1_ID
import org.jaudiotagger.tag.FieldKey.MUSICBRAINZ_WORK_PART_LEVEL1_TYPE
import org.jaudiotagger.tag.FieldKey.MUSICBRAINZ_WORK_PART_LEVEL2
import org.jaudiotagger.tag.FieldKey.MUSICBRAINZ_WORK_PART_LEVEL2_ID
import org.jaudiotagger.tag.FieldKey.MUSICBRAINZ_WORK_PART_LEVEL2_TYPE
import org.jaudiotagger.tag.FieldKey.MUSICBRAINZ_WORK_PART_LEVEL3
import org.jaudiotagger.tag.FieldKey.MUSICBRAINZ_WORK_PART_LEVEL3_ID
import org.jaudiotagger.tag.FieldKey.MUSICBRAINZ_WORK_PART_LEVEL3_TYPE
import org.jaudiotagger.tag.FieldKey.MUSICBRAINZ_WORK_PART_LEVEL4
import org.jaudiotagger.tag.FieldKey.MUSICBRAINZ_WORK_PART_LEVEL4_ID
import org.jaudiotagger.tag.FieldKey.MUSICBRAINZ_WORK_PART_LEVEL4_TYPE
import org.jaudiotagger.tag.FieldKey.MUSICBRAINZ_WORK_PART_LEVEL5
import org.jaudiotagger.tag.FieldKey.MUSICBRAINZ_WORK_PART_LEVEL5_ID
import org.jaudiotagger.tag.FieldKey.MUSICBRAINZ_WORK_PART_LEVEL5_TYPE
import org.jaudiotagger.tag.FieldKey.MUSICBRAINZ_WORK_PART_LEVEL6
import org.jaudiotagger.tag.FieldKey.MUSICBRAINZ_WORK_PART_LEVEL6_ID
import org.jaudiotagger.tag.FieldKey.MUSICBRAINZ_WORK_PART_LEVEL6_TYPE
import org.jaudiotagger.tag.FieldKey.MUSICIP_ID
import org.jaudiotagger.tag.FieldKey.OCCASION
import org.jaudiotagger.tag.FieldKey.OPUS
import org.jaudiotagger.tag.FieldKey.ORCHESTRA
import org.jaudiotagger.tag.FieldKey.ORCHESTRA_SORT
import org.jaudiotagger.tag.FieldKey.ORIGINALRELEASEDATE
import org.jaudiotagger.tag.FieldKey.ORIGINAL_ALBUM
import org.jaudiotagger.tag.FieldKey.ORIGINAL_ARTIST
import org.jaudiotagger.tag.FieldKey.ORIGINAL_LYRICIST
import org.jaudiotagger.tag.FieldKey.ORIGINAL_YEAR
import org.jaudiotagger.tag.FieldKey.OVERALL_WORK
import org.jaudiotagger.tag.FieldKey.PART
import org.jaudiotagger.tag.FieldKey.PART_NUMBER
import org.jaudiotagger.tag.FieldKey.PART_TYPE
import org.jaudiotagger.tag.FieldKey.PERFORMER
import org.jaudiotagger.tag.FieldKey.PERFORMER_NAME
import org.jaudiotagger.tag.FieldKey.PERFORMER_NAME_SORT
import org.jaudiotagger.tag.FieldKey.PERIOD
import org.jaudiotagger.tag.FieldKey.PRODUCER
import org.jaudiotagger.tag.FieldKey.PRODUCER_SORT
import org.jaudiotagger.tag.FieldKey.QUALITY
import org.jaudiotagger.tag.FieldKey.RANKING
import org.jaudiotagger.tag.FieldKey.RATING
import org.jaudiotagger.tag.FieldKey.RECORDINGDATE
import org.jaudiotagger.tag.FieldKey.RECORDINGENDDATE
import org.jaudiotagger.tag.FieldKey.RECORDINGLOCATION
import org.jaudiotagger.tag.FieldKey.RECORDINGSTARTDATE
import org.jaudiotagger.tag.FieldKey.RECORD_LABEL
import org.jaudiotagger.tag.FieldKey.REMIXER
import org.jaudiotagger.tag.FieldKey.ROONALBUMTAG
import org.jaudiotagger.tag.FieldKey.ROONTRACKTAG
import org.jaudiotagger.tag.FieldKey.SCRIPT
import org.jaudiotagger.tag.FieldKey.SECTION
import org.jaudiotagger.tag.FieldKey.SINGLE_DISC_TRACK_NO
import org.jaudiotagger.tag.FieldKey.SONGKONG_ID
import org.jaudiotagger.tag.FieldKey.SUBTITLE
import org.jaudiotagger.tag.FieldKey.TAGS
import org.jaudiotagger.tag.FieldKey.TEMPO
import org.jaudiotagger.tag.FieldKey.TIMBRE
import org.jaudiotagger.tag.FieldKey.TITLE
import org.jaudiotagger.tag.FieldKey.TITLE_MOVEMENT
import org.jaudiotagger.tag.FieldKey.TITLE_SORT
import org.jaudiotagger.tag.FieldKey.TONALITY
import org.jaudiotagger.tag.FieldKey.TRACK
import org.jaudiotagger.tag.FieldKey.TRACK_TOTAL
import org.jaudiotagger.tag.FieldKey.URL_DISCOGS_ARTIST_SITE
import org.jaudiotagger.tag.FieldKey.URL_DISCOGS_RELEASE_SITE
import org.jaudiotagger.tag.FieldKey.URL_LYRICS_SITE
import org.jaudiotagger.tag.FieldKey.URL_OFFICIAL_ARTIST_SITE
import org.jaudiotagger.tag.FieldKey.URL_OFFICIAL_RELEASE_SITE
import org.jaudiotagger.tag.FieldKey.URL_WIKIPEDIA_ARTIST_SITE
import org.jaudiotagger.tag.FieldKey.URL_WIKIPEDIA_RELEASE_SITE
import org.jaudiotagger.tag.FieldKey.VERSION
import org.jaudiotagger.tag.FieldKey.WORK
import org.jaudiotagger.tag.FieldKey.WORK_TYPE
import org.jaudiotagger.tag.FieldKey.YEAR
import org.jaudiotagger.tag.KeyNotFoundException
import org.jaudiotagger.tag.images.Artwork
import java.io.File

object TagService {

    private val logger = KotlinLogging.logger { }

    fun scanDirs(vararg dirs: String, callback: (Pair<Track, List<Artwork>>) -> Unit) {
        dirs.forEach { dir -> scanDir(dir, callback) }
    }

    fun scanDir(dir: String, callback: (Pair<Track, List<Artwork>>) -> Unit) {
        File(dir)
            .also { logger.debug { "scanDir('$dir')" } }
            .walkTopDown()
            .filter { it.isScannable() }
            .map { it.readTag() }
            .forEach { callback(it) }
    }

    fun File.readTag(): Pair<Track, List<Artwork>> = createTrack(path, AudioFileIO.read(this))

    private val scannableFileExtensions =
        setOf("mp3", "m4a", "m4p", "flac", "aac", "ogg", "wav", "wma", "dsf")

    private fun File.isScannable(): Boolean = isFile && extension in scannableFileExtensions

    private fun createTrack(path: String, audioFile: AudioFile): Pair<Track, List<Artwork>> {

        fun AudioFile.getTag(key: FieldKey) =
            try {
                tag?.getAll(key)?.toSet()?.joinToString("\n")
            } catch (_: KeyNotFoundException) {
                null
            }

        val audioHeader = audioFile.audioHeader
        var track = Track(
            path = path,
            encodingType = audioHeader.encodingType,
            bitRate = audioHeader.bitRateAsNumber,
            sampleRate = audioHeader.sampleRateAsNumber,
            format = audioHeader.format,
            channels = audioHeader.channels,
            isVariableBitRate = audioHeader.isVariableBitRate,
            trackLength = audioHeader.preciseTrackLength,
            bitsPerSample = audioHeader.bitsPerSample,
            isLossless = audioHeader.isLossless,
            noOfSamples = audioHeader.noOfSamples,
            acoustidFingerprint = audioFile.getTag(ACOUSTID_FINGERPRINT),
            acoustidId = audioFile.getTag(ACOUSTID_ID),
            album = audioFile.getTag(ALBUM),
            albumArtist = audioFile.getTag(ALBUM_ARTIST),
            albumArtistSort = audioFile.getTag(ALBUM_ARTIST_SORT),
            albumArtists = audioFile.getTag(ALBUM_ARTISTS),
            albumArtistsSort = audioFile.getTag(ALBUM_ARTISTS_SORT),
            albumSort = audioFile.getTag(ALBUM_SORT),
            albumYear = audioFile.getTag(ALBUM_YEAR),
            amazonId = audioFile.getTag(AMAZON_ID),
            arranger = audioFile.getTag(ARRANGER),
            arrangerSort = audioFile.getTag(ARRANGER_SORT),
            artist = audioFile.getTag(ARTIST),
            artists = audioFile.getTag(ARTISTS),
            artistsSort = audioFile.getTag(ARTISTS_SORT),
            artistSort = audioFile.getTag(ARTIST_SORT),
            barcode = audioFile.getTag(BARCODE),
            bpm = audioFile.getTag(BPM),
            catalogNo = audioFile.getTag(CATALOG_NO),
            classicalCatalog = audioFile.getTag(CLASSICAL_CATALOG),
            classicalNickname = audioFile.getTag(CLASSICAL_NICKNAME),
            choir = audioFile.getTag(CHOIR),
            choirSort = audioFile.getTag(CHOIR_SORT),
            comment = audioFile.getTag(COMMENT),
            composer = audioFile.getTag(COMPOSER),
            composerSort = audioFile.getTag(COMPOSER_SORT),
            conductor = audioFile.getTag(CONDUCTOR),
            conductorSort = audioFile.getTag(CONDUCTOR_SORT),
            copyright = audioFile.getTag(COPYRIGHT),
            country = audioFile.getTag(COUNTRY),
            coverArt = audioFile.getTag(COVER_ART),
            custom1 = audioFile.getTag(CUSTOM1),
            custom2 = audioFile.getTag(CUSTOM2),
            custom3 = audioFile.getTag(CUSTOM3),
            custom4 = audioFile.getTag(CUSTOM4),
            custom5 = audioFile.getTag(CUSTOM5),
            discNo = audioFile.getTag(DISC_NO),
            discSubtitle = audioFile.getTag(DISC_SUBTITLE),
            discTotal = audioFile.getTag(DISC_TOTAL),
            djmixer = audioFile.getTag(DJMIXER),
            djmixerSort = audioFile.getTag(DJMIXER_SORT),
            encoder = audioFile.getTag(ENCODER),
            engineer = audioFile.getTag(ENGINEER),
            engineerSort = audioFile.getTag(ENGINEER_SORT),
            ensemble = audioFile.getTag(ENSEMBLE),
            ensembleSort = audioFile.getTag(ENSEMBLE_SORT),
            fbpm = audioFile.getTag(FBPM),
            genre = audioFile.getTag(GENRE),
            group = audioFile.getTag(GROUP),
            grouping = audioFile.getTag(GROUPING),
            instrument = audioFile.getTag(INSTRUMENT),
            involvedpeople = audioFile.getTag(INVOLVEDPEOPLE),
            ipi = audioFile.getTag(IPI),
            isrc = audioFile.getTag(ISRC),
            iswc = audioFile.getTag(ISWC),
            isClassical = audioFile.getTag(IS_CLASSICAL),
            isGreatestHits = audioFile.getTag(IS_GREATEST_HITS),
            isHd = audioFile.getTag(IS_HD),
            isLive = audioFile.getTag(IS_LIVE),
            isSoundtrack = audioFile.getTag(IS_SOUNDTRACK),
            isCompilation = audioFile.getTag(IS_COMPILATION),
            itunesGrouping = audioFile.getTag(ITUNES_GROUPING),
            jaikozId = audioFile.getTag(JAIKOZ_ID),
            key = audioFile.getTag(KEY),
            language = audioFile.getTag(LANGUAGE),
            lyricist = audioFile.getTag(LYRICIST),
            lyricistSort = audioFile.getTag(LYRICIST_SORT),
            lyrics = audioFile.getTag(LYRICS),
            media = audioFile.getTag(MEDIA),
            mixer = audioFile.getTag(MIXER),
            mixerSort = audioFile.getTag(MIXER_SORT),
            mood = audioFile.getTag(MOOD),
            moodAcoustic = audioFile.getTag(MOOD_ACOUSTIC),
            moodAggressive = audioFile.getTag(MOOD_AGGRESSIVE),
            moodArousal = audioFile.getTag(MOOD_AROUSAL),
            moodDanceability = audioFile.getTag(MOOD_DANCEABILITY),
            moodElectronic = audioFile.getTag(MOOD_ELECTRONIC),
            moodHappy = audioFile.getTag(MOOD_HAPPY),
            moodInstrumental = audioFile.getTag(MOOD_INSTRUMENTAL),
            moodParty = audioFile.getTag(MOOD_PARTY),
            moodRelaxed = audioFile.getTag(MOOD_RELAXED),
            moodSad = audioFile.getTag(MOOD_SAD),
            moodValence = audioFile.getTag(MOOD_VALENCE),
            movement = audioFile.getTag(MOVEMENT),
            movementNo = audioFile.getTag(MOVEMENT_NO),
            movementTotal = audioFile.getTag(MOVEMENT_TOTAL),
            musicbrainzArtistid = audioFile.getTag(MUSICBRAINZ_ARTISTID),
            musicbrainzDiscId = audioFile.getTag(MUSICBRAINZ_DISC_ID),
            musicbrainzOriginalReleaseId = audioFile.getTag(MUSICBRAINZ_ORIGINAL_RELEASE_ID),
            musicbrainzRecordingWork = audioFile.getTag(MUSICBRAINZ_RECORDING_WORK),
            musicbrainzRecordingWorkId = audioFile.getTag(MUSICBRAINZ_RECORDING_WORK_ID),
            musicbrainzReleaseartistid = audioFile.getTag(MUSICBRAINZ_RELEASEARTISTID),
            musicbrainzReleaseid = audioFile.getTag(MUSICBRAINZ_RELEASEID),
            musicbrainzReleaseCountry = audioFile.getTag(MUSICBRAINZ_RELEASE_COUNTRY),
            musicbrainzReleaseGroupId = audioFile.getTag(MUSICBRAINZ_RELEASE_GROUP_ID),
            musicbrainzReleaseStatus = audioFile.getTag(MUSICBRAINZ_RELEASE_STATUS),
            musicbrainzReleaseTrackId = audioFile.getTag(MUSICBRAINZ_RELEASE_TRACK_ID),
            musicbrainzReleaseType = audioFile.getTag(MUSICBRAINZ_RELEASE_TYPE),
            musicbrainzTrackId = audioFile.getTag(MUSICBRAINZ_TRACK_ID),
            musicbrainzWork = audioFile.getTag(MUSICBRAINZ_WORK),
            musicbrainzWorkId = audioFile.getTag(MUSICBRAINZ_WORK_ID),
            musicbrainzWorkPartLevel1 = audioFile.getTag(MUSICBRAINZ_WORK_PART_LEVEL1),
            musicbrainzWorkPartLevel1Id = audioFile.getTag(MUSICBRAINZ_WORK_PART_LEVEL1_ID),
            musicbrainzWorkPartLevel1Type = audioFile.getTag(MUSICBRAINZ_WORK_PART_LEVEL1_TYPE),
            musicbrainzWorkPartLevel2 = audioFile.getTag(MUSICBRAINZ_WORK_PART_LEVEL2),
            musicbrainzWorkPartLevel2Id = audioFile.getTag(MUSICBRAINZ_WORK_PART_LEVEL2_ID),
            musicbrainzWorkPartLevel2Type = audioFile.getTag(MUSICBRAINZ_WORK_PART_LEVEL2_TYPE),
            musicbrainzWorkPartLevel3 = audioFile.getTag(MUSICBRAINZ_WORK_PART_LEVEL3),
            musicbrainzWorkPartLevel3Id = audioFile.getTag(MUSICBRAINZ_WORK_PART_LEVEL3_ID),
            musicbrainzWorkPartLevel3Type = audioFile.getTag(MUSICBRAINZ_WORK_PART_LEVEL3_TYPE),
            musicbrainzWorkPartLevel4 = audioFile.getTag(MUSICBRAINZ_WORK_PART_LEVEL4),
            musicbrainzWorkPartLevel4Id = audioFile.getTag(MUSICBRAINZ_WORK_PART_LEVEL4_ID),
            musicbrainzWorkPartLevel4Type = audioFile.getTag(MUSICBRAINZ_WORK_PART_LEVEL4_TYPE),
            musicbrainzWorkPartLevel5 = audioFile.getTag(MUSICBRAINZ_WORK_PART_LEVEL5),
            musicbrainzWorkPartLevel5Id = audioFile.getTag(MUSICBRAINZ_WORK_PART_LEVEL5_ID),
            musicbrainzWorkPartLevel5Type = audioFile.getTag(MUSICBRAINZ_WORK_PART_LEVEL5_TYPE),
            musicbrainzWorkPartLevel6 = audioFile.getTag(MUSICBRAINZ_WORK_PART_LEVEL6),
            musicbrainzWorkPartLevel6Id = audioFile.getTag(MUSICBRAINZ_WORK_PART_LEVEL6_ID),
            musicbrainzWorkPartLevel6Type = audioFile.getTag(MUSICBRAINZ_WORK_PART_LEVEL6_TYPE),
            musicipId = audioFile.getTag(MUSICIP_ID),
            occasion = audioFile.getTag(OCCASION),
            opus = audioFile.getTag(OPUS),
            orchestra = audioFile.getTag(ORCHESTRA),
            orchestraSort = audioFile.getTag(ORCHESTRA_SORT),
            originalAlbum = audioFile.getTag(ORIGINAL_ALBUM),
            originalreleasedate = audioFile.getTag(ORIGINALRELEASEDATE),
            originalArtist = audioFile.getTag(ORIGINAL_ARTIST),
            originalLyricist = audioFile.getTag(ORIGINAL_LYRICIST),
            originalYear = audioFile.getTag(ORIGINAL_YEAR),
            overallWork = audioFile.getTag(OVERALL_WORK),
            part = audioFile.getTag(PART),
            partNumber = audioFile.getTag(PART_NUMBER),
            partType = audioFile.getTag(PART_TYPE),
            performer = audioFile.getTag(PERFORMER),
            performerName = audioFile.getTag(PERFORMER_NAME),
            performerNameSort = audioFile.getTag(PERFORMER_NAME_SORT),
            period = audioFile.getTag(PERIOD),
            producer = audioFile.getTag(PRODUCER),
            producerSort = audioFile.getTag(PRODUCER_SORT),
            quality = audioFile.getTag(QUALITY),
            ranking = audioFile.getTag(RANKING),
            rating = audioFile.getTag(RATING),
            recordLabel = audioFile.getTag(RECORD_LABEL),
            recordingdate = audioFile.getTag(RECORDINGDATE),
            recordingstartdate = audioFile.getTag(RECORDINGSTARTDATE),
            recordingenddate = audioFile.getTag(RECORDINGENDDATE),
            recordinglocation = audioFile.getTag(RECORDINGLOCATION),
            remixer = audioFile.getTag(REMIXER),
            roonalbumtag = audioFile.getTag(ROONALBUMTAG),
            roontracktag = audioFile.getTag(ROONTRACKTAG),
            section = audioFile.getTag(SECTION),
            script = audioFile.getTag(SCRIPT),
            singleDiscTrackNo = audioFile.getTag(SINGLE_DISC_TRACK_NO),
            songkongId = audioFile.getTag(SONGKONG_ID),
            subtitle = audioFile.getTag(SUBTITLE),
            tags = audioFile.getTag(TAGS),
            tempo = audioFile.getTag(TEMPO),
            timbre = audioFile.getTag(TIMBRE),
            title = audioFile.getTag(TITLE),
            titleSort = audioFile.getTag(TITLE_SORT),
            titleMovement = audioFile.getTag(TITLE_MOVEMENT),
            tonality = audioFile.getTag(TONALITY),
            track = audioFile.getTag(TRACK),
            trackTotal = audioFile.getTag(TRACK_TOTAL),
            urlDiscogsArtistSite = audioFile.getTag(URL_DISCOGS_ARTIST_SITE),
            urlDiscogsReleaseSite = audioFile.getTag(URL_DISCOGS_RELEASE_SITE),
            urlLyricsSite = audioFile.getTag(URL_LYRICS_SITE),
            urlOfficialArtistSite = audioFile.getTag(URL_OFFICIAL_ARTIST_SITE),
            urlOfficialReleaseSite = audioFile.getTag(URL_OFFICIAL_RELEASE_SITE),
            urlWikipediaArtistSite = audioFile.getTag(URL_WIKIPEDIA_ARTIST_SITE),
            urlWikipediaReleaseSite = audioFile.getTag(URL_WIKIPEDIA_RELEASE_SITE),
            work = audioFile.getTag(WORK),
            workType = audioFile.getTag(WORK_TYPE),
            year = audioFile.getTag(YEAR),
            version = audioFile.getTag(VERSION)
        )

        if (track.title == null) {
            logger.warn { "File '$path' has no title tag, using filename as title" }
            val file = File(requireNotNull(track.path) { "Track.path is null" })
            track = track.copy(title = file.nameWithoutExtension)
        }

        val artworks = audioFile.tag?.artworkList?.filterNotNull() ?: emptyList()

        return Pair(track, artworks)
    }


}