<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../common/header.jsp" %>

<div id="main-content" class="container">

    <div class="testContainer">
        <c:forEach var="chat" items="${chats}">
            <h1>${chat.content}</h1>
        </c:forEach>
    </div>

    <input type="hidden" class="data__roomId" data-chatroom = "${roomid}">
    <input type="hidden" class="data__target" data-target = "${target}">

    <div class="row">
        <div class="col-md-6">
            <form class="form-inline">
                <div class="form-group">
                    <label for="connect">WebSocket connection:</label>
                    <button id="connect" class="btn btn-default" type="submit">Connect</button>
                    <button id="disconnect" class="btn btn-default" type="submit">Disconnect
                    </button>
                </div>
            </form>
        </div>
        <div class="col-md-6">
            <form class="form-inline">
                    <label for="name">What is your name?</label>
                    <input type="text" id="name" class="form-control" placeholder="Your name here...">
                </div>

                <div class="form-group">
                    <label for="name">What is your message?</label>
                    <input type="text" id="message" class="form-control" placeholder="Your message here...">
                </div>
                <button id="send" class="btn btn-default" type="submit">Send</button>

            </form>
        </div>
    </div>
    <div class="row">
        <div class="col-md-12">
            <table id="conversation" class="table table-striped">
                <thead>
                <tr>
                    <th>Greetings</th>
                </tr>
                </thead>
                <tbody id="greetings">
                </tbody>
            </table>
        </div>
    </div>
</div>

</body>