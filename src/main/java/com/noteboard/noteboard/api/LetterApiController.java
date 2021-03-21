package com.noteboard.noteboard.api;

import com.noteboard.noteboard.dto.LetterDTO;
import com.noteboard.noteboard.entity.Letter;
import com.noteboard.noteboard.service.LetterService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class LetterApiController {

    private final LetterService letterService;

    @GetMapping("/api/v1/letters")
    public List<LetterDTO> letters(){
        List<Letter> all = letterService.findAll();
        List<LetterDTO> result = all.stream().map(letter -> new LetterDTO(letter)).collect(Collectors.toList());
        return result;
    }

    @PostMapping("/api/letters")
    public LetterDTO write(@RequestBody Letter letter){
        System.out.println(letter.getContent()+" "+letter.getFromAccount()+" "+letter.getToAccount());
        letterService.addLetter(letter.getFromAccount(),letter.getToAccount(),letter.getContent());
        return new LetterDTO(letter);
    }

}
