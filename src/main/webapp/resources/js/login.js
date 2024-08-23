;(function($, win, doc) {
  $.extend($.fn, {
    inputValueClear: function(inputSelect) {
      var $input = $(this).find(inputSelect);
      var $btnDel = $(this).find('.btnDel');

      $btnDel.on('click', function() {
        $input.val('').focus();
      });
    },

    pwTypeToggle: function() {
      var $btnShowToggle = $(this).find('.btnShowToggle');
      var $idInput = $(this).find('input.pw');

      $btnShowToggle.on('click', function() {
        $idInput.prop('type') === 'text' ?
          $idInput.prop('type', 'password') : $idInput.prop('type', 'text');

        $idInput.focus();
      });
    },

    layerClose: function(layerClassName) {
      var $btnOpen = $(this).find('.btnOpen');
      var $btnClose = $(this).find('.btnClose');
      var $layer = $('.' + layerClassName);
      var activeClass = 'active';

      $btnOpen.on('click', function() {
        $layer.addClass(activeClass);
      });

      $btnClose.on('click', function() {
        $layer.removeClass(activeClass);
      });
    },

    captchaKeyDown: function() {
        $(this).on('keydown', function (e) {
            if (e.keyCode === 13) {
              e.preventDefault();
              $('#btn_login').trigger('click');
            }
        });
    },

    captchaRefresh: function() {
      $(this).on('click', function () {
        const captchaFrame = $('#oCaptchaFrame');
        const timestamp = new Date().getTime();
        captchaFrame.attr('src', `/captcha-image?${timestamp}`);
      });
    },

    captchaTextDelete: function() {
      $(this).on('click', function() {
        $('#captcha_text').val('');
      });
    },

    kakaoOpenIdLogin: function(popPosition) {
      $(this).on('click', function() {
        if ($('#postProc').val() === 'IFRAME') {
          const popUrl = ["/openid/init?snsTp=kakao&reqSite=90&saveSess=", $("#saveSess").val()].join("");
          window.open(popUrl, "snsPopup", popPosition);
        } else {
          location.replace(["/openid/init?snsTp=kakao&reqSite=90&saveSess=", $("#saveSess").val()].join(""));
        }
      });
    },

    naverOpenIdLogin: function(popPosition) {
      $(this).on('click', function() {
        if ($('#postProc').val() === 'IFRAME') {
          const popUrl = ["/openid/init?snsTp=naver&reqSite=90&saveSess=", $("#saveSess").val()].join("");
          window.open(popUrl, "snsPopup", popPosition);
        } else {
          location.replace(["/openid/init?snsTp=naver&reqSite=90&saveSess=", $("#saveSess").val()].join(""));
        }
      });
    },

    appleOpenIdLogin: function(popPosition) {
      $(this).on('click', function() {
        if ($('#postProc').val() === 'IFRAME') {
          const popUrl = ["/web/open-id/auth/apple?saveSess=", $("#saveSess").val()].join("");
          window.open(popUrl, "snsPopup", popPosition);
        } else {
          location.replace(["/web/open-id/auth/apple?saveSess=", $("#saveSess").val()].join(""));
        }
      });
    },

    join: function() {
      $(this).on('click', function() {
        const activeProfile = $('#activeProfile').val();
        const joinUrl = activeProfile === 'RELEASE'
            ? 'https://member.interpark.com/signup?join_site_tp=20&join_ipp_id=&smid1=header&smid2=logout&smid3=signup'
            : activeProfile === 'DEV' || activeProfile === 'LOCAL'
                ? 'https://member-dev.interpark.com/signup?join_site_tp=20&join_ipp_id=&smid1=header&smid2=logout&smid3=signup'
                : activeProfile === 'STAGE'
                    ? 'https://member-stg.interpark.com/signup?join_site_tp=20&join_ipp_id=&smid1=header&smid2=logout&smid3=signup'
                    : 'https://member.interpark.com/signup?join_site_tp=20&join_ipp_id=&smid1=header&smid2=logout&smid3=signup';

        if ($('#postProc').val() === 'IFRAME') {
          window.parent.location.replace(joinUrl);
        } else {
          window.location = joinUrl;
        }
      });
    },

    findPwd: function() {
      $(this).on('click', function() {
        const fromSvc = $('#fromSVC').val();
        const sector = fromSvc === 'ticket' ? 50 : fromSvc === 'tour' ? 60 : "";
        const postProc = $('#postProc').val();
        const isMobile = (/iphone|ipad|ipod|android/i.test(navigator.userAgent.toLowerCase()));

        if (postProc === 'IFRAME') {
          window.parent.location.replace(`https://incorp.interpark.com/member/matchpwd.do?_method=findMemberPwd&sector=${sector}`);
        } else if (postProc === 'POPUP') {
          if (window.opener) {
            window.opener.location.replace(`https://incorp.interpark.com/member/matchpwd.do?_method=findMemberPwd&sector=${sector}`);
            if (isMobile) self.close(); else window.close();
          } else {
            window.location = `https://incorp.interpark.com/member/matchpwd.do?_method=findMemberPwd&sector=${sector}`;
          }
        } else {
          window.location = `https://incorp.interpark.com/member/matchpwd.do?_method=findMemberPwd&sector=${sector}`;
        }
      });
    },

    findId: function() {
      $(this).on('click', function() {
        const fromSvc = $('#fromSVC').val();
        const sector = fromSvc === 'ticket' ? 50 : fromSvc === 'tour' ? 60 : "";
        const postProc = $('#postProc').val();
        const isMobile = (/iphone|ipad|ipod|android/i.test(navigator.userAgent.toLowerCase()));

        if (postProc === 'IFRAME') {
          window.parent.location.replace(`https://incorp.interpark.com/member/matchid.do?_method=findMemberId&sector=${sector}`);
        } else if (postProc === 'POPUP') {
          if (window.opener) {
            window.opener.location.replace(`https://incorp.interpark.com/member/matchid.do?_method=findMemberId&sector=${sector}`);
            if (isMobile) self.close(); else window.close();
          } else {
            window.location = `https://incorp.interpark.com/member/matchid.do?_method=findMemberId&sector=${sector}`;
          }
        } else {
          window.location = `https://incorp.interpark.com/member/matchid.do?_method=findMemberId&sector=${sector}`;
        }
      });
    }
  });

  $(doc).ready(function() {

    function setOpenIdLastLogin() {
      let cookieName = '__l_l_oid';
      let cookieData = document.cookie;

      let cookieValue = "";
      let start = cookieData.indexOf(cookieName);

      if (start !== -1) {
        start += cookieName.length + 1;
        let end = cookieData.indexOf(";", start);
        if (end === -1) end = cookieData.length;
        cookieValue = cookieData.substring(start, end);
      }

      if (cookieValue === "kakao") {
        $('#kakao_li').prepend('<em class="lastLogin"><img src="//openimage.interpark.com/UI/login/last_login.png" alt="최근 로그인"></em>');
      } else if (cookieValue === "naver") {
        $('#naver_li').prepend('<em class="lastLogin"><img src="//openimage.interpark.com/UI/login/last_login.png" alt="최근 로그인"></em>');
      } else if (cookieValue === "apple") {
        $('#apple_li').prepend('<em class="lastLogin"><img src="//openimage.interpark.com/UI/login/last_login.png" alt="최근 로그인"></em>');
      }
    }

    function failCheck() {
      const isFail = $('#isFail').val();

      if (isFail) {
        const errorMessage = $('.errorMessage').find('.message');

        switch ($('#failCode').val()) {
          case '05': case '06': case '10': blockIdPopup($('#recertMthd').val(), $('#mbrId').val(), $('#mbrInfo').val()); break;
          case '09': {
            errorMessage.html('로그인 정보 입력 시간이 만료 되었습니다. 새로고침 후 입력 해 주세요.');
            alert("로그인 정보 입력 시간이 만료 되었습니다. 재로그인 해주세요.");
            location.reload();
            break;
          }
          case '99':
          default: errorMessage.html('일시적인 오류가 발생하였습니다.\n잠시 후 다시 시도하시기 바랍니다.'); break;
        }
      }
    }

    function deleteCommonHeader() {
      $('#commonHeaderDom').remove();
    }

    function getPopPosition( _w, _h, _optStr) {
      let w	= _w;
      let h	= _h;

      let popX, popY;
      if ( self.innerWidth&&self.innerHeight ) { // IE
        //IE 외
        popX = window.screenLeft + ((self.innerWidth)-(w/2)) -100;
        popY = window.screenTop + ((self.innerHeight)-(h/2)) - 350;
        if (popY > self.innerHeight || popY < 0) {
          popY = self.innerHeight / 2 - 150;
        }
      } else if (document.body.clientWidth&&document.body.clientHeight) {
        // IE
        popX = window.screenLeft + ((document.body.clientWidth-w)/2);
        popY = window.screenTop - ((document.body.clientHeight-h)/2);
      } else if (document.documentElement.clientWidth && document.documentElement.clientHeight) {
        //IE 6 Strict
        popX = window.screenLeft + ((document.documentElement.clientWidth-w)/2);
        popY = window.screenTop + ((document.documentElement.clientHeight-h)/2);
      }

      let popOption = "left="+popX+", top="+popY+", width="+w+", height="+h;
      let optStr = _optStr;
      if(optStr === undefined) {
        popOption+= ", resizable=no, scrollbars=yes, status=no, toolbar=0,location=0,directories=0,menubar=0;";
      } else {
        popOption+= optStr;
      }

      return popOption;
    }

    $('.inputId').inputValueClear('input.id');
    $('.capchaLabel').inputValueClear('input');
    $('.inputPw').pwTypeToggle();
    $('.tooltip').layerClose('layerTooltip');
    $('.layerContainer').layerClose('layerContainer');

    $('#saveSess').on('click', function(){
      $(this).val($(this).is(':checked')?'Y':'N');
    });

    $('#captcha_refresh').captchaRefresh();
    $('#captcha_text').captchaKeyDown();
    $('#captcha_del_btn').captchaTextDelete();

    $('#openid_kakao').kakaoOpenIdLogin(getPopPosition(600, 630));
    $('#openid_naver').naverOpenIdLogin(getPopPosition(633, 947));
    $('#openid_apple').appleOpenIdLogin(getPopPosition(580, 680));

    $('#join').join();
    $('#findPwd').findPwd();
    $('.findId').findId();

    setOpenIdLastLogin();
    failCheck();
    deleteCommonHeader();
  });
})(jQuery, window, document);