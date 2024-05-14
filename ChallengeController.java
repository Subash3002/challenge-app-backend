package com.example.challenge.app;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/challenges")
@CrossOrigin(origins = {"http://localhost:3000/","http://challenge-demo-bucket.s3-website.eu-north-1.amazonaws.com/"})
public class ChallengeController {
    private ChallengeService challengeService;

    public ChallengeController(ChallengeService challengeService){
        this.challengeService=challengeService;
    }

    @GetMapping
    public ResponseEntity<List<Challenge>> getAllChallenges(){
        return new ResponseEntity<>(challengeService.getAllChallenges(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> addChallenge(@RequestBody Challenge challenge){
        boolean isChallengeAdded=challengeService.addChallenge(challenge);
        if(isChallengeAdded){
            return new ResponseEntity<>("Challenge added",HttpStatus.OK);
        }
        return new ResponseEntity<>("Challenge not added",HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{month}")
    public ResponseEntity<Challenge> getChallenge(@PathVariable String month){
        Challenge challenge=challengeService.getChallenge(month);
        if(challenge!=null)return new ResponseEntity<>(challenge,HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateChallenge(@PathVariable Long id,@RequestBody Challenge updt){
        boolean isUpdated=challengeService.updateChallenge(id,updt);
        if(isUpdated)return new ResponseEntity<>("Challenge Updated Successfully",HttpStatus.OK);
        return new ResponseEntity<>("Challenge not updated successfully",HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteChallenge(@PathVariable Long id){
        boolean isDeleted= challengeService.deleteChallenge(id);
        if(isDeleted)return new ResponseEntity<>("Challenge Deleted Successfully",HttpStatus.OK);
        return new ResponseEntity<>("Challenge Not Deleted Successfully",HttpStatus.NOT_FOUND);
    }




}
