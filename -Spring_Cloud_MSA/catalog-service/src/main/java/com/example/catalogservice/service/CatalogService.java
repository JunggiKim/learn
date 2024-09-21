package com.example.catalogservice.service;

import java.util.List;

import com.example.catalogservice.jpa.CatalogEntity;

public interface CatalogService {
	List<CatalogEntity> getAllCatalogs();
}