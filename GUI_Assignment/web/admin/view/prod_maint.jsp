<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Product Maintaint Page</title>
        <link rel="stylesheet" href="https://bootswatch.com/5/darkly/bootstrap.min.css">
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
            <h2 class="text-center mb-3">Edit/Add New Product</h2>
            <form action="prod_maint.jsp" onsubmit="return validateForm()">
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
                            <th scope="row">Description</th>
                            <td><textarea id="description" name="description" class="error-border form-control" style="height: 180px;"></textarea></td>
                        </tr>
                        <tr>
                            <th scope="row">Price</th>
                            <td><span id="price_error" class="error-message"></span><input onkeypress="return isPriceKey(event)" onchange="formatPrice(this)" onblur="isValidPrice()" type="text" id="price" name="price" class="error-border form-control" value=""></td>
                        </tr>
                        <tr>
                            <th scope="row">Active</th>
                            <td><input type="checkbox" id="active" name="active" class="form-check-input" value=""></td>
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
                    <button type="submit" class="btn btn-primary ms-3">Submit</button>
                </div>
            </form>
        </div>
    </body>

    <script src="../js/maint_page_util.js" type="text/javascript"></script>
    <script src="../js/maint_page_prod.js" type="text/javascript"></script>
</html>
