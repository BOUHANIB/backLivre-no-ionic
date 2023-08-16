package ma.livre.livreexposant.Controller;


import ma.livre.livreexposant.Service.ExposantService;
import ma.livre.livreexposant.payload.ApiResponse;
import ma.livre.livreexposant.payload.Dto.ExposantDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/exposant")
public class ExposantController {

    @Autowired
    private ExposantService expService;


    //exposant/create
    @PostMapping("/create")
    @ResponseBody
    public ResponseEntity<ExposantDto> create(@RequestBody ExposantDto expDto) {
        //expDto.setLocalisation(null);
        ExposantDto create = this.expService.create(expDto);
        return new ResponseEntity<ExposantDto>(create, HttpStatus.CREATED);
    }

    //view Exposant
    @GetMapping("/getAll")
    public ResponseEntity<List<ExposantDto>> getAll() {
        List<ExposantDto> all = this.expService.getAll();
        return new ResponseEntity<List<ExposantDto>>(all, HttpStatus.OK);
    }

    //view ExposantById
    @GetMapping("/getByid/{expId}")
    public ResponseEntity<ExposantDto> getById(@PathVariable int expId) {
        ExposantDto getbyId = this.expService.getbyId(expId);
        return new ResponseEntity<ExposantDto>(getbyId, HttpStatus.OK);
    }

    //delete exposant
    @DeleteMapping("del/{expId}")
    public ResponseEntity<ApiResponse> delete(@PathVariable int expId) {
        this.expService.delete(expId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Exposant Delete Successfully",true), HttpStatus.OK);
    }

    //update exposant
    @PutMapping("/update/{expid}")
    public ResponseEntity<ExposantDto> update(@RequestBody ExposantDto expDto,@PathVariable int expid) {
        ExposantDto update = this.expService.update(expDto,expid);
        return new ResponseEntity<ExposantDto>(update, HttpStatus.OK);
    }

    // Search exposants by name
    @GetMapping("/search")
    public ResponseEntity<List<ExposantDto>> searchExposants(@RequestParam String keyword) {
        List<ExposantDto> searchResult = expService.searchByKeyword(keyword);
        return new ResponseEntity<>(searchResult, HttpStatus.OK);
    }
}
