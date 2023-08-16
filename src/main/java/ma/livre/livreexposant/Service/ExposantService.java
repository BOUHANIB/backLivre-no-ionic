package ma.livre.livreexposant.Service;

import ma.livre.livreexposant.Exception.ResourceNotFoundException;
import ma.livre.livreexposant.Model.Exposant;
import ma.livre.livreexposant.Repository.ExposantRepository;
import ma.livre.livreexposant.payload.Dto.ExposantDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExposantService {
    @Autowired
    private ExposantRepository expRepo;

    @Autowired
    private ModelMapper mapper;

    //exposant/create
    public ExposantDto create(ExposantDto dto) {
        //ExposantDto to Exposant
        Exposant exp = this.mapper.map(dto,Exposant.class);
        Exposant save = this.expRepo.save(exp);
        //Exposant to ExposantDto
        return this.mapper.map(save,ExposantDto.class);
    }

    //view Exposants
    public List<ExposantDto> getAll() {
        List<Exposant> findAll = this.expRepo.findAll();
        List<ExposantDto> allDto = findAll.stream().map(exp -> this.mapper.map(exp,ExposantDto.class)).collect(Collectors.toList());
        return allDto;
    }

    //view ExposantById
    public ExposantDto getbyId(int expoid) {
        Exposant getByid = this.expRepo.findById(expoid).orElseThrow(() ->
                new ResourceNotFoundException(+expoid + " from this exposant id exposant not found"));
        return this.mapper.map(getByid,ExposantDto.class);
    }

    //delete exposant
    public void delete(int expId) {
        Exposant Exp = this.expRepo.findById(expId).orElseThrow(() ->
                new ResourceNotFoundException(+expId + "This Exposant Id Not found"));
        this.expRepo.delete(Exp);
    }

    //update exposant
    public ExposantDto update(ExposantDto newexp,int expid) {
        Exposant oldExp = this.expRepo.findById(expid).orElseThrow(() ->
                new ResourceNotFoundException(+expid + "This Exposant Id Not found"));
        oldExp.setNom(newexp.getNom());
        oldExp.setPays(newexp.getPays());
        oldExp.setMail(newexp.getMail());
        oldExp.setTelephone(newexp.getTelephone());
        oldExp.setPassword(newexp.getPassword());
        oldExp.setSiteWeb(newexp.getSiteWeb());
        oldExp.setAdresse(newexp.getAdresse());
        oldExp.setResponsableSalle(newexp.getResponsableSalle());
        oldExp.setResponsable(newexp.getResponsable());
        oldExp.setSpecialite(newexp.getSpecialite());
        oldExp.setLocalisation(newexp.getLocalisation());
        Exposant save = this.expRepo.save(oldExp);
        return this.mapper.map(save,ExposantDto.class);
    }


    // Search exposants by keyword (name containing the keyword)
    public List<ExposantDto> searchByKeyword(String keyword) {
        List<Exposant> searchResult = expRepo.findByNomContains(keyword);
        return searchResult.stream().map(exp -> mapper.map(exp, ExposantDto.class)).collect(Collectors.toList());
    }
}
