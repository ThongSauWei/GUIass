
function cf_password_onkeyup() {
    removeError(document.getElementById('cf_password'));
    removeError(document.getElementById('password'));
    arePasswordsMatching();
}

function arePasswordsMatching() {
    const passwordField = document.getElementById("password");
    const confirmPasswordField = document.getElementById("cf_password");
    const errorDiv = document.getElementById("cf_password_error");
    if (validateInput('password', /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$/,
            'Please enter a password with at least 8 characters and containing both letters and numbers!')) {
        if (passwordField.value !== confirmPasswordField.value) {
            const errorMessage = "Passwords do not match";
            confirmPasswordField.style.borderColor = "red";
            errorDiv.innerHTML = errorMessage;
            return false;
        } else {
            confirmPasswordField.style.borderColor = "";
            errorDiv.innerHTML = "";
            return true;
        }

    } else {
        isInputFieldEmpty(confirmPasswordField);
        return false;
    }
}


function validateForm() {
    const name = document.getElementById("name");
    const phone_num = document.getElementById("phone_num");
    const email = document.getElementById("email");
    const birthday = document.getElementById("birthday");
    const ic = document.getElementById("ic");
    const password = document.getElementById("password");
    const cf_password = document.getElementById("cf_password");

    var isValid = true;

        if (isInputFieldEmpty(name)) {
            name.focus();
            isValid = false;
        }
        if (isInputFieldEmpty(ic)) {
            name.focus();
            isValid = false;
        }
        if (isInputFieldEmpty(phone_num)) {
            phone_num.focus();
            isValid = false;
        }
        if (isInputFieldEmpty(email)) {
            email.focus();
            isValid = false;
        }
        if (isInputFieldEmpty(birthday)) {
            birthday.focus();
            isValid = false;
        }
        if (!icValid) {
            ic.focus();
            isValid = false;
        }
        if (isInputFieldEmpty(password)) {
            password.focus();
            isValid = false;
        }
        if (isInputFieldEmpty(cf_password)) {
            cf_password.focus();
            isValid = false;
        }

    if (isValid) {
    if (!emailValid())
        isValid = false;
    if (!phoneNumValid())
        isValid = false;
    if (!icValid())
        isValid = false;
    if (!passwordValid())
        isValid = false;
    if (!arePasswordsMatching()) {
        isValid = false;
        cf_password.focus();
    }
    if (!isValidDate) {
        isValid = false;
        birthday.focus();
    }
    }

    return isValid;
}
function deleteStaff(id) {
    if (confirm('Are you sure?'))
        window.location.href = "../../StaffMaint?id=" + id + "&delete=1";
}
function emailValid() {
    return validateInput('email', /^[^\s@]+@[^\s@]+\.[^\s@]+$/, 'Please enter a valid email address!');
}
function phoneNumValid() {
    return validateInput('phone_num', /^[0-9]{10,11}$/, 'Please enter a 10 or 11-digit numeric value for Phone Number!');
}
function icValid() {
    return validateInput('ic', /\d{6}[01][0-4]\d{4}$/, 'Please enter a 12-digit numeric value for IC!');
}
function passwordValid() {
    return validateInput('password', /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$/, 'Please enter a password with at least 8 characters and containing both letters and numbers!');
}
function isValidDate(inputField) {
    const s_date = inputField.value;

    const date = new Date(s_date);

    const errorDiv = document.getElementById(inputField.id + "_error");

    const year = date.getFullYear();
    if (year < 1582 || year > 9999 || s_date === "") {
        const errorMessage = "pls fill in GregorianDate";
        inputField.style.borderColor = "red";
        errorDiv.innerHTML = errorMessage;
        inputField.value = "";
        return false;
    }

    const month = date.getMonth();
    if (year === 1582 && month < 9 || s_date === "") {
        const errorMessage = "pls fill in GregorianDate";
        inputField.style.borderColor = "red";
        errorDiv.innerHTML = errorMessage;
        inputField.value = "";
        return false;
    }

    return true;
}
function cancel() {
    window.location.href = "staff_list.jsp";
}
