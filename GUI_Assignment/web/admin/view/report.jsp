<%@page import="Controller.ReportController"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Report</title>
        <link rel="stylesheet" href="https://bootswatch.com/4/darkly/bootstrap.min.css">
        <style>
            .container {
                display: flex;
                flex-direction: column;
                justify-content: center;
                align-items: center;
                height: 100vh;
            }
            .sections{
                display: none;
                width: 100%;
                height: 100vh;
            }
            #section1{
                display: block;
            }

            .fixed-bottom-center {
                position: fixed;
                bottom: 0;
                left: 50%;
                transform: translateX(-50%);
            }
            .submit{
                position: fixed;
                right: 25%;
                transform: translateX(-50%);
                bottom: 50%;
                font-size:30px;
                width:80px;
                height: 80px;
            }
            #back-btn{
                position: fixed;
                left: 25%;
                transform: translateX(-50%);
                bottom: 50%;
                font-size:30px;
                width:80px;
                height: 80px;
            }
            select[multiple] {
                overflow: hidden;
                scrollbar-width: none;
                -ms-overflow-style: none;
                ::-webkit-scrollbar {
                    display: none;
                }
            }
        </style>




        <script>
            window.onload = function () {
                // Add click event listeners to section 1 links
                document.getElementById('sales-link').addEventListener('click', function () {
                    // Hide section 1 and show section 2
                    document.getElementById('section1').style.display = 'none';
                    document.getElementById('section2').style.display = 'block';
                    document.getElementById('back-btn').style.display = 'block';
                    document.getElementById('home-btn').style.display = 'none';
                });
                document.getElementById('popularity-link').addEventListener('click', function () {
                    // Hide section 1 and show section 3
                    document.getElementById('section1').style.display = 'none';
                    document.getElementById('section3').style.display = 'block';
                    document.getElementById('back-btn').style.display = 'block';
                    document.getElementById('home-btn').style.display = 'none';
                });
            };
            function back() {
                document.getElementById('section1').style.display = 'block';
                const elements = document.querySelectorAll('.sections');
                for (let i = 0; i < elements.length; i++) {
                    elements[i].style.display = 'none';
                }
                document.getElementById('back-btn').style.display = 'none';
                document.getElementById('home-btn').style.display = 'block';
            }
            ;
        </script>
    </head>
    <body>
        <div class="container">
            <div id="section1" class=" btn-group-vertical">
                <h1 class="text-center mb-5">Report</h1>
                <a id="sales-link" class="mt-1 btn btn-primary btn-lg rounded-lg ">Sales</a>
                 <!-- <a id="popularity-link" class="mt-1  btn btn-primary btn-lg rounded-lg ">Popularity</a> -->
            </div>
            <%String[] opt = null;%>
            <div class="sections" id="section2">
                <h1 class="text-center mt-5">Sales Report</h1>
                <form action="../../ReportResult" method="POST" onsubmit="return validateForm()">
                    <table class="table table-striped table-dark mt-5" style="width: 550px;margin:auto;">
                        <thead class="bg-primary">
                            <tr>
                                <th>Field</th>
                                <th>Value</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <th>Columns</th>
                                <td>
                                    <div class="row">
                                        <div class="">
                                            <select style="height: 100%;width:170px;" id="my-select" multiple readonly class="form-control">
                                                <%
                                                    opt = ReportController.getSalesColumnNamesOption().toArray(new String[0]);
                                                    for (int i = 0; i < opt.length; i++)
                                                        out.print("<option value='" + (i + 1) + "'>" + opt[i] + "</option>");
                                                %>
                                            </select>
                                        </div>
                                        <div class="ml-3 mr-3 d-flex flex-column justify-content-center align-items-center">
                                            <button style="margin-top:3px;width:50px;" type="button" onclick="handleMultipleSelect('my-select', 'my-input_select', 'my-input')" class="btn btn-primary">&gt;</button>
                                            <button style="margin-top:3px;width:50px;"type="button" onclick="revertMultipleSelect('my-select', 'my-input_select', 'my-input')" class="btn btn-primary">&lt;</button>
                                            <button style="margin-top:3px;width:50px;"type="button" onclick="handleAllSelect('my-select', 'my-input_select', 'my-input')" class="btn btn-primary">&gt;&gt;</button>
                                            <button style="margin-top:3px;width:50px;"type="button" onclick="revertAllSelect('my-select', 'my-input_select', 'my-input')" class="btn btn-primary">&lt;&lt;</button>
                                        </div>
                                        <div class="">
                                            <input hidden name="column" type="text" id="my-input">
                                            <select style="height: 100%;width:170px;" multiple id="my-input_select" class="form-control"></select>
                                        </div>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <th>Order Date</th>
                                <td><label style="width:25px;">From</label><input class="ml-3" name="dateFrom" type="date"><br><label style="width:25px;">To</label><input class="ml-3" name="dateTo" type="date"></td>
                            </tr>
                            <tr>
                                <th>Group By</th>
                                <td>
                                    <div class="row">
                                        <div class="">
                                            <select style="height: 100%;width:170px;" id="my-select-groupby" multiple readonly class="form-control">
                                                <%
                                                    opt = ReportController.getSalesGroupByNamesOption().toArray(new String[0]);
                                                    for (int i = 0; i < opt.length; i++)
                                                        out.print("<option value='" + (i + 1) + "'>" + opt[i] + "</option>");
                                                %>
                                            </select>
                                        </div>
                                        <div class="ml-3 mr-3 d-flex flex-column justify-content-center align-items-center">
                                            <button style="margin-top:3px;width:50px;" type="button" onclick="handleMultipleSelect('my-select-groupby', 'my-input-groupby_select', 'my-input-groupby')" class="btn btn-primary">&gt;</button>
                                            <button style="margin-top:3px;width:50px;"type="button" onclick="revertMultipleSelect('my-select-groupby', 'my-input-groupby_select', 'my-input-groupby')" class="btn btn-primary">&lt;</button>
                                            <button style="margin-top:3px;width:50px;"type="button" onclick="handleAllSelect('my-select-groupby', 'my-input-groupby_select', 'my-input-groupby')" class="btn btn-primary">&gt;&gt;</button>
                                            <button style="margin-top:3px;width:50px;"type="button" onclick="revertAllSelect('my-select-groupby', 'my-input-groupby_select', 'my-input-groupby')" class="btn btn-primary">&lt;&lt;</button>
                                        </div>
                                        <div class="">
                                            <input  hidden name="groupby" type="text" id="my-input-groupby">
                                            <select style="height: 100%;width:170px;" multiple id="my-input-groupby_select" class="form-control"></select>
                                        </div>
                                    </div>
                                </td>
                            </tr>
                        <th>Order By<br><label for="acs" data-toggle="tooltip" data-placement="left" title="Accending">(Asc? </label><input id="acs" value="1" name="acs" type="checkbox">)</th>
                        <td>
                            <div class="row">
                                <div class="">
                                    <select style="height: 100%;width:170px;" id="my-select-orderby" multiple readonly class="form-control">
                                        <%
                                            opt = ReportController.getSalesOrderByNamesOption().toArray(new String[0]);
                                            for (int i = 0; i < opt.length; i++)
                                                out.print("<option value='" + (i + 1) + "'>" + opt[i] + "</option>");
                                        %>
                                    </select>
                                </div>
                                <div class="ml-3 mr-3 d-flex flex-column justify-content-center align-items-center">
                                    <button style="margin-top:3px;width:50px;" type="button" onclick="handleMultipleSelect('my-select-orderby', 'second-select-orderby', 'my-input-orderby')" class="btn btn-primary">&gt;</button>
                                    <button style="margin-top:3px;width:50px;"type="button" onclick="revertMultipleSelect('my-select-orderby', 'second-select-orderby', 'my-input-orderby')" class="btn btn-primary">&lt;</button>
                                    <button style="margin-top:3px;width:50px;"type="button" onclick="handleAllSelect('my-select-orderby', 'second-select-orderby', 'my-input-orderby')" class="btn btn-primary">&gt;&gt;</button>
                                    <button style="margin-top:3px;width:50px;"type="button" onclick="revertAllSelect('my-select-orderby', 'second-select-orderby', 'my-input-orderby')" class="btn btn-primary">&lt;&lt;</button>
                                </div>
                                <div class="">
                                    <input hidden name="orderby" type="text" id="my-input-orderby">
                                    <select style="height: 100%;width:170px;" multiple id="second-select-orderby" class="form-control"></select>
                                </div>
                            </div>
                        </td>
                        </tr>
                        <tr>
                        <tr>
                            <th>Active</th>
                            <td style="padding-left:0;">
                                <select class="form-control" id="status-select" name="status" style="width:150px;">
                                    <option value="2" selected>All</option>
                                    <option value="1">Active Only</option>
                                    <option value="0">Inactive Only</option>
                                </select>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                    <div class="mt-3 d-flex justify-content-center">
                        <input hidden name="reportName" value="Sales">
                        <input hidden name="submit" value="1">
                        <button onclic="return formValid()" type="submit" class="btn btn-primary ms-3  rounded-pill submit">&gt;</button>
                    </div>
                </form>
            </div>
            <div class="sections" id="section3">
                <h1 class="text-center mt-5">Popularity Report</h1>
                <p class="text-center"">Under develop</p>
                <form action="report_result.jsp">
                    <table class="table table-striped table-dark mt-5">
                        <thead>
                        <th>Condition</th>
                        <th>Value</th>
                        </thead>
                        <tbody>
                        <th></th>
                        <td></td>
                        </tbody>
                    </table>
                </form>
            </div>
        </div>

        <a id="home-btn" href="home.jsp" class=" mb-1 btn btn-primary fixed-bottom-center  rounded-pill">Return</a>
        <button id="back-btn" onclick="back()" class="btn btn-primary ms-3 rounded-pill " style="display:none;">&lt;</button>
        <script src="../js/report.js" type="text/javascript"></script>
    </body>
</html>
