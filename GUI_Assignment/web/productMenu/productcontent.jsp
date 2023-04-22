<%-- 
    Document   : productcontent
    Created on : Mar 17, 2023, 7:41:06 PM
    Author     : guoc7
--%>

<%@page import="java.util.HashMap"%>
<%@page import="Model.Member"%>
<%@page import="Model.RateReview"%>
<%@page import="Model.Cart"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Collections"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Model.Product"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <title>Lego</title>
        <link href="css/bootstrap.css" rel="stylesheet" type="text/css"/>
        <link href="css/productcontent.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.2/font/bootstrap-icons.css"" />
        <link rel="stylesheet" href="https://unpkg.com/swiper@8/swiper-bundle.min.css" />
        <script src="https://unpkg.com/swiper@8/swiper-bundle.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/swiper@9/swiper-element-bundle.min.js"></script>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <div class="product-content-container">
            <div class="container">
                <div class="row">
                    <div class="col-12 col-lg-12 col-md-12 col-xl-12">
                        <%Product view = (Product) request.getAttribute("productDetail");%>
                        <div class="product-content-image-container">
                            <!--<swiper-container class="mySwiper" pagination="true" pagination-clickable="true" navigation="true" space-between="30"
                            centered-slides="true" autoplay-delay="2500" autoplay-disable-on-interaction="false">-->
                            <img src="RetrieveImageServlet?imageID=<%=view.getImageTable().getImageId()%>" class="product-content-img"/>
                            <!-- </swiper-container>-->
                            <div class="product-content-desc-container">

                                <div class="container">
                                    <h3 class="product-content-text product-content-text-center"><span class="product-content-span"><%=view.getProductName()%></span> </h3>
                                    <h4 class="product-content-text mt-3"><span class="product-content-span">Desc:</span> <%=view.getProductDesc()%></h4>
                                    <h3 class="product-content-text "><span class="product-content-span">RM<%=view.getProductPrice()%> </span> </h3>
                                    <% double avgRating=Double.parseDouble(request.getParameter("avgRating"));
                                        if(avgRating==0.0){%>
                                    <p><span class="product-content-rating product-content-span"><i class="bi bi-star"></i>  RATING:</span> <span class="product-content-span product-content-rating-number-size"> No Rate Review Yet</span>/10</p>
                                    <%}else{%>
                                    <p><span class="product-content-rating product-content-span"><i class="bi bi-star"></i>  RATING:</span> <span class="product-content-span product-content-rating-number-size"> <%=request.getParameter("avgRating")%></span>/10</p>
                                    <%}%>
                                    <form action="addToCartPerMore" method="POST">
                                        <input type="hidden" name="hrefId" value="<%=view.getProductId()%>"/>
                                        <input type="hidden" name="hrefRating" value="<%=request.getParameter("avgRating")%>"/>
                                        <div class="product-content-quantityfield-container">
                                            <i class="product-content-btn bi bi-dash-square-fill" id="decrement-product" ></i>
                                            <input type="number" class="product-content-form-field" name="quantity" id="quantity" value="1" readonly>
                                            <i class="product-content-btn bi bi-plus-square-fill" id="increment-product"></i>

                                        </div>
                                        <div class="product-content-addcart-btn-container">
                                            <button type="submit" name="productId" value="<%=view.getProductId()%>" class="product-content-btn-cart btn btn-warning white-text"><i class="bi bi-bag-check"></i> To Cart</button>
                                        </div>
                                    </form>

                                </div>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
        </div>

        <div class="container">
            <div class="row">
                <div class="col-12 col-lg-12 col-xl-12 col-md-12">
                    <div class="product-content-display-comment-container">
                        <div class="container">
                            <div class="product-content-customer-comment-container">
                                <h3 class="product-content-comment-title">COMMENT</h3>
                            </div>
                            <div class="product-content-customer-detail-container">
                                <% ArrayList<RateReview> rateList = (ArrayList<RateReview>) request.getAttribute("customerReviewProductDetails");%>
                                <%ArrayList<Member> memberList = (ArrayList<Member>) request.getAttribute("memberNameFound");%>
                                <%if (rateList == null || rateList.isEmpty()) {%>
                                <h4 class="p-5 m-5"><i class="bi bi-clipboard2-pulse"></i>Theres are no any comments yet</h4>
                                <%} else {%>
                                <%for (int h = 0; h < rateList.size(); h++) {%>
                                <div class="row m-3">
                                    <div class="col-12 col-lg-2 col-xl-2 col-md-2">
                                        <img src="data:image/jpeg;base64,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" class="product-content-customer-profile"/>  
                                    </div>
                                    <div class="col-12 col-lg-8 col-md-8 col-xl-8">
                                        <%if (memberList == null || memberList.isEmpty()) {%>
                                        Member is Empty
                                        <%} else {%>
                                        <h5 class="product-content-customer-name"><%=memberList.get(h).getMemberName()%></h5>
                                        <%}%>
                                        <p class="white-text"><i class="bi bi-star"></i> RATING <%=rateList.get(h).getReviewRating()%> <span class="menu-list-ratingTotal">/10</span></p>
                                        <div class="product-content-review-container">
                                            <p class="product-content-comment"> <%=rateList.get(h).getReviewText()%></p>
                                        </div>
                                    </div>  
                                </div>
                                <%}
                                    }%>
                            </div>
                        </div>

                    </div>
                </div>
            </div>
        </div>

        <div class="container">
            <div class="product-content-something-like-container mt-5">
                <h3 class="product-content-something-like-title">SOMETHING YOU MAY LIKE</h3>
                <div class="row">
                    <div class="col-12 col-xl-12 col-md-12 col-lg-12">
                        <div class="row">
                            <%  ArrayList<Product> banner = (ArrayList<Product>) request.getAttribute("contentBanner");
                                HashMap<Integer, Double> bannerRate = (HashMap<Integer, Double>) request.getAttribute("productContentHashBanner");
                                Collections.shuffle(banner);
                                int bannerminimunIndex = Math.min(banner.size(), 4);
                                if (banner == null && !banner.isEmpty()) {
                            %><p>Banner is null</p>
                            <%} else {%>
                            <%    for (int y = 0; y < bannerminimunIndex; y++) {%>
                            <div class="col-3 col-xl-3 col-md-3 col-lg-3">
                                <div class="product-content-something-recommend-container">
                                    <img src="RetrieveImageServlet?imageID=<%=banner.get(y).getImageTable().getImageId()%>" class="product-content-something-recommend-image"/>
                                    <p><span class="product-content-span product-content-recomment-span-size"><%=banner.get(y).getProductName()%></span> </p>
                                    <p>RM : <span class="product-content-span product-content-recomment-span-size"><%=banner.get(y).getProductPrice()%></span> </p>
                                    <%if(bannerRate.get(banner.get(y).getProductId()) * 1.0==0){%>
                                    <p>Rating : <span class="product-content-span product-content-recomment-span-size"> No Rate Review Yet</span> </p>
                                    <%}else{%>
                                    <p>Rating : <span class="product-content-span product-content-recomment-span-size"> <%=bannerRate.get(banner.get(y).getProductId()) * 1.0%></span> </p>                                    
                                    <%}%>
                                    <div class="product-content-addcart-btn-container ">  
                                        <a href="viewProductServlet?id=<%=banner.get(y).getProductId()%>&avgRating=<%=bannerRate.get(banner.get(y).getProductId()) * 1.0 <= 0 ? 0 :bannerRate.get(banner.get(y).getProductId())  * 1.0%>" class="px-3"><button type="submit" class="btn btn-primary white-text"><i class="bi bi-eye-fill"></i> View</button></a>
                                        <form method="POST" action="addToCartPerOne" >
                                            <button type="submit" name="menu-list-one" value="<%=banner.get(y).getProductId()%>" class="px-3 btn btn-warning white-text"><i class="bi bi-bag-check"></i> To Cart</button>
                                        </form>
                                    </div>

                                </div>

                            </div>
                            <%
                                    }
                                }
                            %>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div>
        </div>

    </div>
</body>
</html>
<script>
    var increase = document.getElementById("increment-product");
    var decrease = document.getElementById("decrement-product");
    var totalQuantityProduct = document.getElementById("quantity");

    increase.addEventListener("click", function () {
        quantity.value = parseInt(quantity.value) + 1;
        if (quantity.value > 1) {
            decrease.disabled = false;
        } else {
            decrease.disabled = true;
        }
    });

    decrease.addEventListener("click", function () {
        if (quantity.value > 1) {
            quantity.value = parseInt(quantity.value) - 1;
        }
        if (quantity.value === 1) {
            decrease.disabled = true;
        }
    });
</script>