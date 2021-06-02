<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/views/common/header.jsp" %>

<div class="container">

    <button class="createBtn" style="margin: auto 0px;">개설하기</button>

    <hr />

    <div class="roomsContainer">

    </div>

</div>

<script>

    $('.createBtn').on("click", function(){

        $.ajax({
            
            url: '/api/create/chatroom',
            type: 'POST',
            data: 'user=123&target=1234',
            contentType: 'application/x-www-form-urlencoded',
            success: function(response){
                $('.roomsContainer').append('<div onclick="goToUrl(\'' + response.target + '\', \''+ response.roomId +'\')" class="room">' + response.roomId +'</div>');
            }

        })

    })

    function goToUrl(target, roomid){
        location.href = 'http://localhost:8000/api/target=' + target + '/chat/' + roomid;
    }

</script>