const handleSearchInput = () => {
    const searchInput = document.querySelector('#search-input');
    if(searchInput) {
        console.log('Found search input');
        searchInput.addEventListener('input', e => {
            e.preventDefault();
            console.log('Searching for...', e.target.value);
            console.log('Employee data: ', employees);
        })
    }
}

const init = () => {
    handleSearchInput();
}

document.addEventListener('DOMContentLoaded', () => init());