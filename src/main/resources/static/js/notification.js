var stompClient = null;

$(document).ready(function(){
    console.log('notifi');
    connect();
})

window.onbeforeunload = function () {
    stompClient.disconnect();
};

function showMessage(message) {
    $(".roomsContainer").append("<tr><td>" + message.message + "</td></tr>");
}

function connect() {

    var user = $(('.user__name__info')).text();

    var socket = new SockJS('/ws');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (){
        console.log(user);
        stompClient.subscribe('/queue/notification/' + user, function (message) {
            var value = JSON.parse(message.body);
            showMessage(value);
        });
    });
}

function sendNotification(response) {
    console.log(response);
    data = {
            'resultCreate' : response.createResult,
            'notificationType' : response.notificationType,
            'roomId' : response.roomId,
            'user' : response.user,
            'target' : response.target,
            'message' : response.message
            };

    stompClient.send('/app/chat/notification/' + data.target, {}, JSON.stringify(data));
}
