const screenId = "register_screen_lock";

(function() {
    $("#registerEntryFormSubmitButton").click(function(e) {
        e.preventDefault();
        lockScreen(screenId);

        let form = $(this).parents('form');
        let url = $(this).attr('href');

        registerEntryFormSubmit(form, url);
    });

    $('.form-control').on('keydown keyup keypress change', function() {
        $(this).parent().children('.error-text').text("").hide();
    });
})();

function registerEntryFormSubmit(form, url) {
    let registerEntryForm = $("#registerEntryForm").serialize();
    $.ajax({
        url: url,
        type: 'POST',
        data: registerEntryForm,
        dataType: 'json',
        success: function (data) {
            let token = $("meta[name='_csrf']").attr("content");
            $('<form/>', {action: data.nextUrl, method: 'post'})
                .append($('<input/>', {type:'hidden', name:'_csrf', value: token}))
                .appendTo(document.body)
                .submit();

            unlockScreen(screenId);
        },
        error: function (data) {
            clearErrorMessage();
            setErrorMessage(data);

            unlockScreen(screenId);
        }
    });
}