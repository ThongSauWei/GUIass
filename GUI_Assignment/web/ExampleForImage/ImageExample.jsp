<%-- 
    Document   : ImageExample
    Created on : Apr 9, 2023, 1:14:21 AM
    Author     : LENOVO
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body style="display: flex; align-items: center; justify-content: center">
        <div>
            <h1>Upload your image</h1>
            <form action="../RetrieveImageServlet" method="POST" enctype="multipart/form-data">
                <input type="file" name="image" accept=".jpg, .jpeg, .jfif, .pjpeg, .pjp, .png">
                <br><br>
                <input style="position: relative; right: -90%"type="submit">
            </form>
            
            
        </div>
    </body>
</html>
