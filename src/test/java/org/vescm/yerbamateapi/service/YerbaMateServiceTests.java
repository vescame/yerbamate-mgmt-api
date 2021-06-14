package org.vescm.yerbamateapi.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.vescm.yerbamateapi.builder.YerbaMateBuilder;
import org.vescm.yerbamateapi.dto.request.YerbaMateRequest;
import org.vescm.yerbamateapi.dto.response.YerbaMateResponse;
import org.vescm.yerbamateapi.exception.YerbaNameAlreadyExistsException;
import org.vescm.yerbamateapi.model.YerbaMate;
import org.vescm.yerbamateapi.repository.YerbaMateRepository;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class YerbaMateServiceTests {
    @Mock
    private YerbaMateRepository yerbaMateRepository;

    @InjectMocks
    private YerbaMateService yerbaMateService;

    @Test
    void testGivenYerbaRequestThenReturnYerbaResponse() throws YerbaNameAlreadyExistsException {
        YerbaMateRequest request = YerbaMateBuilder.builder().build().toRequestDto();
        YerbaMate model = YerbaMateBuilder.builder().build().toEntity();
        when(yerbaMateRepository.save(any(YerbaMate.class))).thenReturn(model);
        YerbaMateResponse response = yerbaMateService.create(request);
        Assertions.assertEquals(YerbaMateBuilder.builder().build().toResponseDto(), response);
    }

    @Test
    void testCreateSameNameYerbaThenThrowException() {
        YerbaMateRequest request = YerbaMateBuilder.builder().build().toRequestDto();
        YerbaMate model = YerbaMateBuilder.builder().build().toEntity();

        when(yerbaMateRepository.existsByName(model.getName())).thenReturn(true);

        Assertions.assertThrows(YerbaNameAlreadyExistsException.class, () -> {
            yerbaMateService.create(request);
        });
    }

    @Test
    void testFindAllShouldReturnEmptyList() {
        when(yerbaMateRepository.findAll()).thenReturn(Collections.emptyList());
        Assertions.assertEquals(Collections.emptyList(), yerbaMateService.findAll());
    }

    @Test
    void testGivenYerbaRequestThenShouldReturnSingletonList() {
        YerbaMate model = YerbaMateBuilder.builder().build().toEntity();
        YerbaMateRequest request = YerbaMateBuilder.builder().build().toRequestDto();
        YerbaMateResponse response = YerbaMateBuilder.builder().build().toResponseDto();

        Assertions.assertDoesNotThrow(() -> {
            yerbaMateService.create(request);
        });

        when(yerbaMateRepository.findAll()).thenReturn(Collections.singletonList(model));

        Assertions.assertEquals(Collections.singletonList(response), yerbaMateService.findAll());
    }
}
