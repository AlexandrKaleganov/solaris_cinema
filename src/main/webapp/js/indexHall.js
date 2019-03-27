/**
 * обновление информаци один раз в секунду
 * если массив с занятыи местами придёт пустой, до зал будет очищен
 */
$(updateHall());
setInterval(updateHall, 1000);

function updateHall() {
    $.ajax({
        type: "GET",
        url: "./hall",
        data: {action: "getListHall"},
        success: function (data) {
            var tr = document.getElementsByTagName("tbody")[0].getElementsByTagName("tr");
            if (data.length > 0) {
                for (var i = 0; i < data.length; i++) {
                    for (j = 0; j < tr.length; j++) {
                        if (data[i].row == tr[j].getElementsByTagName("th")[0].textContent) {
                            var input = tr[j].querySelector('td input[value="' + data[i].place + '"]');
                            input.parentElement.style.backgroundColor = "red";
                        }
                    }
                }
            } else {
                var td = $('td');
                for (i = 0; i < td.length; i++) {
                    if (td[i].style.backgroundColor == "red") {
                        td[i].style.backgroundColor = "";
                    }
                }
            }
        }
    })
}

/**
 * добавление места
 * @returns {boolean}
 */
function add() {
    var input = document.querySelector('td input[name="place"]:checked');
    if (valid(input)) {
        if (input.parentElement.style.backgroundColor == "red") {
            alert(input.parentElement.textContent + " занято");
            return false;
        } else {
            var th = input.parentElement.parentElement.getElementsByTagName("th")[0];
            $.ajax({
                type: "POST",
                url: "./hall",
                data: JSON.stringify({id: "0", row: th.textContent, place: input.value}),
                success: function (data) {
                    window.location.href = "./payment.html";
                }
            })
        }
    } else {
        alert("Выберете место");
    }
}

/**
 * проверка выбрано ли место
 * @param input
 * @returns {boolean}
 */
function valid(input) {
    if (input == null) {
        return false;
    }
    return true;
}

/**
 * очистка зала
 */
function sclear() {
    $.ajax({
        type: "POST",
        url: "./clear"
    });
    updateHall()
}