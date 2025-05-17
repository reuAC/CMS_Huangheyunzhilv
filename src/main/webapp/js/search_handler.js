document.addEventListener('DOMContentLoaded', function() {
    function performSearch(searchTerm) {
        if (searchTerm && searchTerm.trim() !== '') {
            const encodedSearchTerm = encodeURIComponent(searchTerm.trim());
            const contextPath = window.APP_CONTEXT_PATH || '';
            window.location.href = contextPath + '/search?q=' + encodedSearchTerm;
        } else {
            console.log("搜索词为空，不执行搜索。");
        }
    }

    const navSearchInput = document.getElementById('navSearchInput');
    const navSearchIcon = document.getElementById('navSearchIcon');

    if (navSearchInput && navSearchIcon) {
        navSearchIcon.addEventListener('click', function() {
            performSearch(navSearchInput.value);
        });
        navSearchInput.addEventListener('keypress', function(event) {
            if (event.key === 'Enter' || event.keyCode === 13) {
                event.preventDefault();
                performSearch(navSearchInput.value);
            }
        });
    }

    const heroSearchInput = document.getElementById('heroSearchInput');
    const heroSearchIcon = document.getElementById('heroSearchIcon');

    if (heroSearchInput && heroSearchIcon) {
        heroSearchIcon.addEventListener('click', function() {
            performSearch(heroSearchInput.value);
        });
        heroSearchInput.addEventListener('keypress', function(event) {
            if (event.key === 'Enter' || event.keyCode === 13) {
                event.preventDefault();
                performSearch(heroSearchInput.value);
            }
        });
    }

});