package com.ifba.plataformaGamificada.services;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class Disc {

    private static String caminhoImagens = "C:\\Users\\Yago\\Documents\\ArquivosPlataforma\\usuarios";

    public void salvarFoto(MultipartFile foto) {
        this.salvar(this.caminhoImagens, foto);
    }

    public void salvar(String diretorio, MultipartFile arquivo) {
        Path diretorioPath = Paths.get(diretorio);
        Path arquivoPath = diretorioPath.resolve(arquivo.getOriginalFilename());

        try {
            Files.createDirectories(diretorioPath);
            arquivo.transferTo(arquivoPath.toFile());
        } catch (IOException e) {
            throw new RuntimeException("Nao foi possível salvar o arquivo");
        }
    }




}
