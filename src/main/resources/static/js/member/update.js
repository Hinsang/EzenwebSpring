function setupdate() {
    let mpassword = document.querySelector('.mpassword2').value

    $.ajax({
        url : "/member/setupdate",
        type : "put",
        data : {'mpassword' : mpassword},
        success : function(re) {
            window.location.href = "/"
        }
    })

}
