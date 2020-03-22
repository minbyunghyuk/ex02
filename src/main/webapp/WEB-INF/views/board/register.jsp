<%@ page contentType="text/html;charset=UTF-8" language="java"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="../includes/header.jsp"%>
<link rel="shortcut icon" href="#">
<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">게시물 작성</h1>
	</div>
	<!-- /.col-lg-12 -->
</div>
<!-- /.row -->
<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="panel-heading">게시물작성</div>
			<div class="panel-body">
				<div class="row">
					<div class="col-lg-6">
						<form role="form" action="/board/register" method="post">
							<div class="form-group">
								<label>Title</label> <input class="form-control" name="title">
								<p class="help-block">제목입력</p>
							</div>
							<div class="form-group">
								<label>Text area</label>
								<textarea class="form-control" rows="3" name="content"></textarea>
								<p class="help-block">글내용 작성</p>
							</div>
							<div class="form-group">
								<label>Writer</label> <input class="form-control" name="writer">
								<p class="help-block">작성자입력</p>
							</div>
							<button type="submit" class="btn btn-default">작성완료</button>
							<button type="reset" class="btn btn-default">작성리셋</button>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<%@include file="../includes/footer.jsp"%>