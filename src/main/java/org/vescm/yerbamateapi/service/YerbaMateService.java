package org.vescm.yerbamateapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vescm.yerbamateapi.dto.request.YerbaMateRequest;
import org.vescm.yerbamateapi.dto.response.YerbaMateResponse;
import org.vescm.yerbamateapi.exception.YerbaNameAlreadyExistsException;
import org.vescm.yerbamateapi.mapper.YerbaMateMapper;
import org.vescm.yerbamateapi.model.Comment;
import org.vescm.yerbamateapi.model.YerbaMate;
import org.vescm.yerbamateapi.repository.YerbaMateRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class YerbaMateService {
    private final YerbaMateRepository yerbaMateRepository;
    private final YerbaMateMapper yerbaMateMapper = YerbaMateMapper.INSTANCE;

    @Autowired
    public YerbaMateService(final YerbaMateRepository yerbaMateRepository) {
        this.yerbaMateRepository = yerbaMateRepository;
    }

    public boolean exists(final Long id) {
        return this.yerbaMateRepository.existsById(id);
    }

    public boolean existsByName(final String yerbaName) {
        return yerbaMateRepository.existsByName(yerbaName);
    }

    public YerbaMateResponse create(final YerbaMateRequest yerbaMateRequest)
            throws YerbaNameAlreadyExistsException{
        YerbaMate model = yerbaMateMapper.fromRequestToModel(yerbaMateRequest);
        if (existsByName(model.getName())) {
            throw new YerbaNameAlreadyExistsException(model.getName());
        }
        YerbaMate saved = yerbaMateRepository.save(model);
        return yerbaMateMapper.fromModelToResponseDto(saved);
    }

    public List<YerbaMateResponse> findAll() {
        return yerbaMateRepository.findAll().stream()
                .map(yerbaMateMapper::fromModelToResponseDto)
                .collect(Collectors.toList());
    }

    public Optional<YerbaMateResponse> findById(Long id) {
        return null;
    }

    public YerbaMateResponse update(YerbaMateRequest yerbaMate) {
        return null;
    }

    public void delete(Long id) {

    }

    public void createComment(Comment comment) {

    }
}
