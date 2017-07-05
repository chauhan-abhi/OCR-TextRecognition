<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form action="Api" method="post">
		<div class="row">
			<div class="span12">
				<div class="span4">
					<p>Paste url to image or PDF file (.png or .jpg or .pdf)</p>
				</div>
				<div class="span8">
					<input type="text" name="imageUrl">
				</div>
			</div>
			<div class="span12">
				<div class="span4">
					<p>Select OCR language</p>
				</div>
				<div class="span4">
					<select name="ocrLanguage" class="form-control valid selection">
						<option value="ara">Arabic</option>
						<option value="bul">Bulgarian</option>
						<option value="chs">ChineseSimplified</option>
						<option value="cht">ChineseTraditional</option>
						<option value="hrv">Croation</option>
						<option value="cze">Czech</option>
						<option value="dan">Danish</option>
						<option value="dut">Dutch</option>
						<option value="eng" selected="selected">English</option>
						<option value="fin">Finnish</option>
						<option value="fre">French</option>
						<option value="ger">German</option>
						<option value="gre">Greek</option>
						<option value="hun">Hungarian</option>
						<option value="ita">Italian</option>
						<option value="jpn">Japanese</option>
						<option value="kor">Korean</option>
						<option value="nor">Norwegian</option>
						<option value="pol">Polish</option>
						<option value="por">Portuguese</option>
						<option value="rus">Russian</option>
						<option value="slv">Slovenian</option>
						<option value="spa">Spanish</option>
						<option value="swe">Swedish</option>
						<option value="tur">Turkish</option>

					</select>
				</div>

			</div>
			<br> <br>
			<h4Start OCR>
			</h4>
			<input type="submit">
	</form>
</body>
</html>