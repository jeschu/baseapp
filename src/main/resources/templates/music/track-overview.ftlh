<@p.page title="Music">

    <@t.toolbar id="music">
        MUSIC
    </@t.toolbar>
    <@t.table id='music' addrbarPrefix="t-">
        <@t.col field='title'  sort=true  labelKey='music.track.overview.table.column.title'               />
        <@t.col field='album'  sort=true  labelKey='music.track.overview.table.column.album'               />
        <@t.col field='artist' sort=true  labelKey='music.track.overview.table.column.artist'              />
        <@t.col field='genre'  sort=true  labelKey='music.track.overview.table.column.genre'               />
        <@t.col field='trackLengthString' labelKey='music.track.overview.table.column.length' align='right'/>
        <@t.actions editIcon='info-circle-fill' showDelete=false/>
    </@t.table>
</@p.page>