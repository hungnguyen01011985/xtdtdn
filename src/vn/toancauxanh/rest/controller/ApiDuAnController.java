package vn.toancauxanh.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import vn.toancauxanh.model.DuAn;
import vn.toancauxanh.rest.model.DuAnModel;
import vn.toancauxanh.rest.model.PagingObject;
import vn.toancauxanh.rest.service.DuAnModelService;

@RestController
@RequestMapping(path = "/api/v1/duAns", produces = MediaType.APPLICATION_JSON_VALUE)
public class ApiDuAnController {

	@Autowired
	private DuAnModelService duAnModelService;
	
	@GetMapping
    public PagingObject<DuAnModel> findAll(Pageable pageable, @RequestParam(required = false, defaultValue = "") String keyWord) {
        return duAnModelService.duAns(pageable, keyWord);
    }

	@GetMapping("/{id}")
	public ResponseEntity<DuAnModel> show(@PathVariable("id") Long id) {
		DuAn duAn = duAnModelService.getById(id);
		if (duAn != null) {
			return new ResponseEntity<>(duAn.toDuAnModel(), HttpStatus.OK);
		}
		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}
	
}
