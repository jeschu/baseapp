<#import '../components/page.ftlh' as p>
<#if status?has_content><#assign msg>${status?c} - ${message!''}</#assign></#if>
<@p.page title="${msg}">
    <h1>${msg!'Da ist was schiefgelaufen!'}</h1>
    <table>
        <tbody>
        <tr>
            <th>path:</th>
            <td>${path!'-'}</td>
        </tr>
        <#if exception?has_content>
            <tr>
                <th>exception:</th>
                <td>${(exception?c)!'-'}</td>
            </tr>
        </#if>
        </tbody>
    </table>
    <button class="btn btn-secondary" onclick="history.back();"><span
                class="bi bi-arrow-left"></span>&nbsp;&nbsp;Back
    </button>
</@p.page>