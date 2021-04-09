	<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>V-Class</title>
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
		<link rel="stylesheet" href="assets/styles/layout.css" type="text/css" />
		<script type="text/javascript" src="assets/scripts/jquery.min.js"></script>
		<script type="text/javascript"
			src="assets/scripts/jquery.slidepanel.setup.js"></script>
		<script type="text/javascript" src="assets/scripts/jquery.ui.min.js"></script>
		<script type="text/javascript" src="assets/scripts/jquery.tabs.setup.js"></script>
	</head>
<body>
	
	<!-- Start header section -->
	<header>
	
	<div class="wrapper col0">
		<div id="topbar">
			<div id="slidepanel">
				<div class="topbox">
					<h2>Nullamlacus dui ipsum</h2>
					<p>Nullamlacus dui ipsum conseque loborttis non euisque morbi
						penas dapibulum orna. Urnaultrices quis curabitur phasellentesque
						congue magnis vestibulum quismodo nulla et feugiat.
						Adipisciniapellentum leo ut consequam ris felit elit id nibh
						sociis malesuada.</p>
					<p class="readmore">
						<a href="#">Continue Reading &raquo;</a>
					</p>
				</div>
				<div class="topbox">
					<h2>Teachers Login Here</h2>
					<form action="#" method="post">
						<fieldset>
							<legend>Teachers Login Form</legend>
							<label for="teachername">Username: <input type="text"
								name="teachername" id="teachername" value="" />
							</label> <label for="teacherpass">Password: <input
								type="password" name="teacherpass" id="teacherpass" value="" />
							</label> <label for="teacherremember"> <input class="checkbox"
								type="checkbox" name="teacherremember" id="teacherremember"
								checked="checked" /> Remember me
							</label>
							<p>
								<input type="submit" name="teacherlogin" id="teacherlogin"
									value="Login" /> &nbsp; <input type="reset"
									name="teacherreset" id="teacherreset" value="Reset" />
							</p>
						</fieldset>
					</form>
				</div>
				<div class="topbox last">
					<h2>Pupils Login Here</h2>
					<form action="#" method="post">
						<fieldset>
							<legend>Pupils Login Form</legend>
							<label for="pupilname">Username: <input type="text"
								name="pupilname" id="pupilname" value="" />
							</label> <label for="pupilpass">Password: <input type="password"
								name="pupilpass" id="pupilpass" value="" />
							</label> <label for="pupilremember"> <input class="checkbox"
								type="checkbox" name="pupilremember" id="pupilremember"
								checked="checked" /> Remember me
							</label>
							<p>
								<input type="submit" name="pupillogin" id="pupillogin"
									value="Login" /> &nbsp; <input type="reset" name="pupilreset"
									id="pupilreset" value="Reset" />
							</p>
						</fieldset>
					</form>
				</div>
				<br class="clear" />
			</div>
			<div id="loginpanel">
				<ul>
					<li class="left">Log In Here &raquo;</li>
					<li class="right" id="toggle"><a id="slideit"
						href="#slidepanel">Administration</a><a id="closeit"
						style="display: none;" href="#slidepanel">Close Panel</a></li>
				</ul>
			</div>
			<br class="clear" />
		</div>
	</div>
	
	<div class="wrapper col1">
		<div id="header">
			<div id="logo">
				<h1>
					<a href="">V-CLASS</a>
				</h1>
				<p>A virtual learning platform</p>
			</div>
			<div class="fl_right">
				<ul>
					<li class="last"><a href="#">Search</a></li>
					<li><a href="#">Online Support</a></li>
					<li><a href="#">School Board</a></li>
				</ul>
				<p>Tel: xxxxx xxxxxxxxxx | Mail: info@domain.com</p>
			</div>
			<br class="clear" />
		</div>
	</div>
	
	<div class="wrapper col2">
		<div id="topnav">
			<ul>
				<li class="active"><a href="home">Home</a></li>
				<li><a href="pages/style-demo.html">News</a></li>
				<li><a href="pages/full-width.html">Classes</a></li>
				<li><a href="pages/full-width.html">About</a></li>
				<li class="last"><a href="#">Contact us</a></li>				
			</ul>
		</div>
	</div>
	
	</header>
	<!-- End header section -->