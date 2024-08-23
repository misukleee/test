// searchIdToLogin  : ID를 로그인 input으로 전달
function searchIdToLogin() {
    console.log("searchIdToLogin 함수 호출됨"); // 디버그용 로그
    var selMemId = $('input[name="idlist"]:checked').val();

    // 아이디를 /login 페이지로 전달
    location.replace("/login?username=" + encodeURIComponent(selMemId));
}

// searchPwToLogin  : ID를 로그인 input으로 전달
function searchPwToLogin() {
    console.log("searchPwToLogin 함수 호출됨"); // 디버그용 로그

    var memId = document.getElementById('inputMemId').value;

    // memId 값 확인
    console.log("memId: " + memId);

    if (memId && memId.trim() !== "") {
        location.href = '/login?username=' + encodeURIComponent(memId);
    } else {
        alert('아이디를 확인할 수 없습니다. 다시 시도해 주세요.');
    }
}

// searchIdToPw : ID를 PW찾기 input으로 전달
function searchIdToPw() {
    console.log("searchIdToPw 함수 호출됨"); // 디버그용 로그
    var selMemId = $('input[name="idlist"]:checked').val();

    // 비밀번호 찾기 페이지로 아이디를 전달
    location.replace("/searchPw?username=" + encodeURIComponent(selMemId));
}

;(function($, win, doc) {
    $.extend($.fn, {

        inputValueClear: function(inputSelect) {
            var $btnDel = $(this).find('.btnDel');
            var $input = $(this).find(inputSelect);

            $btnDel.on('click', function() {
                $(this).closest('div').find('input').val('').focus();
            });
        }

    });

    $(document).ready(function() {
        // inputValueClear 함수 호출
        $('.inputForm').inputValueClear('.searchInput');
    });

})(jQuery, window, document);
