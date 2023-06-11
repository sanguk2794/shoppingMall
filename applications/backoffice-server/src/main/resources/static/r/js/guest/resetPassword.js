const screenId = "remind_screen_lock";

(function() {
    $("#resetPasswordEntryFormSubmitButton").click(function(e) {
        e.preventDefault();
        lockScreen(screenId);

        let form = $(this).parents('form');
        let url = $(this).attr('href');

        resetPasswordEntryFormSubmit(form, url);
    });

    $('.form-control').on('keydown keyup keypress change', function() {
        $(this).parent().children('.error-text').text("").hide();
    });
})();

function resetPasswordEntryFormSubmit(form, url) {
    let resetPasswordEntryForm = $("#resetPasswordEntryForm").serialize();
    $.ajax({
        url: url,
        type: 'POST',
        data: resetPasswordEntryForm,
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