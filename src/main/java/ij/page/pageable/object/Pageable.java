package ij.page.pageable.object;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

/**
 * 이 클래스는 페이지네이션을 위한 클래스입니다.
 * 현재 페이지, 총 페이지 수, 블록 내의 시작 및 끝 페이지 등
 * 페이지 네이션 세부 정보를 계산하며 페이지 및 블록을 탐색하기 위한 메서드를 제공합니다.
 */
@ToString
@Getter
public class Pageable {

    private final int DEFAULT_PAGE_SIZE = 10;
    private final int DEFAULT_BLOCK_SIZE = 5;

    private int page;
    private int totalCount;
    private int totalPageCount;
    private int startPage;
    private int endPage;

    private int firstPage;
    private int prevPage;
    private int nextPage;
    private int lastPage;

    private int totalBlockCount;
    private int block;
    private int prevBlock;
    private boolean hasPrevBlock = false;
    private int nextBlock;
    private boolean hasNextBlock = false;

    private int startIndex;
    private int endIndex;

    /**
     * 기본 페이지 크기와 블록 크기로 빌더 생성자.
     *
     * @param page 현재 페이지 번호.
     * @param totalCount 총 항목 수.
     */
    @Builder
    public Pageable(int page, int totalCount) {
        settings(page, totalCount, DEFAULT_PAGE_SIZE, DEFAULT_BLOCK_SIZE);
    }

    /**
     * 사용자 지정 페이지 크기와 블록 크기로 빌더 생성자.
     *
     * @param page 현재 페이지 번호.
     * @param totalCount 총 항목 수.
     * @param pageSize 페이지 당 항목 수.
     * @param blockSize 블록 당 페이지 수.
     */
    @Builder
    public Pageable(int page, int totalCount, int pageSize, int blockSize) {
        if (pageSize <= 0) {
            pageSize = DEFAULT_PAGE_SIZE;
        }

        if (blockSize <= 0) {
            blockSize = DEFAULT_BLOCK_SIZE;
        }

        settings(page, totalCount, pageSize, blockSize);
    }

    /**
     * 페이지 네이션 설정을 초기화합니다.
     *
     * @param page 현재 페이지 번호.
     * @param totalCount 총 항목 수.
     * @param pageSize 페이지 당 항목 수.
     * @param blockSize 블록 당 페이지 수.
     */
    private void settings(int page, int totalCount, int pageSize, int blockSize) {
        this.page = page <= 0 ? 1 : page;
        this.totalCount = totalCount;

        this.totalPageCount = (int) Math.ceil(totalCount * 1.0 / pageSize);
        this.totalBlockCount = (int) Math.ceil(totalPageCount * 1.0 / blockSize);

        this.block = (int) Math.ceil((this.page * 1.0) / blockSize);

        this.startPage = (block - 1) * blockSize + 1;
        this.endPage = (startPage - 1) + blockSize;

        if (endPage >= totalPageCount) {
            this.endPage = totalPageCount;
        }

        if (totalPageCount == 0) {
            this.endPage = 1;
        }

        this.firstPage = 1;
        this.lastPage = totalPageCount;

        this.prevPage = this.page - 1;
        if (prevPage < 1) this.prevPage = 1;
        this.nextPage = this.page + 1;
        if (totalPageCount < nextPage) nextPage = totalPageCount;

        this.prevBlock = (block - 2) * blockSize + 1;
        if (prevBlock < 1) this.prevBlock = 1;
        this.hasPrevBlock = block > 1;

        this.nextBlock = block * blockSize + 1;
        if (nextBlock > totalPageCount) nextBlock = totalPageCount;
        this.hasNextBlock = totalBlockCount > block;

        this.startIndex = (this.page - 1) * pageSize;
        this.endIndex = pageSize;
    }


    /**
     * 이전 블록이 있는지 확인합니다.
     *
     * @return 이전 블록이 있으면 true, 없으면 false.
     */
    public boolean hasPrevBlock() {
        return this.hasPrevBlock;
    }

    /**
     * 다음 블록이 있는지 확인합니다.
     *
     * @return 다음 블록이 있으면 true, 없으면 false.
     */
    public boolean hasNextBlock() {
        return this.hasNextBlock;
    }

    /**
     * 시작 인덱스를 반환합니다.
     *
     * @return 시작 인덱스.
     */
    public int getOffset() {
        return this.startIndex;
    }

    /**
     * 끝 인덱스를 반환합니다.
     *
     * @return 끝 인덱스.
     */
    public int getLimit() {
        return this.endIndex;
    }

}
