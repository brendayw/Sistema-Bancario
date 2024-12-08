package ar.utn.frbb.tup.sistema_bancario.controller.validator;

import ar.utn.frbb.tup.sistema_bancario.controller.dto.ClientDto;
import ar.utn.frbb.tup.sistema_bancario.model.exceptions.EmptyFields;
import ar.utn.frbb.tup.sistema_bancario.model.exceptions.clients.ClientUnderage;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Period;

@Component
public class ClientValidator {

    public void validate(ClientDto clientDto) throws ClientUnderage, EmptyFields {
        validateEntityType(clientDto.getEntityType());
        validateClientAge(clientDto.getBirthDate());
        validateEmptyFields(clientDto);
    }

    public void validateEntityType(String entityType){
        if (!"FISICA".equals(entityType) || !"JURIDICA".equals(entityType)) {
            throw new IllegalArgumentException("El tipo de persona no es correcto.");
        }
    }

    public void validateClientAge(String birthDate) throws ClientUnderage {
        try {
            LocalDate parsedBirthDate = LocalDate.parse(birthDate);
            LocalDate currentDate = LocalDate.now();
            int age = Period.between(parsedBirthDate, currentDate).getYears();

            if (age < 18) {
                throw new ClientUnderage("El cliente debe ser mayor de 18 años.");
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("Error en el formato de fecha.");
        }
    }

    public void validateEmptyFields(ClientDto clientDto) throws EmptyFields {
        if (isNullOrEmpty(clientDto.getName()) ||
                isNullOrEmpty(clientDto.getLastname()) ||
                isNullOrEmpty(clientDto.getEmail()) ||
                isNullOrEmpty(clientDto.getPhone()) ||
                isNullOrEmpty(clientDto.getBirthDate()) ||
                isNullOrEmpty(clientDto.getEntityType())) {

            throw new EmptyFields("Todos los campos deben estar completos.");
        }
    }

    private boolean isNullOrEmpty(String field) {
        return field == null || field.trim().isEmpty();
    }

}
