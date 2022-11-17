function setboard() {

    let data = {
        btitle : document.querySelector('.btitle').value,
        bcontent : document.querySelector('.bcontent').value,
    }

    $.ajax({
        url : "/board/setboard",
        type : "post",
        data : JSON.stringify(data),
        // 객체를 JSON화 하고 @RequestBody에 넘겨준다.
        contentType : 'application/json', // ---> @RequestBody
        success : function(re) {
            alert(re)
        }
    })
}
