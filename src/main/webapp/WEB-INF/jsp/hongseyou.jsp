<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh">
<head>
    <c:set var="pageSpecificCss" value="hongseyou.css" scope="request"/>
    <jsp:include page="common/_head.jsp"/>
</head>
<body>
<div class="page-wrapper">
    <jsp:include page="common/_header_nav.jsp"/>
    <jsp:include page="common/_hero_search.jsp"/>

    <div class="basic page-content red-tourism-carousel-section">
        <div class="container">
            <div class="carousel-header">
                <h2><c:out value="${carouselHeaderSection.title}" default="红色景点 · 影像集锦"/></h2>
                <p><c:out value="${carouselHeaderSection.subtitle}"
                          default="滑动浏览，感受震撼人心的红色历史瞬间和壮丽景色。"/></p>
            </div>

            <div class="carousel-container">
                <div class="carousel-slides">
                    <c:forEach items="${carouselSlides}" var="slide" varStatus="loop">
                        <div class="carousel-slide ${loop.first ? 'active' : ''} <c:if test='${not empty slide.linkedArticleSlug}'>js-clickable-card</c:if>"
                             data-article-slug="<c:out value='${slide.linkedArticleSlug}'/>">
                            <img src="${pageContext.request.contextPath}/${slide.imageUrl}"
                                 alt="<c:out value='${slide.imageAltText}'/>">
                            <div class="carousel-caption">
                                <h3><c:out value="${slide.captionTitle}"/></h3>
                                <p><c:out value="${slide.captionText}"/></p>
                                <c:if test="${not empty slide.linkUrl && empty slide.linkedArticleSlug}">
                                    <a href="${pageContext.request.contextPath}/${slide.linkUrl}"
                                       class="carousel-caption-link">了解详情</a>
                                </c:if>
                            </div>
                        </div>
                    </c:forEach>
                </div>

                <button class="carousel-control prev" aria-label="上一张幻灯片">❮</button>
                <button class="carousel-control next" aria-label="下一张幻灯片">❯</button>

                <div class="carousel-dots">
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="common/_footer.jsp"/>

<script>
    document.addEventListener('DOMContentLoaded', function () {
        const carouselSlidesContainer = document.querySelector('.carousel-slides');
        if (!carouselSlidesContainer) return;

        const slides = Array.from(carouselSlidesContainer.children);
        const nextButton = document.querySelector('.carousel-control.next');
        const prevButton = document.querySelector('.carousel-control.prev');
        const dotsContainer = document.querySelector('.carousel-dots');

        let currentIndex = 0;
        const totalSlides = slides.length;

        function createDots() {
            if (!dotsContainer) return;
            dotsContainer.innerHTML = '';
            slides.forEach((_, index) => {
                const dot = document.createElement('button');
                dot.classList.add('carousel-dot');
                if (index === currentIndex) dot.classList.add('active');
                dot.setAttribute('aria-label', `跳转到幻灯片 ${index + 1}`);
                dot.addEventListener('click', () => {
                    goToSlide(index);
                });
                dotsContainer.appendChild(dot);
            });
        }

        function updateDots() {
            if (!dotsContainer) return;
            const dots = Array.from(dotsContainer.children);
            dots.forEach((dot, index) => {
                dot.classList.toggle('active', index === currentIndex);
            });
        }

        function goToSlide(index) {
            if (totalSlides === 0) return;
            slides[currentIndex].classList.remove('active');
            slides[index].classList.add('active');
            currentIndex = index;
            updateDots();
        }

        function showNextSlide() {
            if (totalSlides === 0) return;
            let nextIndex = (currentIndex + 1) % totalSlides;
            goToSlide(nextIndex);
        }

        function showPrevSlide() {
            if (totalSlides === 0) return;
            let prevIndex = (currentIndex - 1 + totalSlides) % totalSlides;
            goToSlide(prevIndex);
        }

        if (nextButton) {
            nextButton.addEventListener('click', showNextSlide);
        }
        if (prevButton) {
            prevButton.addEventListener('click', showPrevSlide);
        }

        document.addEventListener('keydown', (event) => {
            if (document.activeElement && ['INPUT', 'TEXTAREA', 'BUTTON'].includes(document.activeElement.tagName)) {
                return;
            }
            if (event.key === 'ArrowRight') {
                showNextSlide();
            } else if (event.key === 'ArrowLeft') {
                showPrevSlide();
            }
        });

        if (totalSlides > 0) {
            createDots();
        }
    });
</script>
<script src="${pageContext.request.contextPath}/js/card_click_handler.js" defer></script>
<script src="${pageContext.request.contextPath}/js/search_handler.js" defer></script>
</body>
</html>