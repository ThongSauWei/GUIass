<% String search = request.getParameter("search") != null ? (String) request.getParameter("search") : "";%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>Order Listing</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <link rel="stylesheet" href="https://bootswatch.com/4/darkly/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <link href="../css/css.css" rel="stylesheet" type="text/css"/>
        <script src="../js/list_page_util.js" type="text/javascript"></script>

        <style>
            .fixed-bottom-center {
                position: fixed;
                bottom: 0;
                left: 50%;
                transform: translateX(-50%);
            }
            .home-btn{
                z-index: 1;
            }
        </style>
    </head>
    <body>

        <a href="home.jsp" class=" mb-1 btn btn-primary fixed-bottom-center  rounded-pill home-btn">Return</a>

        <div class="container mt-5">
            <div class="row justify-content-center">
                <div class="col-md-8">
                    <div class=" rounded-lg shadow-sm">
                        <div class="card-body">
                            <h2 class="text-center mb-3">Order List</h2>
                        </div>
                    </div>
                </div>
            </div>

            <div class="row mt-5">
                <div class="col-md-12">
                    <table class="table table-hover table-striped">
                        <thead>
                            <tr>
                                <th style="width: 10%">Order ID</th>
                                <th style="width: 19%">Order Date</th>
                                <th style="width: 10%">Member ID</th>
                                <th style="width: 25%">Member Name</th>
                                <th style="width: 10%">Product ID</th>
                                <th style="width: 25%">Product Name</th>
                                <th style="width: 10%">Quantity</th>
                            </tr>
                        </thead>
                        <tbody>
                            <jsp:include page="../../OrderList" />
                        </tbody>
                    </table>
                </div>
            </div>
    </body>
</html>
