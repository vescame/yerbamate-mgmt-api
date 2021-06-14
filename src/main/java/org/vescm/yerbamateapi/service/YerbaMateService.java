package org.vescm.yerbamateapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vescm.yerbamateapi.dto.request.YerbaMateRequest;
import org.vescm.yerbamateapi.dto.response.YerbaMateResponse;
import org.vescm.yerbamateapi.exception.YerbaNameAlreadyExistsException;
import org.vescm.yerbamateapi.mapper.YerbaMateMapper;
import org.vescm.yerbamateapi.model.YerbaMate;
import org.vescm.yerbamateapi.repository.YerbaMateRepository;

@Service
public class YerbaMateService {
    private final YerbaMateRepository yerbaMateRepository;
    private final YerbaMateMapper yerbaMateMapper = YerbaMateMapper.INSTANCE;

    @Autowired
    public YerbaMateService(final YerbaMateRepository yerbaMateRepository) {
        this.yerbaMateRepository = yerbaMateRepository;
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
}
