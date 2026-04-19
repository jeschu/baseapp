<#import "../components/page.ftlh" as p>
<#import "../components/table.ftlh" as t>
<#import "../components/button.ftlh" as b>
<@p.page title="Music">

    <@t.toolbar id="music">
        MUSIC
    </@t.toolbar>
    <table id="music"
           data-id-field="id"
           data-toggle="table"
           data-data-field="content"
           data-url="/api/tracks"
           data-pagination="true"
           data-page-list="5,10,50,all"
           data-page-size="5"
           data-page-number="1"
           data-show-columns="true"
           data-show-columns-toggle-all="true"
           <#--data-pagination-pre-text=""-->
           <#--data-pagination-next-text=""-->
           data-search="false"
           data-searchable="true"
           data-side-pagination="server"
           data-toolbar="#music-toolbar"
           data-show-extended-pagination="false"
    >
        <thead>
        <tr>
            <th data-field="id" data-sortable="false">ID</th>
            <th data-field="title" data-sortable="true">Title</th>
            <th data-field="album" data-sortable="true">Album</th>
            <th data-field="artist" data-sortable="true">Artist</th>
            <th data-formatter="actionsFormatter"></th>
            <script>
                function actionsFormatter(value, row, index) {
                    const id = row["id"];
                    const editUrl = '${rc.getRequestUri()}/' + id;
                    return [
                        '<a class="me-1" role="button" href="' + editUrl + '" title="Edit"><i class="bi bi-pencil"></i></a>',
                    ]
                }
            </script>
        </tr>
        </thead>
    </table>
<#--
<@t.table id="music" url="/api/tracks" toolbar=false>
    <@t.col field="id" sort=false label="ID"/>
    <@t.col field="title" sort=true label="Title"/>
    <@t.col field="album" sort=true label="Album"/>
    <@t.col field="artist" sort=true label="Artist"/>
</@t.table>
-->
</@p.page>