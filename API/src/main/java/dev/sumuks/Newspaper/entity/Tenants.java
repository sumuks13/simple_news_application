package dev.sumuks.Newspaper.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tenants")
public class Tenants {

    @Id
    @Column(name = "tenant_id")
    private String tenantId;

    @Column(name = "db_url")
    private String dbUrl;

    @Column(name = "db_user")
    private String dbUser;

    @Column(name = "db_pass")
    private String dbPass;

    @Column(name = "db_driver")
    private String dbDriver;

}
