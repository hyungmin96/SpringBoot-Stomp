var stompClient = null;

function connect() {
    var socket = new SockJS('/ws');
    stompClient = Stomp.over(socket);
    // stompClient.debug = null;
    stompClient.connect({}, function (){
        stompClient.subscribe('/topic/chat/' + document.getElementsByClassName('data__roomId')[0].dataset.chatroom, function (message) {
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

    data = {
            'chatRoomid' : document.getElementsByClassName('data__roomId')[0].dataset.chatroom, 
            'user' : '', 
            'message' : $("#message").val()
            };

    stompClient.send('/app/content', {}, JSON.stringify(data));
}

function showMessage(message) {
    console.log(message);
    $("#greetings").append("<tr><td>" + message.user + ' : ' +  message.message + "</td></tr>");
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#connect" ).click(function() { connect(); });
    $( "#disconnect" ).click(function() { disconnect(); });
    $( "#send" ).click(function() { sendMessage(); });
});