<%@ page language="java" contentType="text/html; charset=GB18030" pageEncoding="GB18030"%>
<%
/*��ȡ����·��*/
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>

<!-- Head -->
<head>

	<title>��¼��</title>

	<!-- Meta-Tags -->
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">



	<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
	<!-- //Meta-Tags -->

	<!-- Style --> <link rel="stylesheet" href="css/style1.css" type="text/css" media="all">



</head>
<!-- //Head -->

<!-- Body -->
<body>

	<h1>��¼��</h1>

	<div class="container w3layouts agileits">

		<div class="login w3layouts agileits">
			<h2>�� ¼</h2>
			<form action="<%=basePath%>user/login" method="post" accept-charset="UTF-8">
				<input type="text" Name="username" placeholder="�û���" required="">
				<input type="password" Name="password" placeholder="����" required="">
				<ul class="tick w3layouts agileits">
					<li>
						<input type="checkbox" id="brand1" value="">
						<label for="brand1"><span></span>��ס��</label>
					</li>
				</ul>
				<div class="send-button w3layouts agileits">
					<input type="submit" value="�� ¼">
				</div>
			</form>
			<h3>${msg}</h3>
			<a href="#">��ס����?</a>
			<div class="social-icons w3layouts agileits">
				<p>- ������ʽ��¼ -</p>
				<ul>
					<li class="qq"><a href="#">
					<span class="icons w3layouts agileits"></span>
					<span class="text w3layouts agileits">QQ</span></a></li>
					<li class="weixin w3ls"><a href="#">
					<span class="icons w3layouts"></span>
					<span class="text w3layouts agileits">΢��</span></a></li>
					<li class="weibo aits"><a href="#">
					<span class="icons agileits"></span>
					<span class="text w3layouts agileits">΢��</span></a></li>
					<div class="clear"> </div>
				</ul>
			</div>
			<div class="clear"></div>
		</div>
		<div class="register w3layouts agileits">
			<h2>ע ��</h2>
			<form action="<%=basePath%>user/register" method="post" accept-charset="UTF-8">
				<input type="text" Name="username" placeholder="�û���" required="">
				<input type="password" Name="password" placeholder="����" required="">
				<input type="text" Name="netname" placeholder="�ǳ�" required="">
				<input type="text" Name="sex" placeholder="�Ա�" required="">
				<div class="send-button w3layouts agileits">
					<input type="submit" value="ע��">
				</div>
			</form>
			<div class="clear"></div>
		</div>
		<h3>${msg2}</h3>
		<div class="clear"></div>

	</div>



</body>
<!-- //Body -->

</html>