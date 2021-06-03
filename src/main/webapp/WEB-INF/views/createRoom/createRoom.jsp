<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../common/header.jsp"%>

        <sec:authorize access="isAuthenticated()">
            <sec:authentication property="principal" var="principal" />
        </sec:authorize>

<div class="container">
    <h2>채팅할 target username</h2>
    <input type="text" class="form-control" id="target__id">
    <button class="createBtn" style="margin: auto 0px;">개설하기</button>
    <hr />
        <div><h1>[<span class="user__name__info">${principal.username}</span>]님<h1></div>
        <div class="roomsContainer">
            <c:forEach var="room" items="${roomList}">
                <div><a href="/api/chat/${room.chatRoomVo.id}">${room.target.username}님과의 대화방</a></div>
            </c:forEach>
        </div>
</div>

<script>

    $('.createBtn').on("click", function(){

        var user = $('.user__name__info').text();
        var target = $('#target__id').val();

        $.ajax({
            
            url: '/api/create/chatroom',
            type: 'POST',
            data: 'user=' + user + '&target=' + target ,
            contentType: 'application/x-www-form-urlencoded',
            success: function(response){
                // $('.roomsContainer').append('<div onclick="goToUrl(\'' + response.target + '\', \''+ response.roomId +'\')" class="room">' + response.roomId +'</div>');
            }

        })

    })

    function goToUrl(target, roomid){
        location.href = 'http://localhost:8000/api/target=' + target + '/chat/' + roomid;
    }

</script>