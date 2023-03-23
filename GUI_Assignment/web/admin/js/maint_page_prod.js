function validateForm() {
    const id = document.getElementById("id");
    const name = document.getElementById("name");
    const description = document.getElementById("description");
    const price = document.getElementById("price");

    var isValid = true;
    if (isInputFieldEmpty(name)) {
        isValid = false;
    }
    if (isInputFieldEmpty(price)) {
        isValid = false;
    }

    if (isValid) {
        if (!isValidPrice)
            isValid = false;
    }

    return isValid;
}
function isValidPrice() {
    return validateInput('price', /^\d+(\.\d{1,2})?$/, 'Please enter a valid price (e.g. 10.99)');
}

function isPriceKey(evt) {
    var charCode = (evt.which) ? evt.which : evt.keyCode;
    if (charCode !== 46 && charCode > 31 && (charCode < 48 || charCode > 57))
        return false;
    return true;
}
function formatPrice(input) {
    let price = parseFloat(input.value);

    if (isNaN(price)) {
        price = 0;
    }

    const formattedPrice = price.toFixed(2);

    input.value = formattedPrice;
}
function cancel() {
    window.location.href = "prod_list.jsp";
}
