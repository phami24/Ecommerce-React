package shop.plant.shop.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shop.plant.shop.exception.UserException;
import shop.plant.shop.model.Users;
import shop.plant.shop.repositories.UsersRepository;
import shop.plant.shop.security.JwtProvider;
import shop.plant.shop.service.UsersService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsersServiceImpl implements UsersService {
    private final UsersRepository userRepository;
    private final JwtProvider jwtProvider;


    @Override
    public Users findUserById(Long userId) throws UserException {
        Optional<Users> user = userRepository.findById(userId);
        if (user.isPresent()) {
            return user.get();
        }
        throw new UserException("User not found id: " + userId);
    }

    @Override
    public Users findUserByJwt(String jwt) throws UserException {

        String email = jwtProvider.getEmailFromToken(jwt);

        Users user = userRepository.findByEmail(email);

        if (user == null) {
            throw new UserException("User not found with email" + email);
        }
        return user;
    }
}
