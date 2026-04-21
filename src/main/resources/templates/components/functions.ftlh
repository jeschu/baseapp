<#function grid grid>
    <#if grid != 0>
        <#return "col-${grid?c}"/>
    <#else>
        <#return "col"/>
    </#if>
</#function>

<#function label labelKey label=''>
    <#if label?has_content>
        <#return label/>
    <#else>
        <#return "${rc.getMessage(labelKey, '{{${labelKey}}}')}">
    </#if>
</#function>