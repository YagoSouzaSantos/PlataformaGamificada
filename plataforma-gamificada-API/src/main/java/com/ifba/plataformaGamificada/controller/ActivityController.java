package com.ifba.plataformaGamificada.controller;

import com.ifba.plataformaGamificada.controller.dto.ActivityDTO;
import com.ifba.plataformaGamificada.controller.dto.PhaseDTO;
import com.ifba.plataformaGamificada.controller.form.PhaseForm;
import com.ifba.plataformaGamificada.controller.form.TopicoForm;
import com.ifba.plataformaGamificada.model.Activity;
import com.ifba.plataformaGamificada.model.Phase;
import com.ifba.plataformaGamificada.repository.IActivityRepository;
import com.ifba.plataformaGamificada.repository.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping(value = "/activity")
public class ActivityController {
    @Autowired
    private IActivityRepository activityRepository;
    private final String DIRECTORY_PATH = "C:/Platform";

    @GetMapping
    @Cacheable(value = "listaDeAtividades")
    public Page<ActivityDTO> lista(Pageable paginacao) {

        Page<Activity> activities = activityRepository.findAll(paginacao);

        return ActivityDTO.toConvert(activities);
    }

    @GetMapping("/phase/{phaseId}")
    @Cacheable(value = "listaDeAtividadesPorFase")
    public Page<ActivityDTO> listaPorFase(@PathVariable("phaseId") Long phase, Pageable pageable) {
        Page<Activity> activities = activityRepository.findByPhase(phase, pageable);
        return ActivityDTO.toConvert(activities);
    }


    @PostMapping(value = "/upload", consumes = "multipart/form-data")
    public ResponseEntity<ActivityDTO> handleFileUpload(
            @RequestParam("file") MultipartFile file,
            @RequestParam("phaseId") Long phaseId){
        try {
            String directory = DIRECTORY_PATH;

            File dir = new File(directory);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            String filename = file.getOriginalFilename();
            String filepath = directory + "/" + filename;
            File dest = new File(filepath);
            file.transferTo(dest);

            // Criar instância de Activity e definir os valores
            Activity activity = new Activity();
            activity.setTitle(filename);
            activity.setPhase(phaseId);

            // Salvar a instância de Activity usando JpaRepository (você precisa injetar o repositório adequado)
            activityRepository.save(activity);

            // Criar a resposta contendo os detalhes da atividade salva
            ActivityDTO response = new ActivityDTO();
            response.setId(activity.getId());
            response.setPhase(phaseId);
            response.setTitle(activity.getTitle());

            return ResponseEntity.ok(response);
        } catch (IOException e) {
            // Lidar com o erro de forma apropriada
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


    @GetMapping("/download/{filename:.+}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable String filename) {

        String directory = DIRECTORY_PATH;

        String filepath = directory + "/" + filename;
        Path path = Paths.get(filepath);

        if (!Files.exists(path)) {
            return ResponseEntity.notFound().build();
        }

        byte[] contents;
        try {
            contents = Files.readAllBytes(path);
        } catch (IOException e) {
            return ResponseEntity.status(500).build();
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", filename);

        return ResponseEntity.ok().headers(headers).body(contents);
    }
}