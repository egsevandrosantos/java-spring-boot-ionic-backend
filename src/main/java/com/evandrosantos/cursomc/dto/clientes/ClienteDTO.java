package com.evandrosantos.cursomc.dto.clientes;

import com.evandrosantos.cursomc.domain.Cliente;
import com.evandrosantos.cursomc.domain.Endereco;
import com.evandrosantos.cursomc.domain.enums.Status;
import com.evandrosantos.cursomc.domain.enums.TipoCliente;
import com.evandrosantos.cursomc.dto.enderecos.EnderecoDTO;
import com.evandrosantos.cursomc.services.validation.ClienteValidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@ClienteValidate
public class ClienteDTO implements Serializable {
    @Autowired
    private BCryptPasswordEncoder bCrypt;

    private static final long serialVersionUID = 1L;
    private Integer id;
    @NotBlank(message = "Preenchimento obrigatório")
    @Size(min = 5, max = 120, message = "O tamanho deve ser entre 5 e 120 caracteres.")
    private String nome;
    @NotBlank(message = "Preenchimento obrigatório")
    @Email(message = "Email inválido")
    private String email;
    private String cpfCnpj;
    private TipoCliente tipo;
    private Status status;
    private List<EnderecoDTO> enderecos = new ArrayList<>();
    private Set<String> telefones = new HashSet<>();
    @NotBlank(message = "Preenchimento obrigatório")
    private String senha;

    public ClienteDTO() { }

    public ClienteDTO(Integer id, String nome, String email, String cpfCnpj, TipoCliente tipo, Status status, List<EnderecoDTO> enderecos, Set<String> telefones, String senha) {
        setId(id);
        setNome(nome);
        setEmail(email);
        setCpfCnpj(cpfCnpj);
        setTipo(tipo);
        setStatus(status);
        setEnderecos(enderecos);
        setTelefones(telefones);
        setSenha(senha);
    }

    public ClienteDTO(Cliente cliente) {
        this(cliente.getId(), cliente.getNome(), cliente.getEmail(), cliente.getCpfCnpj(), cliente.getTipo(), cliente.getStatus(), cliente.getEnderecos().stream().map(endereco -> new EnderecoDTO(endereco)).collect(Collectors.toList()), cliente.getTelefones(), cliente.getSenha());
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
        return tipo;
    }

    public String getDescricaoTipo() {
        return this.tipo.getDescricao();
    }

    public void setTipo(TipoCliente tipo) {
        this.tipo = tipo;
    }

    public Status getStatus() {
        return status;
    }

    public String getDescricaoStatus() {
        return this.status.getDescricao();
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<EnderecoDTO> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<EnderecoDTO> enderecos) {
        this.enderecos = enderecos;
    }

    public Set<String> getTelefones() {
        return telefones;
    }

    public void setTelefones(Set<String> telefones) {
        this.telefones = telefones;
    }

    public String getSenha() {
        return bCrypt.encode(senha);
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
