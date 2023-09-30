package shop.plant.shop.service;

import shop.plant.shop.exception.UserException;
import shop.plant.shop.model.Users;

public interface UsersService {
    public Users findUserById(Long userId) throws UserException;

    public Users findUserByJwt(String jwt) throws UserException;
}