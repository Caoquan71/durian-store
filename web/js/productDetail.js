var slideIndex = 1;

const moveImage = (move) => {
    showImage((slideIndex += move));
};

const showImage = () => {
    let image = document.getElementsByClassName("mySlides");
    let preBtn = document.getElementById("pre-slide");
    let nextBtn = document.getElementById("next-slide");
    slideIndex === 1
            ? (preBtn.style.display = "none")
            : (preBtn.style.display = "block");
    slideIndex === image.length
            ? (nextBtn.style.display = "none")
            : (nextBtn.style.display = "block");
    for (let i = 0; i < image.length; i++) {
        image[i].style.display = "none";
    }
    image[slideIndex - 1].style.display = "block";
};
showImage(slideIndex);

const chooseColorId = () => {
    let buttons = document.querySelectorAll(".btn-color");
    let id = "";
    buttons.forEach(function (button) {
        button.addEventListener("click", function (event) {
            id = event.target.id;
            sendDataColor(id);
        });
    });
};
chooseColorId();


//function changeImage(id) {
//  const xhr = new XMLHttpRequest();
//  xhr.onreadystatechange = function() {
//  if (this.readyState === XMLHttpRequest.DONE) {
//    if (this.status === 200) {
//        document.documentElement.innerHTML = this.responseText;
//    } else {
//      console.log('Đã có lỗi:', this.status);
//    }
//  }
//};
//xhr.open("GET", "/Durian_Shop/product-detail?colorName=" + id, true);
//xhr.send();
//}
var queryString = window.location.search;
var urlParams = new URLSearchParams(queryString);
var productName = urlParams.get('productName');
const displayStorage = () => {
    if (queryString.includes('colorName')) {
        document.getElementById("storage-options").classList.remove("disabled");
    }
};
displayStorage();

const choosenStorage = () => {
    if (queryString.includes('storageId')) {
        var storageId = urlParams.get('storageId');
        storageId = "storage" + storageId;
        document.getElementById(storageId).style.border = "2px solid blue";
        const cartContainer = document.getElementById("add-to-cart-container");
        cartContainer.classList.remove("hidden");
    }
};
choosenStorage();
const sendDataColor = (id) => {
    window.location.href = "/Durian_Shop/product-detail?productName=" + productName + "&colorName=" + id;
};

//const showColor = (id) => {
//  text = "Color ";
//  text = text + " - " + id;
//  document.getElementById("color-name").innerText = text;
//};

const showText = () => {
    let review = document.querySelector(".review");
    let description = document.querySelector(".description");

    review.addEventListener("click", function () {
        document.querySelector(".review-content").style.display = "block";
        document.querySelector(".description-content").style.display = "none";
        description.style.border = "none";
        review.style.borderTop = "2px solid green";
        review.style.borderRight = "0.5px solid black";
        review.style.borderLeft = "0.5px solid black";
    });
    description.addEventListener("click", function () {
        document.querySelector(".description-content").style.display = "flex";
        document.querySelector(".review-content").style.display = "none";
        review.style.border = "none";
        description.style.borderTop = "2px solid green";
        description.style.borderRight = "0.5px solid black";
    });
};
showText();

const chooseStorage = () => {
    let buttons = document.querySelectorAll(".storage");
    let id = "";
    buttons.forEach(function (button) {
        button.addEventListener("click", function (event) {
            id = event.target.id;
            sendDataStorage(id);
        });
    });
}
chooseStorage();

const sendDataStorage = (id) => {
    var queryString = window.location.search;
    var urlParams = new URLSearchParams(queryString);
    var productName = urlParams.get('productName');
    var colorName = urlParams.get('colorName');
    var arr = id.match(/\d+/);
    var id = parseInt(arr[0]);
    window.location.href = "/Durian_Shop/product-detail?productName=" + productName + "&colorName=" + colorName + "&storageId=" + id;
};

