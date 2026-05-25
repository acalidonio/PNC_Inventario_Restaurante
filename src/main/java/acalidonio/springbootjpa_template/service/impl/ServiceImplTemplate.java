package acalidonio.springbootjpa_template.service.impl;

import acalidonio.springbootjpa_template.common.mappers.MapperTemplate;
import acalidonio.springbootjpa_template.repository.RepositoryTemplate;
import acalidonio.springbootjpa_template.service.ServiceTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ServiceImplTemplate implements ServiceTemplate {
    private final RepositoryTemplate repository;
    private final MapperTemplate mapper;
}
