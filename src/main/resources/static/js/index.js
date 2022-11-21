getloginMno()
function getloginMno() {
    $.ajax({
        url : "member/getloginMno",
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
            let html = "";
            re.forEach( c =>{
                html += '<button type="button" onclick="btcchange('+c.btcno+')">'+c.btcname+'</button>';
            })
            document.querySelector('.categorybox').innerHTML = html;
        }
    })
}

let cno = 1;
// 카테고리 번호 변경
function btcchange(btcno) {
    cno = btcno
    alert(cno + '번 카테고리 변경')
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
            alert(re)
        }
    })
}
