package com.tutorials.app.service;

import com.tutorials.app.dto.TutorialDto;
import com.tutorials.app.model.Tutorial;

import java.util.List;


public interface TutorialService {
    void addBook(TutorialDto tutorialDto);
    void updateTutorial(TutorialDto tutorialDto, Long id);
    TutorialDto getById(Long id);
    void deleteById(Long id);
    List<TutorialDto> getAll();
    List<TutorialDto> getByPublished(boolean published);
    void deleteAll();

}
