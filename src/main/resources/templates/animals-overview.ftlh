<#import "components/page.ftlh" as p>
<#import "components/table.ftlh" as t>
<#import "components/button.ftlh" as b>
<@p.page title='Animals'>

    <@t.toolbar id="animals">
        <@b.link href="/animals/new"
        icon="plus"
        label="Add"
        />
    </@t.toolbar>
    <@t.table id="animals" url="/api/animals" toolbar=true>
        <@t.col field="id" sort=true label="ID"/>
        <@t.col field="name" sort=true label="Name"/>
        <@t.col field="type" sort=true label="Type"/>
        <@t.actions/>
    </@t.table>

</@p.page>