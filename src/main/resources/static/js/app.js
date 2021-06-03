var stompClient = null;

function connect() {

    var socket = new SockJS('/ws');
    stompClient = Stomp.over(socket);
    // stompClient.debug = null;

    stompClient.connect({}, function (){
        stompClient.subscribe('/sub/greetings', function (message) {
            var value = JSON.parse(message.body);
            showMessage(value);
        });
    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
}

function sendMessage() {

    var chatRoomId = document.getElementsByClassName('data__roomId')[0];
    var chatRoomTarget = document.getElementsByClassName('data__target')[0];
    
    data = {
            'chatRoomid' : chatRoomId.dataset.chatroom, 
            'user' : '123', 
            'message' : $("#message").val()
            };

    stompClient.send('/pub/content', {}, JSON.stringify(data));
}

function showMessage(message) {
    console.log(message);
    $("#greetings").append("<tr><td>" + message.user + ' : ' +  message.message + "</td></tr>");
}

function greeting(){
    stompClient.send('/pub/greeting', {}, JSON.stringify(
        {
            'content' : $("#name").val() + '님이 입장하셨습니다.'
        }))
}


$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#connect" ).click(function() { connect(); });
    $( "#disconnect" ).click(function() { disconnect(); });
    $( "#send" ).click(function() { sendMessage(); });
});