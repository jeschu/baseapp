<@p.page title=version>
    <h1><@i18n "greeting"/></h1>
    <h2>h2. Bootstrap heading</h2>
    <h3>h3. Bootstrap heading</h3>
    <h4>h4. Bootstrap heading</h4>
    <h5>h5. Bootstrap heading</h5>
    <h6>h6. Bootstrap heading</h6>
    <p>
        This is an example paragraph.<br/>
        <a href="#">This is an example link</a>
    </p>
    <div>A table:</div>
    <div class="container">
        <div class="row">
            <div class="col border">R1 / C1</div>
            <div class="col border">R1 / C2</div>
            <div class="col border">R1 / C3</div>
        </div>
        <div class="row">
            <div class="col border">R2 / C1</div>
            <div class="col border">R2 / C2</div>
        </div>
    </div>
    <button class="btn btn-danger" role="button"
            onclick="showMessage('error', 'Header', 'Message')"
    ><i class="bi bi-lightning-fill"></i>
    </button>
    <button class="btn btn-warning" role="button"
            onclick="showMessage('warn', 'Header', 'Message')"
    ><i class="bi bi-exclamation-triangle"></i>
    </button>
    <button class="btn btn-info" role="button"
            onclick="showMessage('info', 'Header', 'Message')"
    ><i class="bi bi-exclamation-circle"></i>
    </button>

</@p.page>