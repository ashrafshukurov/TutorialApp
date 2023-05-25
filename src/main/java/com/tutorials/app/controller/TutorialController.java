package com.tutorials.app.controller;

import com.tutorials.app.dto.TutorialDto;
import com.tutorials.app.service.TutorialService;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/app")
public class TutorialController {
    private final TutorialService tutorialService;

    public TutorialController(TutorialService tutorialService) {
        this.tutorialService = tutorialService;
    }
    @PostMapping
    public int insert(@RequestBody TutorialDto tutorialDto){
        return tutorialService.addBook(tutorialDto);
    }
    @ApiOperation(value = "Provide ID to get Tutorial",notes = "Retrieve a tutorial based on its ID",response =TutorialDto.class)
    @ApiResponses(value={@ApiResponse(code=200,message = "successfully greeting"),
    @ApiResponse(code=400,message = "required name field")})
    @GetMapping("/{id}")
    public ResponseEntity<TutorialDto> getById(@ApiParam(name = "id",value = "Tutorial id:",example ="20") @PathVariable Long id){
        return ResponseEntity.ok(tutorialService.getById(id));
    }
    @ApiOperation(value = "delete Tutorial by id",notes = "delete Tutorial based on its ID from tutorials")
    @DeleteMapping("/{id}")
    public int deleteById(@ApiParam(name = "id",value = "Tutorial id:",example = "20")@PathVariable Long id){
        return tutorialService.deleteById(id);
    }
    @ApiOperation(value ="update Tutorial by id",notes = "Update Tutorial based on its ID")
    @PutMapping("/{id}")
    public int updateById(@RequestBody TutorialDto tutorialDto,@ApiParam(name = "id",value ="Tutorial ID",example = "20") @PathVariable Long id){

        return tutorialService.updateTutorial(tutorialDto,id);
    }
    @ApiOperation(value = "get All Tutorial",notes = "Retrieve All Tutorial from Tutorial list")
    @GetMapping
    public ResponseEntity<List<TutorialDto>> getAllTutorial(){
        return ResponseEntity.ok(tutorialService.getAll());
    }
    @ApiOperation(value = "get published Tutorials",notes = "retrieve published Tutorials from list")
    @GetMapping("/pub/{published}")
    public ResponseEntity<List<TutorialDto>> getByPublished(@PathVariable boolean published){
        return ResponseEntity.ok(tutorialService.getByPublished(published));
    }
    @ApiOperation(value = "Delete All Tutorials",notes = "Delete all tutorials from list")
    @DeleteMapping
    public void DeleteAllTutorial(){
        tutorialService.deleteAll();
    }




}
