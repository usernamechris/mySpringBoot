package com.han.domain;

import ch.qos.logback.classic.boolex.GEventEvaluator;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@ToString
@Entity
@Table(name = "tbl_pdsfiles")
@EqualsAndHashCode(of="fno")
public class PDSFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fno;

    private String pdsfile;
}
