<nav class="navbar navbar-expand-lg bg-body-tertiary mb-4">
    <div class="container-fluid">
        <#--<@sidemenu_toggle/>-->
        <a class="navbar-brand" href="/">
            <#--noinspection HtmlUnknownTarget-->
            <img src="/baseapp.png" width="32" height="32" class="navbar-brand" alt="BaseApp"/>
        </a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
                aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <@navitem href="/" label="Home"/>
                <@navitem href="/animals" label="Animals"/>
                <@navitem href="/tracks" label="Music"/>
            </ul>
            <div class="d-flex" role="switch">
                <#include 'theme-switch.ftlh'/>
            </div>
        </div>
    </div>
</nav>

<#macro navitem href label>
    <#local uri = rc.getRequestUri()/>
    <#if href == "/" && uri != "/" >
        <#local active = false/>
    <#else>
        <#local active = uri?starts_with(href)/>
    </#if>
    <#local class = "nav-link"/>
    <#if active>
        <#local class = "${class} active"/>
    </#if>
    <li class="nav-item">
        <a class="${class}" aria-current="${active?c}" href="${href}">${label}</a>
    </li>
</#macro>

<#macro sidemenu_toggle>
    <a class="btn btn-sm btn-outline-secondary" role="button"
            <#--noinspection HtmlUnknownAnchorTarget-->
       href="#sidemenu"
       data-bs-toggle="offcanvas"
       aria-controls="sidemenu">
        <i class="bi bi-list"></i>
    </a>
</#macro>