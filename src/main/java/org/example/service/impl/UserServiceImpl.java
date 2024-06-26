package org.example.service.impl;

import org.example.config.properties.CommonProperties;
import org.example.dto.PageDto;
import org.example.dto.UserDto;
import org.example.entity.User;
import org.example.mapper.UserMapper;
import org.example.repository.UserRepository;
import org.example.service.UserService;
import org.example.specification.UserSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserSpecification userSpecification;
    @Autowired
    private CommonProperties commonProperties;

    @Override
    public User findById(long id) {
        return userRepository.findById(id).get();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public PageDto search() {
        UserDto dto =  new UserDto();
        dto.setPageNumber(commonProperties.getDefaultPageNumber());
        dto.setPageSize(commonProperties.getDefaultPageSize());
        return search(dto);
    }

    @Override
    public PageDto search(UserDto dto) {
        if(dto.getPageSize() <= 0 || dto.getPageNumber() < 0){
            dto.setPageNumber(commonProperties.getDefaultPageNumber());
            dto.setPageSize(commonProperties.getDefaultPageSize());
        }
        Page<User> page = userRepository.findAll(userSpecification.filter(dto),
                PageRequest.of(dto.getPageNumber(),dto.getPageSize(),
                        Sort.by("id").ascending()));
        // findAll  = select , count
        PageDto pageDto = new PageDto();
        pageDto.setContent(page.getContent()
                .stream()
                .map(UserMapper::entityToDto)
                .collect(Collectors.toList()));
        pageDto.setTotalElements(page.getTotalElements());
        pageDto.setNumber(page.getNumber());
        pageDto.setNumberOfElements(page.getNumberOfElements());
        pageDto.setPageNumber(page.getNumber());
        pageDto.setPageSize(page.getSize());
        pageDto.setTotalPages(page.getTotalPages());
        return pageDto;
    }
}
