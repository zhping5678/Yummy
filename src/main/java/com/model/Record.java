package com.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.util.CheckState;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "record")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Record {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    private Date applyTime;//申请时间

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    private Date checkTime;//审核时间

    private String store_id;

    @Enumerated(EnumType.STRING)
    private CheckState state;//审核状态,未审核/已通过/不通过
}
