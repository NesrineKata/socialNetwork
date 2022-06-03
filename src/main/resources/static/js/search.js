function onSearchClick() {
    const searchQuery = document.getElementById('searchInput');
    const searchInput = searchQuery.value;
    console.log(searchInput);

    window.location.replace('/search/' + searchInput);
}