getloginMno()
function getloginMno() {
    $.ajax({
        url : "/member/getloginMno",
        type : "get",
        success : function (re) {

            alert(re)
            let headerbox = '';
            if( re == "0" ) {
                headerbox +=
                `
                    <a href="/member/signup"><button type="button">회원가입</button></a>
                    <a href="/member/login"><button type="button">로그인</button></a>
                `
            } else {
                headerbox +=
                `
                    <button type="button" onclick="logout()">로그아웃</button>
                    <a href="/member/findpassword"><button type="button">비밀번호찾기</button></a>
                    <a href="/member/update"><button type="button">비밀번호수정</button></a>
                    <a href="/member/delete"><button type="button">회원탈퇴</button></a>
                    <a href="/board/list"><button type="button">게시판</button></a>
                `
            }
            document.querySelector(".headerbox").innerHTML = headerbox;
        }
    })
}
function logout() {
    $.ajax({
        url : "/member/logout",
        success : function (re) {
            console.log(re)
            window.location.href = "/"
        }
    })
}

// 회원목록
list()
function list(){
    $.ajax({
        url : "/member/list",
        type : "get" ,
        success : function(list) {
            let html = '<tr>  <th> 번호 </th> <th> 이메일 </th> <th> 비밀번호 </th></tr>';
            list.forEach( (m) => {
                html +=
                '<tr>  <td> '+m.mno+' </td> <td> '+m.memail+' </td> <td> '+m.mpassword+' </td></tr>';
            })
            document.querySelector(".mtable").innerHTML = html;
        }
    })
}

// 카테고리 폼 생성
function categoryform() {
    document.querySelector('.categoryform').innerHTML = `
        <form>
            카테고리 등록 : <input type="text" class="btcname">
            <button type="button" onclick="setcategory()">추가</button><br>
            작성자 : <input type="text" class="btname">
            내용 : <input type="text" class="btcontent">
            <button type="button" onclick="setboard()">글작성</button><br>
        </form>
    `
}

// 카테고리 등록
function setcategory() {
    alert(1)
    let data = {
            btcname : document.querySelector(".btcname").value
        }

    $.ajax({
        url : "/boardtest/setcategory",
        type : "post",
        contentType : "application/json",
        data : JSON.stringify(data),
        success : function(re) {
            alert(re)
            window.location.reload()
        }
    })
}

// 카테고리 리스트 출력
categorylist()
function categorylist(){
    $.ajax({
        url : "/boardtest/categorylist",
        type : "get",
        success : function(re){
            let html = "<button onclick='btcchange(0)'>전체보기</button>";
            re.forEach( c =>{
                html += '<button type="button" onclick="btcchange('+c.btcno+')">'+c.btcname+'</button>';
            })
            document.querySelector('.categorybox').innerHTML = html;
        }
    })
}

let cno = 0;
// 카테고리 번호 변경
function btcchange(btcno) {
    cno = btcno
    if(btcno == 0) {
        alert('전체보기!!')
    } else {
        alert(cno + '번 카테고리 변경')
    }
    boardlist()
}

// 비회원제 글 생성
function setboard() {

    let data = {
        btname : document.querySelector(".btname").value,
        btcontent : document.querySelector(".btcontent").value,
        btcno : cno
    }
    $.ajax({
        url : "/boardtest/setboard",
        data : JSON.stringify(data),
        type : "post",
        contentType : "application/json",
        success : function(re) {
            if(re == true) {
                alert("글 등록성공!!")
                window.location.reload()
            } else {
                alert("카테고리를 선택해주세요!!")
                window.location.reload()
            }
        }
    })
}

boardlist()
// 카테고리별 글 조회
function boardlist() {
    $.ajax({
        url : "/boardtest/boardlist",
        data : { "btcno" : cno },
        success : function(list) {
            console.log(list)
            let html = "<br>"

            for(let i = 0 ; i<list.length ; i++) {
                let b = list[i]
                html += `
                            <tr>
                                <td>${b.btno}</td>
                                <td>${b.btname}</td>
                                <td>${b.btcontent}&nbsp;</td>
                                <td><button type="button" onclick="updateform(${b.btno})">수정</button></td>
                                <td><button type="button" onclick="setdelete(${b.btno})">삭제</button></td>
                            </tr>
                            <tr class="updateform${b.btno}"></tr>
                        `
            }

            document.querySelector(".btable").innerHTML = html
        }
    })
}
let formOn = false;
// 수정 폼 생성
function updateform(btno) {
    if(formOn == false) {
        document.querySelector(".updateform"+btno+"").innerHTML
        = `
            <td colspan="5" style="padding-bottom: 10px;">
                제목<br>
                <input type="text" id="updatetitle(${btno})" style="width: 100%;"><br>
                내용<input type="text" id="updatecontent(${btno})" style="width: 100%; height: 50px;"><br>
                <button type="button" onclick="setupdate(${btno})">수정완료</button>
            </td>
        `
        formOn = true;
    } else {
        document.querySelector(".updateform"+btno+"").innerHTML
                = ""
        formOn = false;
    }
}


// 글 수정
function setupdate(btno) {

    alert(btno)
    let data = {
        btno : btno,
        btname : document.getElementById("updatetitle("+btno+")").value,
        btcontent : document.getElementById("updatecontent("+btno+")").value
    }

    $.ajax({
        url : "/boardtest/setupdate",
        data : JSON.stringify(data),
        type : "put",
        contentType : "application/json",
        success : function (re) {
            if(re == true) {
                alert("수정성공!!")
                window.location.reload()
            } else {
                alert("수정실패!!")
                window.location.reload()
            }
        }
    })

}

// 글 삭제
function setdelete(btno) {
    $.ajax({
        url : "/boardtest/setdelete",
        data : {"btno" : btno},
        type : "delete",
        success : function (re) {
            if(re == true) {
                alert("삭제성공!!")
                window.location.reload()
            } else {
                alert("삭제실패!!")
                window.location.reload()
            }
        }
    })
}
