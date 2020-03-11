package br.com.selecao.locadora.entity;

import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

//@Data
@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class BaseEntity {

/*
    @Column(name = "deleted", columnDefinition = "Bit(1) default false")
    private boolean deleted = false;
*/

/*
    @Column(name = "DataChange_CreatedBy", nullable = false)
    private String dataChangeCreatedBy;
*/

    @CreatedDate
    @Column(name = "CREATEAT", nullable = false)
    private Date createAt;

/*
    @Column(name = "DataChange_LastModifiedBy")
    private String dataChangeLastModifiedBy;

    @Column(name = "DataChange_LastTime")
    private Date dataChangeLastModifiedTime;
*/

    @PrePersist
    protected void prePersist() {
        if (this.createAt == null) createAt = new Date();
//        if (this.dataChangeLastModifiedTime == null) dataChangeLastModifiedTime = new Date();
    }

/*
    @PreUpdate
    protected void preUpdate() {
        this.dataChangeLastModifiedTime = new Date();
    }

    @PreRemove
    protected void preRemove() {
        this.dataChangeLastModifiedTime = new Date();
    }
*/
}
