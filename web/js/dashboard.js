const body = document.querySelector("body");
const sideBar = document.querySelector(".sidebar");
const toggle = document.querySelector(".toggle");
const modeSwitch = document.querySelector(".toggle-switch");
const modeText = document.querySelector(".mode-text");

modeSwitch.addEventListener("click", () => {
  body.classList.toggle("dark");
  if (body.classList.contains("dark")) {
    modeText.innerText = "Light Mode";
  } else {
    modeText.innerText = "Dark Mode";
  }
});

toggle.addEventListener("click", () => {
  sideBar.classList.toggle("close");
});

const menuItems = document.querySelectorAll(".admin-submenu-item");
const subMenu = document.querySelectorAll(".sub-menu");

menuItems.forEach((item, index) => {
  item.addEventListener("click", () => {
    item.classList.toggle("trans-arrow");
    subMenu.forEach((subMenu) => {
      subMenu.classList.toggle("show-submenu");
    });
  });
});
