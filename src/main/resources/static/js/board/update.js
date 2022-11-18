// 세션스토리지 호출
let bno = sessionStorage.getItem("bno");
// 2. 수정 전의 게시물정보 호출
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
// 3. 수정버튼 클릭시 호출 되는 메소드
function upboard() {

    let data = {
        btitle : document.querySelector('.btitle').value,
        bcontent : document.querySelector('.bcontent').value,
        bfile : document.querySelector('.bfile').value,
        bno : bno
    }

    $.ajax({
        url : "/board/setboard",
        type : "post",
        data : JSON.stringify(data),
        // 객체를 JSON화 하고 @RequestBody에 넘겨준다.
        contentType : 'application/json', // ---> @RequestBody
        success : function(re) {

            if(re == true) {
                alert("글 수정 성공")
                window.location.href="/board/list"
            } else {
                alert("글 수정 실패")
            }

        }
    })
}