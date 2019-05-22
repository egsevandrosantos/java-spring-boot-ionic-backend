package com.evandrosantos.cursomc.services.validation;

import com.evandrosantos.cursomc.domain.enums.TipoCliente;
import com.evandrosantos.cursomc.dto.clientes.ClienteDTO;
import com.evandrosantos.cursomc.resources.exceptions.FieldMessage;
import com.evandrosantos.cursomc.services.ClienteService;
import com.evandrosantos.cursomc.services.validation.utils.BR;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class ClienteValidator implements ConstraintValidator<ClienteValidate, ClienteDTO> {
    @Autowired
    private ClienteService service;

    @Override
    public void initialize(ClienteValidate constraintAnnotation) {

    }

    @Override
    public boolean isValid(ClienteDTO value, ConstraintValidatorContext context) {
        List<FieldMessage> list = new ArrayList<>();
        // Testes aqui:

        if (value.getId() == null) {
            if (value.getCpfCnpj().trim().isBlank())
                list.add(new FieldMessage("cpfCnpj", "Preenchimento obrigatório"));

            if (value.getTipo().equals(TipoCliente.PESSOAFISICA)) {
                if (!BR.isValidCPF(value.getCpfCnpj()))
                    list.add(new FieldMessage("cpfCnpj", "CPF inválido"));
            } else {
                if (!BR.isValidCNPJ(value.getCpfCnpj()))
                    list.add(new FieldMessage("cpfCnpj", "CNPJ inválido"));
            }

            if (service.findByCpfCnpjEquals(value.getCpfCnpj()) != null) {
                list.add(new FieldMessage("cpfCnpj", String.format("Este %s já está em uso", value.getTipo().equals(TipoCliente.PESSOAFISICA) ? "CPF" : "CNPJ")));
            }
        }

        if (value.getEnderecos().isEmpty())
            list.add(new FieldMessage("enderecos", "É necessário informar ao menos um endereço"));

        if (value.getTelefones().isEmpty())
            list.add(new FieldMessage("telefones", "É necessário informar ao menos um telefone"));

        if (service.findByEmailEquals(value.getEmail()) != null)
            list.add(new FieldMessage("email", "Este email já está em uso"));

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName()).addConstraintViolation();
        }
        return list.isEmpty();
    }
}
