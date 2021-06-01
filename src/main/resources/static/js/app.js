var stompClient = null;

function connect() {

    var socket = new SockJS('/ws');
    stompClient = Stomp.over(socket);

    stompClient.connect({}, function (){
        stompClient.subscribe('/sub/greetings', function (message) {
            var value = JSON.parse(message.body);
            showMessage(value.target + ' : ' + value.content);
        });
    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
}

function sendMessage() {
    stompClient.send('/pub/content', {}, 
    JSON.stringify(
        {
            'target': $("#name").val(), 
            'content' : $('#message').val()
        }));
}

function showMessage(message) {
    $("#greetings").append("<tr><td>" + message + "</td></tr>");
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