<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Staff Maintaint Page</title>
        <link rel="stylesheet" href="https://bootswatch.com/5/darkly/bootstrap.min.css">
        
        <link href="../css/css.css" rel="stylesheet" type="text/css"/>
        <style>
            .form_wid{
                max-width: 850px;
            }
            .error-message {
                color: red;
                font-size:12px;
            }
            .table td{
                width:50%;
                min-width:400px;
            }

        </style>
    </head>
    <body>
        <div class="form_wid container mt-5">
            <h2 class="text-center mb-3">Edit/Add New Staff</h2>
            <form action="staff_maint.jsp" onsubmit="return validateForm()">
                <table class="table table-striped table-dark">
                    <thead>
                        <tr>
                            <th scope="col">Field</th>
                            <th scope="col">Value</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <th scope="row">ID</th>
                            <td><input style="color:grey;" type="text" id="id" name="id" class="form-control" value="ID will be auto generated." disabled></td>
                        </tr>
                        <tr>
                            <th scope="row">Name</th>
                            <td><span id="name_error" class="error-message"></span><input onblur="isInputFieldEmpty(this);" type="text" id="name" name="name" class="error-border form-control" value=""></td>
                        </tr>
                        <tr>
                            <th scope="row">Phone Num</th>
                            <td><span id="phone_num_error" class="error-message"></span><input onkeypress="return isNumberKey(event)" onblur="phoneNumValid()" type="text" id="phone_num" name="phoneNum" class="error-border form-control" value="" maxlength="11"></td>
                        </tr>
                        <tr>
                            <th scope="row">Email</th>
                            <td><span id="email_error" class="error-message"></span><input onblur="emailValid()" type="text" id="email" name="email" class="error-border form-control" value=""></td>
                        </tr>
                        <tr>
                            <th scope="row">Birthday</th>
                            <td><span id="birthday_error" class="error-message"></span><input onblur="isValidDate(this)" type="date" id="birthday" name="birthday" class="error-border form-control" value=""></td>
                        </tr>
                        <tr>
                            <th scope="row">IC</th>
                            <td><span id="ic_error" class="error-message"></span><input onkeypress="return isNumberKey(event)" onblur="icValid()" type="text" id="ic" name="ic" class="error-border form-control" value="" maxlength="12"></td>
                        </tr>
                        <tr>
                            <th scope="row">Password</th>
                            <td><span id="password_error" class="error-message"></span><input onblur="passwordValid()" type="text" id="password" name="password" class="error-border form-control" value=""></td>
                        </tr>
                        <tr>
                            <th scope="row">Confirm Password</th>
                            <td><span id="cf_password_error" class="error-message"></span><input onkeyup="cf_password_onkeyup()" type="text" id="cf_password" name="cf_password" class="error-border form-control" value=""></td>
                        </tr>
                    </tbody>
                </table>
                <div class="d-flex justify-content-end">
                    <div class="form-check form-check-inline">
                        <input class="form-check-input" type="radio" name="action" id="inlineRadio1" value="option1" checked>
                        <label class="form-check-label" for="inlineRadio1">Return to listing page</label>
                    </div>
                    <div class="form-check form-check-inline">
                        <input class="form-check-input" type="radio" name="action" id="inlineRadio2" value="option2">
                        <label class="form-check-label" for="inlineRadio2">Insert another record</label>
                    </div>
                    <div class="form-check form-check-inline">
                        <input class="form-check-input" type="radio" name="action" id="inlineRadio3" value="option3">
                        <label class="form-check-label" for="inlineRadio3">Preview current record</label>
                    </div>
                    <button type="reset" onclick="resets()" class="btn btn-secondary ms-3">Reset</button>
                    <button type="button" onclick="deleteStaff(1)" class="btn btn-danger ms-3">Delete</button>
                    <button type="submit" class="btn btn-primary ms-3">Submit</button>
                </div>
            </form>
        </div>
    </body>

    <script src="../js/maint_page_util.js" type="text/javascript"></script>
    <script src="../js/maint_page_staff.js" type="text/javascript"></script>
</html>
