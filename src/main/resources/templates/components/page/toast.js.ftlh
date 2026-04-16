<script>
    let toastIdCounter = 0;

    function showMessage(level, header, message) {
        const toastId = 'toast-' + ++toastIdCounter;
        const toastContainer = $('.toast-container');
        if (!toastContainer) {
            alert(header + ": " + message);
            return;
        }
        let ariaLive = '';
        let color = '';
        let textColor = '';
        let icon = '';
        let autoHide = '';
        switch (level) {
            case 'error':
                ariaLive = 'assertive'
                color = 'danger';
                textColor = '';
                icon = 'lightning-fill';
                autoHide = 'false';
                break;
            case 'warn':
                ariaLive = 'polite'
                color = 'warning';
                textColor = ' text-black';
                icon = 'exclamation-triangle';
                autoHide = 'true';
                break;
            case 'info':
                ariaLive = 'polite'
                color = 'info';
                textColor = '';
                icon = 'info-circle';
                autoHide = 'true';
                break;
        }
        const toastDiv = document.createElement("div");
        toastDiv.setAttribute("id", toastId);
        toastDiv.setAttribute("class", "toast");
        toastDiv.setAttribute("role", "alert");
        toastDiv.setAttribute("aria-live", ariaLive);
        toastDiv.setAttribute("aria-atomic", "true");
        toastDiv.setAttribute("data-bs-autohide", autoHide);
        const headerDiv = document.createElement("div");
        headerDiv.setAttribute("class", "toast-header bg-" + color + textColor);
        const headerIcon = document.createElement("i");
        headerIcon.setAttribute("class", "bi bi-" + icon + " me-2");
        toastDiv.append(headerDiv);
        headerDiv.append(headerIcon);
        const headerHeader = document.createElement("strong");
        headerHeader.setAttribute("class", "me-auto");
        headerHeader.textContent = header;
        headerDiv.append(headerHeader);
        const headerClose = document.createElement("button");
        headerClose.setAttribute("type", "button");
        headerClose.setAttribute("class", "btn-close");
        headerClose.setAttribute("data-bs-dismiss", toastId);
        headerClose.setAttribute("aria-label", "Close");
        headerClose.setAttribute("onclick", "$('#" + toastId + "').hide()");
        headerDiv.append(headerClose);
        if (message) {
            const messageDiv = document.createElement("div");
            messageDiv.setAttribute("class", "toast-body bg-" + color + "-subtle");
            messageDiv.textContent = message;
            toastDiv.append(messageDiv);
        }
        toastContainer.append(toastDiv);
        new bootstrap.Toast(toastDiv).show();
    }
</script>