package com.evandrosantos.cursomc.domain;

import com.evandrosantos.cursomc.domain.enums.Status;
import com.evandrosantos.cursomc.dto.categorias.CategoriaDTO;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Categoria implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    @ManyToMany(mappedBy = "categorias")
    private List<Produto> produtos = new ArrayList<>();
    private Integer status;
    @Column(name = "created_at", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt;
    @Column(name = "updated_at", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date updatedAt;

    public Categoria() { }

    public Categoria(Integer id, String nome, Status status) {
        setId(id);
        setNome(nome);
        setStatus(status);
    }

    public Categoria(String nome, Status status) {
        this(null, nome, status);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public Status getStatus() {
        return Status.getEnumByCod(this.status);
    }

    public String getDescricaoStatus() {
        return getStatus().getDescricao();
    }

    public void setStatus(Status status) {
        this.status = status.getCod();
    }

    public void update(CategoriaDTO categoriaDTO) {
        this.setId(categoriaDTO.getId());
        this.setNome(categoriaDTO.getNome());
        this.setStatus(categoriaDTO.getStatus());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Categoria categoria = (Categoria) o;
        return Objects.equals(getId(), categoria.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
