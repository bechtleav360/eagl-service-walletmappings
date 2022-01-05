package com.bechtle.eagl.UserMappingsService.controller;

import com.bechtle.eagl.UserMappingsService.model.Relation;
import com.bechtle.eagl.UserMappingsService.model.Skill;
import com.bechtle.eagl.UserMappingsService.services.UserService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.Set;

@RestController
@RequestMapping("/api/user")
@Api( tags = "Clients")
public class UserApi {


    private final UserService userService;

    public UserApi(@Autowired UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{userId}")
    public Mono<Relation> get(@PathVariable String userId)  {
        return this.userService.getUserProfileById(userId);
    }

    @PostMapping("/{userId}/skills")
    public Mono<Relation> create(@PathVariable String userId, @RequestBody Set<Skill> skills) {
        return this.userService.addSkillsToUser(userId, skills)
                .then(this.userService.getUserProfileById(userId));

    }

    @DeleteMapping("/{userId}")
    public Mono<Void> delete(@PathVariable String userId) {
        return this.userService.deleteUser(userId);

    }
}
