function handleMultipleSelect() {
    const selectElement = document.getElementById('my-select');
    const selectedOptions = Array.from(selectElement.selectedOptions);
    const values = selectedOptions.map(option => option.value);
    const inputElement = document.getElementById('my-input');
    inputElement.value = inputElement.value + ',' + values.join(',');

    selectedOptions.forEach(option => {
        option.selected = false;
        option.style.display = 'none';
        const newOption = document.createElement('option');
        newOption.value = option.value;
        newOption.text = option.text;
        const secondSelect = document.getElementById('second-select');
        secondSelect.add(newOption);
    });
}

function revertMultipleSelect() {
    const selectElement = document.getElementById('my-select');
    const inputElement = document.getElementById('my-input');
    const secondSelect = document.getElementById('second-select');

    const selectedOption = Array.from(secondSelect.selectedOptions);

    selectedOption.forEach(option => {
        option.selected = false;
        option.style.display = 'none';
        const newOption = document.createElement('option');
        newOption.value = option.value;
        newOption.text = option.text;
        selectElement.add(newOption);

        const currentValues = inputElement.value.split(',');

        const newValues = currentValues.filter(value => value !== newOption.value);
        inputElement.value = newValues.join(',');
    });
}


function revertMultipleSelect2() {
    const selectElement = document.getElementById('my-select');
    const inputElement = document.getElementById('my-input');
    const secondSelect = document.getElementById('second-select');

    const selectedOption = secondSelect.querySelector('option:checked');
    if (selectedOption) {
        selectedOption.remove();
        const selectedValue = selectedOption.value;
        const currentValues = inputElement.value.split(',');
        const newValues = currentValues.filter(value => value !== selectedValue);
        inputElement.value = newValues.join(',');
    }

    Array.from(selectElement.options).forEach(option => {
        option.style.display = '';
    });
}