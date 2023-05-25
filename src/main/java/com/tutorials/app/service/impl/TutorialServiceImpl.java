package com.tutorials.app.service.impl;

import com.tutorials.app.dto.TutorialDto;
import com.tutorials.app.model.Tutorial;
import com.tutorials.app.repository.TutorialRepository;
import com.tutorials.app.service.TutorialService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class TutorialServiceImpl implements TutorialService {


    private final TutorialRepository tutorialRepository;

    @Override
    public int addBook(TutorialDto tutorialDto) {
        log.info("adding started");
        Tutorial tutorial = convertToTutorial(tutorialDto);
        log.info("Adding tutorial:{}", tutorialDto);
        log.info("adding finished");
        return tutorialRepository.save(tutorial);

    }

    @Override
    public int updateTutorial(TutorialDto tutorialDto, Long id) {
        try {
            Tutorial tutorial = Tutorial.builder().id(tutorialDto.getId()).title(tutorialDto.getTitle()).description(tutorialDto.getDescription())
                    .published(tutorialDto.isPublished()).build();
            log.info("Updating tutorial with id {}:{}", id, tutorialDto);
            return tutorialRepository.update(tutorial, id);
        } catch (Exception e) {
            log.error("error occurred while updating process");
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public TutorialDto getById(Long id) {
        try {
            log.info("searching id to get info {}", id);
            Tutorial tutorial = tutorialRepository.findById(id);
            TutorialDto tutorialDto = convertToDto(tutorial);
            log.info("searching completed and {} id received", id);
            return tutorialDto;
        } catch (Exception e) {
            log.error("id:{} is not in your list", id);
            return null;
        }

    }

    @Override
    public int deleteById(Long id) {
        try {
            log.info("{}th id is searching to delete", id);
            return tutorialRepository.deleteById(id);
        } catch (Exception e) {
            log.error("your id:{} is not in your list", id);
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public List<TutorialDto> getAll() {
        try{
            log.info("searching all tutorials to get");
            List<TutorialDto> list = new ArrayList<>();
            for (Tutorial t : tutorialRepository.findAll()) {
                TutorialDto tutorialDto = new TutorialDto(t.getId(), t.getTitle(), t.getDescription(), t.isPublished());
                list.add(tutorialDto);
            }
            log.info("searching completed");
            return list;
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
            return list.stream().map(this::convertToDto).collect(Collectors.toList());
        }catch (Exception e){
            log.error("in list is not published status:{}",published);
            return null;
        }

    }

    @Override
    public int deleteAll() {
        try {
            log.info("deleting all tutorials");
            return tutorialRepository.deleteAll();
        }catch (Exception e){
            log.error("list is empty");
            return 0;
        }


    }

    private TutorialDto convertToDto(Tutorial tutorial) {
        return TutorialDto.builder().id(tutorial.getId()).title(tutorial.getTitle())
                .description(tutorial.getDescription()).published(tutorial.isPublished()).build();
    }

    private Tutorial convertToTutorial(TutorialDto tutorialDto) {
        return Tutorial.builder().id(tutorialDto.getId()).description(tutorialDto.getDescription())
                .published(tutorialDto.isPublished()).title(tutorialDto.getTitle()).build();
    }
}
