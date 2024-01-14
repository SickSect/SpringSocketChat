package com.mailer.demo.mapper;

import com.mailer.demo.dto.UserDto;
import com.mailer.demo.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring") // why spring?
public interface UserMapper extends Mappable<User, UserDto> {
}
