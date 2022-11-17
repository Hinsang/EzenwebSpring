function setdelete() {

    let mpassword = document.querySelector(".mpassword").value

    $.ajax({
        url : "/member/setdelete",
        type : "delete",
        data : { "mpassword" : mpassword },
        success : function(re) {
            if(re == 1) {
                window.location.href = "/"
                alert("탈퇴성공!!")
            }
            else {
                alert("탈퇴실패!!")
            }
        }
    })
}
