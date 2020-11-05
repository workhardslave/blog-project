let index = {
    init : function () {
        $("#btn-save").on("click", ()=> { // function(){} 대신 ()=> 쓴 이유 : this를 바인딩 하기 위해서
            this.save();
        });

        $("#btn-update").on("click", ()=> {
            this.update();
        });
    },

    save : function () {
        let data = {
            email: $("#email").val(),
            password: $("#password").val(),
            username: $("#username").val()
        };

        // ajax 호출시, default가 비동기 호출
        // ajax 통신을 이용해서 3개의 데이터를 JSON으로 변경하여, insert 요청
        // ajax가 통신을 성공하고 서버가 JSON을 리턴해주면 자동으로 Object로 변환해줌
        // 따라서 dataType이 JSON인 경우는 생략 가능
        $.ajax({
            type: 'POST',
            url: '/auth/api/signup',
            dataType: 'json', // 요청을 서버로 해서 응답이 왔을 때,기본적으로 문자열 (생긴게 JSON이면) -> js object로 변경
            contentType: 'application/json; charset=utf-8', // body 데이터가 어떤 타입인지(MIME)
            data: JSON.stringify(data) // http body 데이터, js object -> JSON 문자열
        }).done(function () {
            alert("회원가입에 성공하셨습니다!");
            location.href = "/";
        }).fail(function (response) {
            console.log(response);
            markingErrorField(response);
        });
    },

    update : function () {
        let id = $("#id").val();
        let data = {
            email: $("#email").val(),
            password: $("#password").val()
        };

        $.ajax({
            type: 'PUT',
            url: '/api/users/' + id,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function () {
            alert("수정이 완료되었습니다.")
            location.href = "/";
        }).fail(function (response) {
            console.log(response);
            markingErrorField(response);
        })
    }
};

index.init();