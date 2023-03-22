<%@page import="Controller.StaffController"%>
<%@page import="DataAccess.Mapper.StaffMapper"%>
<%@page import="Model.Staff"%>
<%@page import="DataAccess.Mapper.MemberMapper"%>
<%@page import="java.util.ArrayList"%>
<%@page import="DataAccess.DBTable"%>

<%String search = request.getParameter("search");%>
<!DOCTYPE jsp>
<jsp>
    <head>
        <meta charset="utf-8">
        <title>Staff Listing</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <link rel="stylesheet" href="https://bootswatch.com/4/darkly/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <link href="../css/css.css" rel="stylesheet" type="text/css"/>

    </head>
    <body>
        <div class="container mt-5">
            <div class="row justify-content-center">
                <div class="col-md-8">
                    <div class=" rounded-lg shadow-sm">
                        <div class="card-body">
                            <form action="staff_list.jsp">
                                <div class="input-group mb-3">
                                    <div class="input-group-addon">
                                    </div>
                                    <input id="search" name="search" type="text" class="form-control form-control-lg rounded-pill" placeholder="ID, IC, phone num, email or name..." value="<%= search%>">
                                    <div class="input-group-addon">
                                        <button id="submit-button" type="submit" class="btn btn-primary btn-lg rounded-pill">
                                            <i class="fa fa-search"></i>
                                        </button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row mt-5">
                <div class="col-md-12">

                    <a href="staff_maint.jsp" class="btn btn-primary btn-lg rounded-pill">
                        Add New
                    </a>
                    <table class="table table-hover table-striped">
                        <thead>
                            <tr>
                                <th style="width: 5%">ID</th>
                                <th style="width: 20%">Name</th>
                                <th style="width: 15%">Phone Num</th>
                                <th style="width: 20%">Email</th>
                                <th style="width: 10%">Birthday</th>
                                <th style="width: 15%">IC</th>
                                <th style="width: 15%">Password</th>
                                <th style="width: 5%">Edit</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%ArrayList<Staff> staffs = new StaffController().getStaff(search);
                                for (Staff staff : staffs) {%>
                            <tr>
                                <td><%= staff.getStaffId()%></td>
                                <td><%= staff.getStaffName()%></td>
                                <td><%= staff.getStaffPhNo()%></td>
                                <td><%= staff.getStaffEmail()%></td>
                                <td><%= staff.getFormattedBirthdate()%></td>
                                <td><%= staff.getStaffIc()%></td>
                                <td><%= staff.getStaffPass()%></td>
                                <td><a href="staff_maint.jsp?id=<%= staff.getStaffId()%>" style="font-size:20px;color:grey" class="fa"><i class="edit fa fa-pencil"></i></a></td>
                            </tr>
                            <%}%>
                        </tbody>
                    </table>
                </div>
            </div>


            <script src="../js/list_page_util.js" type="text/javascript"></script>
    </body>
</jsp>