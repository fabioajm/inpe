<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<sf:form method="POST" action="save" enctype="multipart/form-data">
		
        Title: <input type="text" name="title"><br />
        Description: <textarea name="description" rows="4" cols="50" ></textarea><br />
        <select name="type">
		  <option value="REGULAR">REGULAR</option>
		  <option value="CHILDRENS">CHILDRENS</option>
		  <option value="NEW_RELEASE">NEW RELEASE</option>
		</select>
        File to upload: <input type="file" name="image"><br />  <br /> 
        <input type="submit" value="Upload"> Press here to upload the file!
    </sf:form>

</body>
</html>