getboards()
function getboards() {

    $.ajax({
        url : "/board/boardlist",
        type : "get",
        success : function (re) {
            let data = re
            let html = ''
            for(let i = 0 ; i<data.length ; i++) {

                         html += `
                                    <tr>
                                        <td>${data[i].bno}</td>
                                        <td style="cursor: pointer;" onclick="view(${data[i].bno})">${data[i].btitle}</td>
                                        <td>${data[i].memail}</td>
                                    </tr>
                                `
                    }
            document.querySelector('.list').innerHTML = html
        }
    })

}

// 게시물 조회 페이지 [ 페이지 전환 -> 클릭한 게시물번호 기록(java, 템플릿, js) ]
function view(bno) {
    // 클릭한 게시물번호 저장
    sessionStorage.setItem("bno", bno);
    // 페이지 전환
    window.location.href = "/board/view";
}
