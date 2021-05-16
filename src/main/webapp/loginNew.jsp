<!DOCTYPE html>
<html>
<html lang="en" ng-app="myApp">
<head>
<link href="static/fonts/css/font-awesome.min.css" rel="stylesheet"
	type="text/css">
<link rel="shortcut icon" type="image/x-icon"
	href="static/img/favicon.ico" />
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1" />
<meta name="author" content="Taxosmart - Smart Invoice" />
<title>Learning Stack</title>

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link href="http://fonts.cdnfonts.com/css/momcake" rel="stylesheet">
<link rel='stylesheet' href='static/js/lib/angular-loading-bar-master/build/loading-bar.min.css'
	type='text/css' media='all' />
<style>
.form-signin {
	max-width: 390px;
	background-color: #fff;
	border: 1px solid rgba(0, 0, 0, 0.1);
}

.checkbox {
	font-weight: normal;
}

.form-control {
	position: relative;
	font-size: 16px;
	height: auto;
	@
	include
	box-sizing(border-box);
	&:
	focus
	{
	z-index
	:
	2;
}

}
@font-face {
	font-family: "untitled-font-2";
	src: url("static/css/fonts/untitled-font-2.eot");
	src: url("static/css/fonts/untitled-font-2.eot?#iefix")
		format("embedded-opentype"),
		url("static/css/fonts/untitled-font-2.woff") format("woff"),
		url("static/css/fonts/untitled-font-2.ttf") format("truetype"),
		url("static/css/fonts/untitled-font-2.svg#untitled-font-2")
		format("svg");
	font-weight: normal;
	font-style: normal;
}

[data-icon]:before {
	font-family: "untitled-font-2" !important;
	content: attr(data-icon);
	font-style: normal !important;
	font-weight: normal !important;
	font-variant: normal !important;
	text-transform: none !important;
	speak: none;
	line-height: 1;
	-webkit-font-smoothing: antialiased;
	-moz-osx-font-smoothing: grayscale;
}

[class^="icon-"]:before, [class*=" icon-"]:before {
	font-family: "untitled-font-2" !important;
	font-style: normal !important;
	font-weight: normal !important;
	font-variant: normal !important;
	text-transform: none !important;
	speak: none;
	line-height: 1;
	-webkit-font-smoothing: antialiased;
	-moz-osx-font-smoothing: grayscale;
}

.icon-tax-o-smart:before {
	content: "\61";
}

.serif {
	font-family: "Times New Roman", Times, serif;
}

.icon-tax-o-smart {
	text-align: center;
}
/* For taxosmart Logo End */
.shortline {
	border: 1px solid #7dc142;
	margin-left: 0px;
}

.mui-card {
	background: white;
	border-radius: 2px;
	display: inline-block;
	height: 300px;
	margin: 1rem;
	position: relative;
	width: 90%;
}

.xs-shadow {
	box-shadow: 0 1px 3px rgba(0, 0, 0, 0.12), 0 1px 2px rgba(0, 0, 0, 0.24);
	transition: all 0.3s cubic-bezier(.25, .8, .25, 1);
}

.xs-shadow:hover {
	box-shadow: 0 14px 28px rgba(0, 0, 0, 0.25), 0 10px 10px
		rgba(0, 0, 0, 0.22);
}

@media screen and (max-width: 500px) {
	.mn_menu {
		display: none;
	}
}

@media only screen and (min-width:499px) {
	.hide-on-desktop {
		display: none;
		max-height: 0;
		overflow: hidden;
	}
}

.button {
	display: inline-block;
	border-radius: 5px;
	width: 140px;
	border: none;
	color: #FFFFFF;
	text-align: center;
	font-size: 15px;
	padding: 20px;
	transition: all 0.5s;
	cursor: pointer;
}

.button span {
	cursor: pointer;
	display: inline-block;
	position: relative;
	transition: 0.5s;
}

.button span:after {
	content: '\00bb';
	position: absolute;
	opacity: 0;
	top: 0;
	right: -20px;
	transition: 0.5s;
	color: #79BD3C;
}

.button:hover span {
	padding-right: 25px;
}

.button:hover span:after {
	opacity: 1;
	right: 0;
}

::-webkit-scrollbar {
	display: none;
}

.logIn {
	margin-top: 10px;
	width: 68%;
	margin-left: 75%;
}
</style>

<!-- Latest compiled JavaScript -->

<script>
    function confirmP() {
	var x = document.getElementById("password");
	if (x.type === "password") {
	    x.type = "text";
	} else {
	    x.type = "password";
	}
    }
</script>
<script src="https://www.google.com/recaptcha/api.js" async defer></script>
</head>
<body style="background-color: #fff; margin: 0; padding: 2%;">
	<br>

	<a class="icon-cog" style="line-height: 0.5%; text-decoration: none;"
		ng-click="cCctr.homepage('homepage')"> <span color="whitle"
		style="font-size: 40px; color: #0F316D; position: relative; bottom: 10px; color: black; font-family: 'MOMCAKE', sans-serif"><b>Learning Stack</b>
	</span>
	</a>
	<br>
	<br>
	<br>
	<div class="row mn_menu">
		<div class="container">
			<br>
			<div class="col-md-6 mn_menu"></div>
			<br>
			<div class="col-md-6 mn_menu" style="margin-top: -3%">
				<div class="mainbox col-md-6 col-md-offset-3 col-sm-6 logIn">
					<form id="loginform" class="form-horizontal" role="form"
						method="post" action="/LearningStackAdmin/login">
						<div class="panel panel-info" style="border-color: #6c5dd3;">
							<div class="panel-heading"
								style="background: #6c5dd3; color: #ffffff">
								<div class="panel-title"
									style="margin-left: 38%; font-weight: bold; font-size: 20px">Sign
									In</div>
							</div>



							<p style="color: red;">
								<b><span>${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}</span></b>
							</p>

							<div style="padding-top: 30px" class="panel-body">



								<div style="display: none" id="login-alert"
									class="alert alert-danger col-sm-12"></div>

								<div style="width: 100%" class="input-group">
									<div class="col-md-2">
										<i class="fa fa-user-circle-o fa-2x" aria-hidden="true"></i>
									</div>
									<div class="col-md-9">
										<input type="text" id="username" name="username"
											class="form-control" placeholder="Username" required
											autofocus>
									</div>
								</div>
								<div style="margin-top: 4%; width: 100%" class="input-group">
									<div class="col-md-2" style="padding: 1% 0 0 7%">
										<i class="fa fa-lock fa-2x" aria-hidden="true"></i>
									</div>
									<div class="col-md-9">
										<input id="password" type="password" class="form-control"
											name="password" placeholder="Password" required>
									</div>
									<div class="col-md-1" style="margin: 3% 0 0 -7%">
										<span onclick="confirmP()"><i class=" fa fa-eye"></i></span>
									</div>
								</div>
								<br>
								<button class="btn btn-success btn-lg btn-block" type="submit">Sign
									in</button>

							</div>
						</div>
					</form>
				</div>
			</div>

		</div>
	</div>

	<div class="col-md-12 hide-on-desktop">
		<div class="col-md-6"></div>
		<div class="col-md-6">

			<form id="loginform" class="form-horizontal" role="form"
				method="post" action="/LearningStackAdmin/login">
				
					<div class="panel-heading"
						style="background: #6c5dd3; color: #ffffff">
						<div class="panel-title">Sign In</div>
					</div>
					<p style="color: red;">
						<b><span>${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}</span></b>
					</p>
					<div style="padding-top: 30px" class="panel-body">
						<div style="display: none" id="login-alert"
							class="alert alert-danger col-sm-12"></div>
						<div style="width: 100%" class="input-group">
							<div class="col-md-2" style="margin: 0 0 -12% -4%">
								<i class="fa fa-user-circle-o fa-2x" aria-hidden="true"></i>
							</div>

							<div class="col-md-9" style="margin: 0 6% 20% 10%">
								<input type="text" id="username" name="username"
									class="form-control" placeholder="Username" required autofocus>

							</div>
						</div>
						<div style="margin-bottom: 25px" class="input-group">
							<div class="col-md-2" style="margin: 0 0 0 -2%">
								<i class="fa fa-lock fa-2x" aria-hidden="true"></i>
							</div>
							<div class="col-md-9" style="margin: -13% -14% 0 12%">
								<input id="password" type="password" class="form-control"
									name="password" placeholder="Password" required>
							</div>
							<div class="col-md-1" style="margin: 3% 0 0 105%">
								<span onclick="confirmP()"><i class=" fa fa-eye"></i></span>
							</div>

							<!-- <div class="input-group">
								<div class="g-recaptcha"
									data-sitekey="6LeIxAcTAAAAAJcZVRqyHh71UMIEGNQ_MXjiZKhI"></div>
							</div> -->

							<br>
							<button class="btn btn-success btn-lg btn-block" type="submit"
								style="margin: 4% 0 -8% 11%">Sign in</button>

						</div>
					</div>
			</form>
		</div>
	</div>

	<script src="static/js/lib/angular-http-loader.js"></script>
	<script type='text/javascript'
		src="static/js/lib/angular-loading-bar-master/build/loading-bar.min.js"></script>
</body>
</html>