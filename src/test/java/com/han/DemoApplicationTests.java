package com.han;

import com.han.domain.Board;
import com.han.persistence.BoardRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

	@Autowired
	private BoardRepository repo;

	@Test
	public void testInsert200() {
		for (int i = 1; i <= 200; i++) {
            Board board = new Board();
            board.setTitle("title.." + i);
            board.setContent("content.." + i);
            board.setWriter("user0" + (i % 10));
            repo.save(board);
        }
	}

    @Test
    public void testByTitle() {
        repo.findBoardByTitle("title..177")
                .forEach(board -> System.out.println(board));
    }

    @Test
    public void testByWriter() {
        Collection<Board> results = repo.findByWriter("user00");
        results.forEach(
                board -> System.out.println(board)
        );
    }

    @Test
    public void testByWriterContaining() {
        Collection<Board> results = repo.findByWriterContaining("05");
        results.forEach(
                board -> System.out.println(board)
        );
    }

    @Test
    public void testByTitleAndBno() {
        Collection<Board> results = repo.findByTitleContainingAndBnoGreaterThan("5", 50L);
        results.forEach(
                board -> System.out.println(board)
        );
    }

    @Test
    public void testByBnoOrderBy() {
        Collection<Board> results = repo.findByBnoGreaterThanOrderByBnoDesc(90L);
        results.forEach(
                board -> System.out.println(board)
        );
    }

    @Test
    public void testBnoOrderByPaging() {
        Pageable paging = PageRequest.of(0, 10);
        Collection<Board> results = repo.findByBnoGreaterThanOrderByBnoDesc(0L, paging);
        results.forEach(
                board -> System.out.println(board)
        );
    }

    @Test
    public void testBnoPagingSort() {
        Pageable paging = PageRequest.of(0, 10, Sort.Direction.ASC, "bno");

        Page<Board> result = repo.findByBnoGreaterThan(0L, paging);

        System.out.println("현재 페이지의 정보: " + result.getNumber());
        System.out.println("한 페이지 크기: " + result.getSize());
        System.out.println("전체 페이지 수:" + result.getTotalPages());
        System.out.println("결과 데이터 수: " + result.getNumberOfElements());
        System.out.println("이전 페이지의 존재 여부: " + result.hasPrevious());
        System.out.println("다음 페이지의 존재 여부: " + result.hasNext());
        System.out.println("마지막 페이지 여부: " + result.isLast());
        System.out.println("다음 페이지 객체: " + result.nextPageable());
        System.out.println("이전 페이지 객체: " + result.previousPageable());
        System.out.println("Total Count: " + result.getTotalElements());
        System.out.println("결과 존재 여부: " + result.hasContent());
        System.out.println("검색 시 사용된 Sort 정보 : " + result.getSort());

        List<Board> list = result.getContent(); // 조회된 데이터

        list.forEach(board -> System.out.println(board));
    }

    @Test
    public void testByTitleWithQuery() {
	    repo.finddByTitle("17")
                .forEach(board -> System.out.println(board));
    }

    @Test
    public void testByTitleWithColumn() {
        repo.finddByTitle2("17")
                .forEach(arr -> System.out.println(Arrays.toString(arr)));
    }

    @Test
    public void testByTitleWithNativeQuery() {
        repo.finddByTitle3("17")
                .forEach(arr -> System.out.println(Arrays.toString(arr)));
    }

    @Test
    public void testByPaging() {
	    Pageable pageable = PageRequest.of(0, 10);
	    repo.finddByPage(pageable)
                .forEach(board -> System.out.println(board));
    }


}
