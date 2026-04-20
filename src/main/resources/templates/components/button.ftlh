<#macro link href type='primary' icon='' label='' class="">
    <#if type?has_content>
        <#local l_type=type/>
    <#else>
        <#local l_type="primary"/>
    </#if>
    <a href="${href}" role="button" class="btn btn-${l_type} ${class}">
        <#if icon?has_content><i class="bi bi-${icon} me-1"></i></#if>
        ${label}
    </a>
</#macro>