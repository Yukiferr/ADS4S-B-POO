package com.ab.controller;

import com.ab.model.Model;
import com.ab.repository.ModelRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/models")
public class ModelController {

    @Autowired
    private ModelRepository modelRepository;

    @GetMapping
    public List<Model> listarModels() {
        return modelRepository.findAll();
    }

    @GetMapping("/{id}")
    public Model buscarModelPorId(@PathVariable Long id) {
        return modelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Model não encontrado"));
    }

    @PostMapping
    public Model criarModel(@RequestBody Model model) {
        return modelRepository.save(model);
    }

    @PutMapping("/{id}")
    public Model atualizarModel(@PathVariable Long id, @RequestBody Model modelDetalhes) {
        Model model = modelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Model não encontrado"));

        model.setNome(modelDetalhes.getNome());
        model.setIdade(modelDetalhes.getIdade());

        return modelRepository.save(model);
    }

    @DeleteMapping("/{id}")
    public String deletarModel(@PathVariable Long id) {
        modelRepository.deleteById(id);
        return "Model deletado com sucesso";
    }
}