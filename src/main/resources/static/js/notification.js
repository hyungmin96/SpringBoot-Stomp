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
    var socket = new SockJS('/ws');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (){
        stompClient.subscribe('/queue/notification/', function (message) {
            var value = JSON.parse(message.body);
            showMessage(value);
        });
    });
}

function sendNotification() {
    data = {
            'notificationType' : 'chat', 
            'message' : '1:1 대화요청이 왔습니다.'
            };

    stompClient.send('/app/chat/notification', {}, JSON.stringify(data));
}
