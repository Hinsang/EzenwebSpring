getboards()
function getboards() {

    $.ajax({
        url : "/board/getboards",
        type : "get",
        success : function (re) {
            let data = re
            let html = ''
            for(let i = 0 ; i<data.length ; i++) {

                         html += `
                                    <tr>
                                        <td>${data[i].bno}</td>
                                        <td>${data[i].btitle}</td>
                                        <td>${data[i].bcontent}</td>
                                    </tr>
                                `
                    }
            document.querySelector('.list').innerHTML = html
        }
    })

}
