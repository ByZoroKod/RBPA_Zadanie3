package org.example.service;

import org.example.dto.CollectibleItemRequest;
import org.example.exception.NotFoundException;
import org.example.exception.ValidationException;
import org.example.model.CollectibleItem;
import org.example.repository.CollectibleItemRepository;
import org.example.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CollectibleItemService {

    private final CollectibleItemRepository itemRepository;
    private final UserRepository userRepository;

    public CollectibleItemService(CollectibleItemRepository itemRepository, UserRepository userRepository) {
        this.itemRepository = itemRepository;
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    public List<CollectibleItem> findAll() {
        return itemRepository.findAll();
    }

    @Transactional(readOnly = true)
    public CollectibleItem getById(Long id) {
        return itemRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Предмет не найден: id=" + id));
    }

    public CollectibleItem create(CollectibleItemRequest request) {
        if (request.getDescription() == null || request.getDescription().isBlank()) {
            throw new ValidationException("Не указано описание предмета");
        }
        if (request.getOwnerId() == null) {
            throw new ValidationException("Не указан владелец (ownerId)");
        }
        if (!userRepository.existsById(request.getOwnerId())) {
            throw new ValidationException("Владелец не найден: id=" + request.getOwnerId());
        }
        CollectibleItem item = new CollectibleItem();
        item.setDescription(request.getDescription());
        item.setOwnerId(request.getOwnerId());
        item.setCondition(request.getCondition());
        return itemRepository.save(item);
    }

    public CollectibleItem update(Long id, CollectibleItemRequest request) {
        CollectibleItem item = getById(id);
        if (request.getDescription() != null) {
            if (request.getDescription().isBlank()) {
                throw new ValidationException("Описание не может быть пустым");
            }
            item.setDescription(request.getDescription());
        }
        if (request.getOwnerId() != null) {
            if (!userRepository.existsById(request.getOwnerId())) {
                throw new ValidationException("Владелец не найден: id=" + request.getOwnerId());
            }
            item.setOwnerId(request.getOwnerId());
        }
        if (request.getCondition() != null) {
            item.setCondition(request.getCondition());
        }
        return itemRepository.save(item);
    }

    public void delete(Long id) {
        if (!itemRepository.existsById(id)) {
            throw new NotFoundException("Предмет не найден: id=" + id);
        }
        itemRepository.deleteById(id);
    }
}
