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
                <div>
                    <a onclick="popupWindow(${room.target}, ${room.roomId}, 360, 700);" style="cursor: pointer;">
                        ${room.target}님과의 대화방
                    </a>
                </div>
            </c:forEach>
        </div>
</div>

<script src="/js/notification.js"></script>
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
                
            }
        })
    })

    function popupWindow(target, roomId, w, h) {
        var y = (screen.width - w) - 2500;
        var x = (screen.height - h) / 2; 
        var targetWin = window.open('/api/chat/target=' + target +'/room=' + roomId, '문의하기', 'toolbar=no, location=no, directories=no, status=no, menubar=no, scrollbars=no, resizable=no, copyhistory=no, width=' + w + ', height=' + h + ', top=' + x + ', left=' + y);
    }

</script>