package at.kaindorf.exa_104_springrest_examdb.api;

import at.kaindorf.exa_104_springrest_examdb.database.ClassnameRepository;
import at.kaindorf.exa_104_springrest_examdb.pojos.Classname;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/classname")
public class ClassnameController {

    @Autowired
    ClassnameRepository classnameRepository;

    @GetMapping
    public ResponseEntity<List<Classname>> getAllClassnames() {
        List<Classname> classnames = classnameRepository.findAll().stream().sorted(Comparator.comparing(Classname::getClassname)).collect(Collectors.toList());
        return ResponseEntity.ok(classnames);
    }
}
