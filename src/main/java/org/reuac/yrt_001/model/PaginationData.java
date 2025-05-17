package org.reuac.yrt_001.model;

public class PaginationData {
    private final int currentPage;
    private final int totalPages;
    private final int startPage;
    private final int endPage;
    private final long totalResults;

    public PaginationData(int currentPage, int totalPages, long totalResults) {
        this.currentPage = currentPage;
        this.totalPages = totalPages;
        this.totalResults = totalResults;

        int maxPagesToShow = 5;
        int halfPagesToShow = maxPagesToShow / 2;

        if (totalPages <= maxPagesToShow) {
            this.startPage = 1;
            this.endPage = totalPages;
        } else if (currentPage <= halfPagesToShow) {
            this.startPage = 1;
            this.endPage = maxPagesToShow;
        } else if (currentPage + halfPagesToShow >= totalPages) {
            this.startPage = totalPages - maxPagesToShow + 1;
            this.endPage = totalPages;
        } else {
            this.startPage = currentPage - halfPagesToShow;
            this.endPage = currentPage + halfPagesToShow;
        }
    }


    public int getCurrentPage() {
        return currentPage;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public int getStartPage() {
        return startPage;
    }

    public int getEndPage() {
        return endPage;
    }

    public long getTotalResults() {
        return totalResults;
    }
}