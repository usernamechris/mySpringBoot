package com.han.persistence;

import com.han.domain.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

public interface BoardRepository extends CrudRepository<Board, Long>, QuerydslPredicateExecutor<Board> {

    public List<Board> findBoardByTitle(String title);

    @Query("select b From Board b where b.title LIKE %?1% And b.bno > 0 Order by b.bno desc") // ?1은 첫번째로 전달되는 파라미터
    public List<Board> finddByTitle(String title);

    @Query("select b from Board b where b.content like %:content% and b.bno > 0 order by b.bno desc")
    public List<Board> finddByContent(@Param("content") String content);

    @Query("select b from #{#entityName} b where b.writer Like %?1% and b.bno > 0 order by b.bno desc)")
    public List<Board> findByWriter(String writer);
//    public Collection<Board> findByWriter(String writer);

    // 필요한 컬럼만 추출
    @Query("select b.bno, b.title, b.writer, b.regdate from Board b where b.title like %?1% And b.bno > 0 order by b.bno desc")
    public List<Object[]> finddByTitle2(String title);

    // nave query 사용
    @Query(value = "select bno, title, writer from tbl_boards where title like concat('%', ?1, '%') and bno > 0 order by bno desc", nativeQuery = true)
    public List<Object[]> finddByTitle3(String title);

    // paging
    @Query("select b from Board b where b.bno > 0 order by b.bno desc ")
    public List<Board> finddByPage(Pageable pageable);

    // like
    public Collection<Board> findByWriterLike(String writer);

    // like % keyword %
    public Collection<Board> findByWriterContaining(String writer);

    // like keyword %
    public Collection<Board> findByWriterStartingWith(String writer);

    // like % keyword
    public Collection<Board> findByWriterEndingWith(String writer);

    public Collection<Board> findByTitleContainingOrContentContaining(String title, String content);

    // title like % ? % AND BNO > ?
    public Collection<Board> findByTitleContainingAndBnoGreaterThan(String keyword, Long num);

    // bno > ? ORDER BY bno DESC
    public Collection<Board> findByBnoGreaterThanOrderByBnoDesc(Long bno);

    // bno > ? ORDER BY bno DESC limit ?, ?
    public List<Board> findByBnoGreaterThanOrderByBnoDesc(Long bno, Pageable paging);

//    public List<Board> findByBnoGreaterThan(Long bno, Pageable paging);

    public Page<Board> findByBnoGreaterThan(Long bno, Pageable paging);

}
