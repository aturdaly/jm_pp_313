$(document).ready(function () {
    $.ajax({
        type: "GET",
        url: "user/api/info/",
        dataType: 'json',
        success: [function (userDto) {
            let infoRow = '<tr>' +
                '<td>' + userDto.id + '</td>' +
                '<td>' + userDto.name + '</td>' +
                '<td>' + userDto.age + '</td>' +
                '<td>' + userDto.roles + '</td>' +
                '</tr>';
            $('#userInfoTable tbody').append(infoRow);
        }]
    });
});