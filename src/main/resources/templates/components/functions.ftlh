<#function grid grid>
    <#if grid != 0>
        <#return "col-${grid?c}"/>
    <#else>
        <#return "col"/>
    </#if>
</#function>