package LoginAuthenticator.Services;

import LoginAuthenticator.DTOS.UserDto;
import LoginAuthenticator.models.UsersModel;

public interface IUserService{
    UsersModel findByUsername(String username);
    UsersModel save(UserDto userDto);
}
