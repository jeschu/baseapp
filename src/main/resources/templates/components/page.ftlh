<#include "theme-switch/html.ftlh"/>
<#macro page title=''>
    <!DOCTYPE html>
    <html lang="de">
    <head>
        <#if title?has_content>
            <title>${title}</title>
        </#if>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css"
              rel="stylesheet"
              integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB"
              crossorigin="anonymous">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.13.1/font/bootstrap-icons.min.css"
              rel="stylesheet">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-table@1.27.1/dist/bootstrap-table.min.css"
              rel="stylesheet">
    </head>
    <body>
    <div class="toast-container p-3 bottom-0 start-50 translate-middle-x"></div>
    <main>
        <h1 class="visually-hidden">${title!'Document root'}</h1>
        <nav class="navbar navbar-expand-lg bg-body-tertiary border-bottom mb-3">
            <div class="container-fluid">
                <#--<#include 'sidemenu/toggle.ftlh'/>-->
                <a class="navbar-brand" href="/">Home
                </a>
                <a class="navbar-brand" href="/animals">Animals</a>
                <#include 'theme-switch/html.ftlh'/>
            </div>
        </nav>
        <#--<#include 'sidemenu/sidemenu.ftlh'/>-->
        <div class="container">
            <#nested/>
        </div>
    </main>
    <#include 'theme-switch/js.ftlh'/>
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap-table@1.27.1/dist/bootstrap-table.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap-table@1.27.1/dist/extensions/addrbar/bootstrap-table-addrbar.min.js"></script>
    <#include 'page-js/js.ftlh'/>
    </body>
    </html>
</#macro>