package com.cgv.cgvserver.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cgv.cgvserver.domain.theater.Theater;
import com.cgv.cgvserver.domain.theater.TheaterRepository;
import com.cgv.cgvserver.handler.exception.NotFoundTheaterException;
import com.cgv.cgvserver.web.dto.theater.TheaterSaveReqDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class TheaterService {
	private final TheaterRepository theaterRepository;
	private static final Logger log = LoggerFactory.getLogger(TheaterService.class);

	
	@Value("${file.path}")
	private String uploadFolder;
	
	@Transactional(readOnly = true)
	public List<Theater> 극장전체조회() {
		return theaterRepository.findAll();
	}
	
	@Transactional
	public void 극장등록(TheaterSaveReqDto theaterSaveReqDto) {
		UUID uuid = UUID.randomUUID();
		String imageFileName = uuid + "_" + theaterSaveReqDto.getTheaterImage().getOriginalFilename();
		
		Path imageFilePath = Paths.get(uploadFolder + imageFileName);
		
		try {
			Files.write(imageFilePath, theaterSaveReqDto.getTheaterImage().getBytes());
		} catch (Exception e) {
			log.warn("TheaterService : " + e.getStackTrace().toString());
		}
		
		String area = theaterSaveReqDto.getLocation().substring(0, 2);
		Theater theater = theaterSaveReqDto.toEntity(imageFileName);
		theater.setArea(area);
		theaterRepository.save(theater);
	}
	
	@Transactional(readOnly = true)
	public Path 극장이미지찾기(Long id) {
		Theater theaterEntity = theaterRepository.findById(id)
				.orElseThrow(() -> {throw new NotFoundTheaterException();});
		
		Path path = Paths.get(uploadFolder + theaterEntity.getTheaterImageUrl());
		return path;
	}
}
