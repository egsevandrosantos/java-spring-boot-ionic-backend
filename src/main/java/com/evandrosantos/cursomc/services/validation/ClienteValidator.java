package com.evandrosantos.cursomc.services.validation;

import com.evandrosantos.cursomc.domain.enums.TipoCliente;
import com.evandrosantos.cursomc.dto.clientes.ClienteDTO;
import com.evandrosantos.cursomc.resources.exceptions.FieldMessage;
import com.evandrosantos.cursomc.services.validation.utils.BR;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class ClienteValidator implements ConstraintValidator<ClienteValidate, ClienteDTO> {
    @Override
    public void initialize(ClienteValidate constraintAnnotation) {

    }

    @Override
    public boolean isValid(ClienteDTO value, ConstraintValidatorContext context) {
        List<FieldMessage> list = new ArrayList<>();
        // Testes aqui:

        if (value.getEnderecos().isEmpty())
            list.add(new FieldMessage("enderecos", "É necessário informar ao menos um endereço"));

        if (value.getTelefones().isEmpty())
            list.add(new FieldMessage("telefones", "É necessário informar ao menos um telefone"));

        if (value.getTipo().equals(TipoCliente.PESSOAFISICA)) {
            if (!BR.isValidCPF(value.getCpfCnpj()))
                list.add(new FieldMessage("cpfCnpj", "CPF inválido"));
        } else {
            if (!BR.isValidCNPJ(value.getCpfCnpj()))
                list.add(new FieldMessage("cpfCnpj", "CNPJ inválido"));
        }

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName()).addConstraintViolation();
        }
        return list.isEmpty();
    }
}
