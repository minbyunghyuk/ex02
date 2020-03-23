/**
 * 모듈패턴 메서드를 가지는 객체를 구성 javaScript의즉시실행함수는 () 안에 함수를 선언하고 바깥쪽에서 실행 모듈패턴은 즉시실행하는
 * 함수내부에서 필요한 메서드를 구성해서 객체를구성
 * 
 */
console.log("Reply Module........");

var replyService = (function() {

	// 1.add
	// 외부에서는 replyService.add(객체,콜백)을 전달하는 형태로 호출
	// Ajax호출은 은닉되어있음 post방식으로 ㄱㄱ
	// json,ut8방식으로 전송
	// 파라미터는 callback과 error 함수를 받음
	// ajax 호출이 성공하고 callback값으로 적절한함수가 존재한다면 해당함수호출 결과반영
	function add(reply, callback, error) {
		console.log("reply 생성");
		// ajax호출
		$.ajax({
			type : 'post',
			url : '/replies/new',
			data : JSON.stringify(reply),
			contentType : "application/json; charset=utf-8",
			success : function(result, status, xhr) {
				if (callback) {
					callback(result);
				}
			},
			error : function(xhr, status, er) {
				if (error) {
					error(er)
				}
			}
		})
	}

	// 2.getlist
	// jquery의 getjson추가
	// getlist함수는 param이라는 객체를 통해서 필요한 파라미터를 전달받아서 JSON목록을 호출한다.
	// url호출시 확장자를 .json으로 구함
	// 0323 수정 페이처리 함수변경 댓글리스트+ 댓글갯수 를가져오게 변경 
	function getList(param, callback, error) {
	    var bno = param.bno;
        var page = param.page || 1;
        $.getJSON("/replies/pages/" + bno + "/" + page + ".json",
            function (data) {
                if (callback)
                    // callback(data); // 댓글 목록만 가져오는 경우
                    callback(data.replyCnt, data.list); // 댓글 숫자와 목록(페이징)
            }).fail(function (xhr, status, err) {
            if(error) error();
        });
	}

	// 3.remove
	function remove(rno, callback, error) {
		$.ajax({
			type : 'delete',
			url : '/replies/' + rno,
			success : function(deleteResult, status, xhr) {
				if (callback) {
					callback(deleteResult);
				}
			},
			error : function(xhr, status, er) {
				if (error) {
					error(er)
				}
			}
		});
	}
	// 4.update
	function update(reply, callback, error) {
		$.ajax({
			type : 'put',
			url : '/replies/' + reply.rno,
			data : JSON.stringify(reply),
			contentType : "application/json; charset=utf-8",
			success : function(deleteResult, status, xhr) {
				if (callback) {
					callback(deleteResult);
				}
			},
			error : function(xhr, status, er) {
				if (error) {
					error(er)
				}
			}

		});
	}
	// 5. get 특정번호만조회
	function get(rno, callback, error) {
		$.get("/replies/" + rno + ".json", function(result) {
			if (callback) {
				callback(result);
			}
		}).fail(function(xhr, status, err) {
			if (error) {
				error();
			}

		});
	}
	// XML,JSON형태로 데이터를 받을 때는 순수하게 숫자로 표현되는 시간값이 나오게되있음
	// 잘짜포맷은 화면에서 표시
	function displayTime(timeValue) {
		var oneday = 86400000;
		// 현재날짜시간
		var today = new Date();
		// 현재시간과 get.jsp를 조회한시간의 차이
		var gap = today.getTime() - timeValue;
		// 조회한시간의 Datetime 새로생성
		var dateObj = new Date(timeValue);
		// msecond 하루차이 1000*60*60*24 86400000‬ms는 24시간차이
		//하루이상차이가안나면  댓글기록시간 리턴 
		if (gap < oneday) {

			var hour = dateObj.getHours();
			var min = dateObj.getMinutes();
			var sec = dateObj.getSeconds();

			return [ (hour > 9 ? '' : '0') + hour, ':', (min > 9 ? '' : '0') + min,
					':', (sec > 9 ? '' : 0) + sec ].join('');
			
		//	하루이상차이나면 날짜를 리턴 
		} else {
			var yy = dateObj.getFullYear();
			var mm = dateObj.getMonth() + 1;
			var dd = dateObj.getDate();
			return [ yy, '/', (mm > 9 ? '' : '0') + mm, '/',
					(dd > 9 ? '' : '0') + dd ].join('');
		}

	}
	
	
	
	return {
		add : add,
		getList : getList,
		remove : remove,
		update : update,
		get : get,
		displayTime:displayTime
	};
	// return {name:"AAA"};
})();