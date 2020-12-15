<%@ page language="java" contentType="text/html; charset=utf-8;"
	pageEncoding="utf-8"%>

<!DOCTYPE html>
<html lang="en">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<head>
<jsp:include page="/VIEW/jsp/jsp-component/head-css.jsp" />
<c:url var="url" scope="session" value="/VIEW"></c:url>
<link rel="stylesheet" type="text/css"
	href="${url}/css/css-page/signin.css">


</head>

<body>
	<jsp:include page="/VIEW/jsp/jsp-component/menu.jsp"></jsp:include>

	<c:import url="/VIEW/jsp/jsp-component/filter.jsp">
	</c:import>

	<c:import url="/VIEW/jsp/jsp-component/breadcumb.jsp">
		<c:param name="title" value="Nhập OTP"></c:param>
	</c:import>

	<!-- Page Content -->
	<form onsubmit="return checkNum()" method="post"
		action="${pageContext.request.contextPath}/otp" class="form-signin"
		id="otp">
		<img class="mb-4" src="${url}/image/img-sys/OTP.png" alt="" width="72"
			height="72">
		<h1 class="h3 mb-3 font-weight-normal">Nhập mã OTP</h1>
		<label class="sr-only">OTP</label> <input name="OTP" id="checkOTP"
			class="form-control" placeholder="Nhập mã OTP đã được gửi trong mail"
			value="" onfocusout=" check_OTP(this.id)"> <br />
		<p style="display: none; color: red" id="check_OTP">Vui lòng không
			nhập chữ và kí tự đặc biệt.</p>
		</div>
		<h2 style="color: red">${message}</h2>
		<button class="btn btn-lg btn-primary btn-block mt-3" type="submit">Tiếp
			tục</button>
	</form>

	<div class="container">
		<div class="row text-center">
			<div class="col-12">
				<script>
					var timeleft = 120;
					var downloadTimer = setInterval(
							function() {
								if (timeleft <= 0) {
									clearInterval(downloadTimer);
									document.getElementById("countdown").innerHTML = "<form style='margin : -80px;' class='mb-3' action='${pageContext.request.contextPath}/otp' method='POST'><input name='TOKENKEY' value='register' hidden='true'><button type='submit' class='btn btn-link'><strong>Gửi lại OTP</strong></button></form>";
								} else {
									document.getElementById("countdown").innerHTML = "<div style='margin : -80px; font-weight: bold;'>Mã OTP có hiệu lực trong : "
											+ timeleft + " giây<div>";
								}
								timeleft -= 1;
							}, 1000);
				</script>
				<div id="countdown"></div>
			</div>
		</div>
	</div>
	<!-- /.container -->


	<jsp:include page="/VIEW/jsp/jsp-component/footer.jsp"></jsp:include>
	<!-- Bootstrap core JavaScript -->


	<c:url var="url" scope="session" value="/VIEW"></c:url>
	<script src="${url}/js/js-page/form-login.js"></script>
</body>

</html>