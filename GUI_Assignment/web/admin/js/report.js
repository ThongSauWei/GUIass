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
        option.remove();
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
        option.remove();
    });
}

function handleAllSelect() {
    const selectElement = document.getElementById('my-select');
    const options = Array.from(selectElement.options);
    const values = options.map(option => option.value);
    const inputElement = document.getElementById('my-input');
    inputElement.value = values.join(',');

    options.forEach(option => {
        option.style.display = 'none';
        const newOption = document.createElement('option');
        newOption.value = option.value;
        newOption.text = option.text;
        const secondSelect = document.getElementById('second-select');
        secondSelect.add(newOption);
        option.remove();
    });
}

function revertAllSelect() {
    const selectElement = document.getElementById('my-select');
    const inputElement = document.getElementById('my-input');
    const secondSelect = document.getElementById('second-select');

    const options = Array.from(secondSelect.options);

    options.forEach(option => {
        option.style.display = 'none';
        const newOption = document.createElement('option');
        newOption.value = option.value;
        newOption.text = option.text;
        selectElement.add(newOption);

        const currentValues = inputElement.value.split(',');

        const newValues = currentValues.filter(value => value !== newOption.value);
        inputElement.value = newValues.join(',');
        option.remove();
    });
}
