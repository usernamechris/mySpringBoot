package com.han;

import com.han.domain.PDSBoard;
import com.han.domain.PDSFile;
import com.han.persistence.PDSBoardRepository;
import lombok.extern.java.Log;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@RunWith(SpringRunner.class)
@SpringBootTest
@Log
@Commit
public class PDSBoardTests {

    @Autowired
    PDSBoardRepository repo;

    @Test
    public void testInsertPDS() {
        PDSBoard pds = new PDSBoard();
        pds.setPname("DOCUMENT 1 - 2");

        PDSFile file1 = new PDSFile();
        file1.setPdsfile("file1.doc");

        PDSFile file2 = new PDSFile();
        file2.setPdsfile("file2.doc");

        pds.setFiles(Arrays.asList(file1, file2));

        log.info("try to save pds");

        repo.save(pds);
    }

    @Transactional
    @Test
    public void testUpdateFileName1() {
        Long fno = 1L;
        String newName = "updatedFile1.doc";

        int count = repo.updatePDSFile(fno, newName);
        log.info("update count: " + count);
    }

    @Transactional
    @Test
    public void testUpdateFileName2() {
        String newName = "updatedFile2.doc";

        Optional<PDSBoard> result = repo.findById(1L);

        result.ifPresent(pds -> {
            log.info("데이터가 존재하므로 update 시도 ");
            PDSFile target = new PDSFile();
            target.setFno(2L);
            target.setPdsfile(newName);

            int idx = pds.getFiles().indexOf(target);
            if (idx > -1) {
                List<PDSFile> list = pds.getFiles();
                list.remove(idx);
                list.add(target);
            }

            repo.save(pds);
        });

    }

    @Transactional
    @Test
    public void deletePDSFile() {
        Long fno = 2L;

        int count = repo.deletePDSFile(fno);

        log.info("Delete PDSFile: " + count);
    }

    @Test
    public void insertDummies() {
        List<PDSBoard> list = new ArrayList<>();

        IntStream.range(1, 100).forEach(i -> {
            PDSBoard pds = new PDSBoard();
            pds.setPname("자료 " + i);

            PDSFile file1 = new PDSFile();
            file1.setPdsfile("file1.doc");

            PDSFile file2 = new PDSFile();
            file2.setPdsfile("file2.doc");

            pds.setFiles(Arrays.asList(file1, file2));

            log.info("try to save pds");

            list.add(pds);
        });

        repo.saveAll(list);
    }

    @Test
    public void viewSummary() {
        repo.getSummary().forEach(arr -> {
            log.info(Arrays.toString(arr));
        });
    }
}
