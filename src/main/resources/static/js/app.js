var stompClient = null;

$(document).ready(function(){
    console.log('chat');
    connect();
})

window.onbeforeunload = function () {
    stompClient.disconnect();
};

$(function () {
    $( "#send" ).click(function() { sendMessage(); });
    $( "#notification" ).click(function() { sendNotification(); });
});

function showMessage(message) {
    $("#greetings").append("<tr><td>" + message.user + ' : ' +  message.message + "</td></tr>");
}

function connect() {
    var socket = new SockJS('/ws');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (){
        stompClient.subscribe('/queue/websocket/' + document.getElementsByClassName('data__roomId')[0].dataset.chatroom, function (message) {
            var value = JSON.parse(message.body);
            showMessage(value);
        });
    });
}

function sendMessage() {
    data = {
            'chatRoomid' : document.getElementsByClassName('data__roomId')[0].dataset.chatroom, 
            'user' : '', 
            'message' : $("#message").val()
            };

    stompClient.send('/app/send/chat', {}, JSON.stringify(data));
}