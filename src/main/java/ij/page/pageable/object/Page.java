package ij.page.pageable.object;

import lombok.Getter;

/**
 * 이 클래스는 페이지 정보를 포함하는 제네릭 클래스입니다.
 * `Pageable` 객체와 페이지 데이터(`data`)를 포함합니다.
 *
 * @param <T> 페이지에 포함될 데이터의 타입
 */
@Getter
public class Page<T> {

    private Pageable pageable;

    private T data;

    /**
     * `Page` 클래스의 생성자입니다.
     *
     * @param pageable 페이지 네이션 정보를 담고 있는 `Pageable` 객체.
     * @param data 페이지에 포함될 데이터.
     */
    public Page(Pageable pageable, T data) {
        this.pageable = pageable;
        this.data = data;
    }

}
