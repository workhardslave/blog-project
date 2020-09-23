let index = {
    init : function () {
        $("#btn-save").on("click", ()=> { // function(){} 대신 ()=> 쓴 이유 : this를 바인딩 하기 위해서
            this.save();
        });
    },

    save : function () {
        let data = {
            username: $("#username").val(),
            password: $("#password").val(),
            email: $("#email").val()
        };

        // 프론트엔드 유효성 체크 코드
        let isValid = true;

        if (!data.username) {
            markingErrorMessage("#username", "이름을 작성해주세요.");
            isValid = false;
        }

        if (!data.password) {
            markingErrorMessage("#password", "패스워드를 작성해주세요.");
            isValid = false;
        }

        if (!data.email) {
            markingErrorMessage("#email", "이메일을 작성해주세요.");
            isValid = false;
        }

        // ajax 호출시, default가 비동기 호출
        // ajax 통신을 이용해서 3개의 데이터를 JSON으로 변경하여, insert 요청
        // ajax가 통신을 성공하고 서버가 JSON을 리턴해주면 자동으로 Object로 변환해줌
        // 따라서 dataType이 JSON인 경우는 생략 가능
        if (isValid) {
            $.ajax({
                // 회원가입 수행 요청
                type: "POST",
                url: "/blog/api/users",
                data: JSON.stringify(data), // http body 데이터, js object -> JSON 문자열
                contentType: "application/json; charset=utf-8", // body 데이터가 어떤 타입인지(MIME)
                dataType: "JSON" // 요청을 서버로 해서 응답이 왔을 때,기본적으로 문자열 (생긴게 JSON이면) -> js object로 변경

            }).done(function (response) {
                console.log(response);
                if(response == 200) {
                    alert("회원가입이 완료되었습니다.");
                    // location.href = "/blog";
                } else {
                    alert("회원가입이 실패했습니다.");
                    console.log(response);
                }
            }).fail(function (error) {
                alert("회원가입이 실패했습니다.");
                alert(JSON.stringify(error));
            });
        }
    }
}

let markingErrorMessage = function (targetElement, message) {
    let $targetElement = $(targetElement); // 위에서 해당 요소의 id를 가져와 저장
    $targetElement.siblings(".error-message").remove(); // 해당 코드를 통해 한번만 출력
    $targetElement.after('<span class="error-message text-muted text-small text-danger" ' +
        'style="color: red !important;">' + message + '</span>'); // 입력 창 밑에 경고 메세지 출력

};

index.init();