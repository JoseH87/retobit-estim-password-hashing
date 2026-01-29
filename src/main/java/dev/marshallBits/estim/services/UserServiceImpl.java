package dev.marshallBits.estim.services;

import dev.marshallBits.estim.dto.CreateUserDTO;
import dev.marshallBits.estim.dto.SignupResponseDTO;
import dev.marshallBits.estim.models.User;
import dev.marshallBits.estim.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder; // inyectamos el servicio de encriptacion de contraseñas

    @Override
    public SignupResponseDTO registerUser(CreateUserDTO createUserDTO) {

         // TODO: Agregar encriptación de contraseña antes de guardar
        createUserDTO.setPassword(passwordEncoder.encode(createUserDTO.getPassword()));
        // TODO: Verificar si el nombre de usuario o correo electrónico ya existen

        if (userRepository.findByUsername(createUserDTO.getUsername()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username already exists"); // lanzamos una excepcion si el usuario ya existe
        }

        if (userRepository.findByEmail(createUserDTO.getEmail()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email already exists"); // lanzamos una excepcion si el usuario ya existe
        }


        User user = User.builder()
                .username(createUserDTO.getUsername())
                .email(createUserDTO.getEmail())
                .password(createUserDTO.getPassword())
                .build();

        User savedUser = userRepository.save(user);

        // TODO: Mandar la info del nuevo user pero sin la contraseña

        return SignupResponseDTO.builder()
                .id(savedUser.getId())
                .username(savedUser.getUsername())
                .email(savedUser.getEmail())
                .build();

    }


}
