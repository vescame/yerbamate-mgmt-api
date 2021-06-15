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
import org.vescm.yerbamateapi.exception.YerbaNotFoundException;
import org.vescm.yerbamateapi.model.YerbaMate;
import org.vescm.yerbamateapi.repository.YerbaMateRepository;

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

    @Test
    void testGivenYerbaRequestThenFindByIdShouldReturn() throws YerbaNotFoundException {
        YerbaMate model = YerbaMateBuilder.builder().build().toEntity();
        YerbaMateRequest request = YerbaMateBuilder.builder().build().toRequestDto();
        YerbaMateResponse response = YerbaMateBuilder.builder().build().toResponseDto();

        Assertions.assertDoesNotThrow(() -> {
            yerbaMateService.create(request);
        });

        when(yerbaMateRepository.findById(1L)).thenReturn(Optional.of(model));

        YerbaMateResponse actual = yerbaMateService.findById(1L);

        Assertions.assertEquals(response, actual);
    }

    @Test
    void testGivenYerbaRequestThenShouldThrowNotFoundException() {
        when(yerbaMateRepository.findById(1L)).thenReturn(Optional.empty());

        Assertions.assertThrows(YerbaNotFoundException.class, () -> {
            yerbaMateService.findById(1L);
        });
    }

    @Test
    void testGivenYerbaRequestThenShouldUpdateExistent()
            throws YerbaNameAlreadyExistsException, YerbaNotFoundException {
        YerbaMate model = YerbaMateBuilder.builder().build().toEntity();
        model.setName("Yerba New");

        YerbaMateRequest request = YerbaMateBuilder.builder().build().toRequestDto();

        when(yerbaMateRepository.existsById(1L)).thenReturn(true);
        when(yerbaMateRepository.save(any(YerbaMate.class))).thenReturn(model);

        yerbaMateService.create(request);

        YerbaMateResponse response = yerbaMateService.update(1L, request);

        Assertions.assertEquals("Yerba New", response.getName());
    }

    @Test
    void testGivenInvalidYerbaRequestThenShouldThrowNotFoundException() {
        when(yerbaMateRepository.existsById(1L)).thenReturn(false);
        Assertions.assertThrows(YerbaNotFoundException.class, () -> {
            yerbaMateService.update(1L, any(YerbaMateRequest.class));
        });
    }

    @Test
    void testGivenInvalidYerbaRequestThenShouldThrowNameExistsException() {
        YerbaMateRequest request = YerbaMateBuilder.builder().build().toRequestDto();

        when(yerbaMateRepository.existsById(1L)).thenReturn(true);
        when(yerbaMateRepository.existsByName(request.getName())).thenReturn(true);

        Assertions.assertThrows(YerbaNameAlreadyExistsException.class, () -> {
            yerbaMateService.update(1L, request);
        });
    }

    @Test
    void testGivenInvalidYerbaRequestThenDeleteShouldThrowNotFoundException() {
        Assertions.assertThrows(YerbaNotFoundException.class, () -> {
            yerbaMateService.delete(1L);
        });
    }

    @Test
    void testDeleteYerbaThenShouldThrowNotFoundException() throws YerbaNotFoundException {
        YerbaMate model = YerbaMateBuilder.builder().build().toEntity();

        when(yerbaMateRepository.findById(1L)).thenReturn(Optional.of(model));

        yerbaMateService.delete(1L);

        when(yerbaMateRepository.findById(1L)).thenReturn(Optional.empty());

        Assertions.assertThrows(YerbaNotFoundException.class, () -> {
            yerbaMateService.delete(1L);
        });
    }

}
