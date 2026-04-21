<#import "/templates/components/functions.ftlh" as fun>

<#macro link role='' href='#' type='primary' icon='' label='' labelKey='' class=''>
    <#switch role>
        <#case 'add'>
            <#local type='primary'/>
            <#local icon='plus'/>
            <#local labelKey='common.button.add'/>
            <#break>
        <#case 'back'>
            <#local href="${httpRequest.getHeader('Referer')?has_content?string(httpRequest.getHeader('Referer'), 'javascript:history.back()')}"/>
            <#local type='secondary'/>
            <#local icon='arrow-left'/>
            <#local labelKey='common.button.back'/>
            <#break>
    </#switch>
    <#if type?has_content>
        <#local l_type=type/>
    <#else>
        <#local l_type="primary"/>
    </#if>
    <a href="${href}" role="button" class="btn btn-${l_type} ${class}">
        <#if icon?has_content><i class="bi bi-${icon} me-1"></i></#if>
        ${fun.label(labelKey, label)}
    </a>
</#macro>