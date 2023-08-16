package ma.livre.livreexposant.Controller;

import ma.livre.livreexposant.Service.LivreService;
import ma.livre.livreexposant.payload.Dto.ExposantDto;
import ma.livre.livreexposant.payload.Dto.LivreDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/livre")
public class LivreController {

    @Autowired
    private LivreService livreService;


    //livre/create
    @PostMapping("/create")
    @ResponseBody
    public ResponseEntity<LivreDto> create(@RequestBody LivreDto livre) {
        LivreDto createLivre = livreService.create(livre, livre.getExposantId());
        return new ResponseEntity<LivreDto>(createLivre, HttpStatus.CREATED);
    }

    //view Livre
    @GetMapping("/view")
    public ResponseEntity<List<LivreDto>> viewAllLivre() {
        List<LivreDto> all = this.livreService.viewAll();
        return new ResponseEntity<List<LivreDto>>(all, HttpStatus.OK);
    }

    //view LivreById
    @GetMapping("/view/{livreId}")
    public ResponseEntity<LivreDto> viewLivreById(@PathVariable int livreId) {
        LivreDto viewById = livreService.viewLivreById(livreId);
        return new ResponseEntity<LivreDto>(viewById, HttpStatus.OK);
    }

    //delete livre
    @DeleteMapping("del/{livreId}")
    public ResponseEntity<String> deleteLivre(@PathVariable int livreId) {
        livreService.deleteLivre(livreId);
        return new ResponseEntity<String>("Livre Deleted", HttpStatus.OK);
    }

    //update livre
    @PutMapping("/update/{livreId}")
    public ResponseEntity<LivreDto> updateLivre(@PathVariable int livreId, @RequestBody LivreDto newlivre) {
        LivreDto updateLivre = livreService.updateLivre(newlivre,livreId);
        return new ResponseEntity<LivreDto>(updateLivre, HttpStatus.ACCEPTED);
    }

    //Find livre by Exposant
    @GetMapping("/exposant/{expId}")
    public ResponseEntity<List<LivreDto>> getLivrebyExposant(@PathVariable int expId) {
        List<LivreDto> findLivreByExposant = this.livreService.findLivreByExposant(expId);
        return new ResponseEntity<List<LivreDto>>(findLivreByExposant, HttpStatus.ACCEPTED);
    }

    @GetMapping("/search")
    public ResponseEntity<List<LivreDto>> searchLivres(@RequestParam String keyword) {
        List<LivreDto> searchResult = livreService.searchByKeyword(keyword);
        return new ResponseEntity<>(searchResult, HttpStatus.OK);
    }
}
