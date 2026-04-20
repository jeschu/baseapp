<#macro table
id
idField='id'
url=''
dataField='rows'
pagination=true
sidePagination='server'
pageNumber=1
pageSize=10
pageList='[10,50,100,1000,all]'
showExtendedPagination=true
showColumns=true
showColumnsToggleAll=true
showColumnsSearch=true
search=true
searchable=true
searchTimeOut=500
paginationPreText=''
paginationNextText=''
toolbar=false
addrbar=true
addrbarPrefix=''
rememberOrder=true
>
    <#assign l_url = "${url?empty_to_null!('/api'+rc.getRequestUri())}"/>
    <#assign dataAjax = 'dataAjax_' + statics['java.util.UUID'].randomUUID()?replace('-', '')/>
    <table id="${id}"
           data-id-field="${idField}"
           data-toggle="table"
           data-data-field="${dataField}"
           data-url="${l_url}"
           data-pagination="${pagination?c}"
           data-side-pagination="${sidePagination}"
           data-page-list="${pageList}"
           data-show-extended-pagination="${showExtendedPagination?c}"
           data-page-size="${pageSize}"
           data-page-number="${pageNumber}"
           data-show-columns="${showColumns?cn}"
           data-show-columns-toggle-all="${showColumnsToggleAll?c}"
           data-show-columns-search="${showColumnsSearch?c}"
           data-search="${search?c}"
           data-searchable="${searchable?c}"
           data-search-time-out="${searchTimeOut}"
            <#if paginationPreText?has_content>
                data-pagination-pre-text="${paginationPreText}"
            </#if>
            <#if paginationNextText?has_content>
                data-pagination-next-text="${paginationNextText!''}"
            </#if>
            <#if toolbar>
                data-toolbar="#${id}-toolbar"
            </#if>
           data-addrbar="${addrbar?c}"
           data-addrbar-prefix="${addrbarPrefix!id}"
           data-ajax="${dataAjax}"
           data-remember-order="${rememberOrder?c}"
    >
        <thead>
        <tr>
            <#nested/>
        </tr>
        </thead>
    </table>

    <script>
        window.${dataAjax} = (ajaxConfig) => {
            let table = $("#${id}");
            let visibleFields = table.bootstrapTable('getVisibleColumns').map(it => it.field).join(',');
            ajaxConfig.data = {
                offset: ajaxConfig.data.offset,
                limit: ajaxConfig.data.limit,
                sort: ajaxConfig.data.sort,
                order: ajaxConfig.data.order,
                search: ajaxConfig.data.search,
                visibleFields
            };
            $.ajax(ajaxConfig);
        }
    </script>
</#macro>

<#macro col
field
label=''
sort=false
switchable=true
searchable=true
>
    <#assign l_label="${label?empty_to_null!field}"/>
    <th
            data-field="${field}"
            data-sortable="${sort?cn}"
            data-switchable="${switchable?cn}"
            data-searchable="${searchable?cn}"
    >
        ${l_label}
    </th>
</#macro>

<#macro actions idField='id' showEdit=true showDelete=true>
    <th data-formatter="actionsFormatter" data-field="[_actions_]"></th>
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