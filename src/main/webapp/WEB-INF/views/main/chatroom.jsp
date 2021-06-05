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
            <c:forEach var="chat" items="${chats}">
                <div class="message__container">
                    <li class="chat__list">
                    
                        <div class="user__send">
                        </div>

                        <span class="target__send">
                        </span>

                    </li>
                </div>
            </c:forEach>
            </ul>
        </div>

    <div class="chat__send">
        <input type="text" id="message" placeholder="내용을 입력해주세요" class="chat__content__send">
    </div>

</div>

<script src="/js/app.js"></script>
