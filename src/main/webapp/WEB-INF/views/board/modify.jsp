<%@ page contentType="text/html;charset=UTF-8" language="java"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ include file="../includes/header.jsp"%>
<div class="row">

	<div class="col-lg-12">
		<h1 class="page-header">글수정</h1>
	</div>
	<!-- /.col-lg-12 -->
</div>
<!-- /.row -->
<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="panel-heading">글수정</div>
			<!-- /.panel-heading -->
			<div class="panel-body">
				<form role="form" action="/board/modify" method="post">
					<div class="form-group">
						<label>번호</label><input class="form-control" name='bno'
							readonly="readonly" value='<c:out value='${board.bno}'/>'>
					</div>

					<div class="form-group">
						<label>제목</label><input class="form-control" name='title'
							value='<c:out value='${board.title}'/>'>
					</div>

					<div class="form-group">
						<label>내용</label>
						<textarea class="form-control" rows="3" name='content'><c:out value='${board.content}' /></textarea>
					</div>

					<div class="form-group">
						<label>작성자</label> <input class="form-control" name='writer'
							value='<c:out value='${board.writer}'/>' readonly="readonly">
					</div>
					<div class="form-group">
						<label>작성시간</label> <input class="form-control" name="regdate"
							value="<fmt:formatDate value='${board.regdate}' pattern="yyyy/MM/dd" />"
							readonly="readonly">
					</div>
					<div class="form-group">
						<label>최종수정시간</label> <input class="form-control"
							name='updatedate'
							value='<fmt:formatDate  pattern="yyyy/MM/dd" value='${board.updatedate}'/>'
							readonly="readonly">
					</div>

					<button type="submit" data-oper='modify' class="btn btn-info">수정</button>
					<button type="submit" data-oper='remove' class="btn btn-danger">삭제</button>
					<button type="submit" data-oper='list' class="btn btn-default">글목록으로</button>

					<input type='hidden' name='pageNum' value='<c:out value="${cri.pageNum}"/>'>
					<input type='hidden' name='amount' value='<c:out value="${cri.amount}"/>'>
					<input type='hidden' name='keyword' value='<c:out value="${cri.keyword }"/>'>
					<input type='hidden' name='type' value='<c:out value="${cri.type }"/>'>
				</form>
			</div>

		</div>
	</div>
</div>
<script type="text/javascript">
	$(document).ready(function() {

		var formObj = $("form");

		console.log(formObj);
		$('button').on("click", function(e) {
			e.preventDefault();

			//var operation = $(this).data("oper");
			var operation = $(this).data("oper");

			console.log(operation);
			if (operation === 'remove') {
				formObj.attr("action", "/board/remove");

			} else if (operation === 'list') {
				formObj.attr("action", "/board/list").attr("method", "get");
				var pageNumTag = $("input[name='pageNum']").clone();
				var amountTag = $("input[name='amount']").clone();
				var keywordTag=  $("input[name='keyword']").clone();
				var typeTag=  $("input[name='type']").clone();
				formObj.empty();
				formObj.append(pageNumTag);
				formObj.append(amountTag);
				formObj.append(keywordTag);
				formObj.append(typeTag);
				
			}
			formObj.submit();
		});

	});
</script>



<%@include file="../includes/footer.jsp"%>