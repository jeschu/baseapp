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
                <#list trackArtworkMetadataList as artwork>
                    <button type="button" data-bs-target="#artworkCarousel" data-bs-slide-to="${artwork.dbOrder?c}"
                            ${((artwork.dbOrder) == 0)?string('class=active aria-current=true', '')}
                            aria-label="Slide ${artwork.dbOrder?c}">
                    </button>
                </#list>
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
            </div>
        </div>
        <div class="col-9">
            <div class="fs-1" title="Track">${track.title!'No title'}</div>
            <div class="fs-5" title="Album">${track.album!'No album'} ${track.albumYear!}</div>
            <div class="fs-3" title="Artist">${track.artist!'No artist'}</div>
            <#if track.year?has_content>
                <div>${track.year!}</div>
            </#if>
            <#assign showTrack = track.track?has_content || track.trackTotal?has_content/>
            <#assign showDisc = track.discNo?has_content || track.discTotal?has_content/>
            <#if showTrack || showDisc>
                <div>
                    <#if showTrack>Track: ${track.track!'-'} / ${track.trackTotal!'-'}</#if>
                    <#if showTrack && showDisc><span class="ms-2 me-2">|</span></#if>
                    <#if showDisc>Disc: ${track.discNo!'-'} / ${track.discTotal!'-'}</#if>
                </div>
            </#if>
            <#if track.composer?has_content>
                <div>Composer: ${track.composer}</div></#if>
            <div>
                <@b.link href="javascript:history.back()" type="secondary"
                label="Back" icon="arrow-left" class="mt-3"
                />
            </div>
        </div>
    </div>
</@p.page>