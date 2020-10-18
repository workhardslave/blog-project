<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<%@ include file="layout/header.jsp"%>

    <c:forEach var="board" items="${boards.content}">
        <div class="container">
            <div class="card m-2">
                <div class="card-body">
                    <h4 class="card-title">${board.title}</h4>
                    <a href="/boards/detail/${board.id}" class="btn btn-primary">상세보기</a>
                </div>
            </div>
        </div>
    </c:forEach>

    <ul class="pagination justify-content-center">
        <c:choose>
            <c:when test="${boards.first}">
                <li class="page-item disabled"><a class="page-link" href="?page=${boards.number-1}">Previous</a></li>
            </c:when>
            <c:otherwise>
                <li class="page-item"><a class="page-link" href="?page=${boards.number-1}">Previous</a></li>
            </c:otherwise>
        </c:choose>
        <!-- 페이지 블록 나중에 추가하기 -->
        <c:choose>
            <c:when test="${boards.last}">
                <li class="page-item disabled"><a class="page-link" href="?page=${boards.number+1}">Next</a></li>
            </c:when>
            <c:otherwise>
                <li class="page-item"><a class="page-link" href="?page=${boards.number+1}">Next</a></li>
            </c:otherwise>
        </c:choose>
    </ul>

<%@ include file="layout/footer.jsp"%>
