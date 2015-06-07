package br.inpe.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import br.inpe.model.Movie;
import br.inpe.repository.MovieRepository;

@Controller
@RequestMapping("/movie")
@Transactional
public class MovieController {

	@Autowired
	private MovieRepository movieRepository;

	@RequestMapping("/list")
	public void execute(Model model) {
		model.addAttribute("movies", movieRepository.findAll());
		System.out.println("Executando o movies");
	}

	@RequestMapping("/remove")
	public String remove(Movie movie) {
		movieRepository.remove(movie);
		return "forward:list";
	}
	
	@RequestMapping("/create")
	public String newMovie(Model model){
		return "movie/create";
	}

	@RequestMapping("/save")
	public String create(Movie movie, @RequestParam(value = "image", required = false) MultipartFile image, HttpServletRequest request) throws IOException {
		movie.setPoster(image.getBytes());
		movieRepository.save(movie);
		return "forward:list";
	}

	@RequestMapping("/image")
	@ResponseBody
	public byte[] image(Movie movie){
		movie = movieRepository.findById(movie.getId());
		System.out.println(Base64.encodeBase64String(movie.getPoster()));
		return movie.getPoster();		
	}
	
	private void saveImage(String filename, MultipartFile image) {
		try {
			String rootPath = System.getProperty("catalina.home");
			File file = new File(rootPath + File.separator + "ProjetoFinal" + File.separator + filename);

			FileUtils.writeByteArrayToFile(file, image.getBytes());
			System.out.println("Go to the location:  " + file.toString() + " on your computer and verify that the image has been stored.");
		} catch (IOException e) {
			throw new RuntimeException("Erro ao gravar imagem");
		}
	}

}
