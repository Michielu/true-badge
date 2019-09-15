package com.truebadge.controller;

import javax.validation.Valid;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.truebadge.models.Photo;
import com.truebadge.repositories.PhotoRepository;

@RestController
@RequestMapping("/photos")
public class PhotoController {
	@Autowired
	private PhotoRepository repository;

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public Photo addPhoto(@Valid @RequestBody Photo photo) {
		photo.set_id(ObjectId.get());
		repository.save(photo);
		return photo;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Photo getPhotoById(@PathVariable("id") ObjectId id) {
		return repository.findBy_id(id);
	}

}