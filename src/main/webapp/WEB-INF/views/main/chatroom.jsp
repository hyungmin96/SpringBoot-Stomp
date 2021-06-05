<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../common/header.jsp" %>

<input type="hidden" class="data__roomId" data-chatroom = "${roomid}">

<div class="chat_wrapper" style="width: 100%; height: 100%; display: flex;">

    <div class="target__text">
        <div class="data__tatgetId">
            <span class="room__targetId">${target}</span>님과 대화
        </div>
    </div>

        <div class="chat__log">
            <ul>
                <div class="message__container">
                    <li class="chat__list">


                    </li>
                </div>
            </ul>
        </div>

    <div class="chat__send">
        <input type="text" id="message" placeholder="내용을 입력해주세요" class="chat__content__send" value="">
    </div>

</div>

<script src="/js/app.js"></script>
<script>

var flag = false;
var page = 0;

$(document).ready(function(){
    loadChatData();
})

$('.chat__log').scroll(function(){
    if($('.chat__log').scrollTop() < 1){
        if(!flag){
            flag = true;
            loadChatData();
        }
    }
});

function loadChatData(){
    
    var display = 20;
    var roomId = $('.data__roomId')[0].dataset.chatroom;

    $.ajax({
        url: '/api/chat/chats/',
        type: 'GET',
        data: {roomId: roomId, display: display, page: page},
        dataType: 'json',
        success: function(response){
            
            let currentScrollTop = $('.chat__log')[0].scrollHeight;

            $.each(response.content, function(key, value){
                if($('.room__targetId').text() != value.member.username)
                // 로그인한 사용자가 보낸 채팅
                    $(".chat__list").prepend(
                        "<div class='user__send'>" + 
                        "<span class='chat__message'>" + value.content + "</span>" + 
                        "</div>"
                    );
                else
                // 상대 사용자가 보낸 채팅
                    $(".chat__list").prepend(
                        "<div class='target__send'>" + 
                            "<span class='nickname'>" + value.member.username + "</span>" +
                            "<span class='chat__message'>" + value.content + "</span>" +
                        "</div>"
                    );
            })

            let atferScrollTop = $('.chat__log')[0].scrollHeight;

            $('.chat__log')[0].scrollTop = atferScrollTop - currentScrollTop;

            page++;
            flag = false;
        }
    
    })
}

</script>