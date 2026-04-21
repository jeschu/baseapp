<#import "/templates/components/functions.ftlh" as fun>

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
    <#assign l_url = "${url?empty_to_null!('/api' + rc.getRequestUri())}"/>
    <#assign dataAjax = utils.uniqueJavaScriptName('dataAjax')/>
    <table id="${id}"
           data-id-field="${idField}"
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
            let visibleFields = $("#${id}").bootstrapTable('getVisibleColumns').map(it => it.field).join(',');
            ajaxConfig.data = {
                offset: ajaxConfig.data.offset,
                limit: ajaxConfig.data.limit,
                sort: ajaxConfig.data.sort,
                order: ajaxConfig.data.order,
                search: ajaxConfig.data.search,
                visibleFields
            };
            $.ajax(ajaxConfig);
        };
        <#-- lazy init bootstrap table -->
        window.onload = () => {
            $("#${id}").bootstrapTable({
                formatSearch: () => {
                    return "${fun.label('common.table.search.label')}"
                },
                formatShowingRows: (pageFrom, pageTo, totalRows) => {
                    return "${utils.message('common.table.showing-rows.label')}"
                        .replace('$pageFrom$', pageFrom)
                        .replace('$pageTo$', pageTo)
                        .replace('$totalRows$', totalRows)
                },
                formatRecordsPerPage: (pageNumber) => {
                    return pageNumber + ' ' + "${utils.message('common.table.records-per-page.label')}"
                },
            });
        };
    </script>
</#macro>

<#macro col
field
label=''
labelKey=''
sort=false
switchable=true
searchable=true
align=''
>
    <th
            data-field="${field}"
            data-sortable="${sort?cn}"
            data-switchable="${switchable?cn}"
            data-searchable="${searchable?cn}"
            data-align="${align?has_content?string(align,'left')}"
    >
        ${(label?has_content || labelKey?has_content)?string(fun.label(labelKey, label), field)}
    </th>
</#macro>

<#macro actions idField='id' showEdit=true showDelete=true editIcon='pencil' deleteIcon='trash'>
    <th data-formatter="actionsFormatter" data-switchable="false" data-field="[_actions_]"></th>
    <script>
        function actionsFormatter(value, row, index) {
            const id = row["${idField}"];
            const editUrl = '${rc.getRequestUri()}/' + id;
            return [
                <#if showEdit>
                '<a class="me-1" role="button" href="' + editUrl + '" title="Edit"><i class="bi bi-${editIcon}"></i></a>',
                </#if>
                <#if showDelete>
                '<a class="me-1" role="button" href="#" onclick="ajaxSend(\'DELETE\', \'' + editUrl + '\')" title="Delete"><i class="bi bi-${deleteIcon}"></i></a>',
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