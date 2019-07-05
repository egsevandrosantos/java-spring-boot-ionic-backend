package com.evandrosantos.cursomc.domain;

import com.evandrosantos.cursomc.domain.enums.Perfil;
import com.evandrosantos.cursomc.domain.enums.Status;
import com.evandrosantos.cursomc.domain.enums.TipoCliente;
import com.evandrosantos.cursomc.dto.clientes.ClienteDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

@Entity
public class Cliente implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    @Column(unique = true)
    private String email;
    @Column(unique = true, updatable = false)
    private String cpfCnpj;
    private Integer tipo;
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Endereco> enderecos = new ArrayList<>();
    @ElementCollection
    @CollectionTable(name = "telefone")
    private Set<String> telefones = new HashSet<>();
    @OneToMany(mappedBy = "cliente")
    @JsonIgnore
    private List<Pedido> pedidos = new ArrayList<>();
    private Integer status;
    @Column(name = "created_at", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt;
    @Column(name = "updated_at", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date updatedAt;
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "perfis")
    private Set<Integer> perfis = new HashSet<>();
    @JsonIgnore
    private String senha;

    public Cliente() { this.getPerfis().add(Perfil.CLIENTE); }

    public Cliente(Integer id, String nome, String email, String cpfCnpj, TipoCliente tipo, Status status, String senha) {
        this();
        setId(id);
        setNome(nome);
        setEmail(email);
        setCpfCnpj(cpfCnpj);
        setTipo(tipo);
        setStatus(status);
        setSenha(senha);
    }

    public Cliente(ClienteDTO dto) {
        this(dto.getId(), dto.getNome(), dto.getEmail(), dto.getCpfCnpj(), dto.getTipo(), dto.getStatus(), dto.getSenha());
    }

    public Cliente(String nome, String email, String cpfCnpj, TipoCliente tipo, Status status, String senha) {
        this(null, nome, email, cpfCnpj, tipo, status, senha);
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }

    public TipoCliente getTipo() {
        return TipoCliente.getEnumByCod(this.tipo);
    }

    public String getDescricaoTipo() {
        return getTipo().getDescricao();
    }

    public void setTipo(TipoCliente tipo) {
        this.tipo = tipo.getCod();
    }

    public List<Endereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<Endereco> enderecos) {
        this.enderecos = enderecos;
    }

    public Set<String> getTelefones() {
        return telefones;
    }

    public void setTelefones(Set<String> telefones) {
        this.telefones = telefones;
    }

    public List<Pedido> getPedidos() {
        return this.pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public Status getStatus() {
        return Status.getEnumByCod(this.status);
    }

    public void setStatus(Status status) {
        this.status = status.getCod();
    }

    public String getDescricaoStatus() {
        return this.getStatus().getDescricao();
    }

    public Set<Perfil> getPerfis() {
        return this.perfis.stream().map(p -> Perfil.toEnum(p)).collect(Collectors.toSet());
    }

    public void setPerfis(Set<Integer> perfis) {
        this.perfis = perfis;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void update(ClienteDTO clienteDTO) {
        this.setId(clienteDTO.getId());
        this.setNome(clienteDTO.getNome());
        this.setEmail(clienteDTO.getEmail());
        this.setTipo(clienteDTO.getTipo());
        this.setTelefones(clienteDTO.getTelefones());
        this.setStatus(clienteDTO.getStatus());
        if (this.getId() == null)
            this.setCpfCnpj(clienteDTO.getCpfCnpj());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return Objects.equals(getId(), cliente.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
