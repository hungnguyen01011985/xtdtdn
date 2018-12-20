package vn.toancauxanh.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import vn.toancauxanh.model.DuAn;
import vn.toancauxanh.rest.model.DuAnModel;
import vn.toancauxanh.rest.model.PagingObject;
import vn.toancauxanh.rest.service.DuAnModelService;
import vn.toancauxanh.rest.service.KeyApiService;

@RestController
@RequestMapping(path = "/api/v1/duAns", produces = MediaType.APPLICATION_JSON_VALUE)
public class ApiDuAnController {

	@Autowired
	private DuAnModelService duAnModelService;
	
	@Autowired
	private KeyApiService keyApiService;
	
	@GetMapping
	public ResponseEntity<PagingObject<DuAnModel>> findAll(Pageable pageable,
			@RequestParam(required = false, defaultValue = "") String keyWord,
			@RequestHeader(value = "authentication", required = true) String authentication) {
		boolean checkQuyen = keyApiService.checkKey(authentication);
		if (checkQuyen) {
			return new ResponseEntity<>(duAnModelService.duAns(pageable, keyWord), HttpStatus.OK);
		}
		return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<DuAnModel> show(@PathVariable("id") Long id,
			@RequestHeader(value = "authentication", required = true) String authentication) {
		boolean checkQuyen = keyApiService.checkKey(authentication);
		if (checkQuyen) {
			DuAn duAn = duAnModelService.getById(id);
			if (duAn != null) {
				return new ResponseEntity<>(duAn.toDuAnModel(), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			}
		}
		return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
	}
	
}
