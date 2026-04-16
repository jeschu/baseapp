<#macro form id action model method='post'>
    <form id="${id}"
          method="${method}"
          action="${action}"
          class="row"
    >
        <#nested/>
    </form>
</#macro>

<#macro hidden model field id=''>
    <input <#if id?hasContent>id="${id}" </#if>
           type="hidden"
           name="${field}"
           value="${.vars[model][field]!''}"
    />
</#macro>

<#macro text model field id='' label='' help='' grid=0>
    <#local bindingResult=.vars["org.springframework.validation.BindingResult." + model]>
    <#local errorMessage=''>
    <#if bindingResult?hasContent>
        <#local error=bindingResult.getFieldError(field)!>
        <#if error?hasContent>
            <#local errorMessage=error.defaultMessage>
        </#if>
    </#if>
    <#if !(id?hasContent)>
        <#local l_id="_id-text-${model}-${field}">
    <#else>
        <#local l_id="${id}">
    </#if>
    <#if !(label?hasContent)>
        <#local l_label="${field}"/>
    <#else>
        <#local l_label="${label}">
    </#if>
    <#local l_value="${.vars[model][field]!''}"/>
    <#include "includes/l_grid.ftlh"/>
    <div class="mb-3 ${l_grid}">
        <#if errorMessage?hasContent>
            <div class="d-flex justify-content-between align-items-center">
                <label for="${l_id}" class="mb-0">${l_label!''}</label>
                <span class="text-danger ms-2">
                    <i class="bi bi-lightning"></i>
                    ${errorMessage}
                </span>
            </div>
        <#else>
            <label for="${l_id}">${l_label!''}</label>
        </#if>
        <input id="${l_id}" name="${field}" value="${l_value}"
               type="text" class="form-control"
               <#if help?hasContent>aria-describedby="${l_id}-help"</#if>
        />
        <#if help?hasContent>
            <div id="${l_id}-help" class="form-text">${help}</div>
        </#if>
    </div>
</#macro>

<#macro actions showSave=true showBack=true backUrl=''>
    <div class="d-flex justify-content-between">
        <#if showSave>
            <button type="submit" class="btn btn-primary">
                <span class="bi bi-floppy me-1"></span>Save
            </button>
        </#if>
        <#if showBack>
            <#local l_uri=rc.getRequestUri()/>
            <#local l_uri=l_uri?substring(0, l_uri?lastIndexOf('/'))/>
            <a href="${l_uri}" role="button" class="btn btn-secondary">
                <span class="bi bi-arrow-left me-1"></span>Back
            </a>
        </#if>
    </div>
</#macro>

<#macro region grid=0 class="">
    <#include "includes/l_grid.ftlh"/>
    <div class="row">
        <div class="${l_grid} ${class}">
            <#nested/>
        </div>
    </div>
</#macro>