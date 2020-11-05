let index = {

    init : function () {
        $("#btn-save").on("click", ()=> {
            this.save();
        });

        $("#btn-delete").on("click", ()=> {
            this.delete();
        });

        $("#btn-update").on("click", ()=> {
           this.update();
        });

        $("#btn-reply-save").on("click", ()=> {
            this.saveReply();
        })
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
    },

    delete : function () {
        let id = $("#bid").text();
        console.log(id);

        $.ajax({
            type: 'DELETE',
            url: '/api/boards/' + id,
            dataType: 'json'
        }).done(function () {
            alert("삭제가 완료되었습니다!");
            location.href = "/";
        }).fail(function (response) {
            alert("삭제를 실패했습니다.");
            console.log(response);
        });
    },

    update : function () {
        let id = $("#id").val();
        let data = {
            title : $("#title").val(),
            content : $("#content").val()
        };

        $.ajax({
            type: 'PUT',
            url: '/api/boards/' + id,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function () {
            alert("수정이 완료되었습니다.");
            location.href = "/";
        }).fail(function (response) {
            console.log(response);
            markingErrorField(response);
        })
    },

    saveReply : function () {
        let data = {
            bid: $("#boardId").val(),
            uid: $("#userId").val(),
            content: $("#reply-content").val()
        };

        $.ajax({
            type: 'POST',
            url: '/api/replies',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function () {
            alert("작성이 완료되었습니다.");
            location.href = `/boards/detail/${data.bid}`;
        }).fail(function (response) {
            console.log(response);
            markingErrorField(response);
        })
    },

    deleteReply : function (rid, bid) {
        console.log(rid + " " + bid);

        $.ajax({
            type: 'DELETE',
            url: `/api/replies/${rid}`,
            dataType: 'json'
        }).done(function () {
            alert("삭제가 완료되었습니다!");
            location.href = `/boards/detail/${bid}`;
        }).fail(function (response) {
            alert("삭제를 실패했습니다.");
            console.log(response);
        })

    }
};

index.init();