package shop.plant.shop.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shop.plant.shop.dto.ShoppingSessionDto;
import shop.plant.shop.exception.UserException;
import shop.plant.shop.model.ShoppingSession;
import shop.plant.shop.repositories.ShoppingSessionRepository;
import shop.plant.shop.service.ShoppingSessionService;
import shop.plant.shop.service.UsersService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Implementation of the ShoppingSessionService interface for managing shopping sessions.
 */
@Service
@RequiredArgsConstructor
public class ShoppingSessionServiceImpl implements ShoppingSessionService {

    private final ShoppingSessionRepository shoppingSessionRepository;
    private final UsersService usersService;

    /**
     * Save a new shopping session or update an existing one.
     *
     * @param shoppingSession The ShoppingSession object to be saved or updated.
     * @return The saved ShoppingSession object.
     */
    @Override
    public ShoppingSession saveShoppingSession(ShoppingSession shoppingSession) {
        return shoppingSessionRepository.save(shoppingSession);
    }

    /**
     * Retrieve a shopping session by its unique identifier (ID).
     *
     * @param id The ID of the shopping session to retrieve.
     * @return An Optional containing the ShoppingSession object if found, or empty if not found.
     */
    @Override
    public Optional<ShoppingSession> getShoppingSessionById(Long id) {
        return shoppingSessionRepository.findById(id);
    }

    /**
     * Retrieve all shopping sessions.
     *
     * @return A list of ShoppingSession objects representing all shopping sessions.
     */
    @Override
    public List<ShoppingSession> getAllShoppingSessions() {
        return shoppingSessionRepository.findAll();
    }

    /**
     * Update an existing shopping session.
     *
     * @param shoppingSession The ShoppingSession object to be updated.
     */
    @Override
    public void updateShoppingSession(ShoppingSession shoppingSession) {
        shoppingSessionRepository.save(shoppingSession);
    }

    /**
     * Delete a shopping session by its unique identifier (ID).
     *
     * @param id The ID of the shopping session to delete.
     */
    @Override
    public void deleteShoppingSession(Long id) {
        shoppingSessionRepository.deleteById(id);
    }

    /**
     * Convert a ShoppingSession entity to a ShoppingSessionDto.
     *
     * @param shoppingSession The ShoppingSession entity to be converted.
     * @return The corresponding ShoppingSessionDto.
     */
    @Override
    public ShoppingSessionDto convertToDto(ShoppingSession shoppingSession) {
        ShoppingSessionDto shoppingSessionDto = new ShoppingSessionDto();
        shoppingSessionDto.setId(shoppingSession.getId());
        shoppingSessionDto.setTotal(shoppingSession.getTotal());
        shoppingSessionDto.setCreatedAt(shoppingSession.getCreatedAt());
        shoppingSessionDto.setStatus(shoppingSession.getStatus());

        // Set the user ID associated with the shopping session
        if (shoppingSession.getUsers() != null) {
            shoppingSessionDto.setUserId(shoppingSession.getUsers().getId());
        }

        return shoppingSessionDto;
    }

    /**
     * Convert a list of ShoppingSession entities to a list of ShoppingSessionDto objects.
     *
     * @param shoppingSessions The list of ShoppingSession entities to be converted.
     * @return The corresponding list of ShoppingSessionDto objects.
     */
    @Override
    public List<ShoppingSessionDto> convertToDtoList(List<ShoppingSession> shoppingSessions) {
        return shoppingSessions.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    /**
     * Convert a ShoppingSessionDto to a ShoppingSession entity.
     *
     * @param shoppingSessionDto The ShoppingSessionDto to be converted.
     * @return The corresponding ShoppingSession entity.
     */
    @Override
    public ShoppingSession convertToEntity(ShoppingSessionDto shoppingSessionDto) throws UserException {
        ShoppingSession shoppingSession = new ShoppingSession();

        shoppingSession.setId(shoppingSessionDto.getId());
        shoppingSession.setTotal(shoppingSessionDto.getTotal());
        shoppingSession.setCreatedAt(LocalDateTime.now());
        shoppingSession.setStatus(shoppingSessionDto.getStatus());

        // Set the associated user based on the user ID
        if (shoppingSessionDto.getUserId() != null) {
            shoppingSession.setUsers(usersService.findUserById(shoppingSessionDto.getUserId()));
        }

        return shoppingSession;
    }
}
