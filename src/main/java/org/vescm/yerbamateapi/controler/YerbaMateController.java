package org.vescm.yerbamateapi.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.vescm.yerbamateapi.exception.YerbaNotFoundException;
import org.vescm.yerbamateapi.model.Comment;
import org.vescm.yerbamateapi.model.YerbaMate;
import org.vescm.yerbamateapi.service.YerbaMateService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "api/v1/yerbamate")
public class YerbaMateController {
    private final YerbaMateService yerbaMateService;

    @Autowired
    public YerbaMateController(YerbaMateService yerbaMateService) {
        this.yerbaMateService = yerbaMateService;
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<YerbaMate> findAll() {
        return yerbaMateRepository.findAll();
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public YerbaMate findById(@PathVariable Long id) throws YerbaNotFoundException {
        return yerbaMateRepository.findById(id).orElseThrow(YerbaNotFoundException::new);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public YerbaMateResponse create(@RequestBody @Valid YerbaMateRequest yerbaMate)
            throws YerbaNameAlreadyExistsException {
        return yerbaMateService.create(yerbaMate);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public YerbaMate update(@PathVariable Long id,
                            @RequestBody @Valid YerbaMate yerbaMate)
            throws YerbaNotFoundException {
        YerbaMate existentYerbaMate = yerbaMateRepository.findById(id).orElseThrow(YerbaNotFoundException::new);
        yerbaMate.setId(existentYerbaMate.getId());
        return yerbaMateRepository.save(yerbaMate);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@RequestParam Long id) throws YerbaNotFoundException {
        yerbaMateRepository.delete(yerbaMateRepository
                .findById(id).orElseThrow(YerbaNotFoundException::new));
    }

    @RequestMapping(value = "{yerba_id}/comments", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void createComment(@PathVariable Long yerba_id,
                           @RequestBody @Valid Comment comment) throws YerbaNotFoundException {
        YerbaMate yerbaMate = yerbaMateRepository.findById(yerba_id)
                .orElseThrow(YerbaNotFoundException::new);
        yerbaMate.getComments().add(comment);
        yerbaMateRepository.save(yerbaMate);
    }
}
