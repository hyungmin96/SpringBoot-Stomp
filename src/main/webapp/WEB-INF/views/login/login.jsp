<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../common/header.jsp"%>

<div class="login__wrapper" style="margin-top: 175px; height: 625px;">
    <div class="container">
        <div class="panel-body">
            <div class="mb-3">
                <label for="username">아이디(Email)</label>
                <div class="input-group">
                    <input type="text" class="form-control" id="username" placeholder="" name="user-id" required="required">
                        <div class="invalid-feedback" style="width: 100%;">Your username is required.</div>
                    </div>
                </div>

                <div class="mb-3">
                    <label for="username">비밀번호</label>
                    <div class="input-group">
                        <input type="password" class="form-control" id="pw" placeholder="" name="user-id" required="required">
                            <div class="invalid-feedback" style="width: 100%;">Your username is required.</div>
                        </div>
                    </div>
                </div>

                    <hr/>
                        <button id="login-btn" class="btn btn-primary btn-lg" style="width: 100%">로그인</button>
                    <hr/>

                    <div class="sign__container">
                        <div class="sign__box">
                            <span>아직 회원이 아니신가요?</span>
                            <a href="/user/join">회원가입</a>
                        </div>

                        <div class="sns__login_container">
                          
                        </div>
                    </div>
        </div>


<script>

$(document).ready(function(){

    $('#login-btn').on("click", function(){

        let data = {
            username: $('#username').val(),
            password: $('#pw').val()
        }

        $.ajax({
            url: '/api/login',
            type: 'POST',
            contentType: 'application/x-www-form-urlencoded',
            data: data,
            success: function(result){
                location.href = '/';
            }

        })

    })

})

</script>