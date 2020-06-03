package main.web.controllers;

import main.web.entity.Point;
import main.web.entity.AnswerPoint;
import main.web.repository.PointRepository;
import main.web.repository.UserRepository;
import main.web.service.Checker;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import main.web.service.PointService;

@RestController
public class PointController {

    private final PointRepository pointRepository;
    private final UserRepository userRepository;
    private final PointService pointService;

    PointController(PointRepository pointRepository, UserRepository userRepository, PointService pointService) {
        this.pointRepository = pointRepository;
        this.userRepository = userRepository;
        this.pointService = pointService;
    }



    @CrossOrigin
    @PostMapping("/result")
    Collection<AnswerPoint> allPoints(Principal user) {
        System.out.println("Get point of user: "+user.getName());
        Collection<AnswerPoint> collection = new ArrayList<>();
        for (Point point:pointRepository.findByUser(userRepository.findByUsername(user.getName()))) {
            collection.add(point.convertToSimplePoint());
        }
        return collection;
    }

    @CrossOrigin
    @PostMapping("/table")
    AnswerPoint newPoint(@RequestBody Point newPoint, Principal user) {
        Checker.hit(newPoint);
//        newPoint.setHit(Checker.inArea(newPoint.getX(),newPoint.getY(),newPoint.getR()));
//        System.out.println(newPoint.getHit());
        newPoint.setUser(userRepository.findByUsername(user.getName()));
        System.out.println("Created new point: " +newPoint);
        pointService.count(newPoint);
        pointService.getSquare(newPoint.getR());
        return pointRepository.save(newPoint).convertToSimplePoint();
    }
}