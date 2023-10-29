package com.tutorials.app.service.impl;

import com.tutorials.app.dto.TutorialDto;
import com.tutorials.app.exception.NoSuchElementException;
import com.tutorials.app.exception.TutorialAlreadyExistsException;
import com.tutorials.app.mapper.TutorialMapper;
import com.tutorials.app.model.Tutorial;
import com.tutorials.app.repository.TutorialRepository;
import com.tutorials.app.service.TutorialService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class TutorialServiceImpl implements TutorialService {


    private final  TutorialRepository tutorialRepository;
    private final TutorialMapper tutorialMapper;

    @Override
    public void addBook(TutorialDto tutorialDto) {

            log.info("adding started");
            Tutorial tutorial = tutorialMapper.dtoToTutorial(tutorialDto);
            log.info("Adding tutorial:{}", tutorialDto);
            log.info("adding finished");
            Tutorial tutorial1=tutorialRepository.findById(tutorial.getId()).orElse(null);
            if(tutorial1==null){
                tutorialRepository.save(tutorial);
            }else{
                throw new TutorialAlreadyExistsException("Tutorial Already exists");
            }
    }

    @Override
    public void updateTutorial(TutorialDto tutorialDto, Long id) {
           Tutorial tutorial=tutorialRepository.findById(id).orElseThrow(()->new NoSuchElementException("tutorial not found"));
           tutorial.setId(tutorialDto.getId());
           tutorial.setDescription(tutorialDto.getDescription());
           tutorial.setTitle(tutorialDto.getTitle());
           tutorial.setPublished(tutorialDto.isPublished());
    }

    @Override
    public TutorialDto getById(Long id) {
            log.info("searching id to get info {}", id);
            Tutorial tutorial = tutorialRepository.findById(id).orElseThrow(()->new NoSuchElementException("tutorial Not found"));
                TutorialDto tutorialDto = tutorialMapper.tutorialToDto(tutorial);
                log.info("searching completed and {} id received", id);
                return tutorialDto;
    }

    @Override
    public void deleteById(Long id) {
        Tutorial tutorial=tutorialRepository.findById(id).orElse(null);
        if(tutorial==null){
            throw new NoSuchElementException("Tutorial Not found in the database");
        }else{
            log.info("{}th id is searching to delete", id);
             tutorialRepository.deleteById(id);
        }
    }

    @Override
    public List<TutorialDto> getAll() {
        try{
            log.info("searching all tutorials to get");
            List<Tutorial> list=tutorialRepository.findAll();
            return list.stream().map(tutorialMapper::tutorialToDto).collect(Collectors.toList());
        }catch (Exception e){
            log.error("your list is empty");
            return null;
        }
    }

    @Override
    public List<TutorialDto> getByPublished(boolean published) {
        try {
            log.info("searching tutorials by published status:{}", published);
            List<Tutorial> list = tutorialRepository.findByPublished(published);


            return list.stream().map(tutorialMapper::tutorialToDto).collect(Collectors.toList());
        }catch (Exception e){
            log.error("in list is not published status:{}",published);
            return null;
        }

    }

    @Override
    public void deleteAll() {
            log.info("deleting all tutorials");
            tutorialRepository.deleteAll();
    }

}
