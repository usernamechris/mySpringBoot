package com.han.persistence;

import com.han.domain.PDSBoard;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PDSBoardRepository extends CrudRepository<PDSBoard, Long> {

    @Modifying // @Query는 기본적으로 select만 지원하지만 @Modifying을 이용해 DML작업 처리
    @Query("update from PDSFile f set f.pdsfile = ?2 where f.fno = ?1 ")
    public int updatePDSFile(long fno, String newFileName);

    @Modifying
    @Query("delete from PDSFile f where f.fno = ?1")
    public int deletePDSFile(Long fno);

    @Query("select p, count(p) from PDSBoard p left join p.files f " +
    " on p.pid = f where p.pid > 0 group by p order by p.pid desc ")
    public List<Object[]> getSummary();
}
