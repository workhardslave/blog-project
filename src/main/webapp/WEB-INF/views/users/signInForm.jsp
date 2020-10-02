<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<%@ include file="../layout/header.jsp"%>

    <div class="container">
        <form action="#" method="post">
            <div class="form-group">
                <label for="email">이메일</label>
                <input type="text" class="form-control" id="email" name="email" placeholder="이름을 입력하세요">
            </div>
            <div class="form-group">
                <label for="password"> 패스워드 </label>
                <input type="password" class="form-control" id="password" name="password" placeholder="패스워드를 입력하세요">
            </div>
            <div class="form-group form-check">
                <label class="form-check-label">
                    <input class="form-check-input" type="checkbox" name="remember"> Remember me
                </label>
            </div>
            <button type="button" class="btn btn-primary" id="btn-login">로그인</button>
        </form>

    </div>
    <script src="/js/user.js"></script>

<%@ include file="../layout/footer.jsp"%>
