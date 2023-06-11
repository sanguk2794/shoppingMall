(function() {
    let token = $("meta[name='_csrf']").attr("content") || null;
    let header = $("meta[name='_csrf_header']").attr("content") || null;
    if (token != null && header != null) {
        $(document).ajaxSend(function(e, xhr, options) {
            xhr.setRequestHeader(header, token);
            xhr.withCredentials = true;
        });
    }
});

function lockScreen(id) {
    let domObj = document.createElement('div');
    domObj.id = id;
    domObj.style.height = '100%';
    domObj.style.left = '0px';
    domObj.style.position = 'fixed';
    domObj.style.top = '0px';
    domObj.style.width = '100%';
    domObj.style.zIndex = '9999';
    domObj.style.opacity = '0';

    let domObjParent = document.getElementsByTagName("body").item(0);
    domObjParent.appendChild(domObj);
}

function unlockScreen(id) {
    let domObj = document.getElementById(id);
    let domObjParent = domObj.parentNode;
    domObjParent.removeChild(domObj);
}

function clearErrorMessage() {
    $('error-text').text("");
    $('error-text').hide();
}

function setErrorMessage(data) {
    $.each($.parseJSON(data.responseText), function (key, value) {
        let code = value.code;
        let message = value.message;

        $('#' + code + '-error').text(message);
        $('#' + code + '-error').show();
        $('#' + code).addClass("-error");
    });
}