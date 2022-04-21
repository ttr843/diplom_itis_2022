package ru.itis.pashin.websiteservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.pashin.website.common.model.catalogs.dto.BankDTO;
import ru.itis.pashin.website.common.model.catalogs.dto.CompanySizeTypeDTO;
import ru.itis.pashin.website.common.model.catalogs.dto.IndustryDTO;
import ru.itis.pashin.website.common.model.catalogs.mapper.BankMapper;
import ru.itis.pashin.website.common.model.catalogs.mapper.CompanySizeTypeMapper;
import ru.itis.pashin.website.common.model.catalogs.mapper.IndustryMapper;
import ru.itis.pashin.website.common.service.repository.BankRepository;
import ru.itis.pashin.website.common.service.repository.CompanySizeTypeRepository;
import ru.itis.pashin.website.common.service.repository.IndustryRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author <a href="mailto:ruslan.pashin@waveaccess.ru">Ruslan Pashin</a>
 */
@Service
@RequiredArgsConstructor
public class CatalogsService {
    private final BankRepository bankRepository;
    private final BankMapper bankMapper;
    private final CompanySizeTypeRepository companySizeTypeRepository;
    private final CompanySizeTypeMapper companySizeTypeMapper;
    private final IndustryRepository industryRepository;
    private final IndustryMapper industryMapper;

    public List<BankDTO> findAllBanks() {
        return bankRepository.findAll().stream()
                .map(bankMapper::entityToDTO).collect(Collectors.toCollection(ArrayList::new));
    }

    public List<IndustryDTO> findAllIndustry() {
        return industryRepository.findAll().stream()
                .map(industryMapper::entityToDTO).collect(Collectors.toCollection(ArrayList::new));
    }

    public List<CompanySizeTypeDTO> findAllCompanySizeType() {
        return companySizeTypeRepository.findAll().stream()
                .map(companySizeTypeMapper::entityToDTO).collect(Collectors.toCollection(ArrayList::new));
    }


}
