package com.github.nfers.ifood.cadastro;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "restaurante")
public class Restaurante extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    
    public String proprietario;
    
    public String cnpj;
    
    public String nome;
    
    @ManyToOne
    public Localizacao localizacao;
    
    @CreationTimestamp
    public Date datacriacao;
    
    @UpdateTimestamp
    public Date dataAtualizacao;
}
