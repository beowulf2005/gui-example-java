package org.gui.example.entity.base;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class RowBase {

    public static final int STR_LEN2 = 2;

    public static final int STR_LEN4 = 4;

    public static final int STR_LEN6 = 6;

    public static final int STR_LEN10 = 10;
    
    public static final int STR_LEN20 = 20;

    public static final int STR_LEN50 = 50;

    public static final int STR_LEN100 = 100;

    public static final int STR_LEN200 = 200;

    public static final int STR_LEN3000 = 3000;

    public static final int ROW_STATE_ACTIVE = 0;

    public static final int ROW_STATE_DELETED = 1;

    public RowBase() {
        createDate = new Date();
    }

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date", nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_modified", updatable = false,
            columnDefinition = "timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private Date lastModified;

    @JsonIgnore
    @Column(name = "row_state", nullable = false, columnDefinition = "integer NOT NULL DEFAULT 0")
    protected int rowState;

    public Date getCreateDate() {
        return createDate;
    }

    public Date getLastModified() {
        return lastModified;
    }

    public int getRowState() {
        return rowState;
    }

    public void setRowState(int rowState) {
        this.rowState = rowState;
    }
}
