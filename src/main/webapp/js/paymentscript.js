
$(document).ready(function () {
    $.ajax({
        type:"GET",
        url:"./account",
        success: function (data) {
            var div = document.querySelector('div [class="row pt-3"]');
            $('.pt-3').after("<h3> "+
                "            Вы выбрали ряд " + data.row +  " место " + data.place + ", Сумма : 500 рублей." +
                "    </h3>");
        }
    })
});
/**
 * функция добавления аккаунта
 * @returns {boolean}
 */
function account() {
    var result = false;
    var name = $("#username").val();
    var phone = $("#phone").val();
    if (!(testName(name) + testPhone(phone))) {
        $.ajax({
            type: "POST",
            url: "./account",
            data: JSON.stringify({id: "0", name: name, tel: phone}),
            success: function (data) {
                if (data.row != undefined) {
                    alert("Ряд=" + data.row + " Место=" + data.place + " не удалось купить билет");
                }
                window.location.href = "./index.html";
            }
        })
    }
}

/**
 * регулярка для имени
 * @param name
 * @returns {boolean}
 */
function testName(name) {
    var regExp = /^([А-ЯA-Z]|[А-ЯA-Z][\x27а-яa-z]{1,}|[А-ЯA-Z][\x27а-яa-z]{1,}\-([А-ЯA-Z][\x27а-яa-z]{1,}|(оглы)|(кызы)))\040[А-ЯA-Z][\x27а-яa-z]{1,}(\040[А-ЯA-Z][\x27а-яa-z]{1,})?$/;
    if (regExp.test(name)) {
        return false;
    } else {
        alert($("#username").attr("title"));
        return true;
    }
}

/**
 * регулярка для телефона
 * @param phone
 * @returns {boolean}
 */
function testPhone(phone) {
    var regExp = /^(\s*)?(\+)?([- _():=+]?\d[- _():=+]?){10,14}(\s*)?$/;
    if (regExp.test(phone)) {
        return false;
    }
    else {
        alert($("#phone").attr("title"));
        return true;
    }
}
