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
        <#if labelKey?has_content>
            <#return "${utils.message(labelKey, values)}"/>
        <#else>
            <#return ""/>
        </#if>
    </#if>
</#function>