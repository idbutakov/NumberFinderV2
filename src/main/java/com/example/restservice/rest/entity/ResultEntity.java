package com.example.restservice.rest.entity;

import com.example.restservice.external.numberfinder.entity.Result;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResultEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 50)
    private String code;
    @Column
    private int number;
    @Column(length = 100)
    private String fileNames;
    @Column(length = 100)
    private String error;

    public ResultEntity(Result result,int number){
        this.code=result.getCode().asString();
        this.fileNames=result.getFileNames().toString().replaceAll("//[//]","");
        this.number=number;
    }

}
