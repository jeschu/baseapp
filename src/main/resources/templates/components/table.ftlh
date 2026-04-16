<#macro table
id
idField='id'
url=''
dataField='content'
pagination=true
pageNumber=1
pageSize=5
pageList='5,10,50,all'
showColumns=true
showColumnsToggleAll=true
paginationPreText=''
paginationNextText=''
toolbar=false
>
    <#assign l_url="${url?empty_to_null!('/api'+rc.getRequestUri())}"/>
    <table id="${id}"
           data-id-field="${idField}"
           data-toggle="table"
           data-data-field="${dataField}"
           data-url="${l_url}"
           data-pagination="${pagination?cn}"
           data-page-list="${pageList}"
           data-page-size="${pageSize}"
           data-page-number="${pageNumber}"
           data-show-columns="${showColumns?cn}"
           data-show-columns-toggle-all="${showColumnsToggleAll?cn}"
            <#if paginationPreText?has_content>
                data-pagination-pre-text="${paginationPreText}"
            </#if>
            <#if paginationNextText?has_content>
                data-pagination-next-text="${paginationNextText!''}"
            </#if>
            <#if toolbar>
                data-toolbar="#${id}-toolbar"
            </#if>
    >
        <thead>
        <tr>
            <#nested/>
        </tr>
        </thead>
    </table>

</#macro>

<#macro col field label='' sort=false>
    <#assign l_label="${label?empty_to_null!field}"/>
    <th data-field="${field}" data-sortable="${sort?cn}">${l_label}</th>
</#macro>

<#macro actions idField='id' showEdit=true showDelete=true>
    <th data-formatter="actionsFormatter"></th>
    <script>
        <#--noinspection JSUnusedLocalSymbols-->
        function actionsFormatter(value, row, index) {
            const id = row["${idField}"];
            const editUrl = '${rc.getRequestUri()}/' + id;
            return [
                <#if showEdit>
                '<a class="me-1" role="button" href="' + editUrl + '" title="Edit"><i class="bi bi-pencil"></i></a>',
                </#if>
                <#if showEdit>
                '<a class="me-1" role="button" href="#" onclick="ajaxSend(\'DELETE\', \'' + editUrl + '\')" title="Delete"><i class="bi bi-trash"></i></a>',
                </#if>
            ].join('')
        }
    </script>
</#macro>

<#macro toolbar id>
    <div id="${id}-toolbar">
        <#nested/>
    </div>
</#macro>