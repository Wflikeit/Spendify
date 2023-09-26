package com.Spendify.Spendify.User;

import com.Spendify.Spendify.exception.DuplicateResourceException;
import com.Spendify.Spendify.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserDTOMapper userDTOMapper;

    public UserService(UserRepository userRepository, UserDTOMapper userDTOMapper) {
        this.userRepository = userRepository;
        this.userDTOMapper = userDTOMapper;
    }

    public List<UserDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(userDTOMapper)
                .collect(Collectors.toList());

    }

    public UserDTO getUser(Long userId) {
        return userRepository.findById(userId)
                .map(userDTOMapper)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "user with id [%s] not found".formatted(userId)
                ));
    }

    public void deleteUser(Long userId) {
        userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException(
                "user with id [%s] not found".formatted(userId)
        ));
        userRepository.deleteById(userId);
    }

    public void updateUser(Long userId, UserUpdateRequest updateRequest) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException(
                "user with id [%s] not found".formatted(userId)
        ));

        if (updateRequest.surname() != null) user
                .setSurname(updateRequest.surname());

        if (updateRequest.email() != null) user
                .setEmail(updateRequest.email());

        if (updateRequest.password() != null) user
                .setEmail(updateRequest.password());

        if (updateRequest.image() != null) user
                .setEmail(updateRequest.image());

        userRepository.save(user);
    }

    public void addUser(UserAddRequest addRequest) {
        if (existsUserWithEmail(addRequest.email())) {
            throw new DuplicateResourceException(
                    "email already taken"
            );
        }
        User user = new User(
                addRequest.name(),
                addRequest.surname(),
                addRequest.password(),
                addRequest.email(),
                addRequest.image(),
                addRequest.isActive()
        );
        userRepository.save(user);
    }

    public boolean existsUserWithEmail(String email) {
        return userRepository.existsByEmail(email);
    }

}
