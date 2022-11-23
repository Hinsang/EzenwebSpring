function getMapping1() {
    $.ajax({
        url : "/api/vi/get-api/hello",
        success : function(re) { alert(re); }
    })
}

function getMapping2() {
    $.ajax({
        url : "/api/vi/get-api/name",
        success : function(re) { alert(re); }
    })
}

function getMapping3() {
    $.ajax({
        url : "/api/vi/get-api/variable/{variable}",
        success : function(re) { alert(re); }
    })
}

function getMapping4() {
    $.ajax({
        url : "/api/vi/get-api/variable2/{variable}",
        success : function(re) { alert(re); }
    })
}

function getMapping5() {
    $.ajax({
        url : "/api/vi/get-api/variable3",
        data : { "variable" : "인상" },
        success : function(re) { alert(re); }
    })
}

function getMapping6() {
    $.ajax({
        url : "/api/vi/get-api/request1",
        data : { "name" : "인상", "email" : "insang@naver.com", "organization" : "이젠" },
        success : function(re) { alert(re); }
    })
}

function getMapping7() {
    $.ajax({
        url : "/api/vi/get-api/request2",
        success : function(re) { alert(re); }
    })
}

function getMapping8() {
    $.ajax({
        url : "/api/vi/get-api/request3",
        success : function(re) { alert(re); }
    })
}

function postMapping1() {
    $.ajax({
        url : "/api/v1/post-api/domain",
        type: "POST",
        success : function(re) {
            alert(re);
        }
    })
}

function postMapping2() {
    let member = {
        name : "유재석",
        email : "qweqwe@qweqwe",
        organization : "qweqweqwe"
    }

    $.ajax({
        url : "/api/v1/post-api/member",
        type : "POST",
        data : JSON.stringify(member),
        contentType : "application/json", // 전송타입 : application/json
        success : function(re) {
            alert(re);
        }
    })
}

function postMapping3() {
    let member = {
        name : "유재석",
        email : "qweqwe@qweqwe",
        organization : "qweqweqwe"
    }

    $.ajax({
        url : "/api/v1/post-api/member2",
        type : "POST",
        data : JSON.stringify(member),
        contentType : "application/json", // 전송타입 : application/json
        success : function(re) {
            alert(re);
        }
    })
}

function putMapping1() {

    let member = {
            name : "유재석",
            email : "qweqwe@qweqwe",
            organization : "qweqweqwe"
        }

    $.ajax({
        url : "/api/v1/put-api/member",
        type : "PUT",
        // 객체를 JSON 문자열로 넘겨주기
        data : JSON.stringify(member),
        contentType : "application/json",
        success : function(re) {
            alert(re);
        }
    })
}

function putMapping2() {

    let member = {
            name : "유재석",
            email : "qweqwe@qweqwe",
            organization : "qweqweqwe"
        }

    $.ajax({
        url : "/api/v1/put-api/member1",
        type : "PUT",
        data : JSON.stringify(member),
        contentType : "application/json",
        success : function(re) {
            alert(re);
        }
    })
}

function putMapping3() {

    let member = {
            name : "유재석",
            email : "qweqwe@qweqwe",
            organization : "qweqweqwe"
        }

    $.ajax({
        url : "/api/v1/put-api/member2",
        type : "PUT",
        data : JSON.stringify(member),
        contentType : "application/json",
        success : function(re) {

        }
    })
}

// ----------------------- //

function deleteMapping1() {
    $.ajax({
        url : "/api/v1/delete-api/하하하하하하",
        type : "DELETE",
        success : function(re) {
            alert(re)
        }
    })
}

function deleteMapping2() {
    $.ajax({
        url : "/api/v1/delete-api/request1?variable=하하하하하하2",
        type : "DELETE",
        success : function(re) {
            alert(re)
        }
    })
}
