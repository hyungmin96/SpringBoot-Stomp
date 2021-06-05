var stompClient = null;

$(document).ready(function(){
    connect();
})

window.onbeforeunload = function () {
    stompClient.disconnect();
};

$(function () {
    $( ".chat__content__send" ).keyup(function(event) { if (event.keyCode === 13) sendMessage(); });
    $( "#notification" ).click(function() { sendNotification(); });
});

function showMessage(message) {
    if($('.room__targetId').text() != message.user)
        // 로그인한 사용자가 보낸 채팅
        $(".user__send").append(
            "<span class='chat__message'>" + message.message + "</span>"
        );
    else
        // 상대 사용자가 보낸 채팅
        $(".target__send").append(
            "<span class='nickname'>" + message.user + "</span>" +
            "<span class='chat__message'>" + message.message + "</span>"
        );
}

function connect() {
    var socket = new SockJS('/ws');
    stompClient = Stomp.over(socket);
    stompClient.debug = null;
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
            'sender' : '', 
            'user' : '', 
            'message' : $("#message").val()
            };

    stompClient.send('/app/send/chat', {}, JSON.stringify(data));
}