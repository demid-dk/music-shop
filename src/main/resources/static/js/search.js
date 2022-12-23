$("#searchBtn").on("click", (event) => {
    $("#searchForm").toggleClass('hidden')
    $("#searchField").focus()
});

$("#searchField").focusout((event) => {
    $("#searchForm").toggleClass('hidden')
});