package com.han.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@ToString(exclude = "files")
@Entity
@Table(name = "tbl_pds")
@EqualsAndHashCode(of="pid")
public class PDSBoard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pid;

    private String pname;

    private String pwriter;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "pdsno")
    private List<PDSFile> files;
}
