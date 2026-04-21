<#if animal.id?has_content>
    <#assign title="Animal #${animal.id?c} - ${animal.name!'?'}"/>
<#else>
    <#assign title="New animal"/>
</#if>
<@p.page title='${title}'>
    <@f.region grid=6 class="border rounded p-3">
        <h2 class="mb-4">Edit Animal</h2>
        <@f.form id="animal-form" action="/animals/${(animal.id?c)!'new'}"
        model="animal">
            <@f.hidden model='animal' field="id"/>
            <@f.hidden model='animal' field="version"/>

            <@f.text model='animal' field="name" label="Name" grid=0 mandatory=true help="Animal´s name"/>
            <@f.text model='animal' field="type" label="Type" grid=0 readonly=true/>

            <@f.actions/>
        </@f.form>
    </@f.region>
</@p.page>