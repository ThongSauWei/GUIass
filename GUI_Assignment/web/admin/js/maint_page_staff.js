
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
            confirmPasswordField.focus();
            return false;
        } else {
            confirmPasswordField.style.borderColor = "";
            errorDiv.innerHTML = "";
            return true;
        }

    } else {
        confirmPasswordField.style.borderColor = "";
        errorDiv.innerHTML = "";
        passwordField.focus();
    }
}
function isGregorianDate() {
    // Get the date input element by id
    const dateInput = document.getElementById("date");

    // Parse the date value into a Date object
    const date = new Date(dateInput.value);

    // Check if the year is within the valid range for the Gregorian calendar
    const year = date.getFullYear();
    if (year < 1582 || year > 9999) {
        return false;
    }

    // Check if the month is within the valid range for the Gregorian calendar
    const month = date.getMonth();
    if (year === 1582 && month < 9) {
        return false;
    }

    // All checks passed, date is in the Gregorian calendar
    return true;
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

    if (!emailValid())
        isValid = false;
    if (!phoneNumValid())
        isValid = false;
    if (!icValid())
        isValid = false;
    if (!passwordValid())
        isValid = false;

    if (isValid) {
        if (isInputFieldEmpty(name)) {
            isValid = false;
        }
        if (isInputFieldEmpty(phone_num)) {
            isValid = false;
        }
        if (isInputFieldEmpty(email)) {
            isValid = false;
        }
        if (isInputFieldEmpty(birthday)) {
            isValid = false;
        }
        if (isInputFieldEmpty(ic)) {
            isValid = false;
        }
        if (isInputFieldEmpty(password)) {
            isValid = false;
        }
        if (isInputFieldEmpty(cf_password)) {
            isValid = false;
        }
    }

    return isValid;
}
function deleteStaff(id) {
    if (confirm('Are you sure?'))
        window.location.href = "staff_maint.jsp?id=" + id + "&delete=1";
}
function emailValid() {
    return validateInput('email', /^[^\s@]+@[^\s@]+\.[^\s@]+$/, 'Please enter a valid email address!');
}
function phoneNumValid() {
    return validateInput('phone_num', /^[0-9]{10,11}$/, 'Please enter a 10 or 11-digit numeric value for Phone Number!');
}
function icValid() {
    return validateInput('ic', /^[0-9]{12}$/, 'Please enter a 12-digit numeric value for IC!');
}
function passwordValid() {
    return validateInput('password', /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$/, 'Please enter a password with at least 8 characters and containing both letters and numbers!');
}
function isValidDate(inputField) {

    const date = new Date(inputField.value);

    const errorDiv = document.getElementById(inputField.id + "_error");

    const year = date.getFullYear();
    if (year < 1582 || year > 9999) {
        const errorMessage = "pls fill in GregorianDate";
        inputField.style.borderColor = "red";
        errorDiv.innerHTML = errorMessage;
        inputField.focus();
        return false;
    }

    const month = date.getMonth();
    if (year === 1582 && month < 9) {
        const errorMessage = "pls fill in GregorianDate";
        inputField.style.borderColor = "red";
        errorDiv.innerHTML = errorMessage;
        inputField.focus();
        return false;
    }

    return true;
}