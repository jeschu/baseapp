<@p.page title="Music">

    <@t.toolbar id="music">
        MUSIC
    </@t.toolbar>
    <@t.table id="music" addrbarPrefix="t-">
        <@t.col field="title" sort=true label="Title"/>
        <@t.col field="album" sort=true label="Album"/>
        <@t.col field="artist" sort=true label="Artist"/>
        <@t.col field="genre" sort=true label="Genre"/>
        <@t.col field="trackLengthString" label="Length" align="right"/>
        <@t.actions editIcon="info-circle-fill" showDelete=false/>
    </@t.table>
</@p.page>