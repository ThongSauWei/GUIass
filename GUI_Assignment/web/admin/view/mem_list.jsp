<%@page import="java.util.ArrayList"%>
<%@page import="Model.Member"%>
<%@page import="Controller.MemController"%>

<%
    String id = request.getParameter("id");
    String delete = request.getParameter("delete");
    String search = request.getParameter("search") != null ? request.getParameter("search") : "";

    if (delete != null && id != null) {
        new MemController().dltMem(id);
    }

    //turn off alert feature
    delete = "0";

%><!DOCTYPE jsp>
<jsp>
    <head>
        <meta charset="utf-8">
        <title>Customer Listing</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <link rel="stylesheet" href="https://bootswatch.com/4/darkly/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <link href="../css/css.css" rel="stylesheet" type="text/css"/>


        <style>
            .fixed-bottom-center {
                position: fixed;
                bottom: 0;
                left: 50%;
                transform: translateX(-50%);
            }
        </style>
    </head>
    <body>
        <%= delete != null ? delete.equals("1") ? "<script>alert('" + id + " is deleted.')</script>" : "" : ""%>
        <div class="container mt-5">
            <div class="row justify-content-center">
                <div class="col-md-8">
                    <div class=" rounded-lg shadow-sm">
                        <div class="card-body">
                            <form action="mem_list.jsp">
                                <div class="input-group mb-3">
                                    <div class="input-group-addon">
                                    </div>
                                    <input id="search" name="search" type="text" class="form-control form-control-lg rounded-pill" placeholder="ID or Name..." value="<%= search%>">
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
                    <table class="table table-hover table-striped">
                        <thead>
                            <tr>
                                <th style="width: 10%">ID</th>
                                <th style="width: 50%">Name</th>
                                <th style="width: 30%">Phone Num</th>
                                <th style="width: 10%">Delete</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%ArrayList<Member> members = new MemController().getMem(search);
                                if (members == null) {
                                    response.sendRedirect("unexpected_error.jsp");
                                } else if (members.size() == 0) {
                                    out.print("<td colspan=4>No Record.</td>");
                                }
                                for (Member member : members) {%>
                            <tr>
                                <td><%= member.getMemberId()%></td>
                                <td><%= member.getMemberName()%></td>
                                <td><%= member.getMemberPass()%></td>
                                <td><a href="mem_list.jsp?id=<%= member.getMemberId()%>&delete=1" onclick="return confirm('Are you sure?');" style="font-size:20px;color:grey" class="fa"><i class="delete fa fa-trash-o"></i></a></td>
                            </tr>
                            <%}%>
                        </tbody>
                    </table>
                </div>
            </div>

            <a href="home.jsp" class=" mb-1 btn btn-primary fixed-bottom-center  rounded-pill">Return</a>

            <script src="../js/list_page_util.js" type="text/javascript"></script>
    </body>
</jsp>