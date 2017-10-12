package com.han.persistence;

import com.han.domain.FreeBoard;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FreeBoardRepository extends CrudRepository<FreeBoard, Long> {

    public List<FreeBoard> findByBnoGreaterThan(Long bno, Pageable page);

    @Query("select b.bno, b.title, count(r) " +
    " from FreeBoard b left outer join b.replies r " +
    " where b.bno > 0 group by b ")
    public List<Object[]> getPage(Pageable page);
}
