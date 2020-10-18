<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<%@ include file="../layout/header.jsp"%>

<div class="container">
    <button class="btn btn-secondary" onclick="history.back()">돌아가기</button>
    <c:if test="${board.user.id == principal.user.id}">
        <button class="btn btn-warning" id="btn-update">수정</button>
        <button class="btn btn-danger" id="btn-delete">삭제</button>
    </c:if>
    <div style="padding-top: 45px; padding-bottom: 30px">
        글 번호 : <b><span id="bid">${board.id} </span></b><br/>
        작성자 : <b><span>${board.user.email} </span></b>
    </div>
    <div class="form-group">
        <h3>${board.title}</h3>
    </div>
    <hr/>
    <div class="form-group">
        <div>${board.content}</div>
    </div>
    <hr/>
</div>

<script src="/js/board.js"></script>

<%@ include file="../layout/footer.jsp"%>
