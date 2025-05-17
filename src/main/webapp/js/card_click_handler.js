document.addEventListener('DOMContentLoaded', function() {
    document.addEventListener('click', function(event) {
        let clickedCard = event.target.closest('.js-clickable-card');

        if (clickedCard) {
            const articleSlug = clickedCard.dataset.articleSlug;
            if (articleSlug && articleSlug.trim() !== '') {
                const targetIsIndependentLink = event.target.tagName === 'A' && !event.target.closest('.js-clickable-card[data-article-slug]');

                if (targetIsIndependentLink && event.target.href) {
                    return;
                }
                if(event.target.tagName === 'A' && clickedCard.contains(event.target)){
                    event.preventDefault();
                }
                const contextPath = window.APP_CONTEXT_PATH || '';
                window.location.href = contextPath + '/article/' + articleSlug;
            } else if (clickedCard.dataset.linkUrl && clickedCard.dataset.linkUrl.trim() !== '') {}
        }
    });
});