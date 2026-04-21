<#include "page/theme-switch.ftlh"/>

<#macro page title=''>
    <!DOCTYPE html>
    <html lang="de">
    <head>
        <#if title?has_content>
            <title>${title}</title>
        </#if>
        <meta charset="utf-8"/>
        <meta name="viewport" content="width=device-width, initial-scale=1"/>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css"
              rel="stylesheet"
              integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB"
              crossorigin="anonymous"/>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.13.1/font/bootstrap-icons.min.css"
              rel="stylesheet"/>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-table@1.27.1/dist/bootstrap-table.min.css"
              rel="stylesheet"/>
        <link href="https://cdn.jsdelivr.net/gh/lipis/flag-icons@7.3.2/css/flag-icons.min.css"
              rel="stylesheet"/>
        <link href="/styles.css" rel="stylesheet"/>
    </head>
    <body>
    <div class="toast-container p-3 bottom-0 start-50 translate-middle-x"></div>
    <main>
        <h1 class="visually-hidden">${title!'Document root'}</h1>
        <#include 'page/navbar.ftlh'> <#--<#include 'page/sidemenu.ftlh'/>-->
        <div class="container">
            <#nested/>
        </div>
    </main>
    <#include 'page/page.js.ftlh'/>
    </body>
    </html>
</#macro>