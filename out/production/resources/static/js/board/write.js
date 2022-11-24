
getloginMno()
function getloginMno() {
    $.ajax({
        url : "/member/getloginMno",
        type : "get",
        success : function (re) {

            alert(re)
            let headerbox = '';
            if( re == "0" ) {
                alert("로그인해주세요")
                window.location.href="/";
            } else {

            }
        }
    })
}

// -------------- 전역변수 -----------------//
let bcno = 2; // 카테고리 번호   // 카테고리 기본값

/*let data = {
        btitle : document.querySelector('.btitle').value ,
        bcontent : document.querySelector('.bcontent').value,
        bfile : document.querySelector('.bfile').value,
        bcno : bcno
    }*/

// 1. 게시물 등록 메소드
function setboard(){
    let boardform = document.querySelector('.boardform')
    let formdata = new FormData(boardform)
    formdata.set("bcno", bcno) // 폼 데이터에 데이터 추가
    $.ajax({ // http 사용하는 jquery 비동기통신 함수
        url : "/board/setboard",
        type : "post",  // Multipart 전송 방법
        data : formdata,
        // Content-Type : multipart/formed-data
        contentType : false,
        processData : false,
        success : function(re) {
            if( re == true ){
                alert('글작성성공');
                location.href="/board/list";
            }
            else{ alert("글작성실패"); }
        }
    })
}
// 2. 게시물 카테고리 등록 메소드
function setbcategory(){
    let data = { bcname : document.querySelector(".bcname").value }
    $.ajax({
        url : "/board/setbcategory" , type : "post",
        async : false,
        data : JSON.stringify(data), contentType : "application/json",
        success : function(re) {
            if( re == true ){ alert('카테고리추가성공'); bcategorylist();}
            else{ alert('카테고리추가실패')}
        }
    })
}
// 3. 모든 카테고리 출력
bcategorylist()
function bcategorylist(){
    $.ajax({
        url : "/board/bcategorylist" ,  type : "get" ,
        success : function(re){
            let html = "";
            re.forEach( c =>{
                console.log( c )
                html += '<button type="button" onclick="bcnochage('+c.bcno+')">'+c.bcname+'</button>';
            })
            document.querySelector('.bcategorybox').innerHTML = html;
        }
    })
}
// 4. 카테고리 버튼을 클릭했을때 선택된 카테고리 번호 대입
function bcnochage( cno ){ bcno = cno; alert( bcno+"의 카테고리 선택");  }









