let index = {
    init : function () {
        $("#btn-save").on("click", ()=> {
            this.save();
        });
    },

    save : function () {
        let data = {
            title: $("#title").val(),
            content: $("#content").val()
        };

        console.log(data);
        $.ajax({
            type: 'POST',
            url: '/api/boards',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function () {
            alert("작성이 완료되었습니다!");
            location.href="/";
        }).fail(function (response) {
            console.log(response);
            markingErrorField(response);
        });
    }
}

let markingErrorField = function (response) {
    // error 배열을 저장 
    const errorFields = response.responseJSON.errors;

    if(!errorFields){
        alert(response.response.message);
        return;
    }

    let $field, error;
    // error 배열에 있는 값들을 하나씩 호출하여 해당 필드명을 ID로 가진 Dom Element를 찾아 defaultMessage를 붙여서 출력
    for(let i=0, length = errorFields.length; i<length;i++){
        error = errorFields[i];
        $field = $('#'+error['field']); // ex) $("#email")
        if($field && $field.length > 0){
            $field.siblings('.error-message').remove(); // 해당 코드를 통해 한번만 출력
            $field.after('<span class="error-message text-muted taxt-small text-danger"' +
                'style="color: red !important;">' + error.defaultMessage + '</span>'); // 입력 창 밑에 경고 메세지 출력
        }
    }
};

index.init();