// Should support other domains like interpark-dev.com.
//document.domain = 'interpark.com';

var _var_init = function () {
    __window = window;
    __document = document;
    _location = __document.location;
    __history = __window.history;
    _window = $(__window);
    _document = $(__document);
    _html = $('html');
    _body = $('body');
    _head = $('head');
};
_var_init();

var IPK_ENV = {
    protocol: (_location.protocol).replace(':', ''),
    //host : 'm.interpark.com',
    host: _location.hostname,
    button: {
        login: $('#btn_login')
            .on('click', function () {
                IPK_MEMBER['event']['login']();
            }),
        relogin: $('#btn_relogin')
            .on('click', function () {
                location.reload();
            }),
        close: $('#close_window')
            .on('click', function (e) {
                e.preventDefault();
                var scope = $(this);
                if(window.opener) {
                    self.close();
                } else {
                    location.href = scope.attr('href');
                }
            })
    },
    input: {
        id: $('#username')
            .on('keydown', function (e) {
                if((e.keyCode || e.which) === 13) {
                    var username = $(document.getElementById("username")).val();
                    if(jQuery.isEmptyObject(username)) {
                        $("#username").focus();
                    } else {
                        $("#password").focus();
                    }

                }
            }),
        pwd: $('#password')
            .on('keydown', function (e) {
                if((e.keyCode || e.which) === 13) {
                    IPK_MEMBER['event']['login']();
                }
            })
    },
    layer: {
        header: $('#header'),
        main: $('#layer_login'),
        info: $('#layer_info'),
        join_alert: $('#join_alert')
    },
    svc: function () {
        return this.query['fromSVC'] ? this.query['fromSVC'] : '';
    },
    window: function () {
        return this.query['winTarget'] ? this.query['winTarget'] : 'self';
    },
    cur_url: function () {
        return __document.location;
    },
    ret_url: function () {
        return this.query['retUrl'] ? this.query['retUrl'] : encodeURIComponent(this.cur_url());
    },
    is_app: false,
    doc_uri: {
        gate: 'https://m.interpark.com/auth/gate.html',
        login: '/login/form',
        logout: '/logout',
        join: 'https://m.interpark.com/auth/join/interpark.html',
        find_id: 'https://incorp.interpark.com/member/matchid.do?_method=initialPopUp',
        find_pw: 'https://incorp.interpark.com/member/matchpwd.do?_method=initialPopUp',
        site_agree: 'https://m.interpark.com/auth/marketing_agreement.html'
    },
    path: function (type) {
        //return `${this.protocol}://${this.host}${this.doc_uri[type]}?fromSVC=${this.svc()}&reqTp=${this.query['reqTp']}&retUrl=${this.ret_url()}`;
        if(type === "join") {
            try {
                EsLogMinor.sendLog({
                    "tagging": EGS_EVENT_TAGGING,
                    "action": "memberjoin_select_auth",
                    "site": __EGS_DATAOBJ.site,
                    "section_id": "memberjoin_basic"
                });
            } catch (e) {
            }
            var o_form = $('#loginFrm');
            var o_login_tp = o_form.find('#LOGIN_TP').val();
            if(o_login_tp.length !== 4) {
                o_login_tp = "1000";
            }

            var fullUrl = "";

            if(this.doc_uri[type].indexOf("https://") >= 0 || this.doc_uri[type].indexOf("http://") >= 0) {
                fullUrl = this.doc_uri[type];
            } else {
                fullUrl = [this.protocol, '://', this.host, this.doc_uri[type]].join('');
            }

            return [fullUrl,
                '?fromSVC=', this.svc(),
                '&reqTp=', this.query['reqTp'],
                '&chk=www',
                '&loginTp=', o_login_tp,
                '&retUrl=', this.ret_url()].join('');
        } else if(this.doc_uri[type].indexOf("https://") >= 0 || this.doc_uri[type].indexOf("http://") >= 0) {
            return this.doc_uri[type];
        } else {
            return [this.protocol, '://', this.host, this.doc_uri[type],
                '?fromSVC=', this.svc(),
                '&reqTp=', this.query['reqTp'],
                '&retUrl=', this.ret_url()].join('');
        }
    },
    query: {},
    init: function () {
        if($('#isMdShopPc').val() == 'Y') {
            const width = window.innerWidth;
            window.resizeTo(width, 740);
        }

        if($('#loginFrm').find("#fromSVC").val() !== '') {
            this.query["fromSVC"] = $('#loginFrm').find("#fromSVC").val();
        }

        let captchaFrame = $("#oCaptchaFrame");
        let isCaptchaTarget = captchaFrame.attr("data-isc");

        if(isCaptchaTarget === 'true') {
            $('.errorMessage').find('.message').text("안전을 위해 아이디, 비밀번호와 하단의 자동입력 방지문자를 입력해주세요.");
        }
    }
};
IPK_ENV.init();


var IPK_MEMBER = {
    move: function (o) {
        switch (o.action) {
            case 'find_id':
            case 'find_pw':
                try {
                    EsLogMinor.sendLog({
                        "tagging": EGS_EVENT_TAGGING,
                        "action": "login",
                        "site": __EGS_DATAOBJ.site,
                        "section_id": o.action
                    });
                } catch (e) {
                }
                if(o.target === 'popup') {
                    var url = [IPK_ENV['path'](o.action), '&winTarget=', o.target].join('');
                    window.open(url, 'popup' + o.action, ['width=480', ' height=340', ' scrollbars=yes'].join(','));
                } else {
                    window.location = IPK_ENV['path']('login');
                }
                break;
            default :
                if(o.target === 'popup') {

                } else {
                    window.location.href = IPK_ENV['path'](o.action);
                }
                break;
        }
    },
    event: {
        submitted: false,
        login: function () {
            var form = $('#loginFrm');
            var method = form.attr("data-method");
            if(method === "ajax") {
                var $username = $(document.getElementById("username"));
                var usernameVal = $username.val().trim();
                if($username.attr('placeholder') == usernameVal) {
                    usernameVal = "";
                }

                var $password = $(document.getElementById("password"));
                var passwordVal = $password.val();
                if($password.attr('placeholder') == passwordVal) {
                    passwordVal = "";
                }

                if(usernameVal == "") {
                    $(".errorMessage").find('.message').text('아이디를 입력해 주세요.');
                    $username.focus();
                    return;
                } else if(passwordVal == "") {
                    $(".errorMessage").find('.message').text('비밀번호를 입력해 주세요.');
                    $password.focus();
                    return;
                }
                //캡챠 체크
                let isCaptchaTarget = $("#oCaptchaFrame").attr("data-isc");

                if (isCaptchaTarget === 'true') {
                    let captchaText = $("#captcha_text").val().trim();
                    if(captchaText === '') {
                        $(".errorMessage").find('.message').text('안전을 위해 아이디, 비밀번호와 하단의 자동입력 방지문자를 입력해주세요. (로그인 5회이상 실패)');
                        return;
                    } else if(captchaText.length !== 6) {
                        $(".errorMessage").find('.message').text('자동입력방지 문자 6자리를 입력해주세요.');
                        return;
                    }
                }

                return IPK_MEMBER.event.login_ajax();
            } else {
                return true;
            }
        },
        login_ajax: function () {
            if(IPK_MEMBER.event.submitted === true) return;
            let o_form = $('#loginFrm');
            let o_login_tp = o_form.find('#LOGIN_TP').val();
            let o_action = o_form.attr("action");

            try {
                EsLogMinor.sendLog({
                    "tagging": EGS_EVENT_TAGGING,
                    "action": "login",
                    "site": __EGS_DATAOBJ.site,
                    "section_id": "login_basic"
                });
            } catch (e) { }

            if(o_login_tp.length === 4) {
                o_login_tp = o_login_tp + "00";
            } else {
                o_login_tp = "100000";
            }

            IPK_MEMBER.event.submitted = true;

            o_form.ajaxSubmit({
                type: 'post',
                url: o_action,
                async: false,
                timeout: 7000,
                dataType: 'json',
                success: function (res) {
                    $("#snsTp, #snsId").val('');
                    switch (res.result_code) {
                        case '00':  // 로그인 성공
                            location.replace(res.callback_url);
                            break;
                        default:
                            IPK_MEMBER.event.submitted = false;
                            $('.errorMessage').find('.message').html("일시적인 오류가 발생하였습니다.\n잠시 후 다시 시도하시기 바랍니다.");
                    }
                },
                error: function (xhr, ajaxOptions, thrownError) {
                    let errorMessage = $('.errorMessage').find('.message');

                    try {
                        IPK_MEMBER.event.submitted = false;
                        let parseJson = $.parseJSON(xhr.responseText);
                        let resultCode = parseJson.result_code;

                        switch (resultCode) {
                            case '01':  // 캡챠 오류
                                $('.capchaCodeWrap').remove();
                                $('.loginForm').append('<div class="capchaCodeWrap">\n' +
                                    '                <div class="capchaCode">\n' +
                                    '                  <div class="captchaIframe">\n' +
                                    '                    <img id="oCaptchaFrame" data-isc="true" class="captcha_img" src="/captcha-image?' + new Date().getTime() + '" width="100%" height="100%"/>\n' +
                                    '                  </div>\n' +
                                    '                  <div class="btn">\n' +
                                    '                    <button type="button" class="btnRefresh" id="captcha_refresh"><span>새로고침</span></button>\n' +
                                    '                    <button type="button" class="btnSound" id="captcha_sound"><span>음성듣기</span></button>\n' +
                                    '                  </div>\n' +
                                    '                </div>\n' +
                                    '                <label class="capchaLabel">\n' +
                                    '                  <input type="text" class="iInput capcha" name="captcha_text" id="captcha_text" placeholder="자동입력 방지문자" maxlength="6"  style="text-transform: uppercase;">\n' +
                                    '                  <button type="button" id="captcha_del_btn" class="inputBtn btnDel">\n' +
                                    '                    <span class="blind">삭제</span>\n' +
                                    '                  </button>\n' +
                                    '                </label>\n' +
                                    '              </div>');
                                errorMessage.html('자동입력방지 문자를 잘못 입력하셨습니다.');
                                $('#captcha_refresh').captchaRefresh();
                                $('#captcha_sound').captchaSound();
                                $('#captcha_text').captchaKeyDown();
                                $('#captcha_del_btn').captchaTextDelete();
                                $('#captcha_text').focus();
                                break;
                            case '05':  // 해당 ID에 회원 없음
                                errorMessage.html('아이디 또는 비밀번호가 맞지 않아요. 다시 입력해 주세요.');
                                break;
                            case '02':  // 비번 매칭 오류
                                errorMessage.html(`아이디 또는 비밀번호가 맞지 않아요. 다시 입력해 주세요. (로그인 ${parseJson.pwdFailCnt}회 실패)`)
                                break;
                            case '03':  // 비번 미설정 사용자
                                errorMessage.html('아이디 또는 비밀번호를 잘못 입력하셨습니다.<br>SNS 회원이신 경우 연결된 SNS로 로그인해주세요.');
                                break;
                            case '04':  // 비번 5회 초과
                                $('.capchaCodeWrap').remove();
                                $('.loginForm').append('<div class="capchaCodeWrap">\n' +
                                    '                <div class="capchaCode">\n' +
                                    '                  <div class="captchaIframe">\n' +
                                    '                    <img id="oCaptchaFrame" data-isc="true" class="captcha_img" src="/captcha-image?' + new Date().getTime() + '" width="100%" height="100%"/>\n' +
                                    '                  </div>\n' +
                                    '                  <div class="btn">\n' +
                                    '                    <button type="button" class="btnRefresh" id="captcha_refresh"><span>새로고침</span></button>\n' +
                                    '                    <button type="button" class="btnSound" id="captcha_sound"><span>음성듣기</span></button>\n' +
                                    '                  </div>\n' +
                                    '                </div>\n' +
                                    '                <label class="capchaLabel">\n' +
                                    '                  <input type="text" class="iInput capcha" name="captcha_text" id="captcha_text" placeholder="자동입력 방지문자" maxlength="6"  style="text-transform: uppercase;">\n' +
                                    '                  <button type="button" id="captcha_del_btn" class="inputBtn btnDel">\n' +
                                    '                    <span class="blind">삭제</span>\n' +
                                    '                  </button>\n' +
                                    '                </label>\n' +
                                    '              </div>');
                                $('#findIdLayer').addClass('active')
                                errorMessage.html('안전을 위해 아이디, 비밀번호와 하단의 자동입력 방지문자를 입력해주세요. (로그인 5회이상 실패)')
                                $('#captcha_refresh').captchaRefresh();
                                $('#captcha_sound').captchaSound();
                                $('#captcha_text').captchaKeyDown();
                                $('#captcha_del_btn').captchaTextDelete();
                                break;
                            case '06':  // Black list ID 대상.
                                if(parseJson.recertMthd === "05") { //re_cert_target mthd = 05
                                    errorMessage.html('부정거래 모니터링 시스템에 의해 로그인이 제한된 아이디입니다.');
                                } else { //re_cert_target mthd = 02,03 일때
                                    blockIdPopup(parseJson.recertMthd, parseJson.mbrId, parseJson.mbrInfo);
                                }
                                break;
                            case '09':
                                errorMessage.html('로그인 정보 입력 시간이 만료 되었습니다. 새로고침 후 입력 해 주세요.');
                                alert("로그인 정보 입력 시간이 만료 되었습니다. 재로그인 해주세요.");
                                location.reload();
                                break;
                            case '10':
                                errorMessage.html('비정상적인 접속시도가 탐지되어 계정보호를 위해<br>로그인 잠김처리 되었습니다. 자세한 사항은 고객센터로 문의해주세요.');
                                break;
                            case '11':
                                errorMessage.html(`아이디 또는 비밀번호가 맞지 않아요. 다시 입력해 주세요. (로그인 ${parseJson.pwdFailCnt}회 실패)`)
                                $('#findIdLayer').addClass('active')
                                break;
                            case '99':
                            default:
                                errorMessage.html('예상하지 못한 오류가 발생하였습니다.\n잠시 후 다시 시도하시기 바랍니다.');
                        }
                    } catch (e) {
                        errorMessage.html('일시적인 오류가 발생하였습니다.\n잠시 후 다시 시도하시기 바랍니다.');
                    }
                }
            });
        },
        login_cancel: function () {
            if(window.opener) {
                self.close();
            } else {
                window.history.back();
            }
            return false;
        },
        remove_blank: function (o) {
            o.value = o.value.replace(/ /gi, '');
        },
    }
};

$('.ipk_member').on('click', function () {
    var scope = $(this), o_data = JSON.parse(scope.attr('data-set'));

    if(o_data.ret_url) {
        IPK_ENV['query']['retUrl'] = encodeURIComponent(o_data.ret_url);
    }

    if(o_data.callback) {
        IPK_ENV['query']['callback'] = o_data.callback;
    }

    if(o_data.req_tp) {
        IPK_ENV['query']['reqTp'] = o_data.req_tp;
        IPK_ENV['protocol'] = o_data.req_tp;
    }

    if(o_data.action === "join_page") {
        var o_form = $('#loginFrm');
        var o_login_tp = o_form.find('#LOGIN_TP').val();
        if(o_login_tp.length !== 4) {
            o_login_tp = "1000";
        }

        var page = "";
        if (o_data.profile === "LOCAL" ||  o_data.profile === "DEV") {
            page = "https://member-dev.interpark.com/signup?_method=initial";
        } else if (o_data.profile === "STAGE") {
            page = "https://member-stg.interpark.com/signup?_method=initial";
        } else {
            page = "https://member.interpark.com/signup?_method=initial";
        }

        var join_site_tp = o_login_tp.substring(0,2);
        var joinUrl = [
            page,
            "&join_site_tp=",join_site_tp,
            "&reqSite=",IPK_ENV.svc()
        ].join("");
        parent.location.href=joinUrl;
        return;
    }
    IPK_MEMBER['move'](o_data);
});
