package at.kaindorf.backend.mapper;

import at.kaindorf.backend.dto.UserDTO;
import at.kaindorf.backend.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    public UserDTO toDTO(User user);
    public User toEntity(UserDTO userDTO);
}
