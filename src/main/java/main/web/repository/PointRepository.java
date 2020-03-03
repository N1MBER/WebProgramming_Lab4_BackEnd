package main.web.repository;

import main.web.entity.Point;
import main.web.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface PointRepository extends JpaRepository<Point, Long> {

    Collection<Point> findByUser(User user);
}