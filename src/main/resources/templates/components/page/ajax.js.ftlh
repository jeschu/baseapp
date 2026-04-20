<script>

    function handleAjaxError(jqXHR, textStatus, errorThrown) {
        console.log("error -> ", jqXHR, textStatus, errorThrown);
        showMessage('error', jqXHR.status + " - " + jqXHR.statusText);
    }

    <#--noinspection JSUnusedLocalSymbols-->

    function handleAjaxSuccess(data, textStatus, jqXHR) {
        if (data.success) {
            if (data.location) {
                window.location.href = data.location;
                return;
            }
            if (data.reload) {
                $("#" + data.reload).bootstrapTable('refresh');
            }
        } else {
            showMessage(data.error.level, data.error.header, data.error.message);
        }
    }

    function ajaxSend(method, url) {
        $.ajax(url, {
            method: method,
            headers: {'accept': 'application/json'},
            dataType: 'json',
            error: handleAjaxError,
            success: handleAjaxSuccess,
        });
    }

    window.tableAjaxOptions = {
        beforeSend(jqXHR, url) {
            console.log("jqXHR", jqXHR);
            console.log("url", url);
        }
    }

</script>