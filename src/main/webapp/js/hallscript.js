// $(document).ready((function () {
//     $.ajax({
//         type: "GET",
//         url: "./hall",
//         data: {action: "getListHall"},
//         success: function (data) {
//             var tr = document.getElementsByTagName("tbody")[0].getElementsByTagName("tr");
//             console.log(data);
//             for (var i = 0; i < data.length; i++) {
//                 for (j = 0; j < tr.length; j++) {
//                     if (data[i].row == tr[j].getElementsByTagName("th")[0].textContent) {
//                         console.log(tr[j].getElementsByTagName("th")[0].textContent + " ряд найден");
//                         var input = tr[j].querySelector('td input[value="' + data[i].place + '"]');
//                         input.parentElement.style.backgroundColor = "red";
//                     }
//                 }
//             }
//         }
//     })
// }));
// function add() {
//     var input = document.querySelector('td input[name="place"]:checked');
//     if (input.parentElement.style.backgroundColor == "red") {
//         alert(input.parentElement.textContent + " занято");
//         return false;
//     } else {
//         var th = input.parentElement.parentElement.getElementsByTagName("th")[0];
//         console.log(th.textContent);
//
//         $.ajax({
//             type: "POST",
//             url: "./hall",
//             data: JSON.stringify({id: "0", row: th.textContent, place: input.value}),
//             success: function (data) {
//                 window.location.href = "./payment.html";
//             }
//         })
//         return true;
//     }
// };
//попытка найти все инпуты, нашёл только ячейки, что дальше делать не знаю как обратиться к элеметам
//не могу понять как пометить что они уже заняты?
// $(document).ready(function () {
//     var tbody = document.getElementsByTagName("tbody")[0];
//     var tr = tbody.getElementsByTagName("tr");
//
//     // console.log(document.getElementsByName())
//     for (var i = 0; i < tr.length; i++) {
//         if (tr[i].getElementsByTagName("th")[0].textContent == "1") {
//             console.log(tr[i].getElementsByTagName("th")[0].textContent);
//             var td = tr[i].getElementsByTagName("td");
//             // var  td1 = tr[i].getElementsByClassName("1");
//             // td1.style.backgroundColor = "red";
//             for (j = 0; j < td.length; j++) {
//                 td[j].style.backgroundColor = "red";
//                 console.log(td[j]);   // вот тут вижу нужные ячейки а что с ними делать вот даже значение инпута не получается проверить
//             }
//             console.log(tr[i].getElementsByClassName('td[class="1"]')[0]);
//
//         }
//     }
// });


