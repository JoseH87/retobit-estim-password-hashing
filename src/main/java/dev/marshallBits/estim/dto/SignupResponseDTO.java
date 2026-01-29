package dev.marshallBits.estim.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Builder;
import org.springframework.stereotype.Component;

@NoArgsConstructor //* Constructor sin argumentos */
@AllArgsConstructor  //* Constructor con todos los argumentos */
@Data               //* Genera getters, setters, toString, equals, y hashCode */
@Builder            //* Patrón de diseño Builder para facilitar la creación de objetos */

@Component
public class SignupResponseDTO {
    private String username;
    private String email;
    private long id;

}
