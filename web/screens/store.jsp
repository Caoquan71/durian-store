<%-- 
    Document   : store.jsp
    Created on : May 30, 2023, 3:11:43 AM
    Author     : kienk
--%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>DURIAN STORE</title>
        <!-- css -->
        <link rel="stylesheet" href="styles/nav-bar.css" type="text/css"/>
        <link rel="stylesheet" href="styles/footer.css" type="text/css"/>
        <link rel="stylesheet" href="styles/store-product.css" type="text/css"/>
        <link rel="stylesheet" href="styles/footer.css" type="text/css"/>
        <!-- Font family -->
        <link rel="preconnect" href="https://fonts.googleapis.com" />
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
        <link
            href="https://fonts.googleapis.com/css2?family=Montserrat:wght@500&family=Shadows+Into+Light&display=swap"
            rel="stylesheet"
            />
        <!-- Favicon -->
        <link
            rel="stylesheet"
            href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"
            />
    </head>
    <body>
        <div class="header-container">
            <jsp:include page="../screens/navbar.jsp"></jsp:include>
            </div>
            <div class="store-container">
                <div class="store-header"><a href="home" style="text-decoration: none; color: black;">HOME /</a> SHOP</div>
                <div class="card-container">
                    <div class="left">
                        <div class="search-container" style="height: 7vh">
                            <input id="searchInput" style="width: 80%; height: 3vh" type="search" value="${sr}" placeholder="What are you looking for?">
                            <button id="submitSearch" type="submit" class="searchButton" style="height: 3vh; border-radius: 5px">
                                <i class="fa fa-search"></i>
                            </button>
                        </div>
                        <div class="filter-container">
                            <div class="price-container">
                                <div class="price-title"><span><b>FILTER BY PRICE $</b></span></div>
                                <fieldset class="filter-price">
                                    <div class="price-field">
                                        <input type="range"  min="0" max="3000" value="${min}" id="slide-min" class="price-slider" >
                                    <input type="range" min="0" max="3000" value="${max}" id="slide-max" class="price-slider">
                                </div>
                            </fieldset> 
                            <div class="rangeValues">
                                <input type="number"  min="0" max="3000" value="${min}" id="min-value" class="price-input"> to 
                                <input type="number" min="0" max="3000" value="${max}" id="max-value" class="price-input"><br />
                            </div>
                        </div>
                        <div class="filter-color">
                            <div class="color-title"><span><b>FILTER BY COLOR </b></span></div>
                            <div class="color-checkbox">
                                <c:forEach items="${colorList}" var="color" varStatus="status">
                                    <c:if test="${status.index + 1 >= 1 && status.index + 1 <= 5}">
                                        <input onClick="handleColorClick(event.target.value)" value="${color.id}" class="color" type="checkbox" ${col.contains(color.id.toString())?"checked":""}/> ${color.name}<br/>
                                    </c:if>
                                    <c:if test="${status.index + 1 == 6}">
                                        <span id="hidden-checkboxes" class="hidden">
                                        </c:if>
                                        <c:if test="${status.index + 1 > 5}">
                                            <input onClick="handleColorClick(event.target.value)" value="${color.id}" class="color" type="checkbox" ${col.contains(color.id.toString())?"checked":""}/>  ${color.name}<br/>
                                        </c:if>
                                        <c:if test="${status.index + 1 == fn:length(colorList)}">
                                        </span>
                                    </c:if>
                                </c:forEach>
                                <button id="load-more-btn" style="margin-top: 10px; background: #4CAF50;; color: white; padding: 5px;">Load more</button>
                            </div>
                        </div>
                        <div class="filter-storage">
                            <div class="storage-title"><span><b>FILTER BY STORAGE </b></span></div>
                            <div class="storage-checkbox" >
                                <c:forEach items="${storageList}" var="storage">
                                    <input onChange="handleStorageClick(event.target.value)" value="${storage.id}"  class="storage" type="checkbox" ${sto.contains(storage.id.toString())?"checked":""}/> ${storage.storageSize}<br/>
                                </c:forEach>

                            </div>
                        </div>
                        <div class="filter-storage" style="text-align: center">
                            <button id="filter-btn"  style="margin-top: 10px; margin-bottom: 10px; background: #4CAF50;; color: white; padding: 5px; width: 30%">Filter</button>
                        </div>
                    </div>

                </div>
                <div class="right">
                    <div class="right-sort">
                        <div class="products-showing">Showing <strong>${f}</strong> of <strong>${totalProduct}</strong> products</div>
                        <div class="current-showing"><strong>Show</strong> <a href="store?o=1&f=6&tag=1&order=${order}&sto=${sto}&col=${col}&min=${min}&max=${max}&sr=${sr}"><button class="btn-show ${f=="6"?"bg-green":"bg-white"}" id="six">6</button></a><a href="store?o=1&f=12&tag=1&order=${order}&sto=${sto}&col=${col}&min=${min}&max=${max}&sr=${sr}"><button class="btn-show ${f=="12"?"bg-green":"bg-white"}" id="twelve">12</button></a> products</div>
                        <div class="products-sort-by"><strong>Sort by</strong>
                            <select id="sort-by" name="sort-by" class="form-control" onChange="handleSort(event, ${tag}, ${f}, ${tag})">
                                <option value="" selected disabled hidden>Choose here</option>
                                <option value="Variant_price">Price</option>
                                <option value="Variant_price desc">Price descending</option>
                                <option value="sale_id desc">Sale</option>
                            </select>
                        </div>
                    </div>


                    <div class="right-container">
                        <c:forEach items="${product}" var="c">

                            <div class="sellter-item">
                                <a href="/Durian_Shop/product-detail?productName=${c.name}" style="text-decoration: none">
                                    <div class="sellter-item-content">
                                        <img class="img" src="img/${c.url}">                             
                                        <div class="sellter-item-text" style="padding: 10px;">            
                                            <h3>${c.name} ${c.color} <br/>${c.getStorage()} </h3>
                                            <c:if test="${c.sale==0}"><h3>${c.variantPrice}$</h3></c:if>
                                            <c:if test="${c.sale!=0}"><h3><span style="text-decoration: line-through;">${c.variantPrice}$</span> <span style="font-size: larger">${c.variantPrice - (c.variantPrice*c.sale)/100}$</span></h3></c:if>
                                            <c:if test="${c.sale!=0}"><button class="btn-sale">SALE ${c.sale}${c.sale == 0?"":"%"}</button></c:if>
                                            <button class="btn-info">${c.screen}</button>
                                            <button class="btn-info">${c.ram}</button>
                                            <button class="btn-info">${c.camera}</button>
                                            <c:if test="${c.quantity==0}"><button class="btn-info">Out Stock</button></c:if>
                                            <c:if test="${c.quantity!=0}"><button class="btn-info">In Stock</button></c:if>
                                            <div class="btn"><a href="/Durian_Shop/product-detail?productName=${c.name}"><span></span>
                                                    <span></span>
                                                    <span></span>
                                                    <span></span>BUY NOW </a></div>
                                            <br><br>
                                        </div>
                                    </div>
                            </div>
                            </a>
                        </c:forEach>
                    </div>

                    <div class="pagination" id="pagination" style="margin-bottom: 5vh; margin-top: 2vh">                       
                    </div>
                </div>
            </div>
        </div>

        <jsp:include page="../screens/footer.jsp"></jsp:include>
            <script>
                const validateRange = (minPrice, maxPrice, status) => {
                    if (minPrice > maxPrice) {
                        let temp = minPrice;
                        minPrice = maxPrice;
                        maxPrice = temp;
                    }
                    if (status === 'moveSlider') {
                        let priceLower = document.getElementById("min-value");
                        let priceUpper = document.getElementById("max-value");
                        priceLower.value = minPrice;
                        priceUpper.value = maxPrice;
                        minValue = minPrice;
                        maxValue = maxPrice;
                    }
                    if (status === 'input') {
                        let priceLower = document.getElementById("slide-min");
                        let priceUpper = document.getElementById("slide-max");
                        priceLower.value = minPrice;
                        priceUpper.value = maxPrice;
                        minValue = minPrice;
                        maxValue = maxPrice;
                    }
                    if (status === 'error') {
                        let priceLower = document.getElementById("min-value");
                        let priceUpper = document.getElementById("max-value");
                        priceLower.value = 0;
                        priceUpper.value = 3000;
                        minValue = minPrice;
                        maxValue = maxPrice;
                    }
                };
                const handleChangePrice = () => {
                    const priceRange = document.querySelectorAll(".price-slider");
                    priceRange.forEach((element) => {
                        element.addEventListener("change", () => {
                            let minPrice = parseInt(priceRange[0].value);
                            let maxPrice = parseInt(priceRange[1].value);
                            validateRange(minPrice, maxPrice, 'moveSlider');
                        });
                    });
                };
                handleChangePrice();


                const handlePriceInput = () => {
                    const priceInputs = document.querySelectorAll(".price-input");
                    priceInputs.forEach((element) => {
                        element.addEventListener('input', () => {
                            let minPrice = parseInt(document.getElementById("min-value").value);
                            let maxPrice = parseInt(document.getElementById("max-value").value);
                            if (minPrice < 0 || minPrice > 3000 || maxPrice < 0 || maxPrice > 3000) {
                                validateRange(minPrice, maxPrice, 'error');
                            }
                            validateRange(minPrice, maxPrice, 'input');
                        });
                    });
                };


                handlePriceInput();

                const handleSort = (event, o, f, tag) => {
                    var queryString = window.location.search;
                    if (queryString === "" || !queryString.includes('o') || !queryString.includes('&f') || !queryString.includes('&tag')) {
                        window.location.href = "store?order=" + event.target.value;
                    } else {
                        window.location.href = "store?o=" + o + "&f=" + f + "&tag=" + tag + "&order=" + event.target.value + "&sto=${sto}&col=${col}&min=${min}&max=${max}&sr=${sr}";
                    }

//                    window.location.href = "store?productName=" + productName + "&colorName=" + id;
//                    window.location.href = "/Durian_Shop/product-detail?productName=" + productName + "&colorName=" + id;
                };


                const handleLoadMoreColor = () => {
                    const btn = document.getElementById('load-more-btn');
                    const hiddens = document.getElementById('hidden-checkboxes');

                    btn.addEventListener('click', () => {
                        if (hiddens.classList.contains('hidden')) {
                            hiddens.classList.remove('hidden');
                            btn.textContent = 'Hide';
                        } else {
                            hiddens.classList.add('hidden');
                            btn.textContent = 'Load More';
                        }
                    });
                };
                handleLoadMoreColor();

                var pagination = `<a href="store?o=${tag==1?"1":tag-1}&f=${f}&tag=${tag==1?"1":tag-1}&order=${order}&sto=${sto}&col=${col}&min=${min}&max=${max}&sr=${sr}" class="${tag==1?"disabled":""}">&laquo;</a>`;
                pagination += `<c:forEach begin="1" end="${totalPage}" var="page">
                <c:if test="${page==1||page==2||page==totalPage-1||page==totalPage}">
    <a href="store?o=${page}&f=${f}&tag=${page}&order=${order}&sto=${sto}&col=${col}&min=${min}&max=${max}&sr=${sr}" class="${tag==page?"active":""}">${page}</a> </c:if>
                <c:if test="${page>2&&page<totalPage-1&&(page>tag+1||page<tag-1)&&(page==tag-2||page==tag+2)}"><a>...</a></c:if>
                <c:if test="${page>2&&page<totalPage-1&&(page==tag||page==tag-1||page==tag+1 )}">
    <a href="store?o=${page}&f=${f}&tag=${page}&order=${order}&sto=${sto}&col=${col}&min=${min}&max=${max}&sr=${sr}" class="${tag==page?"active":""}">${page}</a> </c:if>
                                            
            </c:forEach>`;
                pagination += `<a href="store?o=${tag==totalPage?totalPage:tag+1}&f=${f}&tag=${tag==totalPage?totalPage:tag+1}&order=${order}&sto=${sto}&col=${col}&min=${min}&max=${max}" class="${tag==totalPage?"disabled":""}">&raquo;</a>`;
                const handlePaging = () => {
                    const pagin = document.getElementById("pagination");
                    pagin.innerHTML = pagination;
                };
                handlePaging();

                var selectedColor = [];
                const toggleColor = (colorId) => {
                    var index = selectedColor.indexOf(colorId);
                    if (index === -1) {
                        selectedColor.push(colorId);
                    } else {
                        selectedColor.splice(index, 1);
                        console.log(selectedColor);
                    }
                };

                const handleColorClick = (colorId) => {
                    toggleColor(colorId);
                };
                var selectedStorage = [];
                const toggleStorage = (storageId) => {
                    var index = selectedStorage.indexOf(storageId);
                    if (index === -1) {
                        selectedStorage.push(storageId);
                    } else {
                        selectedStorage.splice(index, 1);
                        console.log(selectedStorage);
                    }
                };

                const handleStorageClick = (storageId) => {
                    toggleStorage(storageId);
                };
                var minValue = 0;
                var maxValue = 3000;
                var search = "";
                var queryString = window.location.search;
                var urlParams = new URLSearchParams(queryString);
                var min = urlParams.get('min');
                var max = urlParams.get('max');
                if (min !== null && max !== null) {
                    minValue = parseInt(min);
                    maxValue = parseInt(max);
                }
                
                
                const handleSearch = () => {
                    const submitButton = document.getElementById('submitSearch');

                    submitButton.addEventListener('click', function () {
                        const searchInput = document.getElementById('searchInput');
                        const searchValue = searchInput.value;
                         window.location.href = 'store?o=1&f=${f}&tag=1&order=${order}&sto=' + selectedStorage.join(',') + '&col=' + selectedColor.join(',') + "&min=" + minValue + "&max=" + maxValue + "&sr=" + searchValue;
                    });
                };
                handleSearch();



                const handleFilter = () => {
                    var queryString = window.location.search;
                    var urlParams = new URLSearchParams(queryString);
                    var sto = urlParams.get('sto');
                    var col = urlParams.get('col');
                    var sr = urlParams.get('sr');

                    if (sto !== null && col !== null) {
                        selectedColor.push(...col.split(",").filter(Boolean));
                        selectedStorage.push(...sto.split(",").filter(Boolean));
                    }
                    const filter = document.getElementById('filter-btn');
                    filter.addEventListener('click', () => {

                        window.location.href = 'store?o=1&f=${f}&tag=1&order=${order}&sto=' + selectedStorage.join(',') + '&col=' + selectedColor.join(',') + "&min=" + minValue + "&max=" + maxValue + "&sr=${sr}";
                    });
                };
                handleFilter();
        </script>

    </body>
</html>
