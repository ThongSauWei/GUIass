<%

String variableValue = (String)request.getAttribute("variableName");
%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Unexpected Error</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://bootswatch.com/5/darkly/bootstrap.min.css">
    </head>
    <body>
        <div class="container my-5">
            <div class="alert alert-danger" role="alert">
                <h4 class="alert-heading">Unexpected Error Occurred</h4>
                <p>Please contact support for assistance.</p>
                <p>--error message here--</p>
            </div>
            <div class="my-5 text-center">
                <a href="home.jsp" class="btn btn-primary">Go to Home Page</a>
            </div>
        </div>
    </body>
</html>
