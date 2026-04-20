<@p.page title="Music">

    <@t.toolbar id="music">
        MUSIC
    </@t.toolbar>
    <@t.table id="music" addrbarPrefix="t-">
        <@t.col field="id" label="ID"/>
        <@t.col field="title" sort=true label="Title"/>
        <@t.col field="album" label="Album"/>
        <@t.col field="artist" label="Artist"/>
        <@t.actions editIcon="info-circle-fill" showDelete=false/>
    </@t.table>
</@p.page>