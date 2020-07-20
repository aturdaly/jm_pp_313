function getAllUsers() {
    $.ajax({
        type: "GET",
        url: "admin/api/users/",
        dataType: 'json',
        success: [function (allUsers) {
            $.each(allUsers, function (i, userDto) {
                let userRow = '<tr>' +
                    '<td>' + userDto.id + '</td>' +
                    '<td>' + userDto.name + '</td>' +
                    '<td>' + userDto.age + '</td>' +
                    '<td>' + userDto.roles + '</td>' +
                    '<td> <button type="button" ' +
                    'class="btn btn-info" ' +
                    'data-id="' + userDto.id + '" ' +
                    'data-name="' + userDto.name + '" ' +
                    'data-pwd="' + userDto.password + '" ' +
                    'data-age="' + userDto.age + '" ' +
                    'data-toggle="modal" ' +
                    'data-target="#updateModal" > Edit </button> </td>' +
                    '<td> <button type="button" ' +
                    'class="btn btn-danger" ' +
                    'data-id="' + userDto.id + '" ' +
                    'data-name="' + userDto.name + '" ' +
                    'data-pwd="' + userDto.password + '" ' +
                    'data-age="' + userDto.age + '" ' +
                    'data-roles="' + userDto.roles + '" ' +
                    'data-toggle="modal" ' +
                    'data-target="#deleteModal" > Delete </button> </td>' +
                    '</tr>';
                $('#allUserTable tbody').append(userRow);
            })
        }]
    });
}
function getAllRoles() {
    $.ajax({
        type: "GET",
        url: "admin/api/roles/",
        dataType: 'json',
        success: [function (allRoles) {
            $.each(allRoles, function (i, roleDto) {
                let roleRow = '<option value="' + roleDto.role + '">' + roleDto.role + '</option>';
                $('#roles').append(roleRow);
            })
        }]
    });
}

$(document).ready(function () {
    getAllUsers();
});

$('#allUsers-tab').on('shown.bs.tab', function (e) {
    $('#allUserTable tbody tr').remove();
    getAllUsers();
});

$('#newUser-tab').on('shown.bs.tab', function (e) {
    $('#roles option').remove();
    getAllRoles();
});

$('#addUserForm').submit(function (e) {
    e.preventDefault();
    let name = $('#name').val();
    let password = $('#password').val();
    let age = $('#age').val();
    let roles = $('#roles').val();
    $.ajax({
        url: "admin/api/users/",
        type: "POST",
        contentType: "application/json",
        dataType: 'json',
        data: JSON.stringify({
            'name': name, 'password': password, 'age': age, 'roles': roles
        }),
        complete: [function (){
            $('#addUserForm')[0].reset();
            $('#allUserTable tbody tr').remove();
            $('#allUsers-tab').removeClass('active');
            $('.nav-tabs li:first-child a').tab('show')
        }]
    });
});

$('#deleteModal').on('show.bs.modal', function (event) {
    let button = $(event.relatedTarget);
    $('.modal-body #DelId').val(button.data('id'));
    $('.modal-body #DelName').val(button.data('name'));
    $('.modal-body #DelAge').val(button.data('age'));
    $('.modal-body #DelRoles').val(button.data('roles'));
});
$('#DelBtn').on('click', function (e) {
    let id = $('#DelId').val();
    $.ajax({
        type: "DELETE",
        url: "/admin/api/users/" + id,
        contentType: "application/json",
        success: [function () {
            $('#allUserTable tbody tr').remove();
            $('#allUsers-tab').removeClass('active');
            $('.nav-tabs li:first-child a').tab('show')
        }]
    });
});

$('#updateModal').on('show.bs.modal', function (event) {
    $('#UpdRoles option').remove();
    let button = $(event.relatedTarget);
    $('.modal-body #UpdId').val(button.data('id'));
    $('.modal-body #UpdName').val(button.data('name'));
    $('.modal-body #UpdPsw').val(button.data('pwd'));
    $('.modal-body #UpdAge').val(button.data('age'));
    $.ajax({
        type: "GET",
        url: "admin/api/roles/",
        dataType: 'json',
        success: [function (allRoles) {
            $.each(allRoles, function (i, roleDto) {
                let roleRow = '<option value="' + roleDto.role + '">' + roleDto.role + '</option>';
                $('#UpdRoles').append(roleRow);
            })
        }]
    });
});
$('#UpdBtn').on('click', function (e) {
    let id = $('#UpdId').val();
    let name = $('#UpdName').val();
    let password = $('#UpdPsw').val();
    let age = $('#UpdAge').val();
    let roles = $('#UpdRoles').val();
    $.ajax({
        type: "PUT",
        url: "/admin/api/users/" + id,
        contentType: "application/json",
        dataType: 'json',
        data: JSON.stringify({
            'id': id, 'name': name, 'password': password, 'age': age, 'roles': roles
        }),
        complete: [function () {
            $('#allUserTable tbody tr').remove();
            $('#allUsers-tab').removeClass('active');
            $('.nav-tabs li:first-child a').tab('show')
        }]
    });
});
