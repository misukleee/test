<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
.main_logo{
	text-decoration: none;
    color: #000000;
    font-size: 30px;
}
.main_logo_img{
    width: 36px;
    position: relative;
    top: 6px;
}
</style>



<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">
<link href="https://cdn.jsdelivr.net/npm/remixicon/fonts/remixicon.css" rel="stylesheet">
</head>
<body>
	<div style="width:100%; overflow-x: auto; display: flex; align-items: center;" class="auth-page-content" class="row">
		<div class="container">
		
			<div class="row justify-content-center">
				<div class="col-md-8 col-lg-6 col-xl-6">
					<div class="card mt-4 card-bg-fill">
						<form id="upForm" enctype="multipart/form-data">
							<div class="card-body p-4">
								<div class="text-center mt-2">
											<h1>
									<a class="main_logo" href="/">
									<img class="main_logo_img" src="/resources/img/logo.png">
									Booktique_
									</a>
								</h1>
									<br />
								</div>
								<div class="p-2 mt-4">
										<label for="memId" class="form-label">아이디 <span class="text-danger">*</span><span style="font-size: 10px;">&lt;아이디는 5 ~ 20자 사이로 입력해주세요.&gt;</span></label>
									<div class="mb-3" style="display: flex;">
										<input type="text" class="form-control" id="memId" name="memId" style="width: 83%;margin-right: 6px;"
											placeholder="아이디를 입력하세요" maxlength=20 required>
										<button class="btn btn-primary" id="checkId" >중복확인</button>
									</div>
									<div id="idChkMe"></div>
									<div class="mb-3">
										<label class="form-label" for="password-input">비밀번호</label> <span
											class="text-danger">*</span><span style="font-size: 10px;">&lt;영문(대소문자), 숫자, 특수문자(!@#$%^&*)는 반드시 1개이상 포함해야 합니다.&gt;</span>
										<div class="position-relative auth-pass-inputgroup">
											<input type="password" class="form-control pe-5 password-input"
												placeholder="비밀번호" id="memPw" name="memPw" required>
											<button
												class="btn btn-link position-absolute end-0 top-0 text-decoration-none text-muted password-addon material-shadow-none"
												type="button" id="lookPw">
												<i class="ri-eye-fill align-middle"></i>
											</button>
										</div>
									</div>
									<div class="mb-3">
										<label class="form-label" for="password-input">비밀번호 확인</label>
										<span class="text-danger">*</span>
										<div class="position-relative auth-pass-inputgroup">
											<input type="password" class="form-control pe-5 password-input"
												placeholder="비밀번호 확인" id="memPwChk" aria-describedby="passwordInput"
												pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}" required
												aria-autocomplete="list">
											<button
												class="btn btn-link position-absolute end-0 top-0 text-decoration-none text-muted password-addon material-shadow-none"
												type="button" id="lookPwChk">
												<i class="ri-eye-fill align-middle"></i>
											</button>
										</div>
										<div id="chkPw"></div>
									</div>
									<div class="mb-3">
										<label for="username" class="form-label">이름 <span
												class="text-danger">*</span></label> <input type="text" class="form-control"
											id="memNm" name="memNm" placeholder="이름" required>
									</div>
									<div class="mb-3">
										<label for="JoiningdatInput" class="form-label">생년월일 <span
												class="text-danger">*</span></label>
												 <input type="date" class="form-control"
											id="memBirth" name="memBirth" placeholder="생년월일(ex.19970910)">
									</div>
									<div class="mb-3">
										<label for="JoiningdatInput" class="form-label">성별 <span
												class="text-danger">*</span>
										</label> <select class="form-select" id="gender" name="gender">
											<option value="">선택하세요</option>
											<option value="M">남</option>
											<option value="F">여</option>
										</select>
									</div>
									<div class="mb-3">
										<label for="JoiningdatInput" class="form-label">연락처 <span
												class="text-danger">*</span></label> <input type="text" class="form-control"
											id="memPhone" name="memPhone" placeholder="연락처" maxlength="13" required>
									</div>
									<div class="input-group mb-6">
										<label for="JoiningdatInput" class="form-label">전자주소(이메일)
											<span class="text-danger">*</span>
										</label>
										<div class="input-group mb-6">
											<input type="text" class="form-control mb-3" id="emailId" name="emailId"
												placeholder="아이디" aria-label="Email" required>
											<span class="input-group-text mb-3">@</span>
											 <input type="text"
												class="form-control mb-3" id="dirEmail" name="dirEmail" value="선택하세요"
												readonly /> 
												<select class="form-select mb-3" name="selectEmail" id="selectEmail" data-choice="active">
												<option value="" selected>선택하세요</option>
												<option value="naver.com">naver.com</option>
												<option value="gmail.com">gmail.com</option>
												<option value="direct">직접입력</option>
											</select>
										</div>
									</div>
									<div class="mb-3">
										<input type="button" class="btn btn-primary w-100" value="가입신청" id="btn_submit">
									</div>
								</div>
							</div>
						</form>
						<!-- end card body -->
					</div>
					<!-- end card -->
				</div>
			</div>
			<!-- end row -->
		</div>
		<!-- end container -->
	</div>
</body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script type="text/javascript">

let dupID;


/**
 * 중복체크 버튼으로 이미 있는 아이디 이면 중복된 아이디라고 안내
 */
$("#checkId").click(function(){
	
	let memId = $("#memId").val().trim();
	console.log("입력된 아이디",memId);
	
	if(memId == ''){
		alert("아이디를 입력해주세요.");
		return
	}
	
	$.ajax({
		url : "/checkId",
        contentType: "application/json;charset=utf-8",
        data: memId,
        type: "post",
        dataType: "json",
        beforeSend: function(xhr) {
            xhr.setRequestHeader("${_csrf.headerName}", "${_csrf.token}");
        },
        success: function(result) {
        	console.log("result >> " , result);
        	
        	if(result > 0 ) {
        		$("#idChkMe").html("이미 있는 아이디입니다. 다른 아이디를 입력해주세요. ");
        		$("#idChkMe").css('color', 'red');
        		dupID = 0;
        	}else{
        		$("#idChkMe").html("사용가능한 아이디입니다.")
        		$("#idChkMe").css('color', 'blue');
        		dupID = 1;
        	}
        	
        },
        error: function(xhr, status, error) {
            console.error("요청 실패:", status, error);
            alert("중복 체크 요청에 실패했습니다. 나중에 다시 시도해 주세요.");
        }
	});
	
});


/* 비밀번호 확인 */
$('#memPwChk').focusout('input', function () {
	let memPw = $('#memPw').val();
	let memPwChk = $('#memPwChk').val();

	if (memPwChk.length > 0 && memPw === memPwChk) {
		$("#chkPw").html('비밀번호가 일치합니다.')
		$("#chkPw").css('color', 'blue');
	} else {
		$("#chkPw").html('비밀번호가 일치하지 않습니다.')
		$("#chkPw").css('color', 'red');
	}

	if (memPw.length == 0 || memPwChk.length == 0) {
		$("#chkPw").css('display', 'none');
	} else {
		$("#chkPw").css('display', 'block');
	}
})

	 // 클릭 이벤트에 lookPw 함수 연결
	$('#lookPw').on('click', lookPw);
	$('#lookPwChk').on('click', lookPwChk);

// 비밀번호 보기/숨기기 함수
function lookPw() {
    const memPw = $('#memPw');
    const lookPw = $('#lookPw');

    if (memPw.attr('type') === 'password') {
        memPw.attr('type', 'text');
        lookPw.html('<i class="ri-eye-off-fill align-middle"></i>'); // 텍스트가 표시될 때 아이콘 변경
    } else {
        memPw.attr('type', 'password');
        lookPw.html('<i class="ri-eye-fill align-middle"></i>'); // 텍스트가 숨겨질 때 아이콘 변경
    }
}

function lookPwChk() {
	const memPwChk = $('#memPwChk');
	const lookPwChk = $('#lookPwChk');

	if (memPwChk.attr('type') === 'password') {
		memPwChk.attr('type', 'text');
		lookPwChk.html('<i class="ri-eye-off-fill align-middle"></i>'); // 텍스트가 표시될 때 아이콘 변경
	}

	else {
		memPwChk.attr('type', 'password');
		lookPwChk.html('<i class="ri-eye-fill align-middle"></i>'); // 텍스트가 숨겨질 때 아이콘 변경
	}
}


$("#btn_submit").click(function() {
//     e.preventDefault();
    
    let memId = $('#memId').val();
	const pattern = /^(?=.*\d)(?=.*[a-z])(?=.*[!@#$%^&*]).{8,}$/;

	/*입력 검증*/
	if (memId.trim().length < 5 || memId.trim().length > 20) {
		alert("아이디는 5 ~ 20자 사이로 설정해야 합니다.");
		return;
	}

	if (!dupID) {
		alert("ID 중복 여부를 확인해주세요!");
		return;
	}
	
	if ($('#memPw').val().trim().length < 8 || $('#memPw').val().trim() > 20) {
		alert("설정 할 비밀번호를 입력하세요( 8 ~ 20자)");
			return;
		}

	if (!pattern.test($('#memPw').val().trim())) {
		alert("영문, 숫자, 특수문자(!@#$%^&*) 는 반드시 1개이상 포함해야 합니다.");
		return;
	}
	
	

	
	let memberVO = {
			memId: memId,
			memPw: $('#memPw').val(),
			memNm: $('#memNm').val(),
			memBirth: $('#memBirth').val(),
			gender: $('#gender').val(),
			memPhone: $('#memPhone').val(),
			memEmail: $('#emailId').val() + '@' + $('#dirEmail').val(),
		};
	console.log("memberVO >> " , memberVO);
	
	
	$.ajax({
		url : "/signUpAjax",
        contentType: "application/json;charset=utf-8",
        data: JSON.stringify(memberVO),
        type: "post",
        dataType: "json",
        beforeSend: function(xhr) {
            xhr.setRequestHeader("${_csrf.headerName}", "${_csrf.token}");
        },
        success: function(result) {
        	console.log("result >> " , result);
        	
        	location.href = "/";
        	
        }
		
	});
        	
});


$('#memPhone').on('input', function() {
    let input = $(this).val();
    input = input.replace(/[^0-9]/g, ''); // 숫자 이외의 문자 제거
    
    // 전화번호 형식 적용
    input = input.replace(/^(\d{0,3})(\d{0,4})(\d{0,4})$/g, "$1-$2-$3").replace(/\-{1,2}$/g, "");
    
    $(this).val(input);
});


$('#selectEmail').change(function () {
	$("#selectEmail option:selected").each(function () {
		if ($(this).val() == 'direct') {
			$("#dirEmail").val('');
			$("#dirEmail").attr("readonly", false);
		} else {
			$("#dirEmail").val($(this).text());
			$("#dirEmail").attr("readonly", true);
		}
	})
});

</script>
</html>