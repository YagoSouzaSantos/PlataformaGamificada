package com.ifba.plataformaGamificada.controller;

import com.ifba.plataformaGamificada.controller.form.PhaseForm;
import com.ifba.plataformaGamificada.controller.form.TopicoForm;
import com.ifba.plataformaGamificada.model.Phase;
import com.ifba.plataformaGamificada.repository.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
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

@RestController
@RequestMapping(value = "/activity")
public class ActivityController {

    private final String DIRECTORY_PATH = "C:/Platform";

    @PostMapping("/upload")
    public String handleFileUpload(
            @RequestParam("file") MultipartFile file) {
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

            return "Arquivo " + filename + " salvo com sucesso em " + directory;
        } catch (IOException e) {
            return "Erro ao salvar o arquivo: " + e.getMessage();

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