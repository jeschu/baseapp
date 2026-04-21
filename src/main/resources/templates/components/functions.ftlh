<#function grid grid>
    <#if grid != 0>
        <#return "col-${grid?c}"/>
    <#else>
        <#return "col"/>
    </#if>
</#function>

<#function label labelKey label='' values...>
    <#if label?has_content>
        <#return label/>
    <#else>
        <#return "${utils.message(labelKey, values)}"/>
    </#if>
</#function>