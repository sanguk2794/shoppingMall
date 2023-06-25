const screenId = "login_screen_lock";

(function() {
    $('#loginFormSubmit').on('click', function(e) {
        e.preventDefault();
        lockScreen(screenId);

        let form = $(this).parents('form');
        let url = $(this).attr('href');

        loginFormSubmit(form, url);
    });

    $('.form-control').on('keydown keyup keypress change', function() {
        $(this).parent().children('.error-text').text("").hide();
    });
})();

function loginFormSubmit(form, url) {
    let token = $("meta[name='_csrf']").attr("content");
    form.attr('action', url)
        .append($('<input/>', {type:'hidden', name:'_csrf', value: token}))
        .appendTo(document.body)
        .submit();
}