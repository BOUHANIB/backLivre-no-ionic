package ma.livre.livreexposant.Service;

import ma.livre.livreexposant.Exception.ResourceNotFoundException;
import ma.livre.livreexposant.Model.Exposant;
import ma.livre.livreexposant.Model.Livre;
import ma.livre.livreexposant.Repository.ExposantRepository;
import ma.livre.livreexposant.Repository.LivreRepository;
import ma.livre.livreexposant.payload.Dto.ExposantDto;
import ma.livre.livreexposant.payload.Dto.LivreDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LivreService {
    @Autowired
    private LivreRepository livreRepo;

    @Autowired
    private ExposantRepository expRepo;

    @Autowired
    private ModelMapper mapper;

    //livre/create
    public LivreDto create(LivreDto livreDto, int expid) {
        //Fech Exposant where we want to add Livre
        Exposant exp = this.expRepo.findById(expid).orElseThrow(() ->
                new ResourceNotFoundException(+expid + " This Exposant id not found Exposant"));
        //LivreDto to Livre
        Livre livre = this.mapper.map(livreDto,Livre.class);
        livre.setExposant(exp);
        Livre save = this.livreRepo.save(livre);
        //Livre to LivreDto
        return this.mapper.map(save,LivreDto.class);
    }

    //view Livres
    public List<LivreDto> viewAll() {
        List<Livre> findAll = this.livreRepo.findAll();
        List<LivreDto> livreDto = findAll.stream().map(l -> this.toDto(l)).collect(Collectors.toList());
        return livreDto;
    }

    //view LivreById
    public LivreDto viewLivreById(int lid) {
        Livre findById = livreRepo.findById(lid).orElseThrow(() ->
                new ResourceNotFoundException(+lid + " from this livre id livre not found"));
        return this.mapper.map(findById,LivreDto.class);

    }

    //delete livre
    public void deleteLivre(int lid) {
        Livre byId = livreRepo.findById(lid).orElseThrow(() ->
                new ResourceNotFoundException(+lid + " from this livre id livre not found"));
        this.livreRepo.delete(byId);
    }

    //update livre
    public LivreDto updateLivre(LivreDto newl,int lid) {
        Livre oldl = livreRepo.findById(lid).orElseThrow(() ->
                new ResourceNotFoundException(+lid + "livre Not found"));
        oldl.setTitre(newl.getTitre());
        oldl.setAuteur(newl.getAuteur());
        oldl.setEditeur(newl.getEditeur());
        oldl.setDateEdition(newl.getDateEdition());
        oldl.setPrix(newl.getPrix());
        oldl.setIsbn(newl.getIsbn());

        if (newl.getExposant() != null) {
            Exposant exposant = expRepo.findById(newl.getExposant().getExposantId())
                    .orElseThrow(() -> new ResourceNotFoundException("Exposant not found"));
            oldl.setExposant(exposant);
        }

        Livre save = this.livreRepo.save(oldl);
        return this.mapper.map(save,LivreDto.class);

    }

    //Find livre by Exposant
    public List<LivreDto> findLivreByExposant(int expId) {
        Exposant Exp = this.expRepo.findById(expId)
                .orElseThrow(() -> new ResourceNotFoundException("This id Exposant not found"));
        List<Livre> findByExposant = this.livreRepo.findByExposant(Exp);
        List<LivreDto> collect = findByExposant.stream().map(livre -> toDto(livre)).collect(Collectors.toList());
        return collect;
    }

    //Livre to LivreDto
    public LivreDto toDto(Livre livre) {
        LivreDto lDto = new LivreDto();
        lDto.setLivreId(Math.toIntExact(livre.getLivreId()));
        lDto.setTitre(livre.getTitre());
        lDto.setAuteur(livre.getAuteur());
        lDto.setEditeur(livre.getEditeur());
        lDto.setDateEdition(livre.getDateEdition());
        lDto.setPrix(livre.getPrix());
        lDto.setIsbn(livre.getIsbn());

        //Change Exposant to ExposantDao
        ExposantDto expDto = new ExposantDto();
        expDto.setExposantId(Math.toIntExact(livre.getExposant().getExposantId()));
        expDto.setNom(livre.getExposant().getNom());
        expDto.setPays(livre.getExposant().getPays());
        expDto.setMail(livre.getExposant().getMail());
        expDto.setTelephone(livre.getExposant().getTelephone());
        expDto.setSiteWeb(livre.getExposant().getSiteWeb());
        expDto.setAdresse(livre.getExposant().getAdresse());
        expDto.setResponsableSalle(livre.getExposant().getResponsableSalle());
        expDto.setResponsable(livre.getExposant().getResponsable());
        expDto.setSpecialite(livre.getExposant().getSpecialite());
        expDto.setLocalisation(livre.getExposant().getLocalisation());

        //Then Set Exposant Dto in Livre Dto
        lDto.setExposant(expDto);
        return lDto;
    }

    public List<LivreDto> searchByKeyword(String keyword) {
        List<Livre> searchResult = livreRepo.findByTitreContains(keyword);
        return searchResult.stream().map(exp -> mapper.map(exp, LivreDto.class)).collect(Collectors.toList());
    }

}
