<%@ page contentType="text/html;charset=UTF-8" language="java"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="../includes/header.jsp"%>

<!-- /favicon.ico Error 처리 -->
<link rel="shortcut icon" href="#">

<div class="row">

	<div class="col-lg-12">
		<h1 class="page-header">게시글 보기</h1>
	</div>
	<!-- /.col-lg-12 -->
</div>
<!-- /.row -->
<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="panel-heading">Board Read</div>
			<!-- /.panel-heading -->
			<div class="panel-body">
				<div class="form-group">
					<label>번호</label><input class="form-control" name='bno'
						value='<c:out value="${board.bno}"/>' readonly="readonly">
				</div>

				<div class="form-group">
					<label>제목</label><input class="form-control" name='title'
						value='<c:out value="${board.title}"/>' readonly="readonly">
				</div>

				<div class="form-group">
					<label>내용</label>
					<textarea class="form-control" rows="3" name='content'
						readonly="readonly"><c:out value="${board.content}" /></textarea>
				</div>

				<div class="form-group">
					<label>작성자</label> <input class="form-control" name='content'
						value='<c:out value="${board.writer}"/>' readonly="readonly">
				</div>
				<button data-oper='modify' class="btn btn-default">수정</button>

				<button data-oper='list' class="btn btn-info">글목록으로</button>

				<form id='operForm' action="/board/modify" method="get">
					<input type='hidden' id='bno' name='bno'
						value='<c:out value="${board.bno}"/>'> <input
						type='hidden' name='pageNum'
						value='<c:out value="${cri.pageNum}"/>'> <input
						type='hidden' name='amount' value='<c:out value="${cri.amount}"/>'>
					<input type='hidden' name='keyword'
						value='<c:out value="${cri.keyword }"/>'> <input
						type='hidden' name='type' value='<c:out value="${cri.type }"/>'>
				</form>
			</div>
		</div>
	</div>

</div>

<div class="row">
	<div class="col-lg-12">
		<!--  ./panel -->
		<div class="panel panel-default">
			<div class=".panel-heading">
				<!-- 아이콘  -->
				<i class="fa fa-comments fa-fw"></i>Comments
				<button id='addReplyBtn' class='btn btn-primary btn-xs pull-right'>댓글
					쓰기</button>
			</div>
			<!-- ./panel heading -->
			<div class="panel-body">
				<ul class="chat">
					<!-- 여기서부터 댓글출력 -->
					<li class="left clearfix" data-rno="12">
						<div>
							<div class="header">
								<strong class="primary-font">user00</strong>
								<!-- 입력한사용자 -->
								<small class="pull-right text-muted">2020-03-22 16:33</small>
							</div>
							<p>Commnets 내용</p>
						</div>
					</li>
					<!-- 댓글출력 끝 -->
				</ul>
				<!--  end ul -->
			</div>
			<!-- chart panel  -->


		</div>
	</div>
</div>

<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="myModalLabel">Comments</h4>
			</div>
			<div class="modal-body">
			 	<div class="form-group">
			 		<label>Reply</label>
			 		<input class="form-control" name="reply" value="글입력">
				 </div>
				 <div class="form-group">
			 		<label>Replyer</label>
			 		<input class="form-control" name="replyer" value="작성자">
			 	</div>
				 <div class="form-group">
				 	<label>Reply date</label>
				 	<input class="form-control" name="replyDate" value="">
				 </div>
			</div>
			<div class="modal-footer">
				<button id='modalRegisterBtn' type="button" class="btn btn-primary">작성</button>
				<button id='modalModBtn' type="button" class="btn btn-warning">변경</button>
				<button id='modalRemoveBtn' type="button" class="btn btn-danger">삭제</button>
				<button id='modalCloseBtn' type="button" class="btn btn-default">닫기</button>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
<!-- /.modal -->

<script type="text/javascript" src="/resources/js/reply.js"></script>
<!-- reply이벤트 추가  -->

<script type="text/javascript">
	//$(document).ready(function()한페이지내에서 여러번나와도 상관은없다고하는데?
	//아래코드하고섞이기싫음..;;
	$(document)	.ready(function() {

		console.log("js test")
   		//jstl tag인감?
	  	var bnoValue = '<c:out value="${board.bno}"/>';
		//게시판이 열리면 getlist로 가져와서 li태그를 구성한다.
		var replyUL = $(".chat"); //ul chat class를 가져옴

		showList(1); //첫번째 페이지를 가져옴
		function showList(page) {
			replyService.getList({bno : bnoValue,page : page || 1},function(list) {
			var str = "";
			//list가 존재하지않으면
			if (list == null|| list.length == 0) {
				replyUL.html("");
				return;
				}
				for (var i = 0, len = list.length || 0; i < len; i++) {
					str += "<li class='left clearfix' data-rno='" + list[i].rno + "'>";
					str += "    <div><div class='header'><strong class='primary-font'>"	+ list[i].replyer+ "</strong>";
					//str += "    <div><div class='header'><strong class='primary-font'>["+ list[i].rno + "] " + list[i].replyer + "</strong>";
					str += " <small class ='pull-right text-muted'>"+ replyService.displayTime(list[i].replyDate)+ "</small></div>";
					str += "    <p>"+ list[i].reply	+ "</p></div></li>";
					}
					replyUL.html(str);
				}); //end function
			}//end showlist
			
			var modal =$(".modal");
			var modalInputReply= modal.find("input[name='reply']");
			var modalInputReplyer= modal.find("input[name='replyer']");
			var modalInputReplyDate= modal.find("input[name='replyDate']");

			
			var modalModBtn = $("#modalModBtn");
			var modalRemoveBtn = $("#modalRemoveBtn");
			var modalRegisterBtn = $("#modalRegisterBtn");
			var modalCloseBtn =$("modalCloseBtn");
			//add btn누르면 필요없는항목 표시하시않게 작성,작성자만 표시 
			//닫기
			$("#modalCloseBtn").on("click",function(e){
				modal.modal("hide");
			});
			
			//추가
			$("#addReplyBtn").on("click",function(e){
				
				modal.find("input").val("");
				modalInputReplyDate.closest("div").hide();
				modalRegisterBtn.show();
				
				$(".modal").modal("show");
				
			});
			//댓글이 정상적으로 추가되면 경고차을이용해서 성공했다는 사실을알려주고 등록한내용을 다시등록할수없도록 모달창을 닫아준다.
			modalRegisterBtn.on("click",function(e){
			
				var reply ={
					reply: modalInputReply.val(),
					replyer:modalInputReplyer.val(),
					bno:bnoValue
						
				};
			replyService.add(reply, function(result){
				alert(result);
				
				modal.find("input").val("");
				modal.modal("hide");
				//댓글이등록됬으니 list재로드 	
				showList(1);
				});
			});
			//Ajax를 통해서(li)태그가 만들어지면 이후에이벤트를등록해야하기때문에 이벤트위임으로 처리 (delegation)
			//이미존재하는요소에 이벤트를 걸어주고 나중에 이벤트대상을 변경해주는 방식 
			//chat click이벤트를 걸고 실제이벤트대상은 li되도록함
			// 이벤트는 ul에걸었지만 각댓글이 this키워드가되어있음
			$(".chat").on("click","li",function(e){
				var rno=$(this).data("rno");
				console.log(rno);
				replyService.get(rno, function(reply){
					
					modalInputReply.val(reply.reply);
					//일단글쓴이 readonly
					modalInputReplyer.val(reply.replyer).attr("readonly","readonly");
					modalInputReplyDate.val(replyService.displayTime(reply.replyDate)).attr("readonly","readonly");
					modal.data("rno", reply.rno);
					
					modal.find("button[id != 'modalCloseBtn']").hide();
					modalModBtn.show();
					modalRemoveBtn.show();
					
					$(".modal").modal("show");
				});
				
			});
			//댓글수정
			modalModBtn.on("click", function(e){
				//일단 사용자명은 안바뀌게 되어있음 
				var reply={rno:modal.data("rno"), reply:modalInputReply.val()};
				replyService.update(reply,function(result){
				   alert(result);
				   modal.modal("hide");
				   showList(1);
					
				})
				
				
			});
			modalRemoveBtn.on("click", function(e){
			
				var rno= modal.data("rno");
				replyService.remove(rno,function(result){
					alert(result);
					modal.modal("hide");
					showList(1);
				});
				
				
			});


	});
</script>
<script type="text/javascript">
	$(document).ready(function() {

		var operForm = $("#operForm");

		$("button[data-oper='modify']").on("click", function(e) {
			operForm.attr("action", "/board/modify").submit();
		});

		$("button[data-oper='list']").on("click", function(e) {
			operForm.find("#bno").remove();
			operForm.attr("action", "/board/list");
			operForm.submit();
		});

	});
</script>
<script>
//replyservice test code
//reply.js안에있는 add함수 호출 /
/*
replyService.add(
		{reply:"js test",replyer:"tester",bno:bnoValue}
		,
		function(result){
			임시 alert("result :" + result);
		}
);
//getlist test
replyService.getList({bno:bnoValue,page:1},function(list){
	
	for(var i=0, len=list.length||0;i<len; i++){
		console.log(list[i]);
	}
});
//remove test
//remove(rno, callback, error)
//44번쨰 댓글 삭제
//replyService.remove(44,function(count)
replyService.remove(43,function(count){
	console.log(count);
	if(count ==="success"){
		alert("removeOK");
	}
	
},function(err){
	alert('Error');
});

 */

//update test
/*
replyService.update({rno:21,bnoValue,reply:"modify"},
		function(result){
		alert("update");
});
 */

/*
replyService.get(10, function(data) {
console.log(data);
})
 */
</script>



<%@include file="../includes/footer.jsp"%>