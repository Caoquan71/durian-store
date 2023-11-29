/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */
const username = document.getElementById('username');
const password = document.getElementById('password');
const repassword = document.getElementById('repassword');
const phone = document.getElementById('phone');
const answer = document.getElementById('answer');
const SignUp = document.getElementById('InfomationForm');
//Username co the chua chu in hoa, chu thuong, so va phai co it nhat 1 ki tu, ko bao gom khoang trong va cac ki tu dac biet  
const reg_user = /^[A-Za-z0-9]+$/;
//Password phai dai tu 8 ki tu va co chua 1 ki tu dac biet
const reg_password = /^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*])[A-Za-z0-9!@#$%^&\*]{8,31}$/;
//Phone phai co 10 so
const reg_phone = /\d{10}/;

//Check tung su kien:
function checkValid(name, Variable, regax) {
    let VariableValue = Variable.value;
    let error = name + "error";
    if (!regax.test(VariableValue)) {
        document.getElementById(error).style.display = 'block';
        return 0;
    } else {
        document.getElementById(error).style.display = 'none';
        return 1;
    }
}
;

username.addEventListener('change', function () {
    checkValid('username', username, reg_user);
});

//Check pass
password.addEventListener('change', function () {
    checkValid('password', password, reg_password);
});

//check repass
repassword.addEventListener('change', function () {
    checkRepass(repassword, password);
});

function checkRepass(repassword, password) {
    let rePass = repassword.value;
    let Pass = password.value;
    if (rePass === Pass) {
        document.getElementById('repassworderror').style.display = 'none';
        return 1;
    } else {
        document.getElementById('repassworderror').style.display = 'block';
        return 0;
    }
}

//Phone          
phone.addEventListener('change', function () {
    checkValid('phone', phone, reg_phone);
});
//Submit          
SignUp.addEventListener('submit', function (event) {
    if (checkValid('username', username, reg_user) && checkValid('password', password, reg_password)
            && checkRepass(repassword, password) && checkValid('phone', phone, reg_phone) && checkValid('answer', answer, reg_user)) {
        return;
    } else {
        event.preventDefault();
        alert('Opps, May be you have some issue');
    }
});

