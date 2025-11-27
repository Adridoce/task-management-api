package com.taskmanager.mapper;

import com.taskmanager.dto.user.UserRequestDto;
import com.taskmanager.dto.user.UserResponseDto;
import com.taskmanager.dto.user.UserUpdateDto;
import com.taskmanager.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper {
    UserResponseDto toResponse(User user);

    List<UserResponseDto> toResponseList(List<User> users);

    User toEntity(UserRequestDto user);

    void updateUserFromDto(UserUpdateDto dto, @MappingTarget User user);
}
