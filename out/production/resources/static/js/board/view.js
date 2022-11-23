// list.js 에서 클릭된 게시물번호 호출
let bno = sessionStorage.getItem("bno");
// 클릭된 게시물번호의 게시물번호를 호출 하는 메소드
getboard()
function getboard() {
    $.ajax({
        url : "/board/getboard",
        type : "get",
        data : { "bno" : bno },
        success : function(re) {
            console.log(re)
        }
    })
}
// 삭제버튼 클릭시 호출 되는 메소드
function delboard() {
    $.ajax({
        url : "/board/delboard",
        type : "delete",
        data : { "bno" : bno },
        success : function(re) {
            window.location.href="/board/list"
        }
    })
}
