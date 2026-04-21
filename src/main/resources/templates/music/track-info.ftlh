<#-- @ftlvariable name="track" type="info.maila.baseapp.domain.music.Track" -->
<#-- @ftlvariable name="trackArtworkMetadataList" type="java.util.List<info.maila.baseapp.domain.music.TrackArtworkMetadata>" -->
<@p.page>
    <#assign style>
        --bs-carousel-indicator-active-bg: #fff;
        --bs-carousel-control-icon-filter: grayscale(100%);
    </#assign>
    <div class="row" style="${style}">
        <div id="artworkCarousel" class="col-3 carousel slide">
            <div class="carousel-indicators">
                <#if trackArtworkMetadataList?size &gt; 1>
                    <#list trackArtworkMetadataList as artwork>
                        <button type="button" data-bs-target="#artworkCarousel"
                                data-bs-slide-to="${artwork.dbOrder?c}"
                                ${((artwork.dbOrder) == 0)?string('class=active aria-current=true', '')}
                                aria-label="Slide ${artwork.dbOrder?c}">
                        </button>
                    </#list>
                </#if>
            </div>
            <div class="carousel-inner">
                <#list trackArtworkMetadataList as artwork>
                    <div class="carousel-item ${((artwork.dbOrder) == 0)?string('active', '')}">
                        <img class="d-block w-100"
                             src="/tracks/${artwork.trackId?c}/artworks/${artwork.dbOrder?c}/img"
                             alt="${artwork.id}/${artwork.dbOrder}"
                        />
                    </div>
                </#list>
                <#if trackArtworkMetadataList?size &gt; 1>
                    <button class="carousel-control-prev" type="button"
                            data-bs-target="#artworkCarousel" data-bs-slide="prev">
                        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                        <span class="visually-hidden">Previous</span>
                    </button>
                    <button class="carousel-control-next" type="button"
                            data-bs-target="#artworkCarousel" data-bs-slide="next">
                        <span class="carousel-control-next-icon" aria-hidden="true"></span>
                        <span class="visually-hidden">Next</span>
                    </button>
                </#if>
            </div>
        </div>
        <div class="col-9">
            <div class="row">
                <div class="fs-1" title="Track">${track.title!'No title'}</div>
                <div class="fs-5" title="Album">${track.album!'No album'} ${track.albumYear!}</div>
                <div class="fs-3" title="Artist">${track.artist!'No artist'}</div>
                <div class="col-2">${track.trackLengthString!''}</div>
                <div class="col-3">Year: ${track.year!'-'}</div>
                <div class="col-4">
                    <#if track.track?has_content || track.trackTotal?has_content>
                        Track: ${track.track!'-'}&nbsp;/&nbsp;${track.trackTotal!'-'}
                    </#if>
                </div>
                <div class="col-3">
                    <#if track.discNo?has_content || track.discTotal?has_content>
                        Disc: ${track.discNo!'-'}&nbsp;/&nbsp;${track.discTotal!'-'}
                    </#if>
                </div>
                <#if track.composer?has_content>
                    <div>Composer: ${track.composer}</div>
                </#if>
                <div>Genre: ${track.genre!'-'}</div>
                <div class="col-5">
                    Format: ${track.format?upper_case!'-'}
                    <#if track.encodingType?has_content> (${track.encodingType})</#if>
                </div>
                <div class="col-4">
                    Channels: ${(track.channelsInt?c)!''}
                    <#if track.channels?has_content>(${track.channels})</#if>
                </div>
                <div class="col-3">${track.bitsPerSample!'-'} bits/sample</div>
                <div class="col-5">Bit rate:
                    <#if track.bitRate?has_content>${track.bitRate}kBit/s<#else>-</#if>
                    <#if track.isVariableBitRate??>(VBR)</#if>
                </div>
                <div class="col-4">Lossless: ${track.isLossless()?string('Yes','No')!'-'}</div>
                <div class="col-3"></div>
                <div class="col-12 text-truncate"
                     style="font-size: 0.8rem !important;">${track.path}</div>
            </div>
            <@b.link href="${referrer?has_content?string(referrer, 'javascript:history.back()')}" type="secondary"
            label="Back" icon="arrow-left" class="mt-3"/>
        </div>
    </div>
</@p.page>